package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.constant.UserInfoConstant;
import com.scausw215.train.entity.DO.TrainInfoDO;
import com.scausw215.train.entity.DO.TrainTypeDO;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.entity.DTO.TrainInfoDTO;
import com.scausw215.train.entity.VO.UserTrainInfoListVO;
import com.scausw215.train.entity.request.TrainInfoSearchRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.mapper.StationInfoMapper;
import com.scausw215.train.mapper.TrainTypeMapper;
import com.scausw215.train.service.TicketInfoService;
import com.scausw215.train.service.TrainInfoService;
import com.scausw215.train.mapper.TrainInfoMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author sensnow
* @description 针对表【train_info(车次信息表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
@Slf4j
public class TrainInfoServiceImpl extends ServiceImpl<TrainInfoMapper, TrainInfoDO>
    implements TrainInfoService{

    @Resource
    private TrainInfoMapper trainInfoMapper;
    @Resource
    private TrainTypeMapper trainTypeMapper;
    @Resource
    private TicketInfoService ticketInfoService;

    @Resource
    private StationInfoMapper stationInfoMapper;

    @Resource(name = "trainNameList")
    private List<String> trainNameList;

    @Override
    public int insertTrainInfo(TrainInfoDO trainInfoDO) {
        // 校验数据
        checkTrainInfo(trainInfoDO);
        // 插入数据
        int insert = trainInfoMapper.insert(trainInfoDO);
        if(insert==0){
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "插入失败");
        }
        // 生成售票信息
        // TODO 生成售票信息
        ticketInfoService.addTicketInfo(trainInfoDO.getTrainId(), trainInfoDO.getTrainTypeId(),
                trainInfoDO.getStartStation(), trainInfoDO.getEndStation(), trainInfoDO.getStartTime(),
                trainInfoDO.getFirstPrice(), trainInfoDO.getSecondPrice(), trainInfoDO.getThirdPrice());
        return insert;
    }

    @Override
    public int deleteTrainInfo(Long trainId) {
        if (trainId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        int i = trainInfoMapper.deleteByTrainId(trainId);
        if(i==0){
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "删除失败");
        }
        // 删除售票信息(逻辑删除)
        // TODO 删除售票信息
        return i;
    }

    @Override
    public int updateTrainInfo(TrainInfoDO trainInfoDO) {
        // 校验数据
        checkTrainInfo(trainInfoDO);
        // 如果更新的是列车类型，那么列车的类型是一样，也就是name一样，但是id不一样
        // 先拿出来原来的列车类型
        TrainInfoDO oldTrainInfoDO = trainInfoMapper.selectDOByTrainId(trainInfoDO.getTrainId());
        // 判断是否更新了列车类型
        if(!trainTypeMapper.selectTrainTypeById(trainInfoDO.getTrainTypeId()).getTrainName()
                .equals(trainTypeMapper.selectTrainTypeById(oldTrainInfoDO.getTrainTypeId()).getTrainName())){
            // 类型不一样，更新了列车类型
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "不能更新列车类型");
        }
        int i = trainInfoMapper.updateByTrainId(trainInfoDO);
        if(i==0){
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "更新失败");
        }
        return i;
    }

    @Override
    public TrainInfoDTO getTrainInfoByTrainId(Long trainId) {
        if (trainId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        TrainInfoDTO trainInfoDTO = trainInfoMapper.selectByTrainId(trainId);
        if(trainInfoDTO==null){
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "查询失败");
        }
        // 获取剩下多少票
        // TODO 获取剩下多少票
        return trainInfoDTO;
    }

    @Override
    public UserTrainInfoListVO getTrainInfoByAnyCondition(TrainInfoSearchRequest trainInfoSearchRequest, HttpServletRequest request) {

        if(trainInfoSearchRequest.getMode()!=1 && trainInfoSearchRequest.getMode()!= 2){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        // 页数和每页的数量要大于0
        if(trainInfoSearchRequest.getPage()<=0 || trainInfoSearchRequest.getSize()<=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        UserInfoDO user = (UserInfoDO) request.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        if(trainInfoSearchRequest.getMode() == 1)
        {
            // 普通用户校验
            // 参数校验
            if(trainInfoSearchRequest.getTrainName()==null||trainInfoSearchRequest.getStartTime()==null||trainInfoSearchRequest.getStartStationId()==null || trainInfoSearchRequest.getEndStationId()==null){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
            }
            // 起始站不能和终点站相同
            if(trainInfoSearchRequest.getStartStationId().equals(trainInfoSearchRequest.getEndStationId())){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "起始站不能和终点站相同");
            }
            // 判断列车类型是否存在
            if(!trainNameList.contains(trainInfoSearchRequest.getTrainName())){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "列车类型不存在");
            }
            // 到达的站和到达的城市不能同时为空
            if(StringUtils.isBlank(trainInfoSearchRequest.getEndCity()) && trainInfoSearchRequest.getEndStationId()==null){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "到达的站和到达的城市不能同时为空");
            }
            // 出发的站和出发的城市不能同时为空
            if(StringUtils.isBlank(trainInfoSearchRequest.getStartCity()) && trainInfoSearchRequest.getStartStationId()==null){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "到达的站和到达的城市不能同时为空");
            }
            // 时间要不可以小于当前时间
            if(trainInfoSearchRequest.getStartTime().before(new Date())){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "时间不能小于当前时间");
            }
        }else
        {
            if (user.getUserAuthority() != 1)
            {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
            }
        }
        // 计算page和size
        trainInfoSearchRequest.setPage((trainInfoSearchRequest.getPage()-1)*trainInfoSearchRequest.getSize());
        UserTrainInfoListVO userTrainInfoListVO = new UserTrainInfoListVO(trainInfoMapper.selectTrainInfoListByAnyCondition(trainInfoSearchRequest), trainInfoMapper.selectTrainInfoCountByAnyCondition(trainInfoSearchRequest));
        // TODO 获取剩下多少票
        return userTrainInfoListVO;
    }

    @Override
    public List<TrainTypeDO> getUnscheduledTrainType(Date startTime, Date endTime) {
        if(startTime==null||endTime==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        // 开始时间大于现在
        if(startTime.compareTo(new Date())<0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "时间不能早于现在");
        }
        // 开始时间不能大于结束时间
        if(startTime.compareTo(endTime)>0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "开始时间不能大于结束时间");
        }
        return trainInfoMapper.selectUnscheduledTrainTypeByTime(startTime, endTime);
    }


    /**
     * 校验数据
     * @param trainInfoDO 车次信息
     */
    void checkTrainInfo(TrainInfoDO trainInfoDO)
    {
        if(trainInfoDO.getTrainTypeId()==null||trainInfoDO.getEndTime()==null||
                trainInfoDO.getEndStation()==null||trainInfoDO.getStartStation()==null||
                trainInfoDO.getFirstPrice()==null||trainInfoDO.getSecondPrice()==null||
                trainInfoDO.getThirdPrice()==null||trainInfoDO.getStartTime()==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        // 开始时间大于现在
        if(trainInfoDO.getStartTime().compareTo(new Date())<0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "时间不能早于现在");
        }
        // 开始时间不能大于结束时间
        if(trainInfoDO.getStartTime().compareTo(trainInfoDO.getEndTime())>0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "开始时间不能大于结束时间");
        }
        // 起始站不能和终点站相同
        if(trainInfoDO.getStartStation().equals(trainInfoDO.getEndStation())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "起始站不能和终点站相同");
        }
        // 列车类型不能为0 并且存在
        if(trainInfoDO.getTrainTypeId()==0|| trainTypeMapper.selectTrainTypeById(trainInfoDO.getTrainTypeId())==null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "列车类型不存在");
        }
        // 站台是否存在
        if(stationInfoMapper.selectStationById(trainInfoDO.getStartStation())==null||
                stationInfoMapper.selectStationById(trainInfoDO.getEndStation())==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "站台不存在");
        }
        // 时间不能和这个列车的其他时间重叠
        if(trainInfoMapper.selectTrainInfoByTime(trainInfoDO.getStartTime(),trainInfoDO.getEndTime(),trainInfoDO.getTrainTypeId())!=null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "时间不能和这个列车的其他时间重叠");
        }
        // 价格不能小于0
        if(trainInfoDO.getFirstPrice()<0||trainInfoDO.getSecondPrice()<0||trainInfoDO.getThirdPrice()<0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "价格不能小于0");
        }
    }


}





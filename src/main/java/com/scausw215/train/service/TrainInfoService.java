package com.scausw215.train.service;

import com.scausw215.train.entity.DO.TrainInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scausw215.train.entity.DO.TrainTypeDO;
import com.scausw215.train.entity.DTO.TrainInfoDTO;
import com.scausw215.train.entity.VO.UserTrainInfoListVO;
import com.scausw215.train.entity.request.TrainInfoSearchRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

/**
* @author sensnow
* @description 针对表【train_info(车次信息表)】的数据库操作Service
* @createDate 2023-03-25 14:10:16
*/
public interface TrainInfoService extends IService<TrainInfoDO> {

    /**
     * 插入一条车次信息
     * @param trainInfoDO 车次信息
     * @return 插入结果 1:成功 0:失败
     */
    int insertTrainInfo(TrainInfoDO trainInfoDO);

    /**
     * 删除一条车次信息
     * @param trainId 车次id
     * @return 删除结果 1:成功 0:失败
     */
    int deleteTrainInfo(Long trainId);

    /**
     * 更新一条车次信息
     * @param trainInfoDO 车次信息
     * @return 更新结果 1:成功 0:失败
     */
    int updateTrainInfo(TrainInfoDO trainInfoDO);

    /**
     * 获取一条车次信息
     * @param trainId 车次id
     * @return 车次信息
     */
    TrainInfoDTO getTrainInfoByTrainId(Long trainId);

    /**
     * 自适应查询车次信息
     * @param trainInfoSearchRequest 车次信息查询请求
     * @param request 请求
     * @return 车次信息列表
     */
    UserTrainInfoListVO getTrainInfoByAnyCondition(TrainInfoSearchRequest trainInfoSearchRequest, HttpServletRequest request);

    /**
     * 获取未排班的车次
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 未排班的车次列表
     */
    List<TrainTypeDO> getUnscheduledTrainType(Date startTime, Date endTime);


}

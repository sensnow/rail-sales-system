package com.scausw215.train.mapper;

import com.scausw215.train.entity.DO.TrainInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scausw215.train.entity.DO.TrainTypeDO;
import com.scausw215.train.entity.DTO.TrainInfoDTO;
import com.scausw215.train.entity.VO.PurchaseInfo;
import com.scausw215.train.entity.request.TrainInfoSearchRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
* @author sensnow
* @description 针对表【train_info(车次信息表)】的数据库操作Mapper
* @createDate 2023-03-25 14:10:16
* @Entity com.scausw215.train.entity.TrainInfo
*/
@Mapper
public interface TrainInfoMapper extends BaseMapper<TrainInfoDO> {

    /**
     * 插入一条车次信息
     * @param trainInfoDO 车次信息
     * @return 插入结果
     */
    int insertTrainInfo(TrainInfoDO trainInfoDO);

    /**
     * 根据时间和车次类型查询车次信息
     * @param  startTime 开始时间
     * @param endTime 结束时间
     * @param trainTypeId 车次类型
     * @return
     */
    TrainInfoDO selectTrainInfoByTime(Date startTime, Date endTime, Long trainTypeId);

    /**
     * 根据车次id删除车次信息 (逻辑删除)
     * @param trainId 车次id
     * @return 删除结果
     */
    int deleteByTrainId(Long trainId);

    /**
     * 根据车次id更新车次信息
     * @param trainInfoDO 车次信息
     * @return 更新结果
     */
    int updateByTrainId(TrainInfoDO trainInfoDO);

    /**
     * 根据车次id查询车次信息
     * @param trainId 车次id
     * @return 车次信息
     */
    TrainInfoDTO selectByTrainId(Long trainId);

    /**
     * 根据开始日，开始站，结束站查询车次信息
     *
     * @param infoSearchRequest 车次信息查询请求
     * @return 车次信息
     */
    List<TrainInfoDTO> selectTrainInfoListByAnyCondition(TrainInfoSearchRequest infoSearchRequest);

    /**
     * 根据车次id查询车次信息DO
     * @param trainId 车次id
     * @return 车次信息DO
     */
    TrainInfoDO selectDOByTrainId(Long trainId);

    /**
     * 信息列表的数量
     * @param infoSearchRequest 车次信息查询请求
     * @return 数量
     */
    int selectTrainInfoCountByAnyCondition(TrainInfoSearchRequest infoSearchRequest);


    /**
     * 根据时间查询未安排的车次类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 车次类型列表
     */
    List<TrainTypeDO> selectUnscheduledTrainTypeByTime(Date startTime, Date endTime);

    /**
     * 根据车次id查询座位信息
     * @param trainTypeId 座位id
     * @return purchaseInfo
     */
    PurchaseInfo selectTrainInfoDetailByTrainId(Long trainTypeId);
}





package com.scausw215.train.service;

import com.scausw215.train.entity.DO.TrainTypeDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scausw215.train.entity.request.TrainTypeRequest;

import java.util.List;

/**
* @author sensnow
* @description 针对表【train_type(动车类型)】的数据库操作Service
* @createDate 2023-03-25 14:10:16
*/
public interface TrainTypeService extends IService<TrainTypeDO> {

    /**
     * 添加动车类型
     * @param trainTypeRequest 动车类型请求类
     * @return 1：成功，0：失败
     */
    int addTrainType(TrainTypeRequest trainTypeRequest);

    /**
     * 删除动车类型
     * @param trainTypeId 动车类型id
     * @return 1：成功，0：失败
     */
    int deleteTrainType(Long trainTypeId);

    /**
     * 更新动车类型
     * @param trainTypeRequest 动车类型请求类
     * @return 1：成功，0：失败
     */
    int updateTrainType(TrainTypeRequest trainTypeRequest);

    /**
     * 获取动车类型
     * @param trainTypeId 动车类型id
     * @return  动车类型
     */
    TrainTypeDO getTrainType(Long trainTypeId);

    /**
     * 获取所有动车类型
     * @return 动车类型列表
     */
    List<TrainTypeDO> getListTrainType();

    /**
     * 检查动车代码是否存在
     * @param trainTypeCode 动车类型id
     * @return 1：存在，0：不存在
     */
    boolean isTrainTypeExist(String trainTypeCode);

    /**
     * 检查动车类型信息
     * @param trainTypeRequest 动车类型请求类
     * @return 动车DO类型
     */
    TrainTypeDO checkTrainTypeInfo(TrainTypeRequest trainTypeRequest);

}

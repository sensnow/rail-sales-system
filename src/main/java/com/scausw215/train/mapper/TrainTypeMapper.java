package com.scausw215.train.mapper;

import com.scausw215.train.entity.DO.TrainTypeDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
* @author sensnow
* @description 针对表【train_type(动车类型)】的数据库操作Mapper
* @createDate 2023-03-25 14:10:16
* @Entity com.scausw215.train.entity.TrainTypeDTO
*/
@Mapper
public interface TrainTypeMapper extends BaseMapper<TrainTypeDO> {
    
    /**
     * 增加动车类型
     * @param trainTypeDO 动车类型DO对象
     * @return 返回插入的行数
     */
    int insertTrainType(TrainTypeDO trainTypeDO);

    /**
     * 根据id删除动车类型
     * @param id 动车类型id
     * @return 返回删除的行数
     */
    int deleteTrainTypeById(Long id);

    /**
     * 更新动车类型
     * @param trainTypeDO 动车类型DO对象
     * @return 返回更新的行数
     */
    int updateTrainType(TrainTypeDO trainTypeDO);

    /**
     * 根据id查询动车类型
     * @param id 动车类型id
     * @return 返回查询到的动车类型DO对象
     */
    TrainTypeDO selectTrainTypeById(Long id);

    /**
     * 根据列车代码查询动车类型
     * @param trainCode 列车代码
     * @return 返回查询到的动车类型DO对象
     */
    TrainTypeDO selectTrainTypeByTrainCode(String trainCode);

    /**
     * 根据列车类型查询动车类型
     * @param trainName 列车名字
     * @return 返回查询到的动车类型DO对象
     */
    TrainTypeDO selectTrainTypeByType(String trainName);

    /**
     * 查询所有动车类型
     * @return 返回查询到的动车类型DO对象列表
     */
    List<TrainTypeDO> selectAllTrainType();


}





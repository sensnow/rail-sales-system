package com.scausw215.train.mapper;

import com.scausw215.train.entity.DO.TrainInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sensnow
* @description 针对表【train_info(车次信息表)】的数据库操作Mapper
* @createDate 2023-03-25 14:10:16
* @Entity com.scausw215.train.entity.TrainInfo
*/
@Mapper
public interface TrainInfoMapper extends BaseMapper<TrainInfoDO> {


}





package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.entity.DO.TrainTypeDO;
import com.scausw215.train.service.TrainTypeService;
import com.scausw215.train.mapper.TrainTypeMapper;
import org.springframework.stereotype.Service;

/**
* @author sensnow
* @description 针对表【train_type(动车类型)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class TrainTypeServiceImpl extends ServiceImpl<TrainTypeMapper, TrainTypeDO>
    implements TrainTypeService{

}





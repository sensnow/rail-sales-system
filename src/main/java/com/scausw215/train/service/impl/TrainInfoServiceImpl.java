package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.entity.TrainInfo;
import com.scausw215.train.service.TrainInfoService;
import com.scausw215.train.mapper.TrainInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author sensnow
* @description 针对表【train_info(车次信息表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class TrainInfoServiceImpl extends ServiceImpl<TrainInfoMapper, TrainInfo>
    implements TrainInfoService{

}





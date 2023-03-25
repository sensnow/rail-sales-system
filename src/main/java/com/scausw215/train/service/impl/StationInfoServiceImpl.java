package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.entity.StationInfo;
import com.scausw215.train.service.StationInfoService;
import com.scausw215.train.mapper.StationInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author sensnow
* @description 针对表【station_info】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class StationInfoServiceImpl extends ServiceImpl<StationInfoMapper, StationInfo>
    implements StationInfoService {

}





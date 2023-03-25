package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.entity.Passenger;
import com.scausw215.train.service.PassengerService;
import com.scausw215.train.mapper.PassengerMapper;
import org.springframework.stereotype.Service;

/**
* @author sensnow
* @description 针对表【passenger(购票人信息表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger>
    implements PassengerService {

}





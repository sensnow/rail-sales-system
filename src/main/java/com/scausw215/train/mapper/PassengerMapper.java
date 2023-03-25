package com.scausw215.train.mapper;

import com.scausw215.train.entity.Passenger;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author sensnow
* @description 针对表【passenger(购票人信息表)】的数据库操作Mapper
* @createDate 2023-03-25 14:10:16
* @Entity com.scausw215.train.entity.Passenger
*/
public interface PassengerMapper extends BaseMapper<Passenger> {
    //增加
    int addPassenger(Passenger passenger);
    //删除
    int deletePassengerById(String id);
    //修改
    int updatePassenger(Passenger passenger);
    //查询
    Passenger getPassengerById(String id);

}





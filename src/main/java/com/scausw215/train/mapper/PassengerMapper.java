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
    /**
     * 增加购票人信息
     * @param passenger 购票人信息
     * @return 返回插入的行数
     */
    int insertPassenger(Passenger passenger);

    /**
     * 根据id删除购票人信息
     * @param id 购票人id
     * @return 返回删除的行数
     */
    int deletePassengerById(Integer id);

    /**
     * 更新购票人信息
     * @param passenger 购票人信息
     * @return 返回更新的行数
     */
    int updatePassenger(Passenger passenger);

    /**
     * 根据id查询购票人信息
     * @param id 购票人id
     * @return 返回购票人信息
     */
    Passenger selectPassengerById(Integer id);



}





package com.scausw215.train.service;

import com.scausw215.train.entity.Passenger;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author sensnow
* @description 针对表【passenger(购票人信息表)】的数据库操作Service
* @createDate 2023-03-25 14:10:16
*/
public interface PassengerService extends IService<Passenger> {

    /**
     * 新增购票人信息
     * @param passenger 购票人信息
     * @return boolean
     */
    boolean addPassenger(Passenger passenger);

    /**
     * 根据id删除购票人信息
     * @param id 购票人id
     * @return boolean
     */
    boolean deletePassengerById(Integer id);

    /**
     * 更新购票人信息
     * @param passenger 购票人信息
     * @return boolean
     */
    boolean updatePassenger(Passenger passenger);

    /**
     * 根据id查询购票人信息
     * @param id 购票人id
     * @return Passenger
     */
    Passenger getPassengerById(Integer id);


}

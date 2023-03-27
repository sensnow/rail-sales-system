package com.scausw215.train.service;

import com.scausw215.train.entity.DO.PassengerDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scausw215.train.entity.request.PassengerRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
* @author sensnow
* @description 针对表【passenger(购票人信息表)】的数据库操作Service
* @createDate 2023-03-25 14:10:16
*/
public interface PassengerService extends IService<PassengerDO> {

    /**
     * 添加购票人
     * @param passengerRequest 购票人信息
     * @param httpServletRequest http请求
     * @return 添加结果
     */
    int addPassenger(PassengerRequest passengerRequest, HttpServletRequest httpServletRequest);

    /**
     * 删除购票人
     * @param passengerId 购票人id
     * @param httpServletRequest http请求
     * @return 删除结果
     */
    int deletePassenger(Long passengerId, HttpServletRequest httpServletRequest);

    /**
     * 修改购票人
     *
     * @param passengerRequest
     * @param httpServletRequest http请求
     * @return 修改结果
     */
    int updatePassenger(PassengerRequest passengerRequest, HttpServletRequest httpServletRequest);

    /**
     * 查询购票人
     * @param passengerId 购票人id
     * @param httpServletRequest http请求
     * @return 查询结果
     */
    PassengerDO getPassengerByPassengerId(Long passengerId, HttpServletRequest httpServletRequest);


    /**
     * 查询购票人
     * @param userId 用户id
     * @param httpServletRequest http请求
     * @return 查询结果
     */
    List<PassengerDO> getPassengersByUserId(Long userId, HttpServletRequest httpServletRequest);
}

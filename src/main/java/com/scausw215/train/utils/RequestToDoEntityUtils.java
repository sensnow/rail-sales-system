package com.scausw215.train.utils;

import com.scausw215.train.entity.DO.PassengerDO;
import com.scausw215.train.entity.request.PassengerRequest;

/**
 * 请求转换工具类
 * @author sensnow
 */
public class RequestToDoEntityUtils {

    /**
     *  乘客请求类转换为乘客DO类
     */
    public static PassengerDO toPassengerDO(PassengerRequest passengerRequest) {
        PassengerDO passengerDO = new PassengerDO();
        passengerDO.setPassengerName(passengerRequest.getName());
        passengerDO.setPassengerCardNumber(passengerRequest.getNumber());
        passengerDO.setPassengerPhone(passengerRequest.getPhone());
        passengerDO.setPassengerType(passengerRequest.getType());
        passengerDO.setUserId(passengerRequest.getUserId());
        return passengerDO;
    }
}

package com.scausw215.train.utils;

import com.scausw215.train.entity.DO.PassengerDO;
import com.scausw215.train.entity.DO.StationInfoDO;
import com.scausw215.train.entity.request.PassengerRequest;
import com.scausw215.train.entity.request.StationRequest;

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
        passengerDO.setPassengerId(passengerRequest.getId());
        passengerDO.setPassengerName(passengerRequest.getName());
        passengerDO.setPassengerCardNumber(passengerRequest.getNumber());
        passengerDO.setPassengerPhone(passengerRequest.getPhone());
        passengerDO.setPassengerType(passengerRequest.getType());
        passengerDO.setUserId(passengerRequest.getUserId());
        return passengerDO;
    }
    public static StationInfoDO toStationInfoDo(StationRequest stationRequest) {
        StationInfoDO stationInfoDO = new StationInfoDO();
        stationInfoDO.setStationName(stationRequest.getName());
        stationInfoDO.setStationId(stationRequest.getId());
        stationInfoDO.setStationCity(stationRequest.getCity());
        stationInfoDO.setStationProvince(stationRequest.getProvince());
        return stationInfoDO;
    }
}

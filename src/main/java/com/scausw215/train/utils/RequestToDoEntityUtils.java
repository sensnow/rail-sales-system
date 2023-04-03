package com.scausw215.train.utils;

import com.scausw215.train.entity.DO.PassengerDO;
import com.scausw215.train.entity.DO.SeatTypeDO;
import com.scausw215.train.entity.DO.StationInfoDO;
import com.scausw215.train.entity.DO.TrainTypeDO;
import com.scausw215.train.entity.request.PassengerRequest;
import com.scausw215.train.entity.request.SeatTypeRequest;
import com.scausw215.train.entity.request.StationRequest;
import com.scausw215.train.entity.request.TrainTypeRequest;

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

    public static SeatTypeDO toSeatTypeDO(SeatTypeRequest seatTypeRequest)
    {
        SeatTypeDO seatTypeDO = new SeatTypeDO();
        seatTypeDO.setSeatId(seatTypeRequest.getId());
        seatTypeDO.setSeatName(seatTypeRequest.getName());
        seatTypeDO.setSeatDescription(seatTypeRequest.getDescription());
        return seatTypeDO;
    }

    public static TrainTypeDO toTrainTypeDO(TrainTypeRequest trainTypeRequest)
    {
        TrainTypeDO trainTypeDO = new TrainTypeDO();
        trainTypeDO.setTrainTypeId(trainTypeRequest.getId());
        trainTypeDO.setTrainName(trainTypeRequest.getName());
        trainTypeDO.setTrainCode(trainTypeRequest.getCode());
        trainTypeDO.setTrainDescription(trainTypeRequest.getDescription());
        trainTypeDO.setFirstSeatTypeId(trainTypeRequest.getFirstSeatTypeId());
        trainTypeDO.setFirstSeatNum(trainTypeRequest.getFirstSeatNum());
        trainTypeDO.setSecondSeatTypeId(trainTypeRequest.getSecondSeatTypeId());
        trainTypeDO.setSecondSeatNum(trainTypeRequest.getSecondSeatNum());
        trainTypeDO.setThirdSeatTypeId(trainTypeRequest.getThirdSeatTypeId());
        trainTypeDO.setThirdSeatNum(trainTypeRequest.getThirdSeatNum());
        return trainTypeDO;
    }
}

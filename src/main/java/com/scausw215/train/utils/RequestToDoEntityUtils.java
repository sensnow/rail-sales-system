package com.scausw215.train.utils;

import com.scausw215.train.entity.DO.*;
import com.scausw215.train.entity.request.*;

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
    public static TicketInfoDO toTicketInfoDO(TicketRequest ticketRequest){
        TicketInfoDO ticketInfoDO = new TicketInfoDO();
        ticketInfoDO.setTicketId(ticketRequest.getTicketId());
        ticketInfoDO.setTrainId(ticketRequest.getTrainId());
        ticketInfoDO.setCarNumber(ticketRequest.getCarNumber());
        ticketInfoDO.setSeatTypeId(ticketRequest.getSeatTypeId());
        ticketInfoDO.setSeatNumber(ticketRequest.getSeatNumber());
        ticketInfoDO.setIsSold(ticketRequest.getIsSold());
        ticketInfoDO.setTicketPrice(ticketRequest.getTicketPrice());
        ticketInfoDO.setStartSaleTime(ticketRequest.getStartSaleTime());
        ticketInfoDO.setEndSaleTime(ticketRequest.getEndSaleTime());
        ticketInfoDO.setUpdateTime(ticketRequest.getUpdateTime());
        ticketInfoDO.setIsAvailable(ticketRequest.getIsAvailable());
        return ticketInfoDO;
    }
}

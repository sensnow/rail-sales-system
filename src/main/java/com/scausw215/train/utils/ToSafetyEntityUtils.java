package com.scausw215.train.utils;

import com.scausw215.train.entity.DO.*;
import com.scausw215.train.entity.VO.*;

/**
 * @description 用于将entity转换为VO类
 * @author sensnow
 */
public class ToSafetyEntityUtils {
    public static UserInfoVO toUserInfoVO(UserInfoDO userInfoDO) {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setId(userInfoDO.getUserId());
        userInfoVO.setName(userInfoDO.getUserName());
        userInfoVO.setAccount(userInfoDO.getUserAccount());
        userInfoVO.setAuthority(userInfoDO.getUserAuthority());
        return userInfoVO;
    }

    public static PassengerVO toPassengerVO(PassengerDO passengerDO)
    {
        PassengerVO passengerVO = new PassengerVO();
        passengerVO.setId(passengerDO.getPassengerId());
        passengerVO.setName(passengerDO.getPassengerName());
        passengerVO.setNumber(passengerDO.getPassengerCardNumber());
        passengerVO.setPhone(passengerDO.getPassengerPhone());
        passengerVO.setType(passengerDO.getPassengerType());
        passengerVO.setUserId(passengerDO.getUserId());
        return passengerVO;

    }
    public static StationVO toStationVO(StationInfoDO stationInfoDO){
        StationVO stationVO = new StationVO();
        stationVO.setId(stationInfoDO.getStationId());
        stationVO.setName(stationInfoDO.getStationName());
        stationVO.setCity(stationInfoDO.getStationCity());
        stationVO.setProvince(stationInfoDO.getStationProvince());
        return stationVO;
    }
    public static TicketVO toTicketVO(TicketInfoDO ticketInfoDO){
        TicketVO ticketVO = new TicketVO();
        ticketVO.setId(ticketInfoDO.getTicketId());
        ticketVO.setTrainId(ticketInfoDO.getTrainId());
        ticketVO.setCarNumber(ticketInfoDO.getCarNumber());
        ticketVO.setSeatTypeId(ticketInfoDO.getSeatTypeId());
        ticketVO.setSeatNumber(ticketInfoDO.getSeatNumber());
        ticketVO.setIsSold(ticketInfoDO.getIsSold());
        ticketVO.setTicketPrice(ticketInfoDO.getTicketPrice());
        ticketVO.setStartSaleTime(ticketInfoDO.getStartSaleTime());
        ticketVO.setEndSaleTime(ticketInfoDO.getEndSaleTime());
        ticketVO.setUpdateTime(ticketInfoDO.getUpdateTime());
        return ticketVO;
    }

    public static SeatTypeVO toSeatTypeVO(SeatTypeDO seatTypeDO)
    {
        SeatTypeVO seatTypeVO = new SeatTypeVO();
        seatTypeVO.setId(seatTypeDO.getSeatId());
        seatTypeVO.setName(seatTypeDO.getSeatName());
        seatTypeVO.setDescription(seatTypeDO.getSeatDescription());
        return seatTypeVO;
    }

    public static TrainTypeVO toTrainTypeVO(TrainTypeDO trainTypeDO)
    {
        TrainTypeVO trainTypeVO = new TrainTypeVO();
        trainTypeVO.setId(trainTypeDO.getTrainTypeId());
        trainTypeVO.setName(trainTypeDO.getTrainName());
        trainTypeVO.setCode(trainTypeDO.getTrainCode());
        trainTypeVO.setDescription(trainTypeDO.getTrainDescription());
        trainTypeVO.setFirstSeatTypeId(trainTypeDO.getFirstSeatTypeId());
        trainTypeVO.setFirstSeatNum(trainTypeDO.getFirstSeatNum());
        trainTypeVO.setSecondSeatTypeId(trainTypeDO.getSecondSeatTypeId());
        trainTypeVO.setSecondSeatNum(trainTypeDO.getSecondSeatNum());
        trainTypeVO.setThirdSeatTypeId(trainTypeDO.getThirdSeatTypeId());
        trainTypeVO.setThirdSeatNum(trainTypeDO.getThirdSeatNum());
        return trainTypeVO;
    }
    public static TicketRefundedVO toTicketRefundedVO(TicketRefundedDO ticketRefundedDO){
        TicketRefundedVO ticketRefundedVO = new TicketRefundedVO();
        ticketRefundedVO.setTicketId(ticketRefundedDO.getTicketId());
        ticketRefundedVO.setRefundedId(ticketRefundedDO.getRefundedId());
        ticketRefundedVO.setUserId(ticketRefundedDO.getUserId());
        ticketRefundedVO.setPassengerId(ticketRefundedDO.getPassengerId());
        ticketRefundedVO.setRefundedPrice(ticketRefundedDO.getRefundedPrice());
        ticketRefundedVO.setRefundedReason(ticketRefundedDO.getRefundedReason());
        ticketRefundedVO.setRefundedTime(ticketRefundedDO.getRefundedTime());
        return ticketRefundedVO;
    }
    public static TicketSaleVO toTicketSaleVO(TicketSaleDO ticketSaleDO){
        TicketSaleVO ticketSaleVO = new TicketSaleVO();
        ticketSaleVO.setSaleId(ticketSaleDO.getSaleId());
        ticketSaleVO.setUserId(ticketSaleDO.getUserId());
        ticketSaleVO.setPassengerId(ticketSaleDO.getPassengerId());
        ticketSaleVO.setTicketId(ticketSaleDO.getTicketId());
        ticketSaleVO.setPurchasePrice(ticketSaleDO.getPurchasePrice());
        ticketSaleVO.setPurchaseTime(ticketSaleDO.getPurchaseTime());
        ticketSaleVO.setIsRefunded(ticketSaleDO.getIsRefunded());
        return ticketSaleVO;
    }


}

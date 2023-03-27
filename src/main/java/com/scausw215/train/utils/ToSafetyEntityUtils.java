package com.scausw215.train.utils;

import com.scausw215.train.entity.DO.PassengerDO;
import com.scausw215.train.entity.DO.StationInfoDO;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.entity.VO.PassengerVO;
import com.scausw215.train.entity.VO.StationVO;
import com.scausw215.train.entity.VO.UserInfoVO;

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
        passengerVO.setNumber(passengerDO.getPassengerName());
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


}

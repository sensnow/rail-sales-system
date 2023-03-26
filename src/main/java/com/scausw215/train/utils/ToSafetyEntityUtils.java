package com.scausw215.train.utils;

import com.scausw215.train.entity.DO.PassengerDO;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.entity.VO.UserInfoVO;

/**
 * @description 用于将entity转换为VO类
 * @author sensnow
 */
public class ToSafetyEntityUtils {
    public static UserInfoVO toUserInfoVO(UserInfoDO userInfoDO) {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserId(userInfoDO.getUserId());
        userInfoVO.setUserName(userInfoDO.getUserName());
        userInfoVO.setUserAccount(userInfoDO.getUserAccount());
        userInfoVO.setUserAuthority(userInfoDO.getUserAuthority());
        return userInfoVO;
    }

}

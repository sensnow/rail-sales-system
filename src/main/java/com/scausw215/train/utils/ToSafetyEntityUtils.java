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
        userInfoVO.setId(userInfoDO.getUserId());
        userInfoVO.setName(userInfoDO.getUserName());
        userInfoVO.setAccount(userInfoDO.getUserAccount());
        userInfoVO.setAuthority(userInfoDO.getUserAuthority());
        return userInfoVO;
    }

}

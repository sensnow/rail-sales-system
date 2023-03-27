package com.scausw215.train.utils;

import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.constant.UserInfoConstant;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 检查工具类
 * @author sensnow
 */
@Slf4j
public class CheckUtils {
    /**
     * 检查用户登录状态是否合法
     * @param userId 用户id
     * @param httpServletRequest http请求
     */
    public static void isLoginStatusLegal(Long userId, HttpServletRequest httpServletRequest)
    {
        UserInfoDO userInfoDO = (UserInfoDO) httpServletRequest.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        if (!userInfoDO.getUserId().equals(userId)) {
            log.error("获取购票人列表失败，用户ID不一致，参数：{}", userId);
            throw new BusinessException(ErrorCode.ILLEGAL_STATUS, "用户ID不一致");
        }
    }
}

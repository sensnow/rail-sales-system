package com.scausw215.train.interceptor;

import com.scausw215.train.constant.UserInfoConstant;
import com.scausw215.train.entity.DO.PassengerDO;
import com.scausw215.train.entity.DO.UserInfoDO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @description 管理员拦截器
 * @author sensnow
 */
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取用户信息
        UserInfoDO userInfoDO = (UserInfoDO) request.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        // 判断用户是否为管理员
        return userInfoDO.getUserAuthority() == 1;
    }
}

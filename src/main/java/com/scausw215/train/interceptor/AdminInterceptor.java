package com.scausw215.train.interceptor;

import com.alibaba.fastjson.JSON;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.constant.UserInfoConstant;
import com.scausw215.train.entity.DO.PassengerDO;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.utils.ResultUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

/**
 * @description 管理员拦截器
 * @author sensnow
 */
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取用户信息
        UserInfoDO userInfoDO = (UserInfoDO) request.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        // 判断用户是否为管理员
        if(userInfoDO.getUserAuthority() != 1) {
            log.error("AdminInterceptor.preHandle: {}不是管理员", userInfoDO);
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(ResultUtils.error(ErrorCode.NOT_LOGIN, "未登录")));
            writer.flush();
            writer.close();
            return false;
        }
        log.info("AdminInterceptor.preHandle: {}是管理员 ", userInfoDO);
        return true;
    }
}

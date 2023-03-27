package com.scausw215.train.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.constant.UserInfoConstant;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.utils.ResultUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

/**
 * 登录拦截器
 * @author sensnow
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 判定用户是否登录
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String loginUrl = "/user/login";
        String registerUrl = "/user/register";
        // 登录和注册不需要拦截
        if (request.getRequestURI().equals(loginUrl) || request.getRequestURI().equals(registerUrl)) {
            return true;
        }
        // 从session中获取用户信息
        UserInfoDO userInfoDO = (UserInfoDO) request.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        if (userInfoDO == null) {
            // 返回状态码
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(ResultUtils.error(ErrorCode.NO_PERMISSION, "没有权限")));
            writer.flush();
            writer.close();
            return false;
        }
        return true;
    }
}

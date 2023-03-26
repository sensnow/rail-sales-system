package com.scausw215.train.controller;

import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.entity.VO.UserInfoVO;
import com.scausw215.train.entity.request.UserLoginRequest;
import com.scausw215.train.entity.request.UserRegisterRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.UserInfoService;
import com.scausw215.train.utils.ResultUtils;
import com.scausw215.train.utils.ToSafetyEntityUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * 用户信息Controller
 * @author sensnow
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<UserInfoVO> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest) {
        // 请求体校验
        if (httpServletRequest == null) {
            log.error("UserInfoController.login: 请求体为空");
            throw new BusinessException(ErrorCode.EMPTY_REQUEST,"请求体为空");
        }
        // 参数校验
        if (StringUtils.isAnyBlank(userLoginRequest.getAccount(), userLoginRequest.getPassword())) {
            log.error("UserInfoController.login: 账号或者密码为空,{}", userLoginRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号或者密码为空");
        }
        UserInfoDO userInfoDO = userInfoService.login(userLoginRequest, httpServletRequest);
        UserInfoVO userInfoVO = ToSafetyEntityUtils.toUserInfoVO(userInfoDO);
        return ResultUtils.success(userInfoVO);

    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<Long> register(@RequestBody UserRegisterRequest userRegisterRequest, HttpServletRequest httpServletRequest) {
        // 请求体校验
        if (httpServletRequest == null) {
            log.error("UserInfoController.register: 请求体为空");
            throw new BusinessException(ErrorCode.EMPTY_REQUEST,"请求体为空");
        }
        // 参数校验
        if(StringUtils.isAnyBlank(userRegisterRequest.getAccount(),userRegisterRequest.getPassword(),userRegisterRequest.getCheckPassword())){
            log.error("UserInfoController.register: 用户注册失败，用户名或密码不能为空，用户名：{}",userRegisterRequest.getAccount());
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名或密码不能为空");
        }
        long register = userInfoService.register(userRegisterRequest);
        return ResultUtils.success(register);
    }

    @PostMapping("/logout")
    public Result<Integer> logout(HttpServletRequest httpServletRequest) {
        // 请求体校验
        if (httpServletRequest == null) {
            log.error("UserInfoController.logout: 请求体为空");
            throw new BusinessException(ErrorCode.EMPTY_REQUEST,"请求体为空");
        }
        int logout = userInfoService.logout(httpServletRequest);
        return ResultUtils.success(logout);
    }
}

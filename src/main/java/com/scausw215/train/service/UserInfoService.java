package com.scausw215.train.service;

import com.scausw215.train.common.Result;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scausw215.train.entity.request.UserLoginRequest;
import com.scausw215.train.entity.request.UserRegisterRequest;

/**
* @author sensnow
* @description 针对表【user_info(用户信息表)】的数据库操作Service
* @createDate 2023-03-25 14:10:16
*/
public interface UserInfoService extends IService<UserInfoDO> {


    /**
     * 用户注册
     * @param userRegisterRequest 用户注册请求
     * @return 注册结果
     */
    Result<String> register(UserRegisterRequest userRegisterRequest);

    /**
     * 用户登录
     * @param userLoginRequest 用户登录请求
     * @return 登录结果
     */
    Result<String> login(UserLoginRequest userLoginRequest);

}

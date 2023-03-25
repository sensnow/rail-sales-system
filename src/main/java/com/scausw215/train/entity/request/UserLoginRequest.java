package com.scausw215.train.entity.request;

import lombok.Data;

/**
 * 用户登录请求类
 * @author sensnow
 */
@Data
public class UserLoginRequest {
    private String account;
    private String password;
}

package com.scausw215.train.entity.request;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户注册请求类
 * @author sensnow
 */
@Data
@AllArgsConstructor
public class UserRegisterRequest {
    private String account;
    private String password;
    private String checkPassword;
}

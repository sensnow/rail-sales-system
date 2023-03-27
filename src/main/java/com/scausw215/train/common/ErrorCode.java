package com.scausw215.train.common;

/**
 * 错误码
 * @author sensnow
 */
public enum ErrorCode {

    
    /**
     * 成功
     */
    SUCCESS(0, "success", "成功"),
    /**
     * 请求参数错误
     */
    PARAMS_ERROR(40000 ,"请求参数错误", ""),
    /**
     * 用户名或密码错误
     */
    NOT_FOUND(40001, "密码错误", "密码错误"),
    /**
     * 未登录
     */
    NOT_LOGIN(40002, "未登录", "未登录"),
    /**
     * 用户名已存在
     */
    USER_EXIST(40003, "用户名已存在", "用户名已存在"),
    /**
     *
     * 系统错误
     */
    SYSTEM_ERROR(50000, "系统错误", "系统错误"),
    /**
     * 请求体为空
     */
    EMPTY_REQUEST(500001, "请求体为空", "请求体为空"),

    EXIST_PASSENGER(40010, "该购票人已存在", "该购票人已存在");


    /**
     * 错误码
     */
    private final int code;
    /**
     * 错误信息
     */
    private final String message;
    /**
     * 错误描述
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }


}

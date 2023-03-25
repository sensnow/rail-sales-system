package com.scausw215.train.common;

import lombok.Data;

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
    PARAMS_ERROR(40000, "请求参数错误", ""),
    /**
     * 系统错误
     */
    SYSTEM_ERROR(50000, "系统错误", "系统错误");

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

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
    ENTITY_EXIST(40003, "该实例已存在", "该实例已存在"),
    /**
     * 该实例不存在
     */
    ENTITY_NOT_EXIST(40004, "该实例不存在", "该实例不存在"),
    /**
     *
     * 系统错误
     */
    SYSTEM_ERROR(50000, "系统错误", "系统错误"),
    /**
     * 请求体为空
     */
    EMPTY_REQUEST(50001, "请求体为空", "请求体为空"),
    /**
     * 没有权限访问
     */
    NO_PERMISSION(50002, "没有权限", "没有权限"),
    /**
     * 数据库操作失败
     */
    DATABASE_ERROR(50003, "数据库操作失败", "数据库操作失败"),
    /**
     * 非法状态
     */
    ILLEGAL_STATUS(50004, "非法状态", "非法状态");



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

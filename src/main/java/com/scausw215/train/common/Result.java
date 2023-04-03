package com.scausw215.train.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 返回结果
 * @author sensnow
 */
@Data
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回描述
     */
    private String description;

    public Result(int code, T data,String message ,String description) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.description = description;
    }

    public Result(int code,String message, String description) {
        this(code, null,message, description);
    }



}

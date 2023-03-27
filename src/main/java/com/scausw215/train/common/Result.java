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
    private int code;
    private T data;
    private String message;

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

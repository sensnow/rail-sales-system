package com.scausw215.train.exception;

import com.scausw215.train.common.ErrorCode;

import java.io.Serial;

/**
 * 自定义异常类
 * @author sensnow
 */
public class BusinessException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -4926126368570947722L;

    private final int code;

    private final String description;

    public BusinessException(ErrorCode errorCode)
    {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode,String message,String description)
    {
        super(message);
        this.code = errorCode.getCode();
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode,String description)
    {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}

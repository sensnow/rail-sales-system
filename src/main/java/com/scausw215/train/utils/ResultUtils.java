package com.scausw215.train.utils;

import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;

/**
 * 返回结果工具类
 * @author sensnow
 */
public class ResultUtils {
    public static <T> Result<T> success(T data)
    {
        return new Result<>(0,data,"ok","");
    }

    public static Result<String> error(int code, String message, String description)
    {
        return new Result<String>(code,message,description);
    }

    public static Result<String> error(ErrorCode errorCode, String description)
    {
        return new Result<String>(errorCode.getCode(),errorCode.getMessage(),description);
    }


}

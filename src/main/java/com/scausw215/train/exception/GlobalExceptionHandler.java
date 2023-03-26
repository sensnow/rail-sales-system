package com.scausw215.train.exception;

import com.scausw215.train.common.Result;
import com.scausw215.train.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理类
 * @author sensnow
 */
@RestController
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public Result<String> businessExceptionHandler(BusinessException e)
    {
        log.error("BusinessException",e);
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }
}

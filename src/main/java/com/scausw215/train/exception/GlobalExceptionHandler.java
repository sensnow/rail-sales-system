package com.scausw215.train.exception;

import com.scausw215.train.common.Result;
import com.scausw215.train.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 * @author sensnow
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<String> businessExceptionHandler(BusinessException e)
    {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }

}

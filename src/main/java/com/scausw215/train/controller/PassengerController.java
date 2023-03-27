package com.scausw215.train.controller;


import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.entity.DO.PassengerDO;
import com.scausw215.train.entity.request.PassengerRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.PassengerService;
import com.scausw215.train.utils.ResultUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 乘客信息Controller
 * @author sensnow
 */
@RestController
@RequestMapping("/passenger")
@Slf4j
public class PassengerController {

    @Resource
    PassengerService passengerService;

    /**
     * 添加乘客
     */
    @PostMapping("/add")
    public Result<Integer> addPassenger(@RequestBody PassengerRequest passengerRequest, HttpServletRequest httpServletRequest){
        // 请求体校验
        if (httpServletRequest == null) {
            log.error("PassengerController.addPassenger: 请求体为空");
            throw new BusinessException(ErrorCode.EMPTY_REQUEST,"请求体为空");
        }
        // 参数校验
        if (passengerRequest == null) {
            log.error("PassengerController.addPassenger: 乘客信息为空");
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"乘客信息为空");
        }
        int passengerId = passengerService.addPassenger(passengerRequest, httpServletRequest);
        return ResultUtils.success(passengerId);
    }
}

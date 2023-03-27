package com.scausw215.train.controller;


import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.entity.DO.PassengerDO;
import com.scausw215.train.entity.VO.PassengerVO;
import com.scausw215.train.entity.request.PassengerRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.PassengerService;
import com.scausw215.train.utils.ResultUtils;
import com.scausw215.train.utils.ToSafetyEntityUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 通过用户id获取所有乘客信息
     */
    @GetMapping("")
    public Result<List<PassengerVO>> getPassengerByUserId(@RequestParam("id") Long id,HttpServletRequest httpServletRequest){
        // 请求体校验
        if (httpServletRequest == null) {
            log.error("PassengerController.getPassengerByUserId: 请求体为空");
            throw new BusinessException(ErrorCode.EMPTY_REQUEST,"请求体为空");
        }
        // 参数校验
        if (id == null) {
            log.error("PassengerController.getPassengerByUserId: 用户id为空");
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户id为空");
        }
        // 获取乘客信息
        List<PassengerDO> passengerDOList = passengerService.getPassengersByUserId(id,httpServletRequest);
        // 转换为VO
        List<PassengerVO> passengerVO = passengerDOList.stream().map(ToSafetyEntityUtils::toPassengerVO).toList();
        return ResultUtils.success(passengerVO);
    }


    /**
     * 通过乘客id获取乘客信息
     */
    @GetMapping("/{id}")
    public Result<PassengerVO> getPassengerById(@PathVariable Long id, HttpServletRequest httpServletRequest){
        // 请求体校验
        if (httpServletRequest == null) {
            log.error("PassengerController.getPassengerById: 请求体为空");
            throw new BusinessException(ErrorCode.EMPTY_REQUEST,"请求体为空");
        }
        // 参数校验
        if (id == null) {
            log.error("PassengerController.getPassengerById: 乘客id为空");
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"乘客id为空");
        }
        // 获取乘客信息
        PassengerDO passengerDO = passengerService.getPassengerByPassengerId(id, httpServletRequest);
        // 转换为VO
        PassengerVO passengerVO = ToSafetyEntityUtils.toPassengerVO(passengerDO);
        return ResultUtils.success(passengerVO);
    }

    /**
     * 添加乘客
     */
    @PostMapping("")
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
        // 添加乘客
        int passengerId = passengerService.addPassenger(passengerRequest, httpServletRequest);
        return ResultUtils.success(passengerId);
    }

    /**
     * 删除乘客
     */
    @DeleteMapping("/{id}")
    public Result<Integer> deletePassenger(@PathVariable Long id,HttpServletRequest httpServletRequest)
    {
        // 请求体校验
        if (httpServletRequest == null) {
            log.error("PassengerController.deletePassenger: 请求体为空");
            throw new BusinessException(ErrorCode.EMPTY_REQUEST,"请求体为空");
        }
        // 参数校验
        if (id == null) {
            log.error("PassengerController.deletePassenger: 乘客id为空");
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"乘客id为空");
        }
        // 删除乘客
        int result = passengerService.deletePassenger(id, httpServletRequest);
        return ResultUtils.success(result);
    }

    /**
     * 更新乘客信息
     */
    @PutMapping()
    public Result<Integer> updatePassenger(@RequestBody PassengerRequest passengerRequest,HttpServletRequest httpServletRequest)
    {
        // 请求体校验
        if (httpServletRequest == null) {
            log.error("PassengerController.updatePassenger: 请求体为空");
            throw new BusinessException(ErrorCode.EMPTY_REQUEST,"请求体为空");
        }
        // 参数校验
        if (passengerRequest == null) {
            log.error("PassengerController.updatePassenger: 乘客信息为空");
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"乘客信息为空");
        }
        // 更新乘客信息
        int result = passengerService.updatePassenger(passengerRequest, httpServletRequest);
        return ResultUtils.success(result);
    }
}

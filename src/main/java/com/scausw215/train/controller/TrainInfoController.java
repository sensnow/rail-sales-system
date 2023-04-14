package com.scausw215.train.controller;


import com.alibaba.fastjson.JSON;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.entity.DO.TrainInfoDO;
import com.scausw215.train.entity.DO.TrainTypeDO;
import com.scausw215.train.entity.DTO.TrainInfoDTO;
import com.scausw215.train.entity.VO.PurchaseInfo;
import com.scausw215.train.entity.VO.UserTrainInfoListVO;
import com.scausw215.train.entity.request.TrainInfoSearchRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.TrainInfoService;
import com.scausw215.train.utils.ResultUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 车次信息管理
 * @author sensnow
 */
@Slf4j
@RestController
@RequestMapping("/train")
public class TrainInfoController {

    @Resource
    private TrainInfoService trainInfoService;

    /**
     * 添加
     * @param trainInfoDO 车次信息
     * @return 添加结果
     */
    @PostMapping("/admin")
    public Result<Integer> addTrainInfo(@RequestBody TrainInfoDO trainInfoDO) {
        // 检查参数
        if (trainInfoDO == null) {
            log.error("trainInfoDO is null");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        int result = trainInfoService.insertTrainInfo(trainInfoDO);
        return ResultUtils.success(result);
    }

    /**
     * 获取车次信息
     * @param trainInfoSearchRequest 查询条件
     * @param request 请求体
     * @return  车次信息
     */
    @PostMapping("/list")
    public Result<UserTrainInfoListVO> getTrainInfoList(@RequestBody TrainInfoSearchRequest trainInfoSearchRequest, HttpServletRequest request) {
        // 检查参数
        if (trainInfoSearchRequest == null || request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        UserTrainInfoListVO trainInfoByAnyCondition = trainInfoService.getTrainInfoByAnyCondition(trainInfoSearchRequest, request);
        return ResultUtils.success(trainInfoByAnyCondition);
    }

    /**
     * 获取车次的座位信息
     * @param trainTypeId 列车的id
     * @return 车次信息
     */
    @GetMapping("/detail")
    public Result<PurchaseInfo> getTrainInfoDetail(@PathParam("id") Long trainTypeId) {
        // 检查参数
        if (trainTypeId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        PurchaseInfo purchaseInfo = trainInfoService.getTrainInfoDetail(trainTypeId);
        return ResultUtils.success(purchaseInfo);
    }

    /**
     * 删除车次信息
     * @param trainId 车次id
     * @return 删除结果
     */
    @DeleteMapping("/admin")
    public Result<Integer> deleteTrainInfo(@PathParam("id") Long trainId) {
        // 检查参数
        if (trainId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        int result = trainInfoService.deleteTrainInfo(trainId);
        return ResultUtils.success(result);
    }

    /**
     * 更新车次信息
     * @param trainInfoDO 车次信息
     * @return 更新结果
     */
    @PutMapping("/admin")
    public Result<Integer> updateTrainInfo(@RequestBody TrainInfoDO trainInfoDO) {
        // 检查参数
        if (trainInfoDO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        int result = trainInfoService.updateTrainInfo(trainInfoDO);
        return ResultUtils.success(result);
    }

    /**
     * 获取未排班的车次类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 未排班的车次类型
     */
    @GetMapping("/admin/unused")
    public Result<List<TrainTypeDO>> getUnscheduledTrainTypeByTime(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime) {
        // 检查参数
        if (startTime == null || endTime == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        List<TrainTypeDO> unscheduledTrainType = trainInfoService.getUnscheduledTrainType(startTime, endTime);
        return ResultUtils.success(unscheduledTrainType);
    }

}

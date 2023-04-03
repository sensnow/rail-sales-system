package com.scausw215.train.controller;

import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.entity.DO.TrainTypeDO;
import com.scausw215.train.entity.VO.TrainTypeVO;
import com.scausw215.train.entity.request.TrainTypeRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.TrainTypeService;
import com.scausw215.train.utils.ResultUtils;
import com.scausw215.train.utils.ToSafetyEntityUtils;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 动车类型管理
 * @author sensnow
 *
 */
@RestController
@RequestMapping("/trainType/admin")
@Slf4j
public class TrainTypeController {

    @Resource
    TrainTypeService trainTypeService;
    /**
     * 获取单个列车类型
     * @param id 列车类型id
     * @return 列车类型列表
     */
    @GetMapping("")
    public Result<TrainTypeVO> getTrainTypeByTrainId(@PathParam("id") Long id) {
        // 参数检查
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "列车id为空");
        }
        // 获取列车类型
        TrainTypeDO trainType = trainTypeService.getTrainType(id);
        // 转换为VO
        TrainTypeVO trainTypeVO = ToSafetyEntityUtils.toTrainTypeVO(trainType);
        return ResultUtils.success(trainTypeVO);
    }

    /**
     * 获取所有列车类型
     * @return 列车类型列表
     */
    @GetMapping("/all")
    public Result<List<TrainTypeVO>> getAllTrainType() {
        // 获取列车类型
        List<TrainTypeDO> listTrainType = trainTypeService.getListTrainType();
        // 转换为VO
        List<TrainTypeVO> list = listTrainType.stream().map(ToSafetyEntityUtils::toTrainTypeVO).toList();
        return ResultUtils.success(list);
    }

    /**
     * 删除
     * @param id 列车类型id
     * @return 删除结果
     */
    @DeleteMapping("")
    public Result<Integer> deleteTrainType(@PathParam("id") Long id) {
        // 参数检查
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "列车id为空");
        }
        // 删除列车类型
        int result = trainTypeService.deleteTrainType(id);
        return ResultUtils.success(result);
    }

    /**
     * 更新
     * @param trainType 列车类型
     * @return 更新结果
     */
    @PutMapping
    public Result<Integer> updateTrainType(@RequestBody TrainTypeRequest trainType) {
        // 参数检查
        if (trainType == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "列车类型为空");
        }
        // 更新列车类型
        int result = trainTypeService.updateTrainType(trainType);
        return ResultUtils.success(result);
    }

    /**
     * 添加
     * @param trainType 列车类型
     * @return 添加结果
     */
    @PostMapping("")
    public Result<Integer> addTrainType(@RequestBody TrainTypeRequest trainType) {
        // 参数检查
        if (trainType == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "列车类型为空");
        }
        // 添加列车类型
        int result = trainTypeService.addTrainType(trainType);
        return ResultUtils.success(result);
    }

}

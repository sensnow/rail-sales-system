package com.scausw215.train.controller;


import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.entity.DO.SeatTypeDO;
import com.scausw215.train.entity.VO.SeatTypeVO;
import com.scausw215.train.entity.request.SeatTypeRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.SeatTypeService;
import com.scausw215.train.utils.ResultUtils;
import com.scausw215.train.utils.ToSafetyEntityUtils;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 列车座位类型管理
 * @author sensnow
 */
@RestController
@RequestMapping("/seat")
@Slf4j
public class SeatTypeController {


    @Resource
    SeatTypeService seatTypeService;
    /**
     * 获取单个座位类型信息
     * 根据座位类型id获取座位类型信息
     * @param id 座位类型id
     * @return 座位类型信息
     */
    @GetMapping("/user")
    public Result<SeatTypeVO> getSeatTypeById(@PathParam("id") Long id){
        // 检查参数
        if(id == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "座位类型id为空");
        }
        SeatTypeDO seatType = seatTypeService.getSeatType(id);
        SeatTypeVO seatTypeVO = ToSafetyEntityUtils.toSeatTypeVO(seatType);
        return ResultUtils.success(seatTypeVO);
    }

    /**
     * 获取所有座位类型信息
     * @return 座位类型信息列表
     */
    @GetMapping("/admin")
    public Result<List<SeatTypeVO>> getAllSeatType()
    {
        List<SeatTypeDO> seatTypeList = seatTypeService.getListSeatType();
        List<SeatTypeVO> seatTypeVOList = seatTypeList.stream().map(ToSafetyEntityUtils::toSeatTypeVO).toList();
        return ResultUtils.success(seatTypeVOList);
    }

    /**
     * 删除
     * @param id 座位类型id
     * @return 删除的记录数
     */
    @DeleteMapping("/admin")
    public Result<Integer> deleteSeatType(@PathParam("id") Long id)
    {
        // 检查参数
        if(id == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "座位类型id为空");
        }
        int delete = seatTypeService.deleteSeatType(id);
        return ResultUtils.success(delete);
    }

    /**
     * 增加
     * @param seatTypeRequest 座位类型信息
     * @return 增加的记录数
     */
    @PostMapping("/admin")
    public Result<Integer> addSeatType(@RequestBody SeatTypeRequest seatTypeRequest)
    {
        if (seatTypeRequest == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "座位类型信息为空");
        }
        int insert = seatTypeService.addSeatType(seatTypeRequest);
        return ResultUtils.success(insert);
    }

    /**
     * 更新座位类型
     * @param seatTypeRequest 座位类型信息
     * @return 更新的记录数
     */
    @PutMapping
    public Result<Integer> updateSeatType(@RequestBody SeatTypeRequest seatTypeRequest)
    {
        // 检查参数
        if(seatTypeRequest.getId() == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "座位类型id为空");
        }
        int update = seatTypeService.updateSeatType(seatTypeRequest);
        return ResultUtils.success(update);
    }

}

package com.scausw215.train.controller;

import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.entity.DO.StationInfoDO;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.entity.VO.StationVO;
import com.scausw215.train.entity.VO.UserInfoVO;
import com.scausw215.train.entity.request.StationRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.mapper.UserInfoMapper;
import com.scausw215.train.service.impl.StationInfoServiceImpl;
import com.scausw215.train.utils.ResultUtils;
import com.scausw215.train.utils.ToSafetyEntityUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/station")
public class StationInfoController {
    @Autowired
    StationInfoServiceImpl stationInfoService;

    /**
     * 获取车站信息，需要传入需要获取的车站名
     */
    @PostMapping("/getStationInfo")
    public Result<StationVO> getStationInfo(@RequestBody StationRequest stationRequest){
        //先判断信息是否为空
        //获取车站信息需要传入id参数
        if (StringUtils.isBlank(stationRequest.getName())) {
            log.error("StationInfoController.getStationInfo: 车站名为空,{}", stationRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"车站名为空");
        }
        StationInfoDO stationInfoDO = stationInfoService.getStationByStationName(stationRequest.getName());
        if (stationInfoDO == null){
            return ResultUtils.success(null);
        }
        StationVO stationVO = ToSafetyEntityUtils.toStationVO(stationInfoDO);
        return ResultUtils.success(stationVO);


    }

    /**
     * 更新车站信息，需要管理员权限，需要传入新的车站信息，以及要修改的车站对应的名字
     * @param stationRequest
     * @return
     */
    @PostMapping("/admin/updateStationInfo")
    public Result<Boolean> updateStationInfo(@RequestBody StationRequest stationRequest){

        if (StringUtils.isAllBlank(stationRequest.getName(),
                                   stationRequest.getNewName(),
                                   stationRequest.getNewCity(),
                                   stationRequest.getNewProvince()
                )) {
            log.error("StationInfoController.getStationInfo: 新的车站信息不能为空,{}", stationRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"提交的新车站信息为空");
        }
        StationInfoDO stationInfoDO = stationInfoService.getStationByStationName(stationRequest.getName());
        Boolean aBoolean = stationInfoService.upStationByStationName(stationInfoDO.getStationName(),
                                                                     stationRequest.getNewName(),
                                                                     stationRequest.getNewCity(),
                                                                     stationRequest.getNewProvince());
        return ResultUtils.success(aBoolean);

    }
}

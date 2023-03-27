package com.scausw215.train.controller;

import com.alibaba.fastjson.JSON;
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
import com.scausw215.train.utils.RequestToDoEntityUtils;
import com.scausw215.train.utils.ResultUtils;
import com.scausw215.train.utils.ToSafetyEntityUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.scausw215.train.utils.RequestToDoEntityUtils.toStationInfoDo;

@Slf4j
@RestController
@RequestMapping("/station")
public class StationInfoController {
    @Autowired
    StationInfoServiceImpl stationInfoService;

    /**
     * 获取车站信息，需要传入需要获取的车站名
     */
    @GetMapping("/get")
    public Result<StationVO> get(@RequestBody StationRequest stationRequest) {

        //先判断信息是否为空
        //获取车站信息需要传入id参数
        if (StringUtils.isBlank(stationRequest.getName())) {
            log.error("station.get: 车站名为空,{}", stationRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "车站名为空");
        }
        StationInfoDO stationInfoDO = stationInfoService.getStationByStationName(stationRequest.getName());
        if (stationInfoDO == null) {
            return ResultUtils.success(null);
        }
        StationVO stationVO = ToSafetyEntityUtils.toStationVO(stationInfoDO);
        return ResultUtils.success(stationVO);


    }
    /**
     * 根据信息获取多个车站信息
     * 1.直接查询所有
     * 2.根据城市名查询相关车站
     * 3.根据省名查询相关车站
     *
     */
    @GetMapping("/getAll")
    public Result<List<StationVO>> getAll(@RequestBody StationRequest stationRequest){

        //先判断三个参数是否全部为空
        if (StringUtils.isAllBlank(stationRequest.getIsAll(),stationRequest.getIsCity(),stationRequest.getIsProvince())) {
            log.error("station.getAll: 请求参数全为空");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请输入对应的请求参数");
        }
        //如果是查询所有
        if (!StringUtils.isBlank(stationRequest.getIsAll())){
//            1.直接查询所有
            if (!stationRequest.getIsAll().equals("1")){
                log.error("station.getAll: 请求参数不为1,{}",stationRequest.getIsAll());
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
            List<StationInfoDO> stationInfoDOS = stationInfoService.getAll();
            List<StationVO> stationVOS = stationInfoDOS.stream().map(ToSafetyEntityUtils::toStationVO).collect(Collectors.toList());
            return ResultUtils.success(stationVOS);
        }else if (!StringUtils.isBlank(stationRequest.getIsCity())){
//            2.根据城市名查询相关车站
            List<StationInfoDO> stationInfoDOS = stationInfoService.getByCity(stationRequest.getIsCity());
            List<StationVO> stationVOS = stationInfoDOS.stream().map(ToSafetyEntityUtils::toStationVO).collect(Collectors.toList());
            return ResultUtils.success(stationVOS);

        }else {
//            3.根据省名查询相关车站
            List<StationInfoDO> stationInfoDOS = stationInfoService.getByProvince(stationRequest.getIsProvince());
            List<StationVO> stationVOS = stationInfoDOS.stream().map(ToSafetyEntityUtils::toStationVO).collect(Collectors.toList());
            return ResultUtils.success(stationVOS);
        }

    }

    /**
     * 更新车站信息，需要管理员权限，需要传入新的车站信息，以及要修改的车站对应的名字
     *
     * @param stationRequest
     * @return
     */
    @PutMapping("/admin")
    public Result<Integer> update(@RequestBody StationRequest stationRequest) {

        if (StringUtils.isAnyBlank(stationRequest.getName(), stationRequest.getCity(), stationRequest.getProvince()
        )) {
            log.error("StationInfoController.getStationInfo: 新的车站信息不能为空,{}", stationRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "提交的新车站信息为空");
        }
        StationInfoDO stationInfoDO = toStationInfoDo(stationRequest);
        Integer integer = stationInfoService.updateStation(stationInfoDO);
        return ResultUtils.success(integer);
    }

    @PostMapping("/admin")
    public Result<Integer> add(@RequestBody StationRequest stationRequest) {
        if (StringUtils.isAnyBlank(stationRequest.getName(), stationRequest.getCity(), stationRequest.getProvince()
        )) {
            log.error("StationInfoController.getStationInfo: 新的车站信息不能为空,{}", stationRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "提交的新车站信息为空");
        }
        StationInfoDO stationInfoDO = toStationInfoDo(stationRequest);
        Integer integer = stationInfoService.insertStation(stationInfoDO);
        return ResultUtils.success(integer);
    }

    @DeleteMapping("/admin")
    public Result<Integer> delete(@RequestBody StationRequest stationRequest) {
        if (StringUtils.isBlank(String.valueOf(stationRequest.getId()))) {
            log.error("StationInfoController.getStationInfo: 删除车站信息不能为空,{}", stationRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "删除的车站信息为空");
        }
        Integer delete = stationInfoService.delete(String.valueOf(stationRequest.getId()));
        return ResultUtils.success(delete);
    }

}

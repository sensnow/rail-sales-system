package com.scausw215.train.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

/**
 * 车站信息管理
 */
@Slf4j
@RestController
@RequestMapping("/station")
public class StationInfoController {
    @Autowired
    StationInfoServiceImpl stationInfoService;

    /**
     * 获取车站信息，需要传入需要获取的车站名
     */
    @GetMapping("/user/{id}")
    public Result<StationVO> get(@PathVariable Long id) {
        //先判断信息是否为空
        //获取车站信息需要传入id参数
        if (StringUtils.isBlank(String.valueOf(id))) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "输入为空");
        }
        StationInfoDO stationInfoDO = stationInfoService.getStationById(id);
        if (stationInfoDO == null) {
            return ResultUtils.success(null);
        }
        StationVO stationVO = ToSafetyEntityUtils.toStationVO(stationInfoDO);
        return ResultUtils.success(stationVO);

    }
    /**
     * 分页查询
     *
     */
    @GetMapping("/user/page")
    public Result<Page> page(int page,int pageSize){
        if (StringUtils.isAnyBlank(String.valueOf(page),String.valueOf(pageSize))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        Page<StationInfoDO> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<StationInfoDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(StationInfoDO::getStationId);
        stationInfoService.page(pageInfo,queryWrapper);
        return ResultUtils.success(pageInfo);
    }
    /**
     * 更新车站信息
     *
     * @param stationRequest 列车请求体
     * @return 列车信息
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

    /**
     * 添加车站信息
     * @param stationRequest 列车请求体
     * @return 列车信息
     */
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

    /**
     * 删除
     * @param ids
     * @return
     */
    @DeleteMapping("/admin")
    public Result<String> delete(@RequestParam List<Long> ids) {
        // ID是不是数字
        if (StringUtils.isBlank(String.valueOf(ids))) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "车站ID为空");
        }
        stationInfoService.deletePlus(ids);
        return ResultUtils.success("删除成功");
    }

    /**
     * 查询所有
     * 查询城市对应车站
     * 查询省份对应车站
     * @param city
     * @param province
     * @return
     */
    @GetMapping("/getAll")
    public Result<List<StationVO>> getAll(String city,String province){
        LambdaQueryWrapper<StationInfoDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(StationInfoDO::getStationId);
        if (!StringUtils.isBlank(city)){
            queryWrapper.eq(StationInfoDO::getStationCity,city);
        }
        if (!StringUtils.isBlank(province)){
            queryWrapper.eq(StationInfoDO::getStationProvince,province);
        }
        List<StationVO> stationVOS = stationInfoService.list(queryWrapper).stream().map((item) -> {
            StationVO stationVO = ToSafetyEntityUtils.toStationVO(item);
            return stationVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(stationVOS);
    }

}

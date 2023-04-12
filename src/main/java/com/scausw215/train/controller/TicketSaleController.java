package com.scausw215.train.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.entity.DO.TicketInfoDO;
import com.scausw215.train.entity.DO.TicketSaleDO;
import com.scausw215.train.entity.DO.TrainInfoDO;
import com.scausw215.train.entity.DTO.TicketInfoDTO;
import com.scausw215.train.entity.DTO.TicketSaleDTO;
import com.scausw215.train.entity.VO.TicketSaleVO;
import com.scausw215.train.entity.request.TicketSaleRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.*;
import com.scausw215.train.utils.RequestToDoEntityUtils;
import com.scausw215.train.utils.ResultUtils;
import com.scausw215.train.utils.ToSafetyEntityUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ticketSale")
@Slf4j
public class TicketSaleController {
    @Autowired
    private TicketSalesService ticketSalesService;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private TicketInfoService ticketInfoService;
    @Autowired
    private TrainInfoService trainInfoService;

    /**
     * 根据id查询售票信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TicketSaleDTO> get(@PathVariable Long id){
        if (StringUtils.isBlank(String.valueOf(id))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请输入正确的请求");
        }
        TicketSaleDO ticketSaleDO = ticketSalesService.getById(id);
        TicketSaleDTO ticketSaleDTO = new TicketSaleDTO();
        BeanUtils.copyProperties(ticketSaleDO,ticketSaleDTO);
        ticketSaleDTO.setPassengerDO(passengerService.getById(ticketSaleDO.getPassengerId()));
        TicketInfoDTO ticketInfoDTO = new TicketInfoDTO();
        BeanUtils.copyProperties(ticketInfoService.getById(ticketSaleDO.getTicketId()),ticketInfoDTO);
        ticketSaleDTO.setTicketInfo(ticketInfoDTO);

        return ResultUtils.success(ticketSaleDTO);
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result<Page> page(int page,int pageSize){
        if (StringUtils.isAnyBlank(String.valueOf(page),String.valueOf(pageSize))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请输入正确的请求");
        }
        Page<TicketSaleDO> pageInfo = new Page<>(page,pageSize);

        LambdaQueryWrapper<TicketSaleDO> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByAsc(TicketSaleDO::getPurchaseTime);


        ticketSalesService.page(pageInfo,queryWrapper);

        return ResultUtils.success(pageInfo);

    }

    /**
     * 增加车票售票信息
     * @param ticketSaleRequest
     * @param request
     * @return
     */
    @PostMapping("/admin")
    public Result<String> add(@RequestBody TicketSaleRequest ticketSaleRequest, HttpServletRequest request){
        if (StringUtils.isAnyBlank(String.valueOf(ticketSaleRequest.getIsRefunded()),String.valueOf(ticketSaleRequest.getUserId()),String.valueOf(ticketSaleRequest.getPassengerId()),String.valueOf(ticketSaleRequest.getTicketId()),String.valueOf(ticketSaleRequest.getPurchasePrice()))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"输入的参数为空");
        }
        TicketSaleDO ticketSaleDO = RequestToDoEntityUtils.toTicketSaleDO(ticketSaleRequest);
        ticketSalesService.addTicketSale(ticketSaleDO,request);
        return ResultUtils.success("增加车票售票信息成功");
    }

    /**
     * 删除售票信息
     * @param ids
     * @return
     */
    @DeleteMapping("/admin")
    public Result<String> delete(@RequestParam List<Long> ids){
        if (StringUtils.isBlank(String.valueOf(ids))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"输入参数为空");
        }
        ticketSalesService.delete(ids);
        return ResultUtils.success("删除售票信息成功");
    }

    /**
     * 修改售票信息
     * @param ticketSaleRequest
     * @param request
     * @return
     */
    @PutMapping("/admin")
    public Result<String> update(@RequestBody TicketSaleRequest ticketSaleRequest,HttpServletRequest request){
        if (StringUtils.isAnyBlank(String.valueOf(ticketSaleRequest.getSaleId()),String.valueOf(ticketSaleRequest.getIsRefunded()),String.valueOf(ticketSaleRequest.getUserId()),String.valueOf(ticketSaleRequest.getPassengerId()),String.valueOf(ticketSaleRequest.getTicketId()),String.valueOf(ticketSaleRequest.getPurchasePrice()))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"输入的参数为空");
        }
        TicketSaleDO ticketSaleDO = RequestToDoEntityUtils.toTicketSaleDO(ticketSaleRequest);
        ticketSalesService.update(ticketSaleDO,request);
        return ResultUtils.success("售票信息修改成功");
    }

    /**
     * 查询所有
     * 可以传入参数startStation，endStation，startTime，endTime来查询
     * @return
     */
    @GetMapping("/getAll")
    public Result<List<TicketSaleDTO>> getAll(Long startStation, Long endStation, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date endTime){

        LambdaQueryWrapper<TrainInfoDO> queryWrapper1 = new LambdaQueryWrapper<>();

        //判断查询条件
        if (startStation!=null){
            queryWrapper1.eq(TrainInfoDO::getStartStation,startStation);
        }
        if (endStation!=null){
            queryWrapper1.eq(TrainInfoDO::getEndStation,endStation);
        }
        if (startTime!=null){
            queryWrapper1.ge(TrainInfoDO::getStartTime,startTime);
        }
        if (endTime!=null){
            queryWrapper1.le(TrainInfoDO::getEndTime,endTime);
        }

        List<TrainInfoDO> trainInfoDOList = trainInfoService.list(queryWrapper1);

        List<Long> trainIds = trainInfoDOList.stream().map((item) -> {
            Long trainId = item.getTrainId();
            return trainId;
        }).collect(Collectors.toList());

        LambdaQueryWrapper<TicketInfoDO> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.in(TicketInfoDO::getTrainId,trainIds);

        List<TicketInfoDO> ticketInfoDOList = ticketInfoService.list(queryWrapper2);
        List<Long> ticketIds = ticketInfoDOList.stream().map((item) -> {
            Long ticketId = item.getTicketId();
            return ticketId;
        }).collect(Collectors.toList());

        LambdaQueryWrapper<TicketSaleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(TicketSaleDO::getPurchaseTime);
        queryWrapper.eq(TicketSaleDO::getIsRefunded,1);
        queryWrapper.in(TicketSaleDO::getTicketId,ticketIds);

        List<TicketSaleDTO> ticketSaleDTOS = ticketSalesService.list(queryWrapper).stream().map((item) -> {

            TicketSaleDTO ticketSaleDTO = new TicketSaleDTO();
            BeanUtils.copyProperties(item,ticketSaleDTO);

            ticketSaleDTO.setPassengerDO(passengerService.getById(item.getPassengerId()));

            TicketInfoDTO ticketInfoDTO = new TicketInfoDTO();
            BeanUtils.copyProperties(ticketInfoService.getById(item.getTicketId()),ticketInfoDTO);

            ticketSaleDTO.setTicketInfo(ticketInfoDTO);

            return ticketSaleDTO;
        }).collect(Collectors.toList());


        return ResultUtils.success(ticketSaleDTOS);

    }


}

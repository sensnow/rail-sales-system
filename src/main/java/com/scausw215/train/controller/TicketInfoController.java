package com.scausw215.train.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.constant.UserInfoConstant;
import com.scausw215.train.entity.DO.TicketInfoDO;
import com.scausw215.train.entity.DO.TrainInfoDO;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.entity.DTO.TicketInfoDTO;
import com.scausw215.train.entity.Usage.TrainTicketSeatType;
import com.scausw215.train.entity.VO.TicketVO;
import com.scausw215.train.entity.request.TicketRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.mapper.TicketSaleMapper;
import com.scausw215.train.mapper.TrainInfoMapper;
import com.scausw215.train.service.TicketInfoService;
import com.scausw215.train.service.TicketSalesService;
import com.scausw215.train.service.TrainInfoService;
import com.scausw215.train.utils.RequestToDoEntityUtils;
import com.scausw215.train.utils.ResultUtils;
import com.scausw215.train.utils.ToSafetyEntityUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ticket")
@Slf4j
public class TicketInfoController {

    @Autowired
    private TicketInfoService ticketInfoService;
    @Autowired
    private TrainInfoMapper trainInfoMapper;
    @Autowired
    private TicketSaleMapper ticketSaleMapper;

    /**
     * 根据id查询车票信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TicketInfoDTO> get(@PathVariable Long id){
        if (id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"id不能为空");
        }
        TicketInfoDTO ticketInfoDTO = ticketInfoService.getOneById(id);

        return ResultUtils.success(ticketInfoDTO);
    }
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize){
        //构造分页构造器对象
        Page<TicketInfoDO> pageInfo = new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<TicketInfoDO> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.orderByAsc(TicketInfoDO::getUpdateTime);
        //执行分页查询
        ticketInfoService.page(pageInfo, queryWrapper);
        log.info("{}",pageInfo.getRecords());
        return ResultUtils.success(pageInfo);

    }
    /**
     * 新增车票
     * @param ticketRequest
     * @return
     */
    @PostMapping("/admin")
    public Result<TicketVO> add(@RequestBody TicketRequest ticketRequest){

        TicketInfoDO ticketInfoDO = RequestToDoEntityUtils.toTicketInfoDO(ticketRequest);

        ticketInfoService.save(ticketInfoDO);

        TicketVO ticketVO = ToSafetyEntityUtils.toTicketVO(ticketInfoDO);

        return ResultUtils.success(ticketVO);
    }
    /**
     * 删除车票
     */
    @DeleteMapping("/admin")
    public Result<String> delete(@RequestParam List<Long> ids){
        log.info("ids:{}",ids);
        if (ids == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }
        ticketInfoService.delete(ids);
        return ResultUtils.success("删除成功");
    }

    /**
     * 更新车票信息
     * @param ticketRequest
     * @return
     */
    @PutMapping("/admin")
    public Result<String> update(@RequestBody TicketRequest ticketRequest){

        log.info("ticketRequest:{}",ticketRequest);
        TicketInfoDO ticketInfoDO = RequestToDoEntityUtils.toTicketInfoDO(ticketRequest);
        if (StringUtils.isAnyBlank(String.valueOf(ticketInfoDO.getTicketId()),String.valueOf(ticketInfoDO.getTrainId()),String.valueOf(ticketInfoDO.getTicketPrice()),String.valueOf(ticketInfoDO.getCarNumber()),String.valueOf(ticketInfoDO.getSeatNumber()),String.valueOf(ticketInfoDO.getSeatTypeId()),String.valueOf(ticketInfoDO.getIsSold()),String.valueOf(ticketInfoDO.getIsAvailable()))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }
        ticketInfoService.updateById(ticketInfoDO);

        return ResultUtils.success("车票信息修改成功");

    }

    /**
     * 购票操作
     * 1.将车票设置为已出售
     * 2.添加到车票售票表中
     * @param id
     * @return
     */
    @PutMapping
    public Result<String> buy(@RequestParam("id") Long id,@RequestParam("passengerId") Long passengerId, HttpServletRequest request){

        if (id == null||passengerId == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"输入id或passengerId不能为空");
        }

        UserInfoDO userInfoDO = (UserInfoDO) request.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        ticketInfoService.buy(id,passengerId,userInfoDO.getUserId());

        return ResultUtils.success("购票成功");
    }

    @PostMapping("/test")
    public Result<String> buyPlus(@RequestParam("trainId")Long trainId,@RequestParam("passengerId")List<Long> passengerId, HttpServletRequest request,@RequestParam("seatTypeId") Long seatTypeId,@RequestParam("trainName") String trainName){

        if (trainId == null||passengerId == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"输入id或passengerId不能为空");
        }

        UserInfoDO userInfoDO = (UserInfoDO) request.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        ticketInfoService.buyPlus(trainId,passengerId,userInfoDO.getUserId(),seatTypeId,trainName);

        return ResultUtils.success("购票成功");
    }


    /**
     * 获取所有车票的信息
     * (startStation，endStation，startTime，endTime)可根据四个值添加限制条件
     * @return
     */
    @GetMapping("/getAll")
    public Result<List<TrainTicketSeatType>> getAll(Long startStation, Long endStation, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date endTime,@RequestParam(value = "trainId",required = false) Long trainId){

        List<TrainTicketSeatType> all = ticketInfoService.getAll(startStation, endStation, startTime, endTime, trainId);

        return ResultUtils.success(all);
    }



}

package com.scausw215.train.controller;

import ch.qos.logback.core.model.INamedModel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.constant.UserInfoConstant;
import com.scausw215.train.entity.DO.TicketInfoDO;
import com.scausw215.train.entity.DO.TicketRefundedDO;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.entity.DTO.TicketRefundedDTO;
import com.scausw215.train.entity.DTO.TicketSaleDTO;
import com.scausw215.train.entity.Usage.TrainTicketTicketRefundedPassengerSeatType;
import com.scausw215.train.entity.VO.TicketRefundedVO;
import com.scausw215.train.entity.request.TicketRefundedRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.mapper.PassengerMapper;
import com.scausw215.train.mapper.TicketInfoMapper;
import com.scausw215.train.service.PassengerService;
import com.scausw215.train.service.TicketInfoService;
import com.scausw215.train.service.TicketRefundedService;
import com.scausw215.train.utils.ResultUtils;
import com.scausw215.train.utils.ToSafetyEntityUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/refunded")
@Slf4j
public class TicketRefundedController {

    @Autowired
    private TicketRefundedService ticketRefundedService;
    @Autowired
    private PassengerMapper passengerMapper;
    @Autowired
    private TicketInfoMapper ticketInfoMapper;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TicketRefundedDTO> get(@PathVariable Long id){
        if (StringUtils.isBlank(String.valueOf(id))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"输入正确的id");
        }
        TicketRefundedDTO ticketRefundedDTO = ticketRefundedService.getOneById(id);

        return ResultUtils.success(ticketRefundedDTO);
    }
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result<Page> page(int page,int pageSize){
        //构造分页构造器对象
        Page<TicketRefundedDO> pageInfo = new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<TicketRefundedDO> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.orderByAsc(TicketRefundedDO::getRefundedTime);
        //执行分页查询
        ticketRefundedService.page(pageInfo,queryWrapper);

        return ResultUtils.success(pageInfo);
    }

    /**
     * 新增退票信息
     * @param ticketRefundedRequest
     * @param request
     * @return
     */
    @PostMapping
    public Result<String> add(@RequestBody TicketRefundedRequest ticketRefundedRequest, HttpServletRequest request){
        if (StringUtils.isAnyBlank(String.valueOf(ticketRefundedRequest.getRefundedPrice()),String.valueOf(ticketRefundedRequest.getTicketId()),String.valueOf(ticketRefundedRequest.getPassengerId()),String.valueOf(ticketRefundedRequest.getRefundedReason()))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请输入正确的参数");
        }
        ticketRefundedService.addTicketRefunded(ticketRefundedRequest, request);

        return ResultUtils.success("添加退票信息成功");
    }

    /**
     * 删除退票信息
     * @param ids
     * @return
     */
    @DeleteMapping("/admin")
    public Result<String> delete(@RequestParam List<Long> ids){
        if (StringUtils.isBlank(String.valueOf(ids))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请输入正确的参数");
        }
        ticketRefundedService.delete(ids);
        return ResultUtils.success("删除退票信息成功");
    }

    /**
     * 更新退票信息
     * @param ticketRefundedRequest
     * @param request
     * @return
     */
    @PutMapping("/admin")
    public Result<String> update(@RequestBody TicketRefundedRequest ticketRefundedRequest,HttpServletRequest request){
        if (StringUtils.isAnyBlank(String.valueOf(ticketRefundedRequest.getTicketId()),String.valueOf(ticketRefundedRequest.getTicketId()),String.valueOf(ticketRefundedRequest.getPassengerId()),String.valueOf(ticketRefundedRequest.getRefundedReason()))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请输入正确的参数");
        }
        ticketRefundedService.update(ticketRefundedRequest,request);
        return ResultUtils.success("更新退票信息成功");
    }

    /**
     * 获取所有退票信息
     * @return
     */
    @GetMapping("/getAll")
    public Result<List<TrainTicketTicketRefundedPassengerSeatType>> getAll(Long trainId,HttpServletRequest request){

        UserInfoDO userInfoDO = (UserInfoDO) request.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        List<TrainTicketTicketRefundedPassengerSeatType> all = ticketRefundedService.getAll(trainId, userInfoDO.getUserId());

        return ResultUtils.success(all);

    }


    @PutMapping("/admin/{id}")
    public Result<Integer> refundTicketById(@PathVariable Long id)
    {
        if(id==null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请输入正确的参数");
        }
       return ResultUtils.success(ticketRefundedService.refundedByTicketId(id));


    }
}

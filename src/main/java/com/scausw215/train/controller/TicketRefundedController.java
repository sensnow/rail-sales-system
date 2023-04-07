package com.scausw215.train.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.entity.DO.TicketRefundedDO;
import com.scausw215.train.entity.VO.TicketRefundedVO;
import com.scausw215.train.entity.request.TicketRefundedRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.TicketRefundedService;
import com.scausw215.train.utils.ResultUtils;
import com.scausw215.train.utils.ToSafetyEntityUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refunded")
@Slf4j
public class TicketRefundedController {

    @Autowired
    private TicketRefundedService ticketRefundedService;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TicketRefundedVO> get(@PathVariable Long id){
        if (StringUtils.isBlank(String.valueOf(id))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"输入正确的id");
        }
        TicketRefundedDO ticketRefundedDO = ticketRefundedService.getById(id);
        TicketRefundedVO ticketRefundedVO = ToSafetyEntityUtils.toTicketRefundedVO(ticketRefundedDO);
        return ResultUtils.success(ticketRefundedVO);
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
        Page<TicketRefundedDO> pageInfo = new Page<>();
        //条件构造器
        LambdaQueryWrapper<TicketRefundedDO> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.orderByAsc(TicketRefundedDO::getRefundedTime);
        //执行分页查询
        ticketRefundedService.page(pageInfo,queryWrapper);

        return ResultUtils.success(pageInfo);
    }

    /**
     * 新增退票系统
     * @param ticketRefundedRequest
     * @param request
     * @return
     */
    @PostMapping()
    public Result<TicketRefundedVO> add(@RequestBody TicketRefundedRequest ticketRefundedRequest, HttpServletRequest request){
        if (StringUtils.isAnyBlank(String.valueOf(ticketRefundedRequest.getRefundedPrice()),String.valueOf(ticketRefundedRequest.getTicketId()),String.valueOf(ticketRefundedRequest.getPassengerId()),String.valueOf(ticketRefundedRequest.getRefundedReason()))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请输入正确的参数");
        }
        TicketRefundedDO ticketRefundedDO = ticketRefundedService.addTicketRefunded(ticketRefundedRequest, request);
        TicketRefundedVO ticketRefundedVO = ToSafetyEntityUtils.toTicketRefundedVO(ticketRefundedDO);
        return ResultUtils.success(ticketRefundedVO);
    }

    /**
     * 删除退票信息
     * @param ids
     * @return
     */
    @DeleteMapping
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
    @PutMapping
    public Result<String> update(@RequestBody TicketRefundedRequest ticketRefundedRequest,HttpServletRequest request){
        if (StringUtils.isAnyBlank(String.valueOf(ticketRefundedRequest.getTicketId()),String.valueOf(ticketRefundedRequest.getTicketId()),String.valueOf(ticketRefundedRequest.getPassengerId()),String.valueOf(ticketRefundedRequest.getRefundedReason()))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请输入正确的参数");
        }
        ticketRefundedService.update(ticketRefundedRequest,request);
        return ResultUtils.success("更新退票信息成功");
    }
}

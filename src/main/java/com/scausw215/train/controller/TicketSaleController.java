package com.scausw215.train.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.entity.DO.TicketSaleDO;
import com.scausw215.train.entity.VO.TicketSaleVO;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.TicketSalesService;
import com.scausw215.train.utils.ResultUtils;
import com.scausw215.train.utils.ToSafetyEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticketSale")
@Slf4j
public class TicketSaleController {
    @Autowired
    private TicketSalesService ticketSalesService;

    /**
     * 根据id查询售票信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TicketSaleVO> get(@PathVariable Long id){
        if (StringUtils.isBlank(String.valueOf(id))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请输入正确的请求");
        }
        TicketSaleDO ticketSaleDO = ticketSalesService.getById(id);
        TicketSaleVO ticketSaleVO = ToSafetyEntityUtils.toTicketSaleVO(ticketSaleDO);
        return ResultUtils.success(ticketSaleVO);
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

}

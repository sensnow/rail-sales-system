package com.scausw215.train.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.entity.DO.TicketInfoDO;
import com.scausw215.train.entity.DO.TicketSaleDO;
import com.scausw215.train.entity.DTO.TicketSaleDTO;
import com.scausw215.train.entity.VO.TicketSaleVO;
import com.scausw215.train.entity.request.TicketSaleRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.PassengerService;
import com.scausw215.train.service.TicketInfoService;
import com.scausw215.train.service.TicketSalesService;
import com.scausw215.train.service.UserInfoService;
import com.scausw215.train.utils.RequestToDoEntityUtils;
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
@RequestMapping("/ticketSale")
@Slf4j
public class TicketSaleController {
    @Autowired
    private TicketSalesService ticketSalesService;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private TicketInfoService ticketInfoService;

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
        ticketSaleDTO.setTicketInfo(ticketInfoService.getById(ticketSaleDO.getTicketId()));

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
     * @return
     */
    @GetMapping("/getAll")
    public Result<List<TicketSaleDTO>> getAll(){
        LambdaQueryWrapper<TicketSaleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(TicketSaleDO::getPurchaseTime);
        queryWrapper.eq(TicketSaleDO::getIsRefunded,1);
        List<TicketSaleDTO> ticketSaleDTOS = ticketSalesService.list(queryWrapper).stream().map((item) -> {
            TicketSaleDTO ticketSaleDTO = new TicketSaleDTO();
            BeanUtils.copyProperties(item,ticketSaleDTO);
            ticketSaleDTO.setPassengerDO(passengerService.getById(item.getPassengerId()));
            ticketSaleDTO.setTicketInfo(ticketInfoService.getById(item.getTicketId()));
            return ticketSaleDTO;
        }).collect(Collectors.toList());

        return ResultUtils.success(ticketSaleDTOS);

    }


}

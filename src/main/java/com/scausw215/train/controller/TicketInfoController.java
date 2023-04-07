package com.scausw215.train.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.entity.DO.TicketInfoDO;
import com.scausw215.train.entity.DTO.TicketInfoDTO;
import com.scausw215.train.entity.VO.TicketVO;
import com.scausw215.train.entity.request.TicketRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.TicketInfoService;
import com.scausw215.train.utils.RequestToDoEntityUtils;
import com.scausw215.train.utils.ResultUtils;
import com.scausw215.train.utils.ToSafetyEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@Slf4j
public class TicketInfoController {

    @Autowired
    private TicketInfoService ticketInfoService;

    /**
     * 根据id查询车票信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TicketVO> get(@PathVariable Long id){
        TicketInfoDO ticketInfoDO = ticketInfoService.getById(id);
        TicketVO ticketVO = ToSafetyEntityUtils.toTicketVO(ticketInfoDO);
        return ResultUtils.success(ticketVO);
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
        queryWrapper.orderByAsc(TicketInfoDO::getStartSaleTime);
        //执行分页查询
        ticketInfoService.page(pageInfo, queryWrapper);

        return ResultUtils.success(pageInfo);

    }

    /**
     * 新增车票
     * @param ticketRequest
     * @return
     */
    @PostMapping
    public Result<TicketVO> add(@RequestBody TicketRequest ticketRequest){

        TicketInfoDO ticketInfoDO = RequestToDoEntityUtils.toTicketInfoDO(ticketRequest);

        ticketInfoService.save(ticketInfoDO);

        TicketVO ticketVO = ToSafetyEntityUtils.toTicketVO(ticketInfoDO);

        return ResultUtils.success(ticketVO);
    }
    /**
     * 删除车票
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam List<Long> ids){
        log.info("ids:{}",ids);
        if (StringUtils.isBlank(String.valueOf(ids))){
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
    @PutMapping
    public Result<String> update(@RequestBody TicketRequest ticketRequest){

        log.info("ticketRequest:{}",ticketRequest);
        TicketInfoDO ticketInfoDO = RequestToDoEntityUtils.toTicketInfoDO(ticketRequest);
        if (StringUtils.isAnyBlank(String.valueOf(ticketInfoDO.getTicketId()),String.valueOf(ticketInfoDO.getTrainId()),String.valueOf(ticketInfoDO.getTicketPrice()),String.valueOf(ticketInfoDO.getCarNumber()),String.valueOf(ticketInfoDO.getSeatNumber()),String.valueOf(ticketInfoDO.getSeatTypeId()),String.valueOf(ticketInfoDO.getIsSold()),String.valueOf(ticketInfoDO.getIsAvailable()))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }
        ticketInfoService.updateById(ticketInfoDO);

        return ResultUtils.success("车票信息修改成功");

    }

}

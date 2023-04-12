package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.constant.UserInfoConstant;
import com.scausw215.train.entity.DO.TicketInfoDO;
import com.scausw215.train.entity.DO.TicketRefundedDO;
import com.scausw215.train.entity.DO.TicketSaleDO;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.entity.DTO.TicketSaleDTO;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.mapper.TicketInfoMapper;
import com.scausw215.train.mapper.TicketRefundedMapper;
import com.scausw215.train.service.TicketInfoService;
import com.scausw215.train.service.TicketRefundedService;
import com.scausw215.train.service.TicketSalesService;
import com.scausw215.train.mapper.TicketSaleMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author sensnow
* @description 针对表【ticket_sales(售票信息表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class TicketSalesServiceImpl extends ServiceImpl<TicketSaleMapper, TicketSaleDO>
    implements TicketSalesService{
    @Autowired
    private TicketInfoMapper ticketInfoMapper;
    @Autowired
    private TicketRefundedMapper ticketRefundedMapper;

    @Transactional
    public void addTicketSale(TicketSaleDO ticketSaleDO, HttpServletRequest request) {
        UserInfoDO userInfoDO = (UserInfoDO) request.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        ticketSaleDO.setUserId(userInfoDO.getUserId());
        ticketSaleDO.setPurchaseTime(LocalDateTime.now());
        this.save(ticketSaleDO);
    }

    @Override
    public void delete(List<Long> ids) {
        LambdaQueryWrapper<TicketSaleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(TicketSaleDO::getSaleId,ids);
        int count = (int) this.count(queryWrapper);
        if (count == 0){
            throw new BusinessException(ErrorCode.DATABASE_ERROR,"找不到要删除的信息");
        }
        this.removeByIds(ids);
    }

    @Override
    public void update(TicketSaleDO ticketSaleDO, HttpServletRequest request) {
        UserInfoDO userInfoDO = (UserInfoDO) request.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        ticketSaleDO.setUserId(userInfoDO.getUserId());
        ticketSaleDO.setPurchaseTime(LocalDateTime.now());

        this.updateById(ticketSaleDO);

    }


    /**
     * 退票操作
     * @param saleId
     * @param userId
     * @param reason
     */
    @Transactional
    public void refunded(Long saleId,Long userId,String reason) {

        //更新车票出售信息
        TicketSaleDO ticketSaleDO = this.getById(saleId);
        TicketInfoDO ticketInfoDO = ticketInfoMapper.selectById(ticketSaleDO.getTicketId());

        //判断是否超过截止时间
        if(LocalDateTime.now().isAfter(ticketInfoDO.getEndSaleTime())){
            throw new BusinessException(ErrorCode.DATABASE_ERROR,"对不起，已超过售票时间，无法退票");
        }
        ticketInfoDO.setIsSold(0);
        ticketInfoMapper.updateById(ticketInfoDO);

        //添加车票退票信息
        TicketRefundedDO ticketRefundedDO = new TicketRefundedDO();
        //封装
        ticketRefundedDO.setTicketId(ticketSaleDO.getTicketId());
        ticketRefundedDO.setRefundedPrice(ticketSaleDO.getPurchasePrice());
        ticketRefundedDO.setRefundedReason(reason);
        ticketRefundedDO.setRefundedTime(LocalDateTime.now());
        ticketRefundedDO.setUserId(userId);
        ticketRefundedDO.setPassengerId(ticketSaleDO.getPassengerId());

        ticketRefundedMapper.insert(ticketRefundedDO);

        //删除售票信息
        this.removeById(saleId);
    }
}





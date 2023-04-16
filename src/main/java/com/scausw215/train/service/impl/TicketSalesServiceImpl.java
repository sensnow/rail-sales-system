package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.constant.UserInfoConstant;
import com.scausw215.train.entity.DO.*;
import com.scausw215.train.entity.DTO.TicketInfoDTO;
import com.scausw215.train.entity.DTO.TicketSaleDTO;
import com.scausw215.train.entity.Usage.TrainTicketSeatType;
import com.scausw215.train.entity.Usage.TrainTicketTicketsalePassengerSeatType;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.mapper.*;
import com.scausw215.train.service.StationInfoService;
import com.scausw215.train.service.TicketInfoService;
import com.scausw215.train.service.TicketRefundedService;
import com.scausw215.train.service.TicketSalesService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author sensnow
* @description 针对表【ticket_sales(售票信息表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class TicketSalesServiceImpl extends ServiceImpl<TicketSaleMapper, TicketSaleDO>
    implements TicketSalesService {
    @Autowired
    private TicketInfoMapper ticketInfoMapper;
    @Autowired
    private TicketRefundedMapper ticketRefundedMapper;
    @Autowired
    private PassengerMapper passengerMapper;
    @Autowired
    private TrainInfoMapper trainInfoMapper;
    @Autowired
    private StationInfoMapper stationInfoMapper;
    @Autowired
    private SeatTypeMapper seatTypeMapper;
    @Autowired
    private TrainTicketTicketsalePassengerSeatTypeMapper trainTicketTicketsalePassengerSeatTypeMapper;

    /**
     * 添加新售票信息
     *
     * @param ticketSaleDO
     * @param request
     */
    @Transactional
    public void addTicketSale(TicketSaleDO ticketSaleDO, HttpServletRequest request) {
        UserInfoDO userInfoDO = (UserInfoDO) request.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        ticketSaleDO.setUserId(userInfoDO.getUserId());
        ticketSaleDO.setPurchaseTime(LocalDateTime.now());
        this.save(ticketSaleDO);
    }

    /**
     * 删除售票信息
     *
     * @param ids
     */
    @Override
    public void delete(List<Long> ids) {
        LambdaQueryWrapper<TicketSaleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(TicketSaleDO::getSaleId, ids);
        int count = (int) this.count(queryWrapper);
        if (count == 0) {
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "找不到要删除的信息");
        }
        this.removeByIds(ids);
    }

    /**
     * 更新售票信息
     *
     * @param ticketSaleDO
     * @param request
     */
    @Override
    public void update(TicketSaleDO ticketSaleDO, HttpServletRequest request) {
        UserInfoDO userInfoDO = (UserInfoDO) request.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        ticketSaleDO.setUserId(userInfoDO.getUserId());
        ticketSaleDO.setPurchaseTime(LocalDateTime.now());

        this.updateById(ticketSaleDO);

    }

    /**
     * 退票
     * @param ticketId
     * @param userId
     * @param reason
     */
    @Transactional
    public void refunded(Long ticketId, Long userId, String reason) {

        LambdaQueryWrapper<TicketSaleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TicketSaleDO::getTicketId,ticketId);
        queryWrapper.eq(TicketSaleDO::getIsRefunded,0);
        TicketSaleDO ticketSaleDO = this.getOne(queryWrapper);
        ticketSaleDO.setIsRefunded(1);
        this.updateById(ticketSaleDO);

        TicketInfoDO ticketInfoDO = ticketInfoMapper.selectById(ticketId);

        //判断是否超过截止时间
        if (LocalDateTime.now().isAfter(ticketInfoDO.getEndSaleTime())) {
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "对不起，已超过售票时间，无法退票");
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

    }

    /**
     * 根据id获取单个售票信息
     *
     * @param id
     * @return
     */
    @Override
    public TicketSaleDTO getOneById(Long id) {
        TicketSaleDO ticketSaleDO = this.getById(id);
        TicketSaleDTO ticketSaleDTO = new TicketSaleDTO();
        BeanUtils.copyProperties(ticketSaleDO, ticketSaleDTO);
        ticketSaleDTO.setPassengerDO(passengerMapper.selectById(ticketSaleDO.getPassengerId()));
        TicketInfoDTO ticketInfoDTO = new TicketInfoDTO();
        BeanUtils.copyProperties(ticketInfoMapper.selectById(ticketSaleDO.getTicketId()), ticketInfoDTO);
        ticketSaleDTO.setTicketInfo(ticketInfoDTO);
        return ticketSaleDTO;
    }

    /**
     * 查询所有
     *
     * @param startStation
     * @param endStation
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<TrainTicketTicketsalePassengerSeatType> getAll(Long startStation, Long endStation, Date startTime, Date endTime, Long userId, Long trainId) {
        if (trainId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "cuole");
        }
        return trainTicketTicketsalePassengerSeatTypeMapper.getAllByTrainId(trainId);
    }

    @Override
    public List<TrainTicketTicketsalePassengerSeatType> getUserTicket(Long userId) {
        if (userId == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "cuole");
        }
        return trainTicketTicketsalePassengerSeatTypeMapper.getAllByUserId(userId);
    }
}





package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.constant.UserInfoConstant;
import com.scausw215.train.entity.DO.*;
import com.scausw215.train.entity.DTO.TicketInfoDTO;
import com.scausw215.train.entity.DTO.TicketSaleDTO;
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
    implements TicketSalesService{
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

    /**
     * 添加新售票信息
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
     * @param ids
     */
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

    /**
     * 更新售票信息
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
     * 退票操作
     * @param saleId
     * @param userInfoDO
     * @param reason
     */
    @Transactional
    public void refunded(Long saleId,UserInfoDO userInfoDO ,String reason) {

        //更新车票出售信息
        TicketSaleDO ticketSaleDO = this.getById(saleId);
        ticketSaleDO.setIsRefunded(1);//把车票售票信息设置为已退票
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
        ticketRefundedDO.setUserId(userInfoDO.getUserId());
        ticketRefundedDO.setPassengerId(ticketSaleDO.getPassengerId());

        ticketRefundedMapper.insert(ticketRefundedDO);

        //保存售票信息
        this.updateById(ticketSaleDO);
    }

    /**
     * 根据id获取单个售票信息
     * @param id
     * @return
     */
    @Override
    public TicketSaleDTO getOneById(Long id) {
        TicketSaleDO ticketSaleDO = this.getById(id);
        TicketSaleDTO ticketSaleDTO = new TicketSaleDTO();
        BeanUtils.copyProperties(ticketSaleDO,ticketSaleDTO);
        ticketSaleDTO.setPassengerDO(passengerMapper.selectById(ticketSaleDO.getPassengerId()));
        TicketInfoDTO ticketInfoDTO = new TicketInfoDTO();
        BeanUtils.copyProperties(ticketInfoMapper.selectById(ticketSaleDO.getTicketId()),ticketInfoDTO);
        ticketSaleDTO.setTicketInfo(ticketInfoDTO);
        return ticketSaleDTO;
    }

    /**
     * 查询所有
     * @param startStation
     * @param endStation
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<TicketSaleDTO> getAll(Long startStation, Long endStation, Date startTime, Date endTime,UserInfoDO userInfoDO,Long trainId) {

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
        if (trainId!=null){
            queryWrapper1.eq(TrainInfoDO::getTrainId,trainId);
        }

        List<TrainInfoDO> trainInfoDOList = trainInfoMapper.selectList(queryWrapper1);

        List<Long> trainIds = trainInfoDOList.stream().map((item) -> {
            Long trainId1 = item.getTrainId();
            return trainId1;
        }).collect(Collectors.toList());

        LambdaQueryWrapper<TicketInfoDO> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.in(TicketInfoDO::getTrainId,trainIds);
        queryWrapper2.eq(TicketInfoDO::getIsSold,1);//已经出售

        List<TicketInfoDO> ticketInfoDOList = ticketInfoMapper.selectList(queryWrapper2);
        List<Long> ticketIds = ticketInfoDOList.stream().map((item) -> {
            Long ticketId = item.getTicketId();
            return ticketId;
        }).collect(Collectors.toList());

        LambdaQueryWrapper<TicketSaleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(TicketSaleDO::getPurchaseTime);
        queryWrapper.eq(TicketSaleDO::getIsRefunded,0);//0 表示没有被退票
        queryWrapper.in(TicketSaleDO::getTicketId,ticketIds);
        if (userInfoDO.getUserAuthority() != 1){
            queryWrapper.eq(TicketSaleDO::getUserId,userInfoDO.getUserId());
        }

        List<TicketSaleDTO> ticketSaleDTOS = this.list(queryWrapper).stream().map((item) -> {

            TicketSaleDTO ticketSaleDTO = new TicketSaleDTO();
            BeanUtils.copyProperties(item,ticketSaleDTO);

            ticketSaleDTO.setPassengerDO(passengerMapper.selectById(item.getPassengerId()));

            TicketInfoDTO ticketInfoDTO = new TicketInfoDTO();
            TicketInfoDO ticketInfoDO = ticketInfoMapper.selectById(item.getTicketId());

            BeanUtils.copyProperties(ticketInfoDO,ticketInfoDTO);
            ticketInfoDTO.setTrainInfoDO(trainInfoMapper.selectById(ticketInfoDO.getTrainId()));

            ticketSaleDTO.setTicketInfo(ticketInfoDTO);

            return ticketSaleDTO;
        }).collect(Collectors.toList());
        for (TicketSaleDTO ticketSaleDTO : ticketSaleDTOS) {

            TicketInfoDTO ticketInfoDTO = ticketSaleDTO.getTicketInfo();

            ticketInfoDTO.setStartStation(stationInfoMapper.selectById(ticketSaleDTO.getTicketInfo().getTrainInfoDO().getStartStation()));
            ticketInfoDTO.setEndStation(stationInfoMapper.selectById(ticketSaleDTO.getTicketInfo().getTrainInfoDO().getEndStation()));

            ticketInfoDTO.setSeatTypeDO(seatTypeMapper.selectById(ticketSaleDTO.getTicketInfo().getSeatTypeId()));
        }


        return ticketSaleDTOS;
    }


}





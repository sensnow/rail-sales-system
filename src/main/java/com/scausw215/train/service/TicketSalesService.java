package com.scausw215.train.service;

import com.scausw215.train.entity.DO.TicketSaleDO;
import com.scausw215.train.entity.DTO.TicketSaleDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scausw215.train.entity.Usage.TrainTicketTicketsalePassengerSeatType;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

/**
* @author sensnow
* @description 针对表【ticket_sales(售票信息表)】的数据库操作Service
* @createDate 2023-03-25 14:10:16
*/
public interface TicketSalesService extends IService<TicketSaleDO> {
    void addTicketSale(TicketSaleDO ticketSaleDO, HttpServletRequest request);
    void delete(List<Long> ids);
    void update(TicketSaleDO ticketSaleDO,HttpServletRequest request);
    void refunded(Long saleId,Long userId,String reason);
    TicketSaleDTO getOneById(Long id);
    List<TrainTicketTicketsalePassengerSeatType> getAll(Long startStation, Long endStation, Date startTime, Date endTime, Long userId, Long trainId);

    /**
     * 获取用户的所有购票信息
     * @param userId 用户id
     * @return 用户的所有购票信息
     *
     */
    List<TrainTicketTicketsalePassengerSeatType> getUserTicket(Long userId);
}

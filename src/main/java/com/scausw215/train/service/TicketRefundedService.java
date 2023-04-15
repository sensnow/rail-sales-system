package com.scausw215.train.service;

import com.scausw215.train.entity.DO.TicketInfoDO;
import com.scausw215.train.entity.DO.TicketRefundedDO;
import com.scausw215.train.entity.DTO.TicketRefundedDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scausw215.train.entity.Usage.TrainTicketTicketRefundedPassengerSeatType;
import com.scausw215.train.entity.request.TicketRefundedRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
* @author sensnow
* @description 针对表【ticket_refunded(退票信息表)】的数据库操作Service
* @createDate 2023-03-25 14:10:16
*/
public interface TicketRefundedService extends IService<TicketRefundedDO> {

    TicketRefundedDO addTicketRefunded(TicketRefundedRequest ticketRefundedRequest, HttpServletRequest request);
    void delete(List<Long> ids);
    void update(TicketRefundedRequest ticketRefundedRequest,HttpServletRequest request);
    TicketRefundedDTO getOneById(Long id);
    List<TrainTicketTicketRefundedPassengerSeatType> getAll(Long trainId, Long userId);

}

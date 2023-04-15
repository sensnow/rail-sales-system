package com.scausw215.train.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scausw215.train.entity.Usage.TrainTicketTicketRefundedPassengerSeatType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 欧阳
* @description 针对表【train_ticket_ticket_refunded_passenger_seat_type】的数据库操作Mapper
* @createDate 2023-04-15 02:06:27
* @Entity generator.domain.TrainTicketTicketRefundedPassengerSeatType
*/
@Mapper
public interface TrainTicketTicketRefundedPassengerSeatTypeMapper extends BaseMapper<TrainTicketTicketRefundedPassengerSeatType> {

    List<TrainTicketTicketRefundedPassengerSeatType> getAllByTrainId(Long id);
}





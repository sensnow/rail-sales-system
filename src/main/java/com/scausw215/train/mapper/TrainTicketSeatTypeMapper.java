package com.scausw215.train.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scausw215.train.entity.Usage.TrainTicketSeatType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
* @author 欧阳
* @description 针对表【train_ticket_seat_type】的数据库操作Mapper
* @createDate 2023-04-15 02:06:27
* @Entity generator.domain.TrainTicketSeatType
*/
@Mapper
public interface TrainTicketSeatTypeMapper extends BaseMapper<TrainTicketSeatType> {

    List<TrainTicketSeatType> getAllByTrainId(Long id);
}





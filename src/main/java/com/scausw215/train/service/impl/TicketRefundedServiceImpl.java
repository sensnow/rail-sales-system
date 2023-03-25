package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.entity.TicketRefunded;
import com.scausw215.train.service.TicketRefundedService;
import com.scausw215.train.mapper.TicketRefundedMapper;
import org.springframework.stereotype.Service;

/**
* @author sensnow
* @description 针对表【ticket_refunded(退票信息表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class TicketRefundedServiceImpl extends ServiceImpl<TicketRefundedMapper, TicketRefunded>
    implements TicketRefundedService{

}





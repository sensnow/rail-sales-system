package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.entity.DO.TicketSaleDO;
import com.scausw215.train.entity.DTO.TicketSaleDTO;
import com.scausw215.train.service.TicketSalesService;
import com.scausw215.train.mapper.TicketSaleMapper;
import org.springframework.stereotype.Service;

/**
* @author sensnow
* @description 针对表【ticket_sales(售票信息表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class TicketSalesServiceImpl extends ServiceImpl<TicketSaleMapper, TicketSaleDO>
    implements TicketSalesService{

}





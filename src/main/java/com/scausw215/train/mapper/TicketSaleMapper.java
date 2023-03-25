package com.scausw215.train.mapper;

import com.scausw215.train.entity.DTO.TicketSaleDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sensnow
* @description 针对表【ticket_sales(售票信息表)】的数据库操作Mapper
* @createDate 2023-03-25 14:10:16
* @Entity com.scausw215.train.entity.TicketSales
*/
@Mapper
public interface TicketSaleMapper extends BaseMapper<TicketSaleDTO> {

}





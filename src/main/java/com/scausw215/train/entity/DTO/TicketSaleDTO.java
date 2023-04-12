package com.scausw215.train.entity.DTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.scausw215.train.entity.DO.PassengerDO;
import com.scausw215.train.entity.DO.TicketInfoDO;
import com.scausw215.train.entity.DO.TicketSaleDO;
import lombok.Data;

/**
 * 售票信息表
 * @author sensnow
 */
@TableName(value ="ticket_sales")
@Data
public class TicketSaleDTO extends TicketSaleDO {

    /**
     * 购票人信息
     */
    private PassengerDO passengerDO;

    /**
     * 车票id
     */
    private TicketInfoDTO ticketInfo;



    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
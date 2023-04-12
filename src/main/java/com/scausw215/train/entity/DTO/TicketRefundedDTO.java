package com.scausw215.train.entity.DTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.scausw215.train.entity.DO.PassengerDO;
import com.scausw215.train.entity.DO.TicketInfoDO;
import com.scausw215.train.entity.DO.TicketRefundedDO;
import lombok.Data;

/**
 * 退票信息表
 * @author sensnow
 */
@TableName(value ="ticket_refunded")
@Data
public class TicketRefundedDTO extends TicketRefundedDO {

    /**
     * 车票信息
     */
    private TicketInfoDO ticketInfo;

    /**
     * 退票购票人
     */
    private PassengerDO passengerDO;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
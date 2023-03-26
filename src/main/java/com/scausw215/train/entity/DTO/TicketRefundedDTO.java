package com.scausw215.train.entity.DTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.scausw215.train.entity.DO.PassengerDO;
import lombok.Data;

/**
 * 退票信息表
 * @author sensnow
 */
@TableName(value ="ticket_refunded")
@Data
public class TicketRefundedDTO implements Serializable {

    /**
     * 退票信息id
     */
    @TableId
    private Long refundedId;

    /**
     * 退票用户id
     */
    private Long userId;

    /**
     * 车票信息
     */
    private TicketInfoDTO ticketInfo;


    /**
     * 退票购票人
     */
    private PassengerDO passengerDO;

    /**
     * 退票金额
     */
    private Integer refundedPrice;

    /**
     * 退票理由
     */
    private String refundedReason;

    /**
     * 退票时间
     */
    private Date refundedTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
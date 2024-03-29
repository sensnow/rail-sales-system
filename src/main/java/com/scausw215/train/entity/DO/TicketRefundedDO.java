package com.scausw215.train.entity.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author sensnow
 */
@TableName(value ="ticket_refunded")
@Data
public class TicketRefundedDO implements Serializable {

    /**
     * 退票信息id
     */
    @TableId
    private Long refundedId;
    /**
     * 车票id
     */
    private Long ticketId;

    /**
     * 退票用户id
     */
    private Long userId;

    /**
     * 退票购票id
     */
    private Long passengerId;

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
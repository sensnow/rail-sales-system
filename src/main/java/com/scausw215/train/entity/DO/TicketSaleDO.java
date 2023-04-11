package com.scausw215.train.entity.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.scausw215.train.entity.DO.PassengerDO;
import com.scausw215.train.entity.DTO.TicketInfoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 售票信息表
 * @author sensnow
 * @TableName ticket_sales
 */
@TableName(value ="ticket_sale")
@Data
public class TicketSaleDO implements Serializable {
    /**
     * 售票信息id
     */
    @TableId(type = IdType.AUTO)
    private Long saleId;

    /**
     * 购票用户id
     */
    private Long userId;

    /**
     * 购票人id
     */
    private Long passengerId;

    /**
     * 车票id
     */
    private Long ticketId;

    /**
     * 售票价格
     */
    private Integer purchasePrice;

    /**
     * 购买时间
     */

    private LocalDateTime purchaseTime;

    /**
     * 是否退票
     */
    private Integer isRefunded;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
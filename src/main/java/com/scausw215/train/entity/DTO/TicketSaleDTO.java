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
 * 售票信息表
 * @author sensnow
 */
@TableName(value ="ticket_sales")
@Data
public class TicketSaleDTO implements Serializable {
    /**
     * 售票信息id
     */
    @TableId
    private Long saleId;

    /**
     * 购票用户id
     */
    private Long userId;

    /**
     * 购票人信息
     */
    private PassengerDO passengerDO;

    /**
     * 车票id
     */
    private TicketInfoDTO ticketInfo;

    /**
     * 售票价格
     */
    private Integer purchasePrice;

    /**
     * 购买时间
     */
    private Date purchaseTime;

    /**
     * 是否退票
     */
    private Integer isRefunded;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
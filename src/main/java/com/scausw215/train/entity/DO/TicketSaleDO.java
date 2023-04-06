package com.scausw215.train.entity.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
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
@TableName(value ="ticket_sales")
@Data
public class TicketSaleDO implements Serializable {
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
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date purchaseTime;

    /**
     * 是否退票
     */
    private Integer isRefunded;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
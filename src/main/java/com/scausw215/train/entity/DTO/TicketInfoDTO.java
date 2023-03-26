package com.scausw215.train.entity.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.scausw215.train.entity.DO.TrainInfoDO;
import lombok.Data;

/**
 * 车票信息表
 * @author sensnow
 */
@TableName(value ="ticket_info")
@Data
public class TicketInfoDTO implements Serializable {

    /**
     * 车票id
     */
    @TableId(type = IdType.AUTO)
    private Long ticketId;

    /**
     * 车次信息
     */
    private TrainInfoDO trainInfoDO;

    /**
     * 车厢号
     */
    private Integer carNumber;

    /**
     * 座位类型id
     */
    private Long seatTypeId;

    /**
     * 座位号
     */
    private Integer seatNumber;

    /**
     * 是否已经出售
     */
    private Integer isSold;

    /**
     * 车票价格
     */
    private Integer ticketPrice;

    /**
     * 售票时间
     */
    private Date startSaleTime;

    /**
     * 截止售票时间
     */
    private Date endSaleTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
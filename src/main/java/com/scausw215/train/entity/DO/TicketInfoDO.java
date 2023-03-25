package com.scausw215.train.entity.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @author sensnow
 *
 */
@TableName(value ="ticket_info")
@Data
public class TicketInfoDO {
    @TableId(type = IdType.AUTO)
    private Long ticketId;

    /**
     * 车次id
     */
    private Long trainId;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

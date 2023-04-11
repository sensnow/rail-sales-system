package com.scausw215.train.entity.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
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

    private LocalDateTime startSaleTime;

    /**
     * 截止售票时间
     */

    private LocalDateTime endSaleTime;

    /**
     * 更新时间
     */

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否可用 0-不可用 1-可用
     */
    private Integer isAvailable;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

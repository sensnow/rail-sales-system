package com.scausw215.train.entity.request;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class TicketRequest {

    private Long ticketId;
    private Long trainId;
    private Integer carNumber;
    private Long seatTypeId;
    private Integer seatNumber;
    private Integer isSold;
    private Integer ticketPrice;

    /**
     * 售票时间
     */
//    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime startSaleTime;

    /**
     * 截止售票时间
     */
//    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime endSaleTime;

    /**
     * 更新时间
     */
//    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否可用 0-不可用 1-可用
     */
    private Integer isAvailable;
}

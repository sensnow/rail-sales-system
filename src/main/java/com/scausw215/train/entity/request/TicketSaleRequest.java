package com.scausw215.train.entity.request;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TicketSaleRequest {

    private Long saleId;
    private Long userId;
    private Long passengerId;
    private Long ticketId;
    private Integer purchasePrice;
    private LocalDateTime purchaseTime;
    private Integer isRefunded;
}

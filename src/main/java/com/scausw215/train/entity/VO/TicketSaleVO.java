package com.scausw215.train.entity.VO;

import ch.qos.logback.classic.spi.LoggerContextAware;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
public class TicketSaleVO {
    private Long saleId;
    private Long userId;
    private Long passengerId;
    private Long ticketId;
    private Integer purchasePrice;
    private LocalDateTime purchaseTime;
    private Integer isRefunded;
}

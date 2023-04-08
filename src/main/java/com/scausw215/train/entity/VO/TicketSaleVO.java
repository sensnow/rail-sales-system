package com.scausw215.train.entity.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TicketSaleVO {
    private Long saleId;
    private Long userId;
    private Long passengerId;
    private Long ticketId;
    private Integer purchasePrice;
    private Date purchaseTime;
    private Integer isRefunded;
}

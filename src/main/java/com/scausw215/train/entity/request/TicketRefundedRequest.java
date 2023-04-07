package com.scausw215.train.entity.request;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TicketRefundedRequest {
    private Long refundedId;
    private Long ticketId;
    private Long userId;
    private Long passengerId;
    private Integer refundedPrice;
    private String refundedReason;
    private LocalDateTime refundedTime;
}

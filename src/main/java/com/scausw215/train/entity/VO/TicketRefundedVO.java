package com.scausw215.train.entity.VO;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TicketRefundedVO {

    private Long refundedId;
    private Long ticketId;
    private Long userId;
    private Long passengerId;
    private Integer refundedPrice;
    private String refundedReason;
    private LocalDateTime refundedTime;
}

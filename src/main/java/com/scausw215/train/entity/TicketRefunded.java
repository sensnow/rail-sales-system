package com.scausw215.train.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 退票信息表
 * @TableName ticket_refunded
 */
@TableName(value ="ticket_refunded")
@Data
public class TicketRefunded implements Serializable {
    /**
     * 退票信息id
     */
    @TableId
    private Long refundedId;

    /**
     * 车票id
     */
    private Long ticketId;

    /**
     * 退票用户id
     */
    private Long userId;

    /**
     * 退票购票人id
     */
    private Long passengerId;

    /**
     * 退票金额
     */
    private Integer refundedPrice;

    /**
     * 退票理由
     */
    private String refundedReason;

    /**
     * 退票时间
     */
    private Date refundedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TicketRefunded other = (TicketRefunded) that;
        return (this.getRefundedId() == null ? other.getRefundedId() == null : this.getRefundedId().equals(other.getRefundedId()))
            && (this.getTicketId() == null ? other.getTicketId() == null : this.getTicketId().equals(other.getTicketId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPassengerId() == null ? other.getPassengerId() == null : this.getPassengerId().equals(other.getPassengerId()))
            && (this.getRefundedPrice() == null ? other.getRefundedPrice() == null : this.getRefundedPrice().equals(other.getRefundedPrice()))
            && (this.getRefundedReason() == null ? other.getRefundedReason() == null : this.getRefundedReason().equals(other.getRefundedReason()))
            && (this.getRefundedTime() == null ? other.getRefundedTime() == null : this.getRefundedTime().equals(other.getRefundedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRefundedId() == null) ? 0 : getRefundedId().hashCode());
        result = prime * result + ((getTicketId() == null) ? 0 : getTicketId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPassengerId() == null) ? 0 : getPassengerId().hashCode());
        result = prime * result + ((getRefundedPrice() == null) ? 0 : getRefundedPrice().hashCode());
        result = prime * result + ((getRefundedReason() == null) ? 0 : getRefundedReason().hashCode());
        result = prime * result + ((getRefundedTime() == null) ? 0 : getRefundedTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", refundedId=").append(refundedId);
        sb.append(", ticketId=").append(ticketId);
        sb.append(", userId=").append(userId);
        sb.append(", passengerId=").append(passengerId);
        sb.append(", refundedPrice=").append(refundedPrice);
        sb.append(", refundedReason=").append(refundedReason);
        sb.append(", refundedTime=").append(refundedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
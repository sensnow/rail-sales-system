package com.scausw215.train.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 售票信息表
 * @TableName ticket_sales
 */
@TableName(value ="ticket_sales")
@Data
public class TicketSales implements Serializable {
    /**
     * 售票信息id
     */
    @TableId
    private Long saleId;

    /**
     * 购票用户id
     */
    private Long userId;

    /**
     * 购票人id
     */
    private Long passengerId;

    /**
     * 车票id
     */
    private Long ticketId;

    /**
     * 售票价格
     */
    private Integer purchasePrice;

    /**
     * 购买时间
     */
    private Date purchaseTime;

    /**
     * 是否退票
     */
    private Integer isRefunded;

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
        TicketSales other = (TicketSales) that;
        return (this.getSaleId() == null ? other.getSaleId() == null : this.getSaleId().equals(other.getSaleId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPassengerId() == null ? other.getPassengerId() == null : this.getPassengerId().equals(other.getPassengerId()))
            && (this.getTicketId() == null ? other.getTicketId() == null : this.getTicketId().equals(other.getTicketId()))
            && (this.getPurchasePrice() == null ? other.getPurchasePrice() == null : this.getPurchasePrice().equals(other.getPurchasePrice()))
            && (this.getPurchaseTime() == null ? other.getPurchaseTime() == null : this.getPurchaseTime().equals(other.getPurchaseTime()))
            && (this.getIsRefunded() == null ? other.getIsRefunded() == null : this.getIsRefunded().equals(other.getIsRefunded()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSaleId() == null) ? 0 : getSaleId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPassengerId() == null) ? 0 : getPassengerId().hashCode());
        result = prime * result + ((getTicketId() == null) ? 0 : getTicketId().hashCode());
        result = prime * result + ((getPurchasePrice() == null) ? 0 : getPurchasePrice().hashCode());
        result = prime * result + ((getPurchaseTime() == null) ? 0 : getPurchaseTime().hashCode());
        result = prime * result + ((getIsRefunded() == null) ? 0 : getIsRefunded().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", saleId=").append(saleId);
        sb.append(", userId=").append(userId);
        sb.append(", passengerId=").append(passengerId);
        sb.append(", ticketId=").append(ticketId);
        sb.append(", purchasePrice=").append(purchasePrice);
        sb.append(", purchaseTime=").append(purchaseTime);
        sb.append(", isRefunded=").append(isRefunded);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package com.scausw215.train.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 车票信息表
 * @TableName ticket_info
 */
@TableName(value ="ticket_info")
@Data
public class TicketInfo implements Serializable {
    /**
     * 车票id
     */
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
        TicketInfo other = (TicketInfo) that;
        return (this.getTicketId() == null ? other.getTicketId() == null : this.getTicketId().equals(other.getTicketId()))
            && (this.getTrainId() == null ? other.getTrainId() == null : this.getTrainId().equals(other.getTrainId()))
            && (this.getCarNumber() == null ? other.getCarNumber() == null : this.getCarNumber().equals(other.getCarNumber()))
            && (this.getSeatTypeId() == null ? other.getSeatTypeId() == null : this.getSeatTypeId().equals(other.getSeatTypeId()))
            && (this.getSeatNumber() == null ? other.getSeatNumber() == null : this.getSeatNumber().equals(other.getSeatNumber()))
            && (this.getIsSold() == null ? other.getIsSold() == null : this.getIsSold().equals(other.getIsSold()))
            && (this.getTicketPrice() == null ? other.getTicketPrice() == null : this.getTicketPrice().equals(other.getTicketPrice()))
            && (this.getStartSaleTime() == null ? other.getStartSaleTime() == null : this.getStartSaleTime().equals(other.getStartSaleTime()))
            && (this.getEndSaleTime() == null ? other.getEndSaleTime() == null : this.getEndSaleTime().equals(other.getEndSaleTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTicketId() == null) ? 0 : getTicketId().hashCode());
        result = prime * result + ((getTrainId() == null) ? 0 : getTrainId().hashCode());
        result = prime * result + ((getCarNumber() == null) ? 0 : getCarNumber().hashCode());
        result = prime * result + ((getSeatTypeId() == null) ? 0 : getSeatTypeId().hashCode());
        result = prime * result + ((getSeatNumber() == null) ? 0 : getSeatNumber().hashCode());
        result = prime * result + ((getIsSold() == null) ? 0 : getIsSold().hashCode());
        result = prime * result + ((getTicketPrice() == null) ? 0 : getTicketPrice().hashCode());
        result = prime * result + ((getStartSaleTime() == null) ? 0 : getStartSaleTime().hashCode());
        result = prime * result + ((getEndSaleTime() == null) ? 0 : getEndSaleTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ticketId=").append(ticketId);
        sb.append(", trainId=").append(trainId);
        sb.append(", carNumber=").append(carNumber);
        sb.append(", seatTypeId=").append(seatTypeId);
        sb.append(", seatNumber=").append(seatNumber);
        sb.append(", isSold=").append(isSold);
        sb.append(", ticketPrice=").append(ticketPrice);
        sb.append(", startSaleTime=").append(startSaleTime);
        sb.append(", endSaleTime=").append(endSaleTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
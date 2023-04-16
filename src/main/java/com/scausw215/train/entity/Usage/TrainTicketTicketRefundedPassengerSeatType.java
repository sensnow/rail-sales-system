package com.scausw215.train.entity.Usage;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName train_ticket_ticket_refunded_passenger_seat_type
 */
@TableName(value ="train_ticket_ticket_refunded_passenger_seat_type")
@Data
public class TrainTicketTicketRefundedPassengerSeatType implements Serializable {
    /**
     * 退票用户id
     */
    private Long userId;

    /**
     * 退票购票人id
     */
    private Long passengerId;

    /**
     * 车次id
     */
    private Long trainId;

    /**
     * 车票id
     */
    private Long ticketId;

    /**
     * 车厢号
     */
    private Integer carNumber;

    /**
     * 是否已经出售
     */
    private Integer isSold;

    /**
     * 车票价格
     */
    private Integer ticketPrice;

    private String passengerName;

    /**
     * 购票人证件类型
     */
    private Integer passengerType;

    /**
     * 证件号号码
     */
    private String passengerCardNumber;

    /**
     * 购票人手机号
     */
    private String passengerPhone;

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

    /**
     * 发车时间
     */
    private Date startTime;

    /**
     * 到达时间
     */
    private Date endTime;

    /**
     * 
     */
    private String sstStationName;

    /**
     * 
     */
    private String estStationName;

    /**
     * 动车类型代码
     */
    private String trainCode;

    /**
     * 动车类型名称
     */
    private Object trainName;

    /**
     * 座位类型名称
     */
    private String seatName;

    /**
     * 座位类型描述
     */
    private String seatDescription;

    /**
     * 退票理由
     */
    private String refundedReason;

    /**
     * 退票金额
     */
    private Integer refundedPrice;

    /**
     * 退票时间
     */
    private Date refundedTime;

    private Integer seatNumber;

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
        TrainTicketTicketRefundedPassengerSeatType other = (TrainTicketTicketRefundedPassengerSeatType) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPassengerId() == null ? other.getPassengerId() == null : this.getPassengerId().equals(other.getPassengerId()))
            && (this.getTrainId() == null ? other.getTrainId() == null : this.getTrainId().equals(other.getTrainId()))
            && (this.getTicketId() == null ? other.getTicketId() == null : this.getTicketId().equals(other.getTicketId()))
            && (this.getCarNumber() == null ? other.getCarNumber() == null : this.getCarNumber().equals(other.getCarNumber()))
            && (this.getIsSold() == null ? other.getIsSold() == null : this.getIsSold().equals(other.getIsSold()))
            && (this.getTicketPrice() == null ? other.getTicketPrice() == null : this.getTicketPrice().equals(other.getTicketPrice()))
            && (this.getStartSaleTime() == null ? other.getStartSaleTime() == null : this.getStartSaleTime().equals(other.getStartSaleTime()))
            && (this.getEndSaleTime() == null ? other.getEndSaleTime() == null : this.getEndSaleTime().equals(other.getEndSaleTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getSstStationName() == null ? other.getSstStationName() == null : this.getSstStationName().equals(other.getSstStationName()))
            && (this.getEstStationName() == null ? other.getEstStationName() == null : this.getEstStationName().equals(other.getEstStationName()))
            && (this.getTrainCode() == null ? other.getTrainCode() == null : this.getTrainCode().equals(other.getTrainCode()))
            && (this.getTrainName() == null ? other.getTrainName() == null : this.getTrainName().equals(other.getTrainName()))
            && (this.getSeatName() == null ? other.getSeatName() == null : this.getSeatName().equals(other.getSeatName()))
            && (this.getSeatDescription() == null ? other.getSeatDescription() == null : this.getSeatDescription().equals(other.getSeatDescription()))
            && (this.getRefundedReason() == null ? other.getRefundedReason() == null : this.getRefundedReason().equals(other.getRefundedReason()))
            && (this.getRefundedPrice() == null ? other.getRefundedPrice() == null : this.getRefundedPrice().equals(other.getRefundedPrice()))
            && (this.getRefundedTime() == null ? other.getRefundedTime() == null : this.getRefundedTime().equals(other.getRefundedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPassengerId() == null) ? 0 : getPassengerId().hashCode());
        result = prime * result + ((getTrainId() == null) ? 0 : getTrainId().hashCode());
        result = prime * result + ((getTicketId() == null) ? 0 : getTicketId().hashCode());
        result = prime * result + ((getCarNumber() == null) ? 0 : getCarNumber().hashCode());
        result = prime * result + ((getIsSold() == null) ? 0 : getIsSold().hashCode());
        result = prime * result + ((getTicketPrice() == null) ? 0 : getTicketPrice().hashCode());
        result = prime * result + ((getStartSaleTime() == null) ? 0 : getStartSaleTime().hashCode());
        result = prime * result + ((getEndSaleTime() == null) ? 0 : getEndSaleTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getSstStationName() == null) ? 0 : getSstStationName().hashCode());
        result = prime * result + ((getEstStationName() == null) ? 0 : getEstStationName().hashCode());
        result = prime * result + ((getTrainCode() == null) ? 0 : getTrainCode().hashCode());
        result = prime * result + ((getTrainName() == null) ? 0 : getTrainName().hashCode());
        result = prime * result + ((getSeatName() == null) ? 0 : getSeatName().hashCode());
        result = prime * result + ((getSeatDescription() == null) ? 0 : getSeatDescription().hashCode());
        result = prime * result + ((getRefundedReason() == null) ? 0 : getRefundedReason().hashCode());
        result = prime * result + ((getRefundedPrice() == null) ? 0 : getRefundedPrice().hashCode());
        result = prime * result + ((getRefundedTime() == null) ? 0 : getRefundedTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", passengerId=").append(passengerId);
        sb.append(", trainId=").append(trainId);
        sb.append(", ticketId=").append(ticketId);
        sb.append(", carNumber=").append(carNumber);
        sb.append(", isSold=").append(isSold);
        sb.append(", ticketPrice=").append(ticketPrice);
        sb.append(", startSaleTime=").append(startSaleTime);
        sb.append(", endSaleTime=").append(endSaleTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", sstStationName=").append(sstStationName);
        sb.append(", estStationName=").append(estStationName);
        sb.append(", trainCode=").append(trainCode);
        sb.append(", trainName=").append(trainName);
        sb.append(", seatName=").append(seatName);
        sb.append(", seatDescription=").append(seatDescription);
        sb.append(", refundedReason=").append(refundedReason);
        sb.append(", refundedPrice=").append(refundedPrice);
        sb.append(", refundedTime=").append(refundedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
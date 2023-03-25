package com.scausw215.train.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 购票人信息表
 * @TableName passenger
 */
@TableName(value ="passenger")
@Data
public class Passenger implements Serializable {
    /**
     * 购票人id
     */
    @TableId(type = IdType.AUTO)
    private Long passengerId;

    /**
     * 购票人姓名
     */
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
     * 所属账号id
     */
    private Long userId;

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
        Passenger other = (Passenger) that;
        return (this.getPassengerId() == null ? other.getPassengerId() == null : this.getPassengerId().equals(other.getPassengerId()))
            && (this.getPassengerName() == null ? other.getPassengerName() == null : this.getPassengerName().equals(other.getPassengerName()))
            && (this.getPassengerType() == null ? other.getPassengerType() == null : this.getPassengerType().equals(other.getPassengerType()))
            && (this.getPassengerCardNumber() == null ? other.getPassengerCardNumber() == null : this.getPassengerCardNumber().equals(other.getPassengerCardNumber()))
            && (this.getPassengerPhone() == null ? other.getPassengerPhone() == null : this.getPassengerPhone().equals(other.getPassengerPhone()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPassengerId() == null) ? 0 : getPassengerId().hashCode());
        result = prime * result + ((getPassengerName() == null) ? 0 : getPassengerName().hashCode());
        result = prime * result + ((getPassengerType() == null) ? 0 : getPassengerType().hashCode());
        result = prime * result + ((getPassengerCardNumber() == null) ? 0 : getPassengerCardNumber().hashCode());
        result = prime * result + ((getPassengerPhone() == null) ? 0 : getPassengerPhone().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", passengerId=").append(passengerId);
        sb.append(", passengerName=").append(passengerName);
        sb.append(", passengerType=").append(passengerType);
        sb.append(", passengerCardNumber=").append(passengerCardNumber);
        sb.append(", passengerPhone=").append(passengerPhone);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
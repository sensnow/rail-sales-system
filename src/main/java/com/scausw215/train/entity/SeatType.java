package com.scausw215.train.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 座位类型表
 * @TableName seat_type
 */
@TableName(value ="seat_type")
@Data
public class SeatType implements Serializable {
    /**
     * 座位类型id
     */
    @TableId(type = IdType.AUTO)
    private Long seatId;

    /**
     * 座位类型名称
     */
    private String seatName;

    /**
     * 座位类型描述
     */
    private String seatDescription;

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
        SeatType other = (SeatType) that;
        return (this.getSeatId() == null ? other.getSeatId() == null : this.getSeatId().equals(other.getSeatId()))
            && (this.getSeatName() == null ? other.getSeatName() == null : this.getSeatName().equals(other.getSeatName()))
            && (this.getSeatDescription() == null ? other.getSeatDescription() == null : this.getSeatDescription().equals(other.getSeatDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSeatId() == null) ? 0 : getSeatId().hashCode());
        result = prime * result + ((getSeatName() == null) ? 0 : getSeatName().hashCode());
        result = prime * result + ((getSeatDescription() == null) ? 0 : getSeatDescription().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", seatId=").append(seatId);
        sb.append(", seatName=").append(seatName);
        sb.append(", seatDescription=").append(seatDescription);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package com.scausw215.train.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 车次信息表
 * @TableName train_info
 */
@TableName(value ="train_info")
@Data
public class TrainInfo implements Serializable {
    /**
     * 车次id
     */
    @TableId(type = IdType.AUTO)
    private Long trainId;

    /**
     * 发车时间
     */
    private Date startTime;

    /**
     * 到达时间
     */
    private Date endTime;

    /**
     * 出发地
     */
    private Long startStation;

    /**
     * 目的地
     */
    private Long endStation;

    /**
     * 动车类型
     */
    private Long trainTypeId;

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
        TrainInfo other = (TrainInfo) that;
        return (this.getTrainId() == null ? other.getTrainId() == null : this.getTrainId().equals(other.getTrainId()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getStartStation() == null ? other.getStartStation() == null : this.getStartStation().equals(other.getStartStation()))
            && (this.getEndStation() == null ? other.getEndStation() == null : this.getEndStation().equals(other.getEndStation()))
            && (this.getTrainTypeId() == null ? other.getTrainTypeId() == null : this.getTrainTypeId().equals(other.getTrainTypeId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrainId() == null) ? 0 : getTrainId().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getStartStation() == null) ? 0 : getStartStation().hashCode());
        result = prime * result + ((getEndStation() == null) ? 0 : getEndStation().hashCode());
        result = prime * result + ((getTrainTypeId() == null) ? 0 : getTrainTypeId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", trainId=").append(trainId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", startStation=").append(startStation);
        sb.append(", endStation=").append(endStation);
        sb.append(", trainTypeId=").append(trainTypeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
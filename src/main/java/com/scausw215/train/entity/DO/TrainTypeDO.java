package com.scausw215.train.entity.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 动车类型
 * @TableName train_type
 */
@TableName(value ="train_type")
@Data
public class TrainTypeDO implements Serializable {
    /**
     * 动车id
     */
    @TableId(type = IdType.AUTO)
    private Long trainTypeId;

    /**
     * 动车类型名称
     */
    private String trainName;

    /**
     * 动车类型代码
     */
    private String trainCode;

    /**
     * 动车类型描述
     */
    private String trainDescription;

    /**
     * 一等座座位类型id
     */
    private Long firstSeatTypeId;

    /**
     * 一等座座位数
     */
    private Integer firstSeatNum;

    /**
     * 二等座座位类型id
     */
    private Long secondSeatTypeId;

    /**
     * 二等座座位数
     */
    private Integer secondSeatNum;

    /**
     * 三等座座位类型id
     */
    private Long thirdSeatTypeId;

    /**
     * 三等座座位数
     */
    private Integer thirdSeatNum;

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
        TrainTypeDO other = (TrainTypeDO) that;
        return (this.getTrainTypeId() == null ? other.getTrainTypeId() == null : this.getTrainTypeId().equals(other.getTrainTypeId()))
            && (this.getTrainName() == null ? other.getTrainName() == null : this.getTrainName().equals(other.getTrainName()))
            && (this.getTrainCode() == null ? other.getTrainCode() == null : this.getTrainCode().equals(other.getTrainCode()))
            && (this.getTrainDescription() == null ? other.getTrainDescription() == null : this.getTrainDescription().equals(other.getTrainDescription()))
            && (this.getFirstSeatTypeId() == null ? other.getFirstSeatTypeId() == null : this.getFirstSeatTypeId().equals(other.getFirstSeatTypeId()))
            && (this.getFirstSeatNum() == null ? other.getFirstSeatNum() == null : this.getFirstSeatNum().equals(other.getFirstSeatNum()))
            && (this.getSecondSeatTypeId() == null ? other.getSecondSeatTypeId() == null : this.getSecondSeatTypeId().equals(other.getSecondSeatTypeId()))
            && (this.getSecondSeatNum() == null ? other.getSecondSeatNum() == null : this.getSecondSeatNum().equals(other.getSecondSeatNum()))
            && (this.getThirdSeatTypeId() == null ? other.getThirdSeatTypeId() == null : this.getThirdSeatTypeId().equals(other.getThirdSeatTypeId()))
            && (this.getThirdSeatNum() == null ? other.getThirdSeatNum() == null : this.getThirdSeatNum().equals(other.getThirdSeatNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrainTypeId() == null) ? 0 : getTrainTypeId().hashCode());
        result = prime * result + ((getTrainName() == null) ? 0 : getTrainName().hashCode());
        result = prime * result + ((getTrainCode() == null) ? 0 : getTrainCode().hashCode());
        result = prime * result + ((getTrainDescription() == null) ? 0 : getTrainDescription().hashCode());
        result = prime * result + ((getFirstSeatTypeId() == null) ? 0 : getFirstSeatTypeId().hashCode());
        result = prime * result + ((getFirstSeatNum() == null) ? 0 : getFirstSeatNum().hashCode());
        result = prime * result + ((getSecondSeatTypeId() == null) ? 0 : getSecondSeatTypeId().hashCode());
        result = prime * result + ((getSecondSeatNum() == null) ? 0 : getSecondSeatNum().hashCode());
        result = prime * result + ((getThirdSeatTypeId() == null) ? 0 : getThirdSeatTypeId().hashCode());
        result = prime * result + ((getThirdSeatNum() == null) ? 0 : getThirdSeatNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", trainTypeId=").append(trainTypeId);
        sb.append(", trainName=").append(trainName);
        sb.append(", trainCode=").append(trainCode);
        sb.append(", trainDescription=").append(trainDescription);
        sb.append(", firstSeatTypeId=").append(firstSeatTypeId);
        sb.append(", firstSeatNum=").append(firstSeatNum);
        sb.append(", secondSeatTypeId=").append(secondSeatTypeId);
        sb.append(", secondSeatNum=").append(secondSeatNum);
        sb.append(", thirdSeatTypeId=").append(thirdSeatTypeId);
        sb.append(", thirdSeatNum=").append(thirdSeatNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
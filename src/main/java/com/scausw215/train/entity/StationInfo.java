package com.scausw215.train.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName station_info
 */
@TableName(value ="station_info")
@Data
public class StationInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long stationId;

    /**
     * 
     */
    private String stationName;

    /**
     * 
     */
    private String stationCity;

    /**
     * 
     */
    private String stationProvince;

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
        StationInfo other = (StationInfo) that;
        return (this.getStationId() == null ? other.getStationId() == null : this.getStationId().equals(other.getStationId()))
            && (this.getStationName() == null ? other.getStationName() == null : this.getStationName().equals(other.getStationName()))
            && (this.getStationCity() == null ? other.getStationCity() == null : this.getStationCity().equals(other.getStationCity()))
            && (this.getStationProvince() == null ? other.getStationProvince() == null : this.getStationProvince().equals(other.getStationProvince()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStationId() == null) ? 0 : getStationId().hashCode());
        result = prime * result + ((getStationName() == null) ? 0 : getStationName().hashCode());
        result = prime * result + ((getStationCity() == null) ? 0 : getStationCity().hashCode());
        result = prime * result + ((getStationProvince() == null) ? 0 : getStationProvince().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stationId=").append(stationId);
        sb.append(", stationName=").append(stationName);
        sb.append(", stationCity=").append(stationCity);
        sb.append(", stationProvince=").append(stationProvince);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
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
 * 
 * @author sensnow
 * @TableName station_info
 */
@TableName(value ="station_info")
@Data
public class StationInfoDO implements Serializable {
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

}
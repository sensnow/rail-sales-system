package com.scausw215.train.entity.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.scausw215.train.entity.DO.SeatTypeDO;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 动车类型DTO
 * @author sensnow
 */
@Data
public class TrainTypeDTO implements Serializable {
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
    private SeatTypeDO firstSeatType;

    /**
     * 一等座座位数
     */
    private Integer firstSeatNum;

    /**
     * 二等座座位类型id
     */
    private SeatTypeDO secondSeatType;

    /**
     * 二等座座位数
     */
    private Integer secondSeatNum;

    /**
     * 三等座座位类型id
     */
    private SeatTypeDO thirdSeatType;

    /**
     * 三等座座位数
     */
    private Integer thirdSeatNum;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

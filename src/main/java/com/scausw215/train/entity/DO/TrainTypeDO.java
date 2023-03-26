package com.scausw215.train.entity.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

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

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
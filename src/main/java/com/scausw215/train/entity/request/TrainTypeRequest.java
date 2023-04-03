package com.scausw215.train.entity.request;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;

/**
 * 动车类型请求类
 * @TableName train_type
 */
@Data
public class TrainTypeRequest {

    private Long id;

    /**
     * 动车类型名称
     */
    private String name;

    /**
     * 动车类型代码
     */
    private String code;

    /**
     * 动车类型描述
     */
    private String description;

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

}

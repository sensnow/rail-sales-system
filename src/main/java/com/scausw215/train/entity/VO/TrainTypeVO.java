package com.scausw215.train.entity.VO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 动车类型视图类
 * @author sensnow
 */
@Data
public class TrainTypeVO implements Serializable {
    private Long id;

    /**
     * 动车类型名称
     * 火车|普通动车|高铁动车
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

    @Serial
    private static final long serialVersionUID = 1L;
}

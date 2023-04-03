package com.scausw215.train.entity.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

/**
 * 车次信息表
 * @author sensnow
 * @TableName train_info
 */
@TableName(value ="train_info")
@Data
public class TrainInfoDO implements Serializable {
    /**
     * 车次id
     */
    @TableId(type = IdType.AUTO)
    private Long trainId;

    /**
     * 发车时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 到达时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    /**
     * 一等座价格
     */
    private Integer firstPrice;

    /**
     * 二等座价格
     */
    private Integer secondPrice;

    /**
     * 三等座价格
     */
    private Integer thirdPrice;

    /**
     * 是否可用 0-不可用 1-可用
     */
    private Integer isAvailable;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
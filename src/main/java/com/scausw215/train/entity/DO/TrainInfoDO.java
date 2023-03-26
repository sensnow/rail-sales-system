package com.scausw215.train.entity.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
package com.scausw215.train.entity.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 座位类型表
 * @author sensnow
 */
@TableName(value ="seat_type")
@Data
public class SeatTypeDO implements Serializable {
    /**
     * 座位类型id
     */
    @TableId(type = IdType.AUTO)
    private Long seatId;

    /**
     * 座位类型名称
     */
    private String seatName;

    /**
     * 座位类型描述
     */
    private String seatDescription;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
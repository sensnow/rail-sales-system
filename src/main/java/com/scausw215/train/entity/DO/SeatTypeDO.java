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
 * 座位类型表
 * @author sensnow
 * @TableName seat_type
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
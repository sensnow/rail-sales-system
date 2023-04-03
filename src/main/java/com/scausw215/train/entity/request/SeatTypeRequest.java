package com.scausw215.train.entity.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;

/**
 * 座位类型请求类
 * @author sensnow
 */
@Data
public class SeatTypeRequest {
    /**
     * 座位类型id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 座位类型名称
     */
    private String name;

    /**
     * 座位类型描述
     */
    private String description;

}

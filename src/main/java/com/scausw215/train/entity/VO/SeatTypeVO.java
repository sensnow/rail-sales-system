package com.scausw215.train.entity.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 座位类型视图类
 * @author sensnow
 */
@Data
public class SeatTypeVO implements Serializable {
    /**
     * 座位类型id
     */
    private Long id;

    /**
     * 座位类型名称
     */
    private String name;

    /**
     * 座位类型描述
     */
    private String description;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}

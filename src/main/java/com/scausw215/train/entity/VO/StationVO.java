package com.scausw215.train.entity.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 站点视图类
 */
@Data
public class StationVO implements Serializable {
    /**
     *
     */
    private Long Id;

    /**
     *
     */
    private String Name;

    /**
     *
     */
    private String City;

    /**
     *
     */
    private String Province;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
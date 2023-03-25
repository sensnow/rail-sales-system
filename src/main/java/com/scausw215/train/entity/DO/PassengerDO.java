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
 * 购票人信息表
 * @TableName passenger
 */
@TableName(value ="passenger")
@Data
public class PassengerDO implements Serializable {
    /**
     * 购票人id
     */
    @TableId(type = IdType.AUTO)
    private Long passengerId;

    /**
     * 购票人姓名
     */
    private String passengerName;

    /**
     * 购票人证件类型
     */
    private Integer passengerType;

    /**
     * 证件号号码
     */
    private String passengerCardNumber;

    /**
     * 购票人手机号
     */
    private String passengerPhone;

    /**
     * 所属账号id
     */
    private Long userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
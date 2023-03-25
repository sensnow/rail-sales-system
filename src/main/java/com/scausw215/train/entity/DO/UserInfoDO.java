package com.scausw215.train.entity.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户信息表
 * @author sensnow
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
@AllArgsConstructor
public class UserInfoDO implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Long userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户名
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
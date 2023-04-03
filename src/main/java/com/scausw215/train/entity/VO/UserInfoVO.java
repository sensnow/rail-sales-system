package com.scausw215.train.entity.VO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户信息视图类
 * @author sensnow
 */
@Data
public class UserInfoVO implements Serializable {

    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String account;
    /**
     * 用户权限
     */
    private Integer authority;

    @Serial
    private static final long serialVersionUID = 1L;
}

package com.scausw215.train.entity.VO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户信息VO类
 * @author sensnow
 */
@Data
public class UserInfoVO implements Serializable {

    /**
     * 用户id
     */
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
     * 用户权限
     */
    private Integer userAuthority;

    @Serial
    private static final long serialVersionUID = 1L;
}

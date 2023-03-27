package com.scausw215.train.entity.VO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 乘客信息VO类
 * @author sensnow
 */
@Data
public class PassengerVO implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 证件号
     */
    private String number;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 证据类型，0：身份证，1：护照，2：台湾，3：港澳
     */
    private Long type;
    /**
     * 所属账号id
     */
    private String userId;

    @Serial
    private static final long serialVersionUID = 1L;
}

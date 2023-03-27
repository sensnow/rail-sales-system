package com.scausw215.train.entity.request;

import lombok.Data;

/**
 * 乘客信息请求类
 * @author sensnow
 */
@Data
public class PassengerRequest{
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
        private int type;
        /**
         * 所属账号id
         */
        private Long userId;
}

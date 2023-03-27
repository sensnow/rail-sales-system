package com.scausw215.train.entity.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class StationRequest{
    //前端发送的请求数据
    private String name;
    private String city;
    private String province;
    private Long id;

}
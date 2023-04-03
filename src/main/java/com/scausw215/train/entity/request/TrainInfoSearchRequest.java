package com.scausw215.train.entity.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @description 列车信息查询请求类
 * @author sensnow
 */
@Data
public class TrainInfoSearchRequest {
    /**
     * 起始站id
     */
    private Long startStationId;
    /**
     * 终点站id
     */
    private Long endStationId;
    /**
     * 列车类型
     */
    private String trainName;
    /**
     * 列车类型代码
     */
    private String trainCode;
    /**
     * 出发城市
     */
    private String startCity;
    /**
     * 到达城市
     */
    private String endCity;
    /**
     * 出发时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    /**
     * 到达时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    /**
     * 是否查可用车次
     * 0. 查所有车次
     * 1. 查可用车次
     */
    private int isAvailable;
    /**
     * 模式
     * 用户选择查询方式(只能查可用车次)
     * 1. 按照 起始站id、终点站id、列车类型、出发时间 查询
     * 管理员选择查询方式(可查所有车次)
     * 2. 按照是否为不空值查询
     */
    private int mode;
    /**
     * 页码
     */
    private int page;
    /**
     * 每页大小
     */
    private int size;
}

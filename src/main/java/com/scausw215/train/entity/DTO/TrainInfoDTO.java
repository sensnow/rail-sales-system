package com.scausw215.train.entity.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.scausw215.train.entity.DO.StationInfoDO;
import com.scausw215.train.entity.DO.TrainTypeDO;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 车次信息表
 * @author sensnow
 */
@Data
public class TrainInfoDTO implements Serializable {

    /**
     * 车次id
     */
    @TableId(type = IdType.AUTO)
    private Long trainId;

    /**
     * 发车时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 到达时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 出发地
     */
    private StationInfoDO startStation;

    /**
     * 目的地
     */
    private StationInfoDO endStation;

    /**
     * 动车类型
     */
    private TrainTypeDO trainType;

    /**
     * 一等座现在剩余座位数
     */
    private Integer firstSeatNum;

    /**
     * 二等座现在剩余座位数
     */
    private Integer secondSeatNum;

    /**
     * 三等座现在剩余座位数
     */
    private Integer thirdSeatNum;

    /**
     * 一等座价格
     */
    private Integer firstPrice;

    /**
     * 二等座价格
     */
    private Integer secondPrice;

    /**
     * 三等座价格
     */
    private Integer thirdPrice;
    /**
     * 是否可用 0-不可用 1-可用
     */
    private Integer isAvailable;
    /**
     * 页数
     * */
    private Integer total;
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

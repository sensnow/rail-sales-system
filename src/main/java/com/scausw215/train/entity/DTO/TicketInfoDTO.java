package com.scausw215.train.entity.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.scausw215.train.entity.DO.*;
import lombok.Data;

/**
 * 车票信息表
 * @author sensnow
 */
@TableName(value ="ticket_info")
@Data
public class TicketInfoDTO extends TicketInfoDO {

    /**
     * 车次信息
     */
    private TrainInfoDO trainInfoDO;
    private StationInfoDO startStation;
    private StationInfoDO endStation;
    private SeatTypeDO seatTypeDO;
    private TrainTypeDO trainTypeDO;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
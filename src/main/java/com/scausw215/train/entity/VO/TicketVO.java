package com.scausw215.train.entity.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 车票信息视图类
 *
 * @author sensnow
 */
@Data
public class TicketVO implements java.io.Serializable{

    private Long id;
    /**
     * 车次id
     */
    private Long trainId;
    /**
     * 车厢号
     */
    private Integer carNumber;
    /**
     * 座位类型id
     */
    private Long seatTypeId;
    /**
     * 座位号
     */
    private Integer seatNumber;
    /**
     * 是否已经出售 0-未出售 1-已出售
     */
    private Integer isSold;
    /**
     * 车票价格
     */
    private Integer ticketPrice;
    /**
     * 售票时间
     */

    private LocalDateTime startSaleTime;
    /**
     * 截止售票时间
     */

    private LocalDateTime endSaleTime;
    /**
     * 更新时间
     */

    private LocalDateTime updateTime;

    @Serial
    private static final long serialVersionUID = 1L;
}

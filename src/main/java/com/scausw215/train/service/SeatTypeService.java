package com.scausw215.train.service;

import com.scausw215.train.entity.DO.SeatTypeDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scausw215.train.entity.request.SeatTypeRequest;

import java.util.List;

/**
* @author sensnow
* @description 针对表【seat_type(座位类型表)】的数据库操作Service
* @createDate 2023-03-25 14:10:16
*/
public interface SeatTypeService extends IService<SeatTypeDO> {

    /**
     * 添加座位类型
     * @param seatTypeRequest 座位类型请求类
     * @return 1：成功，0：失败
     */
    int addSeatType(SeatTypeRequest seatTypeRequest);

    /**
     * 删除座位类型
     * @param seatTypeId 座位类型id
     * @return 1：成功，0：失败
     */
    int deleteSeatType(Long seatTypeId);

    /**
     * 更新座位类型
     * @param seatTypeRequest 座位类型请求类
     * @return 1：成功，0：失败
     */
    int updateSeatType(SeatTypeRequest seatTypeRequest);

    /**
     * 获取座位类型
     * @param seatTypeId 座位类型id
     * @return  座位类型
     */
    SeatTypeDO getSeatType(Long seatTypeId);

    /**
     * 获取所有座位类型
     * @return 座位类型列表
     */
    List<SeatTypeDO> getListSeatType();

    /**
     * 检查座位类型数据是否合法
     * @param seatTypeRequest 座位类型请求类
     * @return 合法：座位类型，不合法：抛出异常
     */
    SeatTypeDO checkSeatType(SeatTypeRequest seatTypeRequest);
}

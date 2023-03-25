package com.scausw215.train.mapper;

import com.scausw215.train.entity.SeatType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author sensnow
* @description 针对表【seat_type(座位类型表)】的数据库操作Mapper
* @createDate 2023-03-25 14:10:16
* @Entity com.scausw215.train.entity.SeatType
*/
public interface SeatTypeMapper extends BaseMapper<SeatType> {
    /**
     * 插入座位类型
     * @param seatType 座位类型实体类
     * @return 插入成功的记录数
     */
    int insertSeatType(SeatType seatType);

    /**
     * 根据座位类型id删除座位类型
     * @param id 座位类型id
     * @return 删除成功的记录数
     */
    int deleteSeatTypeById(Integer id);

    /**
     * 更新座位类型信息
     * @param seatType 座位类型实体类
     * @return 更新成功的记录数
     */
    int updateSeatType(SeatType seatType);

    /**
     * 根据座位类型id查询座位类型信息
     * @param id 座位类型id
     * @return 座位类型实体类
     */
    SeatType selectSeatTypeById(Integer id);


}





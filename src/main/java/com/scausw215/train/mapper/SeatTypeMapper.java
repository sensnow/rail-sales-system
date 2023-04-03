package com.scausw215.train.mapper;

import com.scausw215.train.entity.DO.SeatTypeDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author sensnow
* @description 针对表【seat_type(座位类型表)】的数据库操作Mapper
* @createDate 2023-03-25 14:10:16
* @Entity com.scausw215.train.entity.SeatType
*/
@Mapper
public interface SeatTypeMapper extends BaseMapper<SeatTypeDO> {
    /**
     * 插入座位类型
     * @param seatTypeDO 座位类型实体类
     * @return 插入成功的记录数
     */
    int insertSeatType(SeatTypeDO seatTypeDO);

    /**
     * 根据座位类型id删除座位类型
     * @param id 座位类型id
     * @return 删除成功的记录数
     */
    int deleteSeatTypeById(Long id);

    /**
     * 更新座位类型信息
     * @param seatTypeDO 座位类型实体类
     * @return 更新成功的记录数
     */
    int updateSeatType(SeatTypeDO seatTypeDO);

    /**
     * 根据座位类型id查询座位类型信息
     * @param id 座位类型id
     * @return 座位类型实体类
     */
    SeatTypeDO selectSeatTypeById(Long id);

    /**
     * 查询所有座位类型
     * @return 座位类型实体类列表
     */
    List<SeatTypeDO> selectAllSeatType();


}





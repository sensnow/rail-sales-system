package com.scausw215.train.mapper;

import com.scausw215.train.entity.DO.TicketInfoDO;
import com.scausw215.train.entity.DTO.TicketInfoDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
* @author sensnow
* @description 针对表【ticket_info(车票信息表)】的数据库操作Mapper
* @createDate 2023-03-25 14:10:16
* @Entity com.scausw215.train.entity.TicketInfo
*/
@Mapper
public interface TicketInfoMapper extends BaseMapper<TicketInfoDO> {
    /**
     * 增加车票信息
     * @param ticketInfoDO
     * @return
     */
    int insertTicket(TicketInfoDO ticketInfoDO);

    /**
     * 删除车票信息
     * @param id
     * @return
     */
    int deleteTicket(Long id);

    /**
     * 更新车票信息
     * @param id
     * @return
     */
    int updateTicket(Long id);

    /**
     * 查询车票信息
     * @param id
     * @return
     */
    TicketInfoDO selectTicket(Long id);

    int insertAllTicketByTrainInfo(Long trainId, Long trainTypeId, Long startStationId, Long endStationId, Date startTime,int firstPrice,int secondPrice,int thirdPrice);


}





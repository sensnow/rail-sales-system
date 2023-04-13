package com.scausw215.train.service;

import com.scausw215.train.entity.DO.TicketInfoDO;
import com.scausw215.train.entity.DTO.TicketInfoDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
* @author sensnow
* @description 针对表【ticket_info(车票信息表)】的数据库操作Service
* @createDate 2023-03-25 14:10:16
*/
public interface TicketInfoService extends IService<TicketInfoDO> {
    public TicketInfoDTO getByIdWithTrain(Long id);
    public void delete(List<Long> ids);

    // 加入车次的时候 自动生成车票

    /**
     * 增加车票信息
     * @param trainId 车次id
     * @param trainTypeId 车型id
     * @param startStationId 起始站id
     * @param endStationId 终点站id
     * @param startTime     出发时间
     * @param firstPrice   一等座价格
     * @param secondPrice 二等座价格
     * @param thirdPrice 三等座价格
     */
    public void addTicketInfo(Long trainId, Long trainTypeId, Long startStationId, Long endStationId, Date startTime, int firstPrice, int secondPrice, int thirdPrice);
    void buy(Long id,Long passengerId,Long userId);
    void buyPlus(Long trainId,List<Long> passengerId,Long userId);
    List<TicketInfoDTO> getAll(Long startStation, Long endStation,Date startTime,Date endTime);
    TicketInfoDTO getOneById(Long id);


}

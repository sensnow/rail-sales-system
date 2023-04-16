package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.entity.DO.*;
import com.scausw215.train.entity.DTO.TicketInfoDTO;
import com.scausw215.train.entity.Usage.TrainTicketSeatType;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.mapper.*;
import com.scausw215.train.service.TicketInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author sensnow
* @description 针对表【ticket_info(车票信息表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class TicketInfoServiceImpl extends ServiceImpl<TicketInfoMapper, TicketInfoDO>
    implements TicketInfoService {

    @Autowired
    private TrainInfoMapper trainInfoMapper;
    @Autowired
    private TicketInfoMapper ticketInfoMapper;
    @Autowired
    TicketSaleMapper ticketSaleMapper;
    @Autowired
    PassengerMapper passengerMapper;
    @Autowired
    StationInfoMapper stationInfoMapper;
    @Autowired
    TrainTicketSeatTypeMapper trainTicketSeatTypeMapper;
    /**
     * 根据id查询车票信息和对应的车次信息
     * @param id
     * @return
     */
    @Override
    public TicketInfoDTO getByIdWithTrain(Long id) {
        //查询车票信息
        TicketInfoDO ticketInfoDO = this.getById(id);

        TicketInfoDTO ticketInfoDTO = new TicketInfoDTO();
        BeanUtils.copyProperties(ticketInfoDO,ticketInfoDTO);

        //查询对应的车次信息
        LambdaQueryWrapper<TrainInfoDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TrainInfoDO::getTrainId,ticketInfoDO.getTrainId());
        TrainInfoDO trainInfoDO = trainInfoMapper.selectOne(queryWrapper);

        ticketInfoDTO.setTrainInfoDO(trainInfoDO);
        return null;
    }

    /**
     * 删除车票信息(管理员)
     * @param ids
     */
    @Override
    public void delete(List<Long> ids) {
        //条件构造器
        LambdaQueryWrapper<TicketInfoDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TicketInfoDO::getIsSold,1);
        queryWrapper.in(TicketInfoDO::getTicketId,ids);

        int count = (int) this.count(queryWrapper);
        if (count > 0){
            //不能删除抛出异常
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"已经出售，不能删除");
        }
        //可以删除则删除
        this.removeByIds(ids);
    }

    @Override
    public void addTicketInfo(Long trainId, Long trainTypeId, Long startStationId, Long endStationId, Date startTime, int firstPrice, int secondPrice, int thirdPrice) {
        ticketInfoMapper.insertAllTicketByTrainInfo(trainId,trainTypeId,startTime,firstPrice,secondPrice,thirdPrice);
    }

    /**
     * 购票操作
     * 1.设置车票为已售
     * 2，将车票信息放入购票表
     * @param id
     * @param passengerId
     * @param userId
     */

    @Transactional
    public void buy(Long id,Long passengerId,Long userId) {
        //添加到售票表中
        TicketSaleDO ticketSaleDO = new TicketSaleDO();
        if (passengerMapper.selectById(passengerId) == null){
            throw new BusinessException(ErrorCode.DATABASE_ERROR,"没有这个乘客,请先添加乘客信息");
        }
        //根据id查询TicketInfoDO
        TicketInfoDO ticketInfoDO = this.getById(id);
        ticketInfoDO.setIsSold(1);
        this.save(ticketInfoDO);

        //封装TicketSaleDO对象
        ticketSaleDO.setUserId(userId);
        ticketSaleDO.setPassengerId(passengerId);
        ticketSaleDO.setTicketId(ticketInfoDO.getTicketId());
        ticketSaleDO.setPurchasePrice(ticketInfoDO.getTicketPrice());
        ticketSaleDO.setIsRefunded(0);
        ticketSaleDO.setPurchaseTime(LocalDateTime.now());

        ticketSaleMapper.insert(ticketSaleDO);

    }

    /**
     * 购买Plus
     * 根据传入车次id和乘客集合自动分配车票，执行购买操作
     * @param trainId
     * @param passengerId
     * @param userId
     */
    @Transactional
    public void buyPlus(Long trainId, List<Long> passengerId, Long userId,Long seatTypeId) {

        LambdaQueryWrapper<TicketInfoDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TicketInfoDO::getTrainId,trainId);
        queryWrapper.eq(TicketInfoDO::getIsSold,0);
        queryWrapper.orderByAsc(TicketInfoDO::getTicketId);
        queryWrapper.eq(TicketInfoDO::getSeatTypeId,seatTypeId);//筛选特定座位类型

        int count = (int) this.count(queryWrapper);//查询对应车票总数

        if (count < passengerId.stream().count()){
            throw new BusinessException(ErrorCode.DATABASE_ERROR,"当前车次剩余车票不足");
        }

        int passengerLen = (int) passengerId.stream().count();

        LambdaQueryWrapper<PassengerDO> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(PassengerDO::getPassengerId,passengerId);
        if (passengerLen != passengerMapper.selectCount(queryWrapper1)){
            throw new BusinessException(ErrorCode.DATABASE_ERROR,"没有乘客信息");
        }

        List<TicketInfoDO> list = this.list(queryWrapper);
        List<TicketInfoDO> list1 = new ArrayList<>();

        for (int i = 0; i < passengerLen; i++) {
            if (this.IsBought(passengerId.get(i),trainId)){
                list1.add(list.get(i));
            }
        }


        for (TicketInfoDO item:list1){
            //设置车票为已售
            item.setIsSold(1);
            this.updateById(item);
//            this.save(item);

            //添加到售票表中
            TicketSaleDO ticketSaleDO = new TicketSaleDO();

            //封装TicketSaleDO对象
            ticketSaleDO.setUserId(userId);
            ticketSaleDO.setPassengerId(passengerId.get(passengerLen-1));
            ticketSaleDO.setTicketId(item.getTicketId());
            ticketSaleDO.setPurchasePrice(item.getTicketPrice());
            ticketSaleDO.setIsRefunded(0);
            ticketSaleDO.setPurchaseTime(LocalDateTime.now());
            passengerLen--;
            ticketSaleMapper.insert(ticketSaleDO);
        }

    }

    /**
     * 查询方法
     * 1.查询所有车票
     * 2.限定条件查询车票
     * @param startStation
     * @param endStation
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<TrainTicketSeatType> getAll(Long startStation, Long endStation, Date startTime, Date endTime, Long trainId) {

        if(trainId == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"11");
        }
        return trainTicketSeatTypeMapper.getAllByTrainId(trainId);
    }

    /**
     * 根据id查询单个车票信息
     * @param id
     * @return
     */
    @Override
    public TicketInfoDTO getOneById(Long id) {
        //查询
        TicketInfoDO ticketInfoDO = this.getById(id);
        TicketInfoDTO ticketInfoDTO = new TicketInfoDTO();
        //封装
        BeanUtils.copyProperties(ticketInfoDO,ticketInfoDTO);

        ticketInfoDTO.setTrainInfoDO(trainInfoMapper.selectById(ticketInfoDO.getTrainId()));

        return ticketInfoDTO;
    }

    /**
     * 判断是否重复购票
     * @param passengerId
     * @param trainId
     * @return
     */
    @Override
    public Boolean IsBought(Long passengerId, Long trainId) {
        LambdaQueryWrapper<TicketSaleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TicketSaleDO::getPassengerId,passengerId);
        queryWrapper.eq(TicketSaleDO::getIsRefunded,0);

        String passengerName = passengerMapper.selectById(passengerId).getPassengerName();

        for (TicketSaleDO ticketSaleDO : ticketSaleMapper.selectList(queryWrapper)) {
            Long trainId1 = ticketInfoMapper.selectById(ticketSaleDO.getTicketId()).getTrainId();
            if (trainId1 == trainId){
                throw new BusinessException(ErrorCode.DATABASE_ERROR,"请勿为乘客["+passengerName+"]重复购入该车次的车票");
            }
        }

        return true;
    }
}





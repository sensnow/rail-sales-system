package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.entity.DO.TicketInfoDO;
import com.scausw215.train.entity.DO.TrainInfoDO;
import com.scausw215.train.entity.DTO.TicketInfoDTO;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.TicketInfoService;
import com.scausw215.train.mapper.TicketInfoMapper;
import com.scausw215.train.service.TrainInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author sensnow
* @description 针对表【ticket_info(车票信息表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class TicketInfoServiceImpl extends ServiceImpl<TicketInfoMapper, TicketInfoDO>
    implements TicketInfoService {

    @Autowired
    private TrainInfoService trainInfoService;

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
        TrainInfoDO trainInfoDO = trainInfoService.getOne(queryWrapper);
        ticketInfoDTO.setTrainInfoDO(trainInfoDO);
        return null;
    }

    /**
     * 删除车票信息
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

}





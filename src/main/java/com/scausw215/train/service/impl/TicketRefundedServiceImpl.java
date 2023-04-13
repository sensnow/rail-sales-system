package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.constant.UserInfoConstant;
import com.scausw215.train.entity.DO.TicketRefundedDO;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.entity.DTO.TicketRefundedDTO;
import com.scausw215.train.entity.request.TicketRefundedRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.mapper.PassengerMapper;
import com.scausw215.train.mapper.TicketInfoMapper;
import com.scausw215.train.service.TicketRefundedService;
import com.scausw215.train.mapper.TicketRefundedMapper;
import com.scausw215.train.utils.RequestToDoEntityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author sensnow
* @description 针对表【ticket_refunded(退票信息表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class TicketRefundedServiceImpl extends ServiceImpl<TicketRefundedMapper, TicketRefundedDO>
    implements TicketRefundedService{
    @Autowired
    private PassengerMapper passengerMapper;
    @Autowired
    private TicketInfoMapper ticketInfoMapper;
    /**
     * 新增退票信息
     * @param ticketRefundedRequest
     * @param request
     * @return
     */
    @Override
    public TicketRefundedDO addTicketRefunded(TicketRefundedRequest ticketRefundedRequest, HttpServletRequest request) {
        UserInfoDO userInfoDO = (UserInfoDO) request.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        ticketRefundedRequest.setUserId(userInfoDO.getUserId());
        ticketRefundedRequest.setRefundedTime(LocalDateTime.now());
        TicketRefundedDO ticketRefundedDO = RequestToDoEntityUtils.toTicketRefundedDO(ticketRefundedRequest);
        this.save(ticketRefundedDO);
        return ticketRefundedDO;
    }

    /**
     * 删除退票信息
     * @param ids
     */
    @Override
    public void delete(List<Long> ids) {
        //条件构造器
        LambdaQueryWrapper<TicketRefundedDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(TicketRefundedDO::getRefundedId,ids);
        int count = (int) this.count(queryWrapper);
        if (count == 0){
            //没有要删除的数据
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"找不到退票信息");
        }
        this.removeByIds(ids);
    }

    /**
     * 更新退票信息
     * @param ticketRefundedRequest
     * @param request
     */
    @Override
    public void update(TicketRefundedRequest ticketRefundedRequest, HttpServletRequest request) {
        if (StringUtils.isBlank(String.valueOf(this.getById(ticketRefundedRequest.getRefundedId())))){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"找不到退票信息");
        }
        UserInfoDO userInfoDO = (UserInfoDO) request.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        ticketRefundedRequest.setUserId(userInfoDO.getUserId());
        ticketRefundedRequest.setRefundedTime(LocalDateTime.now());
        TicketRefundedDO ticketRefundedDO = RequestToDoEntityUtils.toTicketRefundedDO(ticketRefundedRequest);
        this.updateById(ticketRefundedDO);
    }

    /**
     * 根据id单值查询
     * @param id
     * @return
     */
    @Override
    public TicketRefundedDTO getOneById(Long id) {
        TicketRefundedDO ticketRefundedDO = this.getById(id);
        TicketRefundedDTO ticketRefundedDTO = new TicketRefundedDTO();
        BeanUtils.copyProperties(ticketRefundedDO,ticketRefundedDTO);
        ticketRefundedDTO.setPassengerDO(passengerMapper.selectById(ticketRefundedDO.getPassengerId()));
        ticketRefundedDTO.setTicketInfo(ticketInfoMapper.selectById(ticketRefundedDO.getTicketId()));
        return ticketRefundedDTO;
    }

    /**
     * 获取所有退票信息
     * @return
     */
    @Override
    public List<TicketRefundedDTO> getAll() {

        LambdaQueryWrapper<TicketRefundedDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(TicketRefundedDO::getRefundedTime);
        List<TicketRefundedDTO> ticketRefundedDTOS = this.list(queryWrapper).stream().map((item) -> {

            TicketRefundedDTO ticketRefundedDTO = new TicketRefundedDTO();
            BeanUtils.copyProperties(item,ticketRefundedDTO);

            ticketRefundedDTO.setPassengerDO(passengerMapper.selectById(item.getPassengerId()));
            ticketRefundedDTO.setTicketInfo(ticketInfoMapper.selectById(item.getTicketId()));

            return ticketRefundedDTO;

        }).collect(Collectors.toList());
        return ticketRefundedDTOS;
    }
}





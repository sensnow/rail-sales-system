package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.entity.DTO.TicketInfoDTO;
import com.scausw215.train.service.TicketInfoService;
import com.scausw215.train.mapper.TicketInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author sensnow
* @description 针对表【ticket_info(车票信息表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class TicketInfoServiceImpl extends ServiceImpl<TicketInfoMapper, TicketInfoDTO>
    implements TicketInfoService {

}





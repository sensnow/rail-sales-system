package com.scausw215.train.service;

import com.scausw215.train.entity.DO.TicketInfoDO;
import com.scausw215.train.entity.DTO.TicketInfoDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author sensnow
* @description 针对表【ticket_info(车票信息表)】的数据库操作Service
* @createDate 2023-03-25 14:10:16
*/
public interface TicketInfoService extends IService<TicketInfoDO> {
    public TicketInfoDTO getByIdWithTrain(Long id);
    public void delete(List<Long> ids);

}

package com.scausw215.train.mapper;

import com.scausw215.train.entity.DTO.TicketInfoDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author sensnow
* @description 针对表【ticket_info(车票信息表)】的数据库操作Mapper
* @createDate 2023-03-25 14:10:16
* @Entity com.scausw215.train.entity.TicketInfo
*/
@Mapper
public interface TicketInfoMapper extends BaseMapper<TicketInfoDTO> {


}





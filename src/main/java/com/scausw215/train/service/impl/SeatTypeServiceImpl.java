package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.entity.SeatType;
import com.scausw215.train.service.SeatTypeService;
import com.scausw215.train.mapper.SeatTypeMapper;
import org.springframework.stereotype.Service;

/**
* @author sensnow
* @description 针对表【seat_type(座位类型表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class SeatTypeServiceImpl extends ServiceImpl<SeatTypeMapper, SeatType>
    implements SeatTypeService {

}





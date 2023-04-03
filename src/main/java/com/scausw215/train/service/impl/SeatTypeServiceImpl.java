package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.entity.DO.SeatTypeDO;
import com.scausw215.train.entity.request.SeatTypeRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.SeatTypeService;
import com.scausw215.train.mapper.SeatTypeMapper;
import com.scausw215.train.utils.RequestToDoEntityUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author sensnow
* @description 针对表【seat_type(座位类型表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class SeatTypeServiceImpl extends ServiceImpl<SeatTypeMapper, SeatTypeDO>
    implements SeatTypeService {

    @Resource
    SeatTypeMapper seatTypeMapper;
    @Override
    public int addSeatType(SeatTypeRequest seatTypeRequest) {
        // 检查参数
        SeatTypeDO seatTypeDO = checkSeatType(seatTypeRequest);
        // 增加数据库
        int insert = seatTypeMapper.insertSeatType(seatTypeDO);
        // 检查是否增加成功
        if(insert == 0)
        {
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "座位类型增加失败");
        }
        return insert;
    }

    @Override
    public int deleteSeatType(Long seatTypeId) {
        // 检查参数
        if(seatTypeId == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "座位类型id为空");
        }
        // 删除数据库
        int delete = seatTypeMapper.deleteSeatTypeById(seatTypeId);
        // 检查是否删除成功
        if(delete == 0)
        {
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "座位类型删除失败");
        }
        // 返回删除的记录数
        return delete;
    }

    @Override
    public int updateSeatType(SeatTypeRequest seatTypeRequest) {
        // 检查参数
        SeatTypeDO seatTypeDO = checkSeatType(seatTypeRequest);
        // 更新数据库
        int update = seatTypeMapper.updateSeatType(seatTypeDO);
        // 检查是否更新成功
        if(update == 0)
        {
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "座位类型更新失败");
        }
        return update;

    }

    @Override
    public SeatTypeDO getSeatType(Long seatTypeId) {
        // 检查参数
        if(seatTypeId == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "座位类型id为空");
        }
        // 查询数据库
        SeatTypeDO seatTypeDO = seatTypeMapper.selectSeatTypeById(seatTypeId);
        // 检查是否查询成功
        if(seatTypeDO == null)
        {
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "座位类型查询失败");
        }
        return seatTypeDO;
    }

    @Override
    public List<SeatTypeDO> getListSeatType() {
        return seatTypeMapper.selectAllSeatType();
    }

    @Override
    public SeatTypeDO checkSeatType(SeatTypeRequest seatTypeRequest) {
        if(StringUtils.isAnyBlank(seatTypeRequest.getName(), seatTypeRequest.getDescription()))
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "座位类型名称或描述为空");
        }
        return RequestToDoEntityUtils.toSeatTypeDO(seatTypeRequest);
    }


}





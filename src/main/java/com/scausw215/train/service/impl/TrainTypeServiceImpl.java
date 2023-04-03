package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.entity.DO.TrainTypeDO;
import com.scausw215.train.entity.request.TrainTypeRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.TrainTypeService;
import com.scausw215.train.mapper.TrainTypeMapper;
import com.scausw215.train.utils.RequestToDoEntityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author sensnow
* @description 针对表【train_type(动车类型)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
@Slf4j
public class TrainTypeServiceImpl extends ServiceImpl<TrainTypeMapper, TrainTypeDO>
    implements TrainTypeService{

    @Resource
    TrainTypeMapper trainTypeMapper;
    @Resource(name = "trainNameList")
    List<String> trainNameList;

    @Override
    public int addTrainType(TrainTypeRequest trainTypeRequest) {
        TrainTypeDO trainTypeDO = checkTrainTypeInfo(trainTypeRequest);
        if(isTrainTypeExist(trainTypeDO.getTrainCode())) {
            log.error("列车类型已存在 {}", trainTypeRequest);
            throw new BusinessException(ErrorCode.ENTITY_EXIST, "列车类型已存在");
        }
        // 插入数据库
        int result = trainTypeMapper.insertTrainType(trainTypeDO);
        if (result != 1) {
            log.error("添加列车类型失败，数据库操作失败，参数：{}", trainTypeRequest);
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "数据库操作失败");
        }
        return result;
    }

    @Override
    public int deleteTrainType(Long trainTypeId) {
        if(trainTypeId == null) {
            log.error("列车类型id为空");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        // 删除数据库
        int result = trainTypeMapper.deleteTrainTypeById(trainTypeId);
        if (result != 1) {
            log.error("删除列车类型失败，数据库操作失败，参数：{}", trainTypeId);
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "数据库操作失败");
        }
        return result;
    }

    @Override
    public int updateTrainType(TrainTypeRequest trainTypeRequest) {
        TrainTypeDO trainTypeDO = checkTrainTypeInfo(trainTypeRequest);
        if(isTrainTypeExist(trainTypeDO.getTrainCode()))
        {
            log.error("列车类型已存在 {}", trainTypeRequest);
            throw new BusinessException(ErrorCode.ENTITY_EXIST, "列车类型已存在");
        }
        // 更新数据库
        int result = trainTypeMapper.updateTrainType(trainTypeDO);
        if (result != 1) {
            log.error("更新列车类型失败，数据库操作失败，参数：{}", trainTypeRequest);
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "数据库操作失败");
        }
        return result;
    }

    @Override
    public TrainTypeDO getTrainType(Long trainTypeId) {
        if(trainTypeId == null) {
            log.error("列车类型id为空");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        TrainTypeDO trainTypeDO = trainTypeMapper.selectTrainTypeById(trainTypeId);
        if(trainTypeDO == null) {
            log.error("列车类型不存在 {}", trainTypeId);
            throw new BusinessException(ErrorCode.ENTITY_NOT_EXIST, "列车类型不存在");
        }
        return trainTypeDO;
    }

    @Override
    public List<TrainTypeDO> getListTrainType() {
        return trainTypeMapper.selectAllTrainType();
    }

    @Override
    public boolean isTrainTypeExist(String trainTypeCode) {
        TrainTypeDO trainTypeDO = trainTypeMapper.selectTrainTypeByTrainCode(trainTypeCode);
        return trainTypeDO != null;
    }

    @Override
    public TrainTypeDO checkTrainTypeInfo(TrainTypeRequest trainTypeRequest) {
        if(StringUtils.isAnyEmpty(trainTypeRequest.getCode(), trainTypeRequest.getName() )|| trainTypeRequest.getFirstSeatTypeId() == null|| trainTypeRequest.getSecondSeatTypeId() == null || trainTypeRequest.getThirdSeatTypeId() == null ||
        trainTypeRequest.getFirstSeatNum() == null || trainTypeRequest.getSecondSeatNum() == null || trainTypeRequest.getThirdSeatNum() == null) {
            log.error("列车数据参数为空 {}", trainTypeRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        // 判断列车类型是否存在
        if(!trainNameList.contains(trainTypeRequest.getName())) {
            log.error("列车类型不存在 {}", trainTypeRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "列车类型不存在");
        }
        return RequestToDoEntityUtils.toTrainTypeDO(trainTypeRequest);
    }
}





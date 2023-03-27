package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.entity.DO.StationInfoDO;
import com.scausw215.train.service.StationInfoService;
import com.scausw215.train.mapper.StationInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author sensnow
* @description 针对表【station_info】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class StationInfoServiceImpl extends ServiceImpl<StationInfoMapper, StationInfoDO>
    implements StationInfoService {
    @Autowired
    StationInfoMapper stationInfoMapper;

    @Override
    public StationInfoDO getStationById(String id) {
        return stationInfoMapper.selectStationInfoById(id);
    }
    @Override
    public StationInfoDO getStationByStationName(String stationName) {

        return stationInfoMapper.selectStationInfoByStationName(stationName);
    }

    @Override
    public Integer updateStation(StationInfoDO stationInfoDO) {
        return stationInfoMapper.updateStation(stationInfoDO);
    }

    @Override
    public Long getStationIdByName(String name) {
        return stationInfoMapper.getStationIdByName(name);
    }

    @Override
    public Integer insertStation(StationInfoDO stationInfoDO) {
        return stationInfoMapper.insertStation(stationInfoDO);
    }

    @Override
    public Integer delete(String id) {
        return stationInfoMapper.deleteStationInfoById(id);
    }


}





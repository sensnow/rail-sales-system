package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.entity.DO.StationInfoDO;
import com.scausw215.train.service.StationInfoService;
import com.scausw215.train.mapper.StationInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return stationInfoMapper.selectStationById(id);
    }
    @Override
    public StationInfoDO getStationByStationName(String stationName) {

        return stationInfoMapper.selectStationByName(stationName);
    }

    @Override
    public Integer updateStation(StationInfoDO stationInfoDO) {
        return stationInfoMapper.updateStation(stationInfoDO);
    }

    @Override
    public Long getStationIdByName(String name) {
        return stationInfoMapper.getIdByName(name);
    }

    @Override
    public Integer insertStation(StationInfoDO stationInfoDO) {
        return stationInfoMapper.insertStation(stationInfoDO);
    }

    @Override
    public Integer delete(String id) {
        return stationInfoMapper.deleteStationInfoById(id);
    }

    @Override
    public List<StationInfoDO> getAll() {
        return stationInfoMapper.getAll();
    }

    @Override
    public List<StationInfoDO> getByCity(String city) {
        return stationInfoMapper.getByCity(city);
    }

    @Override
    public List<StationInfoDO> getByProvince(String province) {
        return stationInfoMapper.getByProvince(province);
    }
}





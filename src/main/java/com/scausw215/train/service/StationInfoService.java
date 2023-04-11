package com.scausw215.train.service;

import com.scausw215.train.entity.DO.StationInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scausw215.train.entity.VO.StationVO;

import java.util.List;

/**
* @author sensnow
* @description 针对表【station_info】的数据库操作Service
* @createDate 2023-03-25 14:10:16
*/
public interface StationInfoService extends IService<StationInfoDO> {
    StationInfoDO getStationById(Long id);
    StationInfoDO getStationByStationName(String stationName);
    Integer updateStation(StationInfoDO stationInfoDO);
    Long getStationIdByName(String Name);
    Integer insertStation(StationInfoDO stationInfoDO);
    Integer delete(Long id);
    List<StationInfoDO> getAll();
    List<StationInfoDO> getByCity(String city);
    List<StationInfoDO> getByProvince(String province);
    void deletePlus(List<Long> ids);

}

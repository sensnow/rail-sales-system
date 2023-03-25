package com.scausw215.train.mapper;

import com.scausw215.train.entity.StationInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author sensnow
* @description 针对表【station_info】的数据库操作Mapper
* @createDate 2023-03-25 14:10:16
* @Entity com.scausw215.train.entity.StationInfo
*/
public interface StationInfoMapper extends BaseMapper<StationInfo> {

    /**
     * 插入一条站点信息
     * @param stationInfo 站点信息
     * @return 插入结果
     */
    int insertStationInfo(StationInfo stationInfo);

    /**
     * 根据站点id删除一条站点信息
     * @param stationId 站点id
     * @return 删除结果
     */
    int deleteStationInfoById(String stationId);

    /**
     * 更新一条站点信息
     * @param stationInfo 站点信息
     * @return 更新结果
     */
    int updateStationInfo(StationInfo stationInfo);

    /**
     * 根据站点id查询一条站点信息
     * @param stationId 站点id
     * @return 站点信息
     */
    StationInfo selectStationInfoById(String stationId);


}





package com.scausw215.train.mapper;

import com.scausw215.train.entity.DO.StationInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sensnow
* @description 针对表【station_info】的数据库操作Mapper
* @createDate 2023-03-25 14:10:16
* @Entity com.scausw215.train.entity.StationInfo
*/
@Mapper
public interface StationInfoMapper extends BaseMapper<StationInfoDO> {

    /**
     * 插入一条站点信息
     * @param stationInfoDO 站点信息
     * @return 插入结果
     */
    int insertStationInfo(StationInfoDO stationInfoDO);

    /**
     * 根据站点id删除一条站点信息
     * @param stationId 站点id
     * @return 删除结果
     */
    int deleteStationInfoById(String stationId);

    /**
     * 更新一条站点信息
     *
     *        站点信息
     * @param stationNewName
     * @param stationNewCity
     * @param stationNewProvince
     * @return 更新结果
     */
    Boolean updateStationInfo(String stationName,String stationNewName,String stationNewCity,String stationNewProvince);

    /**
     * 根据站点id查询一条站点信息
     * @param stationId 站点id
     * @return 站点信息
     */
    StationInfoDO selectStationInfoById(String stationId);
    StationInfoDO selectStationInfoByStationName(String stationName);


}





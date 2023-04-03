package com.scausw215.train.mapper;

import com.scausw215.train.entity.DO.StationInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    int insertStation(StationInfoDO stationInfoDO);

    /**
     * 根据站点id删除一条站点信息
     * @param stationId 站点id
     * @return 删除结果
     */
    int deleteStationInfoById(Long stationId);

    /**
     * 更新一条站点信息
     *
     *
     * @return 更新结果
     */
    Integer updateStation(StationInfoDO stationInfoDO);

    /**
     * 根据站点id查询一条站点信息
     * @param stationId 站点id
     * @return 站点信息
     */
    StationInfoDO selectStationById(Long stationId);
    StationInfoDO selectStationByName(String stationName);

    Long getIdByName(String name);
    List<StationInfoDO> getAll();

    List<StationInfoDO> getByCity(String city);
    List<StationInfoDO> getByProvince(String province);


}





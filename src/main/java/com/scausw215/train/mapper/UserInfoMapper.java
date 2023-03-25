package com.scausw215.train.mapper;

import com.scausw215.train.entity.DO.UserInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author sensnow
* @description 针对表【user_info(用户信息表)】的数据库操作Mapper
* @createDate 2023-03-25 14:10:16
* @Entity com.scausw215.train.entity.UserInfo
*/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoDO> {
    /**
     * 插入用户信息
     * @param userInfoDO 用户信息
     * @return 插入结果
     */
    int insertUserInfo(UserInfoDO userInfoDO);

    /**
     * 根据用户ID删除用户信息
     * @param userId 用户ID
     * @return 删除结果
     */
    int deleteUserInfoById(String userId);

    /**
     * 更新用户信息
     * @param userInfoDO 用户信息
     * @return 更新结果
     */
    int updateUserInfo(UserInfoDO userInfoDO);

    /**
     * 根据用户ID查询用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    UserInfoDO selectUserInfoById(String userId);

    /**
     * 根据用户名查询用户信息
     * @param userAccount 账号
     * @return 用户信息
     */
    UserInfoDO selectUserInfoByAccount(String userAccount);


}





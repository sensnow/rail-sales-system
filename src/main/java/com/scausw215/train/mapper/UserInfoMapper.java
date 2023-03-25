package com.scausw215.train.mapper;

import com.scausw215.train.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author sensnow
* @description 针对表【user_info(用户信息表)】的数据库操作Mapper
* @createDate 2023-03-25 14:10:16
* @Entity com.scausw215.train.entity.UserInfo
*/
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    /**
     * 插入用户信息
     * @param userInfo 用户信息
     * @return 插入结果
     */
    int insertUserInfo(UserInfo userInfo);

    /**
     * 根据用户ID删除用户信息
     * @param userId 用户ID
     * @return 删除结果
     */
    int deleteUserInfoById(String userId);

    /**
     * 更新用户信息
     * @param userInfo 用户信息
     * @return 更新结果
     */
    int updateUserInfo(UserInfo userInfo);

    /**
     * 根据用户ID查询用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    UserInfo selectUserInfoById(String userId);

}





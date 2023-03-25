package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.entity.UserInfo;
import com.scausw215.train.service.UserInfoService;
import com.scausw215.train.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author sensnow
* @description 针对表【user_info(用户信息表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}





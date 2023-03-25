package com.scausw215.train.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.entity.request.UserLoginRequest;
import com.scausw215.train.entity.request.UserRegisterRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.UserInfoService;
import com.scausw215.train.mapper.UserInfoMapper;
import com.scausw215.train.utils.ResultUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
* @author sensnow
* @description 针对表【user_info(用户信息表)】的数据库操作Service实现
* @createDate 2023-03-25 14:10:16
*/
@Service
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoDO>
    implements UserInfoService{

    /**
     * 加密盐值
     */
    private static final String SALT = "215";

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Result<String> register(UserRegisterRequest userRegisterRequest) {
        // 参数校验
        if(StringUtils.isAnyBlank(userRegisterRequest.getAccount(),userRegisterRequest.getPassword(),userRegisterRequest.getCheckPassword())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名或密码不能为空");
        }
        // 用户名长度限制
        if(userRegisterRequest.getAccount().length() < 6)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名过短");
        }
        // 用户名长度限制
        if(userRegisterRequest.getAccount().length() > 16)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名过长");
        }
        // 密码一致性校验
        if(!userRegisterRequest.getPassword().equals(userRegisterRequest.getCheckPassword()))
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"两次密码不一致");
        }
        // 密码长度限制
        if(userRegisterRequest.getPassword().length() < 6)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码过短");
        }
        // 密码长度限制
        if(userRegisterRequest.getPassword().length() > 16)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码过长");
        }
        // 用户名不能重复
        if(userInfoMapper.selectUserInfoByAccount(userRegisterRequest.getAccount()) != null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名已存在");
        }
        // md5密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT+userRegisterRequest.getPassword()).getBytes());
        // 保存用户信息
        UserInfoDO userInfoDO = new UserInfoDO(null, userRegisterRequest.getAccount(), userRegisterRequest.getAccount(), encryptPassword);
        int i = userInfoMapper.insertUserInfo(userInfoDO);
        if(i == 0)
        {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"系统错误，注册失败");
        }
        log.info("用户注册成功，用户名：{}",userRegisterRequest.getAccount());
        return ResultUtils.success("注册成功");
    }

    @Override
    public Result login(UserLoginRequest userLoginRequest) {
        return null;
    }
}





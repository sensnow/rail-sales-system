package com.scausw215.train.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.common.Result;
import com.scausw215.train.constant.UserInfoConstant;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.entity.request.UserLoginRequest;
import com.scausw215.train.entity.request.UserRegisterRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.UserInfoService;
import com.scausw215.train.mapper.UserInfoMapper;
import com.scausw215.train.utils.ResultUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    public long register(UserRegisterRequest userRegisterRequest) {
        // 参数校验
        if(StringUtils.isAnyBlank(userRegisterRequest.getAccount(),userRegisterRequest.getPassword(),userRegisterRequest.getCheckPassword())){
            log.error("用户注册失败，用户名或密码不能为空，用户名：{}",userRegisterRequest.getAccount());
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名或密码不能为空");
        }
        // 用户名长度限制
        if(userRegisterRequest.getAccount().length() < 4)
        {
            log.error("用户注册失败，用户名过短，用户名：{}",userRegisterRequest.getAccount());
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名过短");
        }
        // 用户名长度限制
        if(userRegisterRequest.getAccount().length() > 10)
        {
            log.error("用户注册失败，用户名过长，用户名：{}",userRegisterRequest.getAccount());
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名过长");
        }
        // 密码一致性校验
        if(!userRegisterRequest.getPassword().equals(userRegisterRequest.getCheckPassword()))
        {
            log.error("用户注册失败，两次密码不一致，用户名：{}",userRegisterRequest.getAccount());
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"两次密码不一致");
        }
        // 密码长度限制
        if(userRegisterRequest.getPassword().length() < 8)
        {
            log.error("用户注册失败，密码过短，用户名：{}",userRegisterRequest.getAccount());
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码过短");
        }
        // 密码长度限制
        if(userRegisterRequest.getPassword().length() > 16)
        {
            log.error("用户注册失败，密码过长，用户名：{}",userRegisterRequest.getAccount());
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码过长");
        }
        // 用户名不能重复
        if(userInfoMapper.selectUserInfoByAccount(userRegisterRequest.getAccount()) != null)
        {
            log.error("用户注册失败，用户名已存在，用户名：{}",userRegisterRequest.getAccount());
            throw new BusinessException(ErrorCode.USER_EXIST,"用户名已存在");
        }
        // md5密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT+userRegisterRequest.getPassword()).getBytes());
        // 保存用户信息
        UserInfoDO userInfoDO = new UserInfoDO(null, userRegisterRequest.getAccount(), userRegisterRequest.getAccount(), encryptPassword,0);
        int i = userInfoMapper.insertUserInfo(userInfoDO);
        if(i == 0)
        {
            log.error("用户注册失败，用户名：{}",userRegisterRequest.getAccount());
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"系统错误，注册失败");
        }
        log.info("用户注册成功，用户名：{}",userRegisterRequest.getAccount());
        return i;
    }

    @Override
    public UserInfoDO login(UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest) {
        // 参数校验
        if(StringUtils.isAnyBlank(userLoginRequest.getAccount(),userLoginRequest.getPassword())){
            log.error("用户登录失败，用户名或密码不能为空，用户名：{}",userLoginRequest.getAccount());
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名或密码不能为空");
        }
        // 查询用户信息
        UserInfoDO userInfoDO = userInfoMapper.selectUserInfoByAccount(userLoginRequest.getAccount());
        if(userInfoDO == null)
        {
            log.error("用户登录失败，用户名不存在，用户名：{}",userLoginRequest.getAccount());
            throw new BusinessException(ErrorCode.NOT_FOUND,"用户名不存在");
        }
        // md5密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT+userLoginRequest.getPassword()).getBytes());
        // 密码校验
        if(!userInfoDO.getUserPassword().equals(encryptPassword))
        {
            log.error("用户登录失败，密码错误，用户名：{}",userLoginRequest.getAccount());
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码错误");
        }
        // 保存用户信息到session
        httpServletRequest.getSession().setAttribute(UserInfoConstant.USER_INFO_STATE,userInfoDO);
        log.info("用户登录成功，用户名：{}",userLoginRequest.getAccount());
        return userInfoDO;
    }

    @Override
    public int logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute(UserInfoConstant.USER_INFO_STATE);
        return 1;
    }


}





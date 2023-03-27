package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.constant.UserInfoConstant;
import com.scausw215.train.entity.DO.PassengerDO;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.entity.request.PassengerRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.PassengerService;
import com.scausw215.train.mapper.PassengerMapper;
import com.scausw215.train.utils.RequestToDoEntityUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author sensnow
 * @description 针对表【passenger(购票人信息表)】的数据库操作Service实现
 * @createDate 2023-03-25 14:10:16
 */
@Service
@Slf4j
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, PassengerDO>
        implements PassengerService {

    @Resource
    PassengerMapper passengerMapper;

    @Override
    public int addPassenger(PassengerRequest passengerRequest, HttpServletRequest httpServletRequest) {
        // Passenger数据校验
        // 看看是否都不是空
        if (StringUtils.isAnyEmpty(passengerRequest.getName(),passengerRequest.getNumber(),passengerRequest.getPhone())) {
            log.error("添加购票人失败，参数错误，参数：{}", passengerRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        // 看看手机号是否合法
        String phoneRegex = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";
        if (!Pattern.matches(phoneRegex, passengerRequest.getPhone())) {
            log.error("添加购票人失败，手机号不合法，参数：{}", passengerRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "手机号不合法");
        }
        // 证件类型只能是0或1
        if (passengerRequest.getType() != 0 && passengerRequest.getType() != 1) {
            log.error("添加购票人失败，证件类型不合法，参数：{}", passengerRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "证件类型不合法");
        }
        // 看看证件号是否合法
        String sfzRegex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
        String hzRegex = "^1[45][0-9]{7}|([P|p|S|s]\\d{7})|([S|s|G|g]\\d{8})|([Gg|Tt|Ss|Ll|Qq|Dd|Aa|Ff]\\d{8})|([H|h|M|m]\\d{8,10})$";
        if (passengerRequest.getType() == 0 && !Pattern.matches(sfzRegex, passengerRequest.getNumber())) {
            log.error("添加购票人失败，身份证号不合法，参数：{}", passengerRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "身份证号不合法");
        } else if (passengerRequest.getType() == 1 && !Pattern.matches(hzRegex, passengerRequest.getNumber())) {
            log.error("添加购票人失败，护照号不合法，参数：{}", passengerRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "护照号不合法");
        }
        // 根据账号id和身份证查看是否已经存在该购票人
        PassengerDO checkPassenger = passengerMapper.selectPassengerByUserIdAndPassengerCardNumber(passengerRequest.getUserId(), passengerRequest.getNumber());
        if (checkPassenger != null) {
            log.error("添加购票人失败，该购票人已存在，参数：{}", passengerRequest);
            throw new BusinessException(ErrorCode.EXIST_PASSENGER, "该购票人已存在");
        }
        // 检测登录号是不是插入购票人的用户
        UserInfoDO user = (UserInfoDO) httpServletRequest.getSession().getAttribute(UserInfoConstant.USER_INFO_STATE);
        Long userId = user.getUserId();
        if (!userId.equals(passengerRequest.getUserId())) {
            log.error("添加购票人失败，登录号不合法，参数：{}", passengerRequest);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "登录号不合法");
        }
        // request转换为DO
        PassengerDO passengerDO = RequestToDoEntityUtils.toPassengerDO(passengerRequest);
        // 插入数据库
        int result = passengerMapper.insertPassenger(passengerDO);
        if (result != 1) {
            log.error("添加购票人失败，数据库操作失败，参数：{}", passengerRequest);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库操作失败");
        }
        return result;
    }
    @Override
    public int deletePassenger(Long passengerId, HttpServletRequest httpServletRequest) {
        return 0;
    }

    @Override
    public int updatePassenger(PassengerRequest passengerRequest, HttpServletRequest httpServletRequest) {
        return 0;
    }

    @Override
    public PassengerDO getPassengerByPassengerId(Long passengerId, HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public List<PassengerDO> getPassengersByUserId(Long userId, HttpServletRequest httpServletRequest) {
        return null;
    }
}





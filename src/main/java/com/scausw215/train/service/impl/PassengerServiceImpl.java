package com.scausw215.train.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scausw215.train.common.ErrorCode;
import com.scausw215.train.constant.UserInfoConstant;
import com.scausw215.train.entity.DO.PassengerDO;
import com.scausw215.train.entity.DO.UserInfoDO;
import com.scausw215.train.entity.VO.PassengerVO;
import com.scausw215.train.entity.request.PassengerRequest;
import com.scausw215.train.exception.BusinessException;
import com.scausw215.train.service.PassengerService;
import com.scausw215.train.mapper.PassengerMapper;
import com.scausw215.train.utils.CheckUtils;
import com.scausw215.train.utils.RequestToDoEntityUtils;
import com.scausw215.train.utils.ToSafetyEntityUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    /**
     * 增加购票人
     * @param passengerRequest 购票人信息
     * @param httpServletRequest http请求
     * @return 添加结果 1成功 0失败
     */
    @Override
    public int addPassenger(PassengerRequest passengerRequest, HttpServletRequest httpServletRequest) {
        // Passenger数据校验
        PassengerDO passengerDO = checkPassengerRequestInfo(passengerRequest, httpServletRequest);
        // 检测登录态
        CheckUtils.isLoginStatusLegal(passengerDO.getUserId(),httpServletRequest);
        // 检测是否已经存在该购票人
        isExistPassenger(passengerDO.getUserId(),passengerDO.getPassengerCardNumber());
        // 插入数据库
        int result = passengerMapper.insertPassenger(passengerDO);
        if (result != 1) {
            log.error("添加购票人失败，数据库操作失败，参数：{}", passengerRequest);
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "数据库操作失败");
        }
        log.info("添加购票人成功，参数：{}", passengerRequest);
        return result;
    }

    /**
     * 根据购票人id删除购票人
     * @param passengerId 购票人id
     * @param httpServletRequest http请求
     * @return 删除结果 1成功 0失败
     */
    @Override
    public int deletePassenger(Long passengerId, HttpServletRequest httpServletRequest) {
        // 检测是否有该乘客并且是否有权限修改
        isHavePermissionAndReturnPassengerDO(passengerId, httpServletRequest);
        // 删除购票人
        int result = passengerMapper.deletePassengerByPassengerId(passengerId);
        if (result != 1) {
            log.error("删除购票人失败，数据库操作失败，参数：{}", passengerId);
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "数据库操作失败");
        }
        return result;
    }

    /**
     * 更新购票人信息
     * @param passengerRequest 购票人请求
     * @param httpServletRequest http请求
     * @return 更新结果 1成功 0失败
     */
    @Override
    public int updatePassenger(PassengerRequest passengerRequest, HttpServletRequest httpServletRequest) {
        // Passenger数据校验
        PassengerDO passengerDO = checkPassengerRequestInfo(passengerRequest, httpServletRequest);
        // 检测是否有该乘客并且是否有权限修改
        isHavePermissionAndReturnPassengerDO(passengerDO.getPassengerId(),httpServletRequest);
        // 更新数据库
        int result = passengerMapper.updatePassenger(passengerDO);
        if (result != 1) {
            log.error("更新购票人失败，数据库操作失败，参数：{}", passengerDO);
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "数据库操作失败");
        }
        return result;
    }

    /**
     * 根据购票人id获取购票人信息
     * @param passengerId 购票人id
     * @param httpServletRequest http请求
     * @return 购票人信息
     */
    @Override
    public PassengerDO getPassengerByPassengerId(Long passengerId, HttpServletRequest httpServletRequest) {
        // 检测是否有该乘客并且是否有权限获取
        PassengerDO passengerDO = isHavePermissionAndReturnPassengerDO(passengerId, httpServletRequest);
        // 检测是否获取到
        if (passengerDO == null) {
            log.error("获取购票人失败，数据库操作失败，参数：{}", passengerId);
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "数据库操作失败");
        }
        return passengerDO;
    }

    /**
     * 根据用户id获取用户的购票人列表
     * @param userId 用户id
     * @param httpServletRequest http请求
     * @return 购票人列表
     */
    @Override
    public List<PassengerDO> getPassengersByUserId(Long userId, HttpServletRequest httpServletRequest) {
        // 判断userID是否与当前用户一致
        CheckUtils.isLoginStatusLegal(userId, httpServletRequest);
        // 获取购票人列表并返回
        return passengerMapper.selectPassengerByUserId(userId);
    }

    /**
     * 检测数据是否规范并且返回PassengerDO
     * @param passengerRequest 乘客信息
     * @param httpServletRequest http请求
     * @return 乘客信息
     */
    public PassengerDO checkPassengerRequestInfo(PassengerRequest passengerRequest,HttpServletRequest httpServletRequest)
    {
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
        // request转换为DO
        return RequestToDoEntityUtils.toPassengerDO(passengerRequest);
    }

    /**
     * 检测是否有该乘客并且是否有权限修改
     * @param passengerId 购票人id
     * @param httpServletRequest 请求
     * @return 购票人DO
     */
    public PassengerDO isHavePermissionAndReturnPassengerDO(Long passengerId,HttpServletRequest httpServletRequest)
    {
        // 根据购票人id查看是否存在该购票人
        PassengerDO checkPassenger = passengerMapper.selectPassengerByPassengerId(passengerId);
        if(checkPassenger == null)
        {
            log.error("该购票人不存在，参数：{}", passengerId);
            throw new BusinessException(ErrorCode.NOT_EXIST_PASSENGER, "该购票人不存在");
        }
        // 检测登录号是不是插入购票人的用户
        CheckUtils.isLoginStatusLegal(checkPassenger.getUserId(), httpServletRequest);
        return checkPassenger;
    }

    /**
     * 检测是否有该乘客
     * @param userId 用户id
     * @param number 身份证号
     */
    public void isExistPassenger(Long userId,String number){
        // 根据账号id和身份证查看是否已经存在该购票人
        PassengerDO checkPassenger = passengerMapper.selectPassengerByUserIdAndPassengerCardNumber(userId,number);
        if (checkPassenger != null) {
            log.error("添加购票人失败，该购票人已存在，参数：{}",userId);
            throw new BusinessException(ErrorCode.EXIST_PASSENGER, "该购票人已存在");
        }
    }


}





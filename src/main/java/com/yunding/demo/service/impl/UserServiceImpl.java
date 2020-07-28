package com.yunding.demo.service.impl;

import com.yunding.demo.common.util.TokenUtil;
import com.yunding.demo.core.exception.SysException;
import com.yunding.demo.dto.LoginSuccessDto;
import com.yunding.demo.dto.MessageSuccessDto;
import com.yunding.demo.dto.TokenInfo;
import com.yunding.demo.dto.UsersInfoDto;
import com.yunding.demo.entity.UserInfo;
import com.yunding.demo.form.LoginInfoForm;
import com.yunding.demo.form.UserLoginCodeForm;
import com.yunding.demo.form.UserLoginPhoneForm;
import com.yunding.demo.form.UserRegisterForm;
import com.yunding.demo.mapper.UsersMapper;
import com.yunding.demo.redis.RedisRepository;
import com.yunding.demo.service.UsersService;
import com.yunding.demo.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author guorui
 * @date 2020-07-23 20:58
 */
@Slf4j
@Service
public class UserServiceImpl implements UsersService {
    @Autowired
    private UsersMapper userMapper;
    @Autowired
    private RedisRepository redisRepository;
    @Override
    public UsersInfoDto selectOfLogin(LoginInfoForm loginInfo){
        String email = loginInfo.getEmail();
        return userMapper.selectOfLogin(email);
    }

    /**
     * 用户注册功能，采用手机验证码
     *
     * @param userRegisterForm
     * @return
     */
    @Override
    public MessageSuccessDto register(UserRegisterForm userRegisterForm) {
        if(redisRepository.selectMessageCodeByPhone(userRegisterForm.getPhone()) == null){
            log.info("系统未能检测到您的验证码，请稍后再试");
            throw new SysException( "系统未能检测到您的验证码，请稍后再试" );
        }else if(!redisRepository.selectMessageCodeByPhone(userRegisterForm.getPhone()).equals(userRegisterForm.getCode())){
            log.info("验证码错误！");
            throw new SysException("验证码错误！");
        }else if(null != userMapper.selectByPhone(userRegisterForm.getPhone())){
            log.info("该手机号已被注册！");
            throw new SysException("该手机号已被注册！");
        }else {
            log.info("验证成功！");

            //生成的id
            String userId = UUID.randomUUID().toString().replace("-","");
            //md5加密后的密码
            String result = Md5Util.MD5Encode(userRegisterForm.getPassword());

            userMapper.insertNewUser(userRegisterForm.getPhone(), result);
            MessageSuccessDto registerSuccessDto = new MessageSuccessDto();
            registerSuccessDto.setMessage("注册成功！");
            return registerSuccessDto;
        }
    }

    /**
     * 验证码登录
     *
     * @param userLoginCodeForm
     * @return
     */
    @Override
    public LoginSuccessDto loginByCode(UserLoginCodeForm userLoginCodeForm) {
        UserInfo user = userMapper.selectByPhone(userLoginCodeForm.getPhone());
        if (null == user) {
            log.info("异地登录！");
            throw new SysException("该手机号未进行注册！");
        } else if (!redisRepository.selectMessageCodeByPhone(userLoginCodeForm.getPhone()).equals(userLoginCodeForm.getCode())) {
            log.info("验证码错误！");
            throw new SysException("验证码错误！");
        } else {
            String oldToken = redisRepository.selectAccessToken(user.getUserId());
            //判断是否存在异地登录
            if (null != oldToken) {
                log.info("异地登录！");
                //如果存在异地登录，删除原有的token
                redisRepository.deleteAccessToken(oldToken);
                redisRepository.deleteLoginAccessToken(user.getUserId());
            }
            //得到token，生成tokenInfo对象
            TokenInfo tokenInfo = new TokenInfo();
            tokenInfo.setUserId(user.getUserId());
            tokenInfo.setAccessToken(TokenUtil.genToken());
            //保存到redis
            redisRepository.saveAccessToken(tokenInfo);
            redisRepository.saveLoginAccessToken(tokenInfo);
            //设置返回值
            LoginSuccessDto messageSuccessLoginDto = UserServiceImpl.getMessageSuccessLoginDto(user, tokenInfo);
            return messageSuccessLoginDto;
        }
    }

    /**
     * 账号密码登录
     *
     * @param userLoginPhoneForm
     * @return
     */
    @Override
    public LoginSuccessDto loginByPassword(UserLoginPhoneForm userLoginPhoneForm) {
        //密码经过md5加密处理
        String result = Md5Util.MD5Encode(userLoginPhoneForm.getPassword());
        UserInfo user = userMapper.selectByEmail(userLoginPhoneForm.getEmail());
        if (null == user) {
            log.info(userLoginPhoneForm.getEmail());
            log.info(result);
            log.info("用户不存在！");
            throw new SysException("邮箱不存在！");
        } else if (!user.getPassword().equals(result)) {
            log.info("邮箱或密码错误");
            throw new SysException("邮箱或密码错误");
        } else {
            //判断是否异地登录
            String oldToken = redisRepository.selectAccessToken(user.getUserId());
            if (null != oldToken) {
                log.info("异地登录！");
                //如果存在异地登录，删除原有的token
                redisRepository.deleteAccessToken(oldToken);
                redisRepository.deleteLoginAccessToken(user.getUserId());
            }
            //得到一个accessToken，生成tokenInfo
            TokenInfo tokenInfo = new TokenInfo();
            tokenInfo.setAccessToken(TokenUtil.genToken());
            tokenInfo.setUserId(user.getUserId());
            LoginSuccessDto messageSuccessLoginDto = UserServiceImpl.getMessageSuccessLoginDto(user, tokenInfo);
            //保存这个用户和token信息
            redisRepository.saveAccessToken(tokenInfo);
            redisRepository.saveLoginAccessToken(tokenInfo);
            return messageSuccessLoginDto;
        }
    }

    /**
         * 得到登录成功返回的一个token信息
         * @param user
         * @return
         */
        public static LoginSuccessDto getMessageSuccessLoginDto(UserInfo user, TokenInfo tokenInfo){
            LoginSuccessDto messageSuccessLoginDto = new LoginSuccessDto();
            //这里看前端具体需要什么再更改吧
            messageSuccessLoginDto.setName(user.getName());
            messageSuccessLoginDto.setUserId(user.getUserId());
            messageSuccessLoginDto.setEmail(user.getEmail());
            messageSuccessLoginDto.setIntroduction(user.getIntroduction());
            messageSuccessLoginDto.setAccessToken(tokenInfo.getAccessToken());
            return messageSuccessLoginDto;
        }

}

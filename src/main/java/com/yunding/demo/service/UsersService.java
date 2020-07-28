package com.yunding.demo.service;

import com.yunding.demo.dto.LoginSuccessDto;
import com.yunding.demo.dto.MessageSuccessDto;
import com.yunding.demo.dto.UsersInfoDto;
import com.yunding.demo.form.*;

/**
 * 业务层，存放业务逻辑处理，不直接对数据库进行操作，有接口和接口实现类，提供controller层调用的方法。
 * @author guorui
 * @date 2020-07-23 20:57
 */
public interface UsersService {

    /**
     * 登录功能，将email作为查询条件，返回用户id和密码
     */
    UsersInfoDto selectOfLogin(LoginInfoForm loginInfo);
    /**
     * 用户注册功能，采用手机验证码
     * @param userRegisterForm
     * @return
     */
    MessageSuccessDto register(UserRegisterForm userRegisterForm);
    /**
     * 验证码登录
     * @param userLoginCodeForm
     * @return
     */
    LoginSuccessDto loginByCode(UserLoginCodeForm userLoginCodeForm);
    /**
     * 邮箱密码登录
     * @param userLoginPhoneForm
     * @return
     */
    LoginSuccessDto loginByPassword(UserLoginPhoneForm userLoginPhoneForm);
}

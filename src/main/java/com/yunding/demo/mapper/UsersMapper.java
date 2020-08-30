package com.yunding.demo.mapper;

import com.yunding.demo.dto.UsersInfoDto;
import com.yunding.demo.entity.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author guorui
 * @date 2020-07-23 20:49
 */
@Repository
public interface UsersMapper {
    /**
     * 注册功能，
     * @param email 用户名
     * @param password 密码
     */
    void regist(String email, String password);
    /**
     * 注册功能，手机号+验证码
     * @param phone 手机号
     * @param password 密码
     */
    void insertNewUser(String phone, String password);
    /**
     * 登录功能，返回影响成功的行数，为0则失败
     * @param email 用户名
     * @return
     */
    UsersInfoDto selectOfLogin(String email);
    /**
     * 通过邮箱号查询用户
     * @param phone
     * @return
     */
    UserInfo selectByEmail(String phone);
    /**
     * 通过手机号查询用户
     * @param phone
     * @return
     */
    UserInfo selectByPhone(String phone);
}

package com.yunding.demo.entity;

import lombok.Data;

/**
 * @author guorui
 * @date 2020-07-23 20:34
 */
//表明当前类是一个Java Bean
@Data
public class UserInfo {
    /**
     * introduction用户个人简介，confirmpass确认密码，picture用户头像，userId用户id，按照注册顺序自动补充。
     */
    private String userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String introduction;
    private String picture;

//    private String confirmpass;
//    private String token;
}

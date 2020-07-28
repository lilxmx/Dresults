package com.yunding.demo.dto;

import lombok.Data;

/**
 * @Author: Cui
 * @Date: 2020/3/10
 * @Description:
 */
@Data
public class LoginSuccessDto {
    private String accessToken;
    private String userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String introduction;
    private String picture;

}

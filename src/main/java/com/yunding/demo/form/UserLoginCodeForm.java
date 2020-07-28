package com.yunding.demo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Author: li
 * @Date: 2020/7/25
 * @Description:
 */
@Data
public class UserLoginCodeForm {
    /**
     *手机号,正则表达式判断手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(message = "请输入正确的手机号", regexp = "^((13[0-9])|(14[5-8])|(15([0-3]|[5-9]))|(16[6])|(17[0|4|6|7|8])|(18[0-9])|(19[8-9]))\\d{8}$")
    private String phone;
    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String code;
}

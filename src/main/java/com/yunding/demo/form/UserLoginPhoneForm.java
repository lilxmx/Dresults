package com.yunding.demo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserLoginPhoneForm {

    @NotBlank(message = "邮箱不能为空")
    @Pattern(message = "请输入正确的手机号", regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$")
    private String email;
    @NotBlank(message = "密码不能为空")
    private String password;

}


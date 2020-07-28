package com.yunding.demo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author guorui
 * @date 2020-07-23 21:03
 */
@Data
public class RegisterForm {
    /**
     *邮箱的基本格式为“名称@域名”，需要使用“^”匹配邮箱的开始部分，用“$”匹配邮箱结束部分以保证邮箱前后不能有其他字符
     * 名称允许汉字、字母、数字，域名只允许英文域名
     *
     汉字在正则表示为[\u4e00-\u9fa5]
     字母和数字表示为A-Za-z0-9
      通过分析得出邮件名称部分表达式为[A-Za-z0-9\u4e00-\u9fa5]+

     分析邮件域名部分
      邮件部分可以参考实例1中的分析域名部分。
      得出域名部分的表达式为[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+。
     最终表达式：
      我们用@符号将邮箱的名称和域名拼接起来，因此完整的邮箱表达式为
     * 举例：杨元庆001Abc@lenovo.com.cn
     */
    @NotBlank(message = "邮箱不能为空")
    @Pattern(message = "请输入正确的邮箱", regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
    private String email;
    @NotBlank(message = "密码不能为空")
    private String password;
}

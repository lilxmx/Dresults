package com.yunding.demo.dto;

import lombok.Data;

/**
 * @author guorui
 * @date 2020-07-24 14:52
 * 用于接收短信验证码的返回消息
 */
@Data
public class MessageDto {
    /**
     * 验证码
     */
    private int code;

    /**
     * 状态
     */
    private String state;

    public MessageDto(){}

    public MessageDto(int code, String state) {
        this.code = code;
        this.state = state;
    }

}

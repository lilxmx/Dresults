package com.yunding.demo.core.exception;

import com.yunding.demo.common.enums.BaseErrorCodeEnum;

/**
 * @author 段启岩
 * 通用异常类
 */
public class SysException extends RuntimeException {
    protected int code;
    public SysException(){}
    public SysException(String message){super(message);}
    public SysException(Throwable cause) {
        super(cause);
    }
    //Object ...args java某个版本的新特性
    public SysException(BaseErrorCodeEnum codeEnum, Object... args) {
        super(String.format(codeEnum.getMessage(), args));
        this.code = codeEnum.getCode();
    }

}

package com.yunding.demo.util;

/**
 * 返回通用数据格式的工具类
 * @author guorui
 * @date 2020-07-18 17:31
 */
public class CommonResult {
    private String status;
    private String result;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

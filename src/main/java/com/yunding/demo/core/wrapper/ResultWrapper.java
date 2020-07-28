package com.yunding.demo.core.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guorui
 * @date 2020-07-18 19:58
 */
@Data
public class ResultWrapper<T> {

    final static Integer CODE_SUCCESS = 0;

    final static String MESSAGE_SUCCESS = "操作成功";

    final static Integer CODE_FAILURE = -1;

    final static String MESSAGE_FAILURE = "操作失败";

    private Integer code;
//即如果加该注解的字段为null,那么就不序列化这个字段了。
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private String message;

    public ResultWrapper() {
    }

    public ResultWrapper(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


    //返回code0 data数据 message自定义
    public static <T> ResultWrapper<T> successWithData(T data)
    {
        return new ResultWrapper<>(CODE_SUCCESS, data, "请求成功");
    }

    //返回code0 data数据 message操作成功
    public static<T> ResultWrapper<T> successWithData(String key, Object value) {
        Map data = new HashMap(1);
        data.put(key, value);
        return new ResultWrapper(CODE_SUCCESS, data, MESSAGE_SUCCESS);
    }

    //返回code0 message操作成功
    public static<T> ResultWrapper<T> success() {
        return new ResultWrapper<>(CODE_SUCCESS, null, MESSAGE_SUCCESS);
    }

    //返回code0 message自定义
    public static<T> ResultWrapper<T> success(String message) {

        return new ResultWrapper<>(CODE_SUCCESS, null, message);
    }

    public static<T> ResultWrapper<T> failure() {
        return new ResultWrapper<>(CODE_FAILURE, null, MESSAGE_FAILURE);
    }

    public static<T> ResultWrapper<T> failure(String message) {
        return new ResultWrapper<>(CODE_FAILURE, null, message);
    }

    public static<T> ResultWrapper<T> failure(Integer code, String message) {
        return new ResultWrapper<>(code, null, message);
    }


   /* public static<T> ResultWrapper<T> failure(ErrorCodeEnum errorCodeEnum) {
        return new ResultWrapper<>(errorCodeEnum.getCode(), null, errorCodeEnum.getMessage());
    }*/

    @JsonIgnore
    public boolean isSuccess() {
        return this.code.equals(CODE_SUCCESS);
    }

}

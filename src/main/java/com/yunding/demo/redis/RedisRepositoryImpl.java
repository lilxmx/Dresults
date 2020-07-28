package com.yunding.demo.redis;

import com.yunding.demo.dto.TokenInfo;
import com.yunding.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import static com.yunding.demo.constant.RedisKeyTemplate.*;
import static com.yunding.demo.util.RedisKeyUtil.buildKey;

/**
 * @author guorui
 * @date 2020-07-24 20:27
 */
@Component
public class RedisRepositoryImpl implements RedisRepository {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 保存手机号和验证码的键值对<phone, messageCode>
     *
     * @param phone
     * @param messageCode 验证码
     */
    @Override
    public void saveMessageCode(String phone, String messageCode) {
        RedisUtil.set(redisTemplate, buildKey(T_VERIFICATION_CODE, phone), messageCode);
    }

    /**
     * 根据key：手机号 查询验证码
     *
     * @param phone
     * @return
     */
    @Override
    public String selectMessageCodeByPhone(String phone) {
        return RedisUtil.<String>get(redisTemplate, buildKey(T_VERIFICATION_CODE, phone), String.class);
    }

    /**
     * 删除手机号和验证码的键值对
     *
     * @param phone
     */
    @Override
    public void deleteMessageCode(String phone) {
        RedisUtil.del(redisTemplate, buildKey(T_VERIFICATION_CODE, phone));
    }

    /**
     * 查询认证
     *
     * @param accessToken
     * @return
     */
    @Override
    public String selectAccessToken(String accessToken) {
        return RedisUtil.<String>get(redisTemplate, buildKey(T_ACCESS_TOKEN, accessToken), String.class);
    }

    /**
     * 查询登录认证
     *
     * @param userId
     * @return
     */
    @Override
    public String selectLoginAccessToken(String userId) {
        return RedisUtil.<String>get(redisTemplate, buildKey(T_USER_CURRENT_TOKEN, userId), String.class);
    }

    /**
     * 认证
     *
     * @param tokenInfo
     */
    @Override
    public void saveAccessToken(TokenInfo tokenInfo) {
        RedisUtil.set(redisTemplate, buildKey(T_ACCESS_TOKEN, tokenInfo.getAccessToken()), tokenInfo.getUserId());
    }

    /**
     * 登录认证
     *
     * @param tokenInfo
     */
    @Override
    public void saveLoginAccessToken(TokenInfo tokenInfo) {
        RedisUtil.set(redisTemplate, buildKey(T_USER_CURRENT_TOKEN, tokenInfo.getUserId()), tokenInfo.getAccessToken());
    }

    /**
     * 删除认证
     *
     * @param accessToken
     */
    @Override
    public void deleteAccessToken(String accessToken) {
        RedisUtil.del(redisTemplate, buildKey(T_ACCESS_TOKEN, accessToken));
    }

    /**
     * 删除登录认证
     *
     * @param userId
     */
    @Override
    public void deleteLoginAccessToken(String userId) {
        RedisUtil.del(redisTemplate, buildKey(T_USER_CURRENT_TOKEN, userId));
    }
}

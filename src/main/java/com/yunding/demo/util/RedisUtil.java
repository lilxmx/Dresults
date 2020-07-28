package com.yunding.demo.util;

import com.yunding.demo.common.util.JsonUtil;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author guorui
 * @date 2020-07-25 11:53
 */
public class RedisUtil {
    public static boolean exists(StringRedisTemplate redisTemplate, String key) {
        return redisTemplate.hasKey(key);
    }

    public static <T> void set(StringRedisTemplate redisTemplate, String key, T value) {
        redisTemplate.opsForValue().set(key, JsonUtil.toJson(value));
    }

    public static <T> T get(StringRedisTemplate redisTemplate, String key, Class<T> clazz) {
        String value = redisTemplate.opsForValue().get(key);
        if (null != value) {
            return JsonUtil.toObject(value, clazz);
        }
        return null;
    }

    public static void del(StringRedisTemplate redisTemplate, String key) {
        redisTemplate.delete(key
        );
    }
}

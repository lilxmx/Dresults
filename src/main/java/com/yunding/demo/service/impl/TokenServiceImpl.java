package com.yunding.demo.service.impl;

import com.yunding.demo.dto.TokenInfo;
import com.yunding.demo.redis.RedisRepository;
import com.yunding.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 宋万顷
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    private RedisRepository redisRepository;

//    @Autowired
//    private UserMapper userMapper;

    @Override
    public TokenInfo getTokenInfo(String accessToken) {
        String userId = redisRepository.selectAccessToken(accessToken);
       // User selectByphone = userMapper.selectByPhone ( phone );
        if (userId != null) {
            TokenInfo tokenInfo = new TokenInfo();
            tokenInfo.setUserId(userId);
            tokenInfo.setAccessToken(accessToken);
            return tokenInfo;
        }
        return null;
    }
}

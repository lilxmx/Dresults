package com.yunding.demo.service;

import com.yunding.demo.dto.TokenInfo;

/**
 *
 */
public interface TokenService {

    /**
     * 从Redis获取token的信息
     * @param accessToken
     * @return
     */
    TokenInfo getTokenInfo(String accessToken);

}

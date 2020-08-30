package com.yunding.demo.core.interceptor;

import com.yunding.demo.common.constant.SysConstant;
import com.yunding.demo.common.util.JsonUtil;
import com.yunding.demo.common.util.ThreadLocalMap;
import com.yunding.demo.constant.AnswerConstant;
import com.yunding.demo.core.wrapper.ResultWrapper;
import com.yunding.demo.dto.TokenInfo;
import com.yunding.demo.dto.UsersInfoDto;
import com.yunding.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 宋万顷
 * 编写拦截器实现类，实现接口   HandlerInterceptor
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
//第一步；实现接口 HanderInterceptor
    @Autowired
    private TokenService tokenService;
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (request.getMethod().equalsIgnoreCase("options")) {
            //放行
            return true;
        }
        String accessToken = null;
        accessToken = request.getHeader(AnswerConstant.HTTP_HEADER_ANSWER_ACCESS_TOKEN);

        if (null == accessToken){
            accessToken = request.getParameter(AnswerConstant.RQE_PARAM_ANSWER_ACCESS_TOKEN);
        }

        ResultWrapper resultWrapper = null;
        response.setHeader("Content-Type", "application/json;charset=utf-8");

        /**
         * 未传递Token
         */
        if (null == accessToken){
            ThreadLocalMap.remove(SysConstant.THREAD_LOCAL_KEY_LOGIN_USER);
            resultWrapper = ResultWrapper.failure("未认证");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            try {
                response.getWriter().println(JsonUtil.toJson(resultWrapper));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        TokenInfo tokenInfo = tokenService.getTokenInfo(accessToken);
        if (null == tokenInfo){
            ThreadLocalMap.remove(SysConstant.THREAD_LOCAL_KEY_LOGIN_USER);
            resultWrapper = ResultWrapper.failure("错误的Token");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                response.getWriter().println(JsonUtil.toJson(resultWrapper));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        /**
         * 将userInfo设置到ThreadLocal
         */
        UsersInfoDto userInfoDto = new UsersInfoDto();
        userInfoDto.setUserId(tokenInfo.getUserId());
        ThreadLocalMap.put(SysConstant.THREAD_LOCAL_KEY_LOGIN_USER, userInfoDto);
        return true;
    }
}

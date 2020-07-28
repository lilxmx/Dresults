package com.yunding.demo.config;

import com.yunding.demo.core.interceptor.LoginInterceptor;
import com.yunding.demo.core.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author 段启岩
 *编写拦截器配置文件类并继承  WebMvcConfigurer类，并重写其中的方法  addInterceptors并且在主类上加上注解  @Configuration
 */

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired

    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                //添加不拦截路径
                //登录、html、js、css静态资源
                .excludePathPatterns(
                        "/error",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/swagger-ui.html",
                        "/user/register",
                        "/user/login/**",
                        "/login/**",
                        "/register"
                );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
                allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(false).maxAge(3600);
    }
}

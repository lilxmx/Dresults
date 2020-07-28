package com.yunding.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 * @author guorui
 * @date 2020-07-18 15:39
 */
@RestController
public class HelloController {
    @Value("${name}")
    private String name;
    @Value("${myLover}")
    private String myLover;
    @Value("${year}")
    private String year;

    @RequestMapping("/love")
    public String love(){
        return name+"love"+myLover+year;
    }

    @RequestMapping("/hello")
    public String hello(){
        return "This is my Third Spring_Boot project啊哈哈哈";
    }
}

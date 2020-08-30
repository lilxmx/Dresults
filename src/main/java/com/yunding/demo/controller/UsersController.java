package com.yunding.demo.controller;

import com.yunding.demo.core.wrapper.ResultWrapper;
import com.yunding.demo.core.exception.SysException;
import com.yunding.demo.dto.LoginSuccessDto;
import com.yunding.demo.dto.MessageSuccessDto;
import com.yunding.demo.form.*;
import com.yunding.demo.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author guorui
 * @date 2020-07-23 20:25
 */
@Slf4j
@Api(value = "UsersController", tags = {"用户API"})
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * @Todo 通过手机验证码注册
     * 用户注册
     * @param userRegisterForm
     * @return
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")

    public ResultWrapper register(@Valid @RequestBody UserRegisterForm userRegisterForm){
        try{
            MessageSuccessDto registerSuccessDto = usersService.register(userRegisterForm);
            return ResultWrapper.successWithData(registerSuccessDto);
        }catch (SysException e){
            log.info("UserController.register");
            return ResultWrapper.failure(e.getMessage());
        }
    }

    /**
     * @Todo 通过邮箱登录
     * @param loginInfoForm
     * @return
     */
    @ApiOperation(value="登录", notes="登录",produces = "application/json")
    @GetMapping  ("/login/Email")
    public ResultWrapper login( LoginInfoForm loginInfoForm){
        try {
//            loginInfoForm.getEmail()==null || loginInfoForm.getPassword()==null
            if(loginInfoForm.getEmail()==null || loginInfoForm.getPassword()==null){
                return ResultWrapper.failure("邮箱和密码不能为空");
            }
            if(usersService.selectOfLogin(loginInfoForm) == null){
                return ResultWrapper.failure("该邮箱未注册");
            }
            if(loginInfoForm.getPassword().equals(usersService.selectOfLogin(loginInfoForm).getPassword())){
                return ResultWrapper.success("登录成功"+loginInfoForm.getPassword()+usersService.selectOfLogin(loginInfoForm).getPassword());
            }
            if(!(usersService.selectOfLogin(loginInfoForm).getPassword().equals(loginInfoForm.getPassword()))){
                return ResultWrapper.success("登录失败，密码错误");
            }else{
                return ResultWrapper.failure("邮箱和密码不匹配");
            }



        }catch (Exception e){
            e.printStackTrace();
            return ResultWrapper.failure();
        }

    }
    /**
     * 邮箱和密码登录完整版
     * @param userLoginPhoneForm
     * @return
     */
    @ApiOperation(value = "邮箱密码登录")
    @PostMapping("/login/password")
    public ResultWrapper login(@Valid @RequestBody UserLoginPhoneForm userLoginPhoneForm){
        try{
            LoginSuccessDto messageSuccessLoginDto = null;
            messageSuccessLoginDto = usersService.loginByPassword(userLoginPhoneForm);
            return ResultWrapper.successWithData(messageSuccessLoginDto);
        }catch (SysException e){
            log.info("UserController.login");
            return ResultWrapper.failure(e.getMessage());
        }
    }
    /**
     * @Todo 通过手机验证码加手机号登录
     * 用户验证码登录
     * @param userLoginForm
     * @return
     */
    @ApiOperation(value = "用户验证码登录")
    @PostMapping("/login/code")
    public ResultWrapper loginPassword(@Valid @RequestBody UserLoginCodeForm userLoginForm) {
        try {
            LoginSuccessDto messageSuccessLoginDto = usersService.loginByCode(userLoginForm);
            return ResultWrapper.successWithData(messageSuccessLoginDto);
        } catch (SysException e) {
            log.info("UserController.loginPassword");
            return ResultWrapper.failure(e.getMessage());
        }
    }
}

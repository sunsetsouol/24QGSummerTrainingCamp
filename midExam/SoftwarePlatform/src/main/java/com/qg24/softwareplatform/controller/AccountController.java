package com.qg24.softwareplatform.controller;

import com.qg24.softwareplatform.po.dto.EmailDTO;
import com.qg24.softwareplatform.po.dto.LoginDTO;
import com.qg24.softwareplatform.po.dto.RegisterDTO;
import com.qg24.softwareplatform.po.result.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {


    /**
     * 登录确认
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginDTO loginDTO){

    }


    /**
     * 注册确认，整个表单提交
     * @param
     * @return
     */
    @PostMapping("/register")
    public Result<?> register(@RequestBody RegisterDTO registerDTO){

    }


    //发送邮箱验证码
    @PostMapping("/sendVerificationCode")
    public Result<?> sendVerificationCode(@RequestBody EmailDTO emailDTO){

    }


    //修改密码
    @PostMapping("/updatePassword")
    public Result<?> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO){

    }

    //首页 个人用户名 头像
    @GetMapping("/homePageUserInfo")
    public Result<?> homePageUserInfo(@RequestParam("userId") String userId){

    }



}

package com.qg24.softwareplatform.controller;

import com.qg24.softwareplatform.po.dto.*;
import com.qg24.softwareplatform.po.entity.UserAndToken;
import com.qg24.softwareplatform.po.result.Result;
import com.qg24.softwareplatform.po.vo.HomePageUserInfoVO;
import com.qg24.softwareplatform.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    /**
     * 登录确认
     * @return 用户id以及token
     */
    @PostMapping("/login")
    public Result<UserAndToken> login(@RequestBody LoginDTO loginDTO){
        UserAndToken res = accountService.login(loginDTO);
        if (Objects.isNull(res)) {
            return Result.error("登陆失败，请检查你的账号和密码是否输入正确");
        }else {
            return Result.success("", res);
        }
    }

    /**
     * 注册确认，整个表单提交
     */
    @PostMapping("/register")
    public Result<?> register(@RequestBody RegisterDTO registerDTO){
        int res = accountService.register(registerDTO);
        if (res == 1) {
            return Result.success("注册成功,赶紧登陆吧!");
        } else if (res == 0) {
            return Result.error("验证码不正确!");
        } else if (res == -1) {
            return Result.error("出现未知异常...");
        }
        return Result.error("出现未知异常...");
    }


    /**
     * 发送邮箱验证码
     */
    @PostMapping("/sendVerificationCode")
    public Result<?> sendVerificationCode(@RequestBody EmailDTO emailDTO){
        try {
            accountService.sendVerificationCodeAndSaveCode(emailDTO.getEmail());
            return Result.success("发送成功");
        } catch (Exception e) {
            return Result.error("邮箱验证码发送失败");
        }
    }

    /**
     * 修改密码
     */
    @PostMapping("/updatePassword")
    public Result<?> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO){
        accountService.updatePassword(updatePasswordDTO);
        return Result.success();
    }

    @PostMapping("/updateUserInfo")
    public Result<?> updateUserInfo(@ModelAttribute UpdateUserInfoDTO updateUserInfoDTO) throws IOException {
        accountService.updateUserInfo(updateUserInfoDTO);

        return Result.success();
    }

    /**
     * 获取首页用户头像url以及用户名
     * @param userId
     * @return
     */
    @GetMapping("/homePageUserInfo")
    public Result<HomePageUserInfoVO> homePageUserInfo(@RequestParam("userId") String userId){
        HomePageUserInfoVO homePageUserInfo = accountService.getHomePageUserInfo(userId);

        return Result.success("", homePageUserInfo);
    }



}

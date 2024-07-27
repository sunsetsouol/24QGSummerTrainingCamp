package com.qg24.softwareplatform.controller;


import com.qg24.softwareplatform.po.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {


    //确认购买授权
//    @PostMapping("/purchaseAuth")
//    public Result<?> purchaseAuth(){
//    }

    //检验授权
//    @PostMapping("/checkAuth")
//    public Result<?> checkAuth(){
//    }


    //(非前端)本地软件发送信息进行服务器比对接口
    @PostMapping("/onlineVertification")
    public Result<?> onlineVertification(){

    }






}

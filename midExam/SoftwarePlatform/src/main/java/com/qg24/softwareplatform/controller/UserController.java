package com.qg24.softwareplatform.controller;

import com.qg24.softwareplatform.po.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    // TODO 个人开发的软件有普 专 两种 是否忽略?
    /**
     * 个人页面展示个人开发的软件
     */
    @GetMapping("/user/homePageShowSoftware")
    public Result<?> homePageShowSoftware(@RequestParam("page")int page,@RequestParam("userId")String userId){

    }

    /**
     * 个人页面展示申请记录
     */
    @GetMapping("/applicationRecord")
    public Result<?> applicationRecord(@RequestParam("userId")String userId){
    }

    /**
     * 个人页面查询可购买过（授权过但是可能过期了）的软件(通过过期时间判断是否过期)
     */
    @GetMapping("/PagedQueryAvailableSoftware")
    public Result<?> PagedQueryAvailableSoftware(@RequestParam("userId")String userId){
    }
}

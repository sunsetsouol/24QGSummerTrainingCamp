package com.qg24.softwareplatform.controller;

import com.qg24.softwareplatform.po.result.Result;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import com.qg24.softwareplatform.po.vo.UserApplicationRecordVO;
import com.qg24.softwareplatform.po.vo.UserBuySoftwareVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    /**
     * 个人页面展示个人开发的软件
     */
    @GetMapping("/user/homePageShowSoftware")
    public Result<?> homePageShowSoftware(){
    }

    /**
     * 个人页面展示申请记录
     */
    @GetMapping("/user/applicationRecord")
    public Result<?> applicationRecord(){
    }

    /**
     * 个人页面查询可购买过（授权过但是可能过期了）的软件(通过过期时间判断是否过期)
     */
    @GetMapping("/user/PagedQueryAvailableSoftware")
    public Result<?> PagedQueryAvailableSoftware(){
    }
}

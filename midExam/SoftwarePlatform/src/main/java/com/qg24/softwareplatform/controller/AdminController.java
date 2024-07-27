package com.qg24.softwareplatform.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    /**
     * 管理员更改软件信息
     */
    @PostMapping("/admin/updateSoftwareBasicInfo")
    public void UpdateSoftWareBasicInfo(){
    }

    /**
     * 管理员修改软件最新版本信息
     */
    @PostMapping("/admin/updateSoftwareLatestInfo")
    public void updateSoftwareLatestInfo(){
    }

    /**
     * 管理员 分页 回显审核记录
     */
    @PostMapping("/admin/showVerificationHistory")
    public void showVerificationHistory(){
    }

    /**
     * 新软件/新版本  审核通过/驳回
     */
    @PostMapping("/admin/verifyApplication")
    public void verifyApplication(){
    }
}

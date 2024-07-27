package com.qg24.softwareplatform.controller;

import com.qg24.softwareplatform.po.dto.UpdateSoftwareLatestInfoDTO;
import com.qg24.softwareplatform.po.result.Result;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    /**
     * 管理员更改软件信息
     */
    @PostMapping("/admin/updateSoftwareBasicInfo")
    public Result<?> UpdateSoftWareBasicInfo(){
    }

    /**
     * 管理员修改软件最新版本信息
     */
    @PostMapping("/admin/updateSoftwareLatestInfo")
    public Result<?> updateSoftwareLatestInfo(@RequestBody UpdateSoftwareLatestInfoDTO updateSoftwareLatestInfoDTO){
    }

    /**
     * 管理员 分页 回显审核记录
     */
    @GetMapping("/admin/showVerificationHistory")
    public Result<?> showVerificationHistory(@RequestParam("page") int page,@RequestParam("pageSize")int pageSize){
    }

    /**
     * 新软件/新版本  审核通过/驳回
     */
    @PostMapping("/admin/verifyApplication")
    public Result<?> verifyApplication(@RequestParam("softwareInfoTempId")int softwareInfoTempId,@RequestParam("status")int status){
    }
}

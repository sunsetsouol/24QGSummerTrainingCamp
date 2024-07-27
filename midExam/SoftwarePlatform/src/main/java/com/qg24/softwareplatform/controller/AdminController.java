package com.qg24.softwareplatform.controller;

import com.qg24.softwareplatform.po.dto.UpdateSoftwareLatestInfoDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.result.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    /**
     * 管理员更改软件信息
     */
    @PostMapping("/updateSoftwareBasicInfo")
    public Result<?> UpdateSoftWareBasicInfo(@RequestBody Software software){
    }

    /**
     * 管理员修改软件最新版本信息
     */
    @PostMapping("/updateSoftwareLatestInfo")
    public Result<?> updateSoftwareLatestInfo(@RequestBody UpdateSoftwareLatestInfoDTO updateSoftwareLatestInfoDTO){
    }

    /**
     * 管理员 分页 回显审核记录
     */
    @GetMapping("/showVerificationHistory")
    public Result<?> showVerificationHistory(@RequestParam("page") int page,@RequestParam("pageSize")int pageSize){
    }

    /**
     * 新软件/新版本  审核通过/驳回
     */
    @PostMapping("/verifyApplication")
    public Result<?> verifyApplication(@RequestParam("softwareInfoTempId")int softwareInfoTempId,@RequestParam("status")int status){
    }
}
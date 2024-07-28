package com.qg24.softwareplatform.controller;

import com.qg24.softwareplatform.po.dto.UpdateSoftwareLatestInfoDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.result.Result;
import com.qg24.softwareplatform.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    /**
     * 管理员更改基本软件信息
     */
    @Autowired
    private AdminService adminService;
    @PostMapping("/updateSoftwareBasicInfo")
    public Result<?> UpdateSoftWareBasicInfo(@RequestBody Software software){
        if(adminService.updateSoftwareBasicInfo(software)){
            return Result.success("Success");
        }else{
            return Result.error("Failed");
        }
    }

    /**
     * 管理员修改软件最新版本信息
     */
    @PostMapping("/updateSoftwareLatestInfo")
    public Result<?> updateSoftwareLatestInfo(@RequestBody UpdateSoftwareLatestInfoDTO updateSoftwareLatestInfoDTO){
        if(adminService.updateSoftwareLatestInfo(updateSoftwareLatestInfoDTO)){
            return Result.success("Success");
        }else{
            return Result.error("Failed");
        }
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

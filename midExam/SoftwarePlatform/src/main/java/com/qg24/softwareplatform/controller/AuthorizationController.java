package com.qg24.softwareplatform.controller;


import com.qg24.softwareplatform.po.dto.CheckAuthDTO;
import com.qg24.softwareplatform.po.dto.OnlineVerificationDTO;
import com.qg24.softwareplatform.po.dto.PurchaseDTO;
import com.qg24.softwareplatform.po.result.Result;
import com.qg24.softwareplatform.po.vo.DownloadUrlsVO;
import com.qg24.softwareplatform.po.vo.ShowLicenseVO;
import com.qg24.softwareplatform.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    /**
     * 用户购买授权
     * @param purchaseDTO
     * @return
     */
    @PostMapping("/purchaseAuth")
    public Result<?> purchaseAuth(@RequestBody PurchaseDTO purchaseDTO){
        boolean b = authorizationService.purchaseAuth(purchaseDTO);
        if(b){
            return Result.success("购买成功");
        }
        return Result.error("unknown");
    }

    /**
     * 检验授权
     * @param checkAuthDTO
     * @return
     */
    @PostMapping("/checkAuth")
    public Result<?> checkAuth(@RequestBody CheckAuthDTO checkAuthDTO){
        String s = authorizationService.checkAuth(checkAuthDTO);
        if("none".equals(s)){
            //没有此软件的授权
            return Result.error("没有此授权");
        }else if("outdated".equals(s)){
            //授权已过期
            return Result.error("授权已过期");
        }else if ("success".equals(s)){
            //有授权，返回下载的地址
            DownloadUrlsVO downloadUrls = authorizationService.getDownloadUrls(checkAuthDTO);
            if (downloadUrls == null){
                return Result.error("可惜了，没有下载地址");
            }
            return Result.success("可以下载", downloadUrls);
        }
        return Result.error("unknown");
    }


    /**
     * (非前端)本地软件发送信息进行服务器比对接口
     * @param onlineVerificationDTO
     * @return
     */
    @PostMapping("/onlineVertification")
    public Result<?> onlineVertification(@RequestBody OnlineVerificationDTO onlineVerificationDTO){
        boolean b = authorizationService.onlineVertification(onlineVerificationDTO);
        if(b){
            return Result.success("通过");
        }else {
            return Result.error("不通过");
        }
    }

    /**
     * 查看用户授权许可
     * @param userId
     * @return
     */

    @GetMapping("/getLicense")
    public Result<?> getLicense(@RequestParam("userId")String userId){
        List<ShowLicenseVO> showLicenseVOList = authorizationService.getLicense(userId);
        if(showLicenseVOList != null){
            return Result.success("", showLicenseVOList);
        }else {
            return Result.error("");
        }
    }





}

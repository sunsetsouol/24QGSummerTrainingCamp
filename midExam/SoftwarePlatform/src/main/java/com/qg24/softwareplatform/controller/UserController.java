package com.qg24.softwareplatform.controller;

import com.qg24.softwareplatform.po.dto.UserHomePageShowSoftwareDTO;
import com.qg24.softwareplatform.po.vo.UserHomePageShowSoftwareVO;
import com.qg24.softwareplatform.po.result.Result;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import com.qg24.softwareplatform.po.vo.UserApplicationRecordVO;
import com.qg24.softwareplatform.po.vo.UserBuySoftwareVO;
import com.qg24.softwareplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 个人页面展示个人开发的软件
     */
    @Autowired
    private UserService userService;
    @GetMapping("/user/homePageShowSoftware")
    public Result<?> homePageShowSoftware(@ModelAttribute UserHomePageShowSoftwareDTO dto){
        int total = userService.getTotalSoftware(dto.getUserId());
        List<SimpleSoftwareVO> list = userService.getHomePageShowSoftware(dto);
        UserHomePageShowSoftwareVO vo = new UserHomePageShowSoftwareVO();
        if(list!=null){
            vo.setTotal(total);
            vo.setList(list);
            return Result.success("",vo);
        }else{
            return Result.error("Failed");
        }
    }

    /**
     * 个人页面展示申请记录
     */
    @GetMapping("/applicationRecord")
    public Result<?> applicationRecord(@RequestParam("userId")String userId){
        List<UserApplicationRecordVO> list = new ArrayList<>();
        list=userService.getApplicationRecord(userId);
        if(list!=null){
            return Result.success("",list);
        }else{
            return Result.error("Failed");
        }
    }

    /**
     * 个人页面查询可购买过（授权过但是可能过期了）的软件(通过过期时间判断是否过期)
     */
    @GetMapping("/PagedQueryAvailableSoftware")
    public Result<?> PagedQueryAvailableSoftware(@RequestParam("userId")String userId){
        List<UserBuySoftwareVO> list = userService.getAvailableSoftware(userId);
        if(list!=null){
            return Result.success("",list);
        }else{
            return Result.error("Failed");
        }
    }
}

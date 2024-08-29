package com.qg24.softwareplatform.controller;

import com.qg24.softwareplatform.po.dto.NewUserInfoDTO;
import com.qg24.softwareplatform.po.dto.ShowPersonalSoftwareInfoDTO;
import com.qg24.softwareplatform.po.entity.Order;
import com.qg24.softwareplatform.po.result.PageBean;
import com.qg24.softwareplatform.po.result.Result;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import com.qg24.softwareplatform.po.vo.UserApplicationRecordVO;
import com.qg24.softwareplatform.po.vo.UserBuySoftwareVO;
import com.qg24.softwareplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    // TODO 个人开发的软件有普 专 两种 是否忽略?
    /**
     * 个人页面展示个人开发的软件
     */
    @Autowired
    private UserService userService;
    @GetMapping("/homePageShowSoftware")
    public Result<PageBean<SimpleSoftwareVO>> homePageShowSoftware(@ModelAttribute ShowPersonalSoftwareInfoDTO dto){
        int total = userService.getTotalSoftware(dto.getUserId());
        List<SimpleSoftwareVO> list = userService.getHomePageShowSoftware(dto);
        PageBean<SimpleSoftwareVO> simpleSoftwareVOPageBean = new PageBean<>();
        simpleSoftwareVOPageBean.setData(list);
        simpleSoftwareVOPageBean.setTotal((long)total);
        return Result.success("", simpleSoftwareVOPageBean);
    }

    /**
     * 个人页面展示申请记录
     */
    @GetMapping("/applicationRecord")
    public Result<?> applicationRecord(@RequestParam("userId")String userId){
        List<UserApplicationRecordVO> list;
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


    /**
     * 用户修改头像，描述，昵称
     */
    @PostMapping("/updateUserInfo")
    public Result<?> updateUserInfo(@ModelAttribute NewUserInfoDTO dto, @RequestPart(required = false) MultipartFile headImage) throws IOException {
        if(userService.uploadNewUserInfo(dto, headImage)){
            return Result.success("Success");
        }else{
            return Result.error("Failed");
        }
    }

    /**
     *  用户查看购买记录
     * @param userId
     * @return
     */
    @GetMapping("/showUserOrder")
    public Result<?> showUserOrder(@RequestParam("userId")String userId){
        List<Order> orderList = userService.showUserOrder(userId);
        if(orderList!=null){
            return Result.success("",orderList);
        }else {
            return Result.error("Failed");
        }

    }
}

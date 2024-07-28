package com.qg24.softwareplatform.controller;


import com.qg24.softwareplatform.mapper.HardwareMapper;
import com.qg24.softwareplatform.po.entity.UserHardware;
import com.qg24.softwareplatform.po.result.Result;
import com.qg24.softwareplatform.po.vo.FingerprintVO;
import com.qg24.softwareplatform.service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hardware")
public class HardwareController {

    @Autowired
    private HardwareService hardwareService;

    /**
     * 用户上传硬件指纹信息
     * @param userHardware
     * @return
     */
    @PostMapping("/insertFingerprint")
    public Result<?> insertFingerprint(@RequestBody UserHardware userHardware){
        //用不用判断用户硬件指纹的上限

        //执行业务
        int i = hardwareService.insertFingerprint(userHardware);
        if (i != 0 ){
            return Result.success("上传成功");
        } else if(i == 0){
            return Result.error("上传失败");
        }
        return Result.error("unknown");
    }

    /**
     * 用户删除硬件指纹信息
     * @param useHardwareId
     * @return
     */
    @DeleteMapping("/deleteFingerprint")
    public Result<?> deleteFingerprint(@RequestParam("useHardwareId") int useHardwareId){
        boolean b = hardwareService.deleteFingerprintByUseHardwareId(useHardwareId);
        if(b){
            return Result.success("删除该指纹信息成功");
        }else {
            return Result.error("删除该指纹信息失败");
        }
    }


    /**
     * 查询用户指纹信息
     * @param userId
     * @return
     */
    @GetMapping("/getFingerprint")
    public Result<?> getFingerprint(@RequestParam("userId") String userId){
        List<FingerprintVO> list = hardwareService.selectFingerprintsByUserId(userId);
        //判断是否有指纹信息
        if (list == null){
            return Result.error("用户还未存入指纹信息");
        }else {
            return Result.success("success" , list);
        }
    }


}

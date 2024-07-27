package com.qg24.softwareplatform.controller;


import com.qg24.softwareplatform.po.result.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hardware")
public class HardwareController {


    //上传指纹信息
//    @PostMapping("/insertFingerprint")
//    public Result<?> insertFingerprint(){
//
//    }

    //删除硬件指纹
    @DeleteMapping("/deleteFingerprint")
    public Result<?> deleteFingerprint(@RequestParam("useHardwareId") int useHardwareId){

    }


    //回显指纹信息
    @GetMapping("/getFingerprint")
    public Result<?> getFingerprint(@RequestParam("userId") String userId){

    }



}

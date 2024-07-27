package com.qg24.softwareplatform.controller;

import com.qg24.softwareplatform.po.dto.UpdateSoftwareLatestInfoDTO;
import com.qg24.softwareplatform.po.dto.UploadNewSoftwareDTO;
import com.qg24.softwareplatform.po.result.Result;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;

@RestController
@RequestMapping("/software")
public class SoftwareController {

    //首页分页展示软件
    @GetMapping("/homePageShowSoftware")
    public Result<?> homePageShowSoftware(@RequestParam("page") int page, @RequestParam("softwareName") String softwareName, @RequestParam("tag") Array tag){

    }

    //软件热门排行
    @GetMapping("/softwareRanking")
    public Result<?> softwareRanking(){

    }


    //软件详情页 上半部分基本软件信息
    @GetMapping("/basicSoftwareInfo")
    public Result<?> basicSoftwareInfo(@RequestParam("softwareId") int softwareId){

    }


    //软件详情页 下半部分 普通/专业 软件信息
    @GetMapping("/detailedSoftwareInfo")
    public Result<?> detailedSoftwareInfo(@RequestParam("softwareId") int softwareId){

    }

    //软件详情页下半 历史查看
    @GetMapping("/historySoftwareVersion")
    public Result<?> historySoftwareVersion(@RequestParam("softwareId") int softwareId, @RequestParam("softwareVersionType") int softwareVersionType){

    }


    //上传软件
    @PostMapping("/upload")
    public Result<?> upload(@RequestBody UploadNewSoftwareDTO uploadNewSoftwareDTO,
                            @RequestParam("winPackage") MultipartFile winPackage,
                            @RequestParam("linuxPackage") MultipartFile linuxPackage,
                            @RequestParam("macPackage") MultipartFile macPackage){

    }

    //更新软件
    @PostMapping("/updateVersion")
    public Result<?> updateVersion(@RequestBody UpdateSoftwareLatestInfoDTO updateSoftwareLatestInfoDTO,
                                   @RequestParam("winPackage") MultipartFile winPackage,
                                   @RequestParam("linuxPackage") MultipartFile linuxPackage,
                                   @RequestParam("macPackage")MultipartFile macPackage){

    }

}

package com.qg24.softwareplatform.controller;

import com.qg24.softwareplatform.po.dto.*;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.result.PageBean;
import com.qg24.softwareplatform.po.result.Result;
import com.qg24.softwareplatform.po.vo.DetailedSoftwareVersionTypeVO;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import com.qg24.softwareplatform.po.vo.SoftwareHistoryVersionDownloadVO;
import com.qg24.softwareplatform.service.SoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/software")
public class SoftwareController {

    @Autowired
    private SoftwareService softwareService;
    //首页分页展示软件
    @GetMapping("/homePageShowSoftware")
    public Result<PageBean<SimpleSoftwareVO>> homePageShowSoftware(@RequestParam HomePageShowSoftwareDTO homePageShowSoftwareDTO){
        PageBean<SimpleSoftwareVO> pageBean = softwareService.homePageShowSoftware(homePageShowSoftwareDTO);
        return Result.success("", pageBean);
    }

    //软件热门排行
    @GetMapping("/softwareRanking")
    public Result<?> softwareRanking(){
        List<SimpleSoftwareVO> softwareVOS = softwareService.softwareRanking();
        if (softwareVOS.isEmpty()){
            return Result.error("");
        }else {
            return Result.success("",softwareVOS);
        }


    }


    //软件详情页 上半部分基本软件信息
    @GetMapping("/basicSoftwareInfo")
    public Result<?> basicSoftwareInfo(@RequestParam("softwareId") int softwareId){
        Software software = softwareService.basicSoftwareInfo(softwareId);
        if (software != null ){
            return Result.success("",software);
        }else {
            return Result.error("");
        }

    }


    //软件详情页 下半部分 普通/专业 软件信息
    @GetMapping("/detailedSoftwareInfo")
    public Result<?> detailedSoftwareInfo(@RequestParam("softwareId") int softwareId){
        List<DetailedSoftwareVersionTypeVO> detailedSoftwareVersionTypeVOList = softwareService.detailedSoftwareInfo(softwareId);
        if (detailedSoftwareVersionTypeVOList.isEmpty()){
            return Result.error("");
        }else {
            return Result.success("",detailedSoftwareVersionTypeVOList);
        }
    }

    //软件详情页下半 历史查看
    @GetMapping("/historySoftwareVersion")
    public Result<?> historySoftwareVersion(@RequestParam HistorySoftwareVersionDTO historySoftwareVersionDTO){
        List<SoftwareHistoryVersionDownloadVO> softwareHistoryVersionDownloadVOList = softwareService.historySoftwareVersion(historySoftwareVersionDTO);
        if (softwareHistoryVersionDownloadVOList.isEmpty()){
            return Result.error("");
        }else {
            return Result.success("",softwareHistoryVersionDownloadVOList);
        }
    }


    //上传软件/更新软件
    @PostMapping("/upload")
    public Result<?> upload(@ModelAttribute UploadNewSoftwareDTO uploadNewSoftwareDTO){
         if (softwareService.uploadNewSoftware(uploadNewSoftwareDTO) != 0){
             return Result.success("",null);
         }else {
             return Result.error("");
         }

    }


}

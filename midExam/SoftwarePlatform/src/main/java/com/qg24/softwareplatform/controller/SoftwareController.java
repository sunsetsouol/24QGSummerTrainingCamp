package com.qg24.softwareplatform.controller;

import com.qg24.softwareplatform.po.dto.HistorySoftwareVersionDTO;
import com.qg24.softwareplatform.po.dto.HomePageShowSoftwareDTO;
import com.qg24.softwareplatform.po.dto.UploadNewSoftwareDTO;
import com.qg24.softwareplatform.po.dto.UserDownloadSoftwareDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.result.PageBean;
import com.qg24.softwareplatform.po.result.Result;
import com.qg24.softwareplatform.po.vo.*;
import com.qg24.softwareplatform.service.SoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/software")
public class SoftwareController {

    @Autowired
    private SoftwareService softwareService;

    // 首页分页展示软件
    @GetMapping("/homePageShowSoftware")
    public Result<PageBean<SimpleSoftwareVO>> homePageShowSoftware(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, @RequestParam(value = "softwareName", required = false) String softwareName, @RequestParam(value = "tags[]", required = false) List<String> tags) {
        HomePageShowSoftwareDTO homePageShowSoftwareDTO = new HomePageShowSoftwareDTO(page, pageSize, softwareName, tags);
        PageBean<SimpleSoftwareVO> pageBean = softwareService.homePageShowSoftware(homePageShowSoftwareDTO);
        return Result.success("", pageBean);
    }

    // 软件热门排行
    @GetMapping("/softwareRanking")
    public Result<?> softwareRanking() {
        List<SimpleSoftwareVO> softwareVOS = softwareService.softwareRanking();
        if (softwareVOS.isEmpty()) {
            return Result.error("");
        } else {
            return Result.success("", softwareVOS);
        }


    }

    // 软件详情页 上半部分基本软件信息
    @GetMapping("/basicSoftwareInfo")
    public Result<?> basicSoftwareInfo(@RequestParam("softwareId") int softwareId) {
        Software software = softwareService.basicSoftwareInfo(softwareId);
        if (software != null) {
            return Result.success("", software);
        } else {
            return Result.error("");
        }

    }


    // 软件详情页 下半部分 普通/专业 软件信息
    @GetMapping("/detailedSoftwareInfo")
    public Result<?> detailedSoftwareInfo(@RequestParam("softwareId") int softwareId) {
        List<DetailedSoftwareVersionTypeVO> detailedSoftwareVersionTypeVOList = softwareService.detailedSoftwareInfo(softwareId);
        if (detailedSoftwareVersionTypeVOList.isEmpty()) {
            return Result.error("");
        } else {
            return Result.success("", detailedSoftwareVersionTypeVOList);
        }
    }

    // 软件详情页下半 历史查看
    @GetMapping("/historySoftwareVersion")
    public Result<List<SoftwareHistoryVersionDownloadVO>> historySoftwareVersion(@ModelAttribute HistorySoftwareVersionDTO historySoftwareVersionDTO) {
        List<SoftwareHistoryVersionDownloadVO> softwareHistoryVersionDownloadVOList = softwareService.historySoftwareVersion(historySoftwareVersionDTO);
        if (softwareHistoryVersionDownloadVOList.isEmpty()) {
            return Result.error("");
        } else {
            return Result.success("", softwareHistoryVersionDownloadVOList);
        }
    }


    // 上传软件/更新软件
    @PostMapping("/upload")
    public Result<?> upload(@ModelAttribute UploadNewSoftwareDTO uploadNewSoftwareDTO,
                            @RequestPart(required = false) MultipartFile winPackage,
                            @RequestPart(required = false) MultipartFile linuxPackage,
                            @RequestPart(required = false) MultipartFile macPackage,
                            @RequestPart(required = false) MultipartFile softwareImage) throws IOException {
        if (softwareService.uploadNewSoftware(uploadNewSoftwareDTO, winPackage, linuxPackage, macPackage, softwareImage) != 0) {
            return Result.success("", null);
        } else {
            return Result.error("");
        }

    }

    //用户查看需要购买的软件信息
    @GetMapping("/showRequiredAuthSoftware")
    public Result<?> showRequiredAuthSoftware(@RequestParam("userId") String userId,@RequestParam("page") int page) {
        List<ShowRequiredAuthSoftwareVO> showRequiredAuthSoftwareVOList = softwareService.showRequiredAuthSoftware(userId,page);
        if (showRequiredAuthSoftwareVOList.isEmpty()) {
            return Result.error("");
        } else {
            return Result.success("", showRequiredAuthSoftwareVOList);
        }
    }

    /**
     * 用户下载后保存此次下载记录,或者
     *
     * @param userDownloadSoftwareDTO
     * @return
     */
    @PostMapping("/downloadSoftware")
    public Result<?> downloadSoftware(@RequestBody UserDownloadSoftwareDTO userDownloadSoftwareDTO) {
        int i = softwareService.addOrUpdateUserSoftwareDownload(userDownloadSoftwareDTO);
        if (i == 0) {
            return Result.error("操作失败");
        } else if (i != 0) {
            return Result.success("操作成功");
        }
        return Result.error("unknown"); //未知操作
    }

    /**
     * 查看用户是否有可更新的软件
     * @param userId
     * @return
     */
    @GetMapping("/checkLatestSoftware")
    public Result<?> checkLatestSoftware (@RequestParam("userId") String userId){
        List<CheckLastestSoftwareVO> checkLastestSoftwareVOS = softwareService.checkLatestSoftware(userId);
        if (checkLastestSoftwareVOS == null){
            return Result.success("没有可更新的软件");
        }else {
            return Result.success("有可更新的软件", checkLastestSoftwareVOS);
        }
    }

    /**
     * 判断某个软件是否有专业版本
     * @param softwareId
     * @return
     */
    @GetMapping("/judgeWhetherHavaPro")
    public Result<?> judgeWhetherHavaPro(@RequestParam("softwareId") int softwareId){
        boolean b = softwareService.judgeWhetherHavaPro(softwareId);
        if (b == true){
            return Result.success();
        }else {
            return Result.error("error");
        }
    }
}
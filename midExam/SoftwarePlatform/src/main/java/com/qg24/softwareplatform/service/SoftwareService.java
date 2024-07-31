package com.qg24.softwareplatform.service;

import com.qg24.softwareplatform.po.dto.HistorySoftwareVersionDTO;
import com.qg24.softwareplatform.po.dto.HomePageShowSoftwareDTO;
import com.qg24.softwareplatform.po.dto.UploadNewSoftwareDTO;
import com.qg24.softwareplatform.po.dto.UserDownloadSoftwareDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.result.PageBean;
import com.qg24.softwareplatform.po.vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SoftwareService {
    PageBean<SimpleSoftwareVO> homePageShowSoftware(HomePageShowSoftwareDTO homePageShowSoftwareDTO);

    List<SimpleSoftwareVO> softwareRanking();

    Software basicSoftwareInfo(int softwareId);

    List<DetailedSoftwareVersionTypeVO> detailedSoftwareInfo(int softwareId);

    List<SoftwareHistoryVersionDownloadVO> historySoftwareVersion(HistorySoftwareVersionDTO historySoftwareVersionDTO);

    int uploadNewSoftware(UploadNewSoftwareDTO uploadNewSoftwareDTO, MultipartFile winPackage, MultipartFile linuxPackage, MultipartFile macPackage, MultipartFile softwareImage) throws IOException;

    List<ShowRequiredAuthSoftwareVO> showRequiredAuthSoftware(String softwareId,int page);

    //用户添加/更新新的下载记录
    int addOrUpdateUserSoftwareDownload(UserDownloadSoftwareDTO userDownloadSoftwareDTO);

    //查看用户有无可更新的软件
    List<CheckLastestSoftwareVO> checkLatestSoftware(String userId);

}

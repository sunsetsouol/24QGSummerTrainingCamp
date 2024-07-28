package com.qg24.softwareplatform.service;

import com.qg24.softwareplatform.po.dto.HistorySoftwareVersionDTO;
import com.qg24.softwareplatform.po.dto.HomePageShowSoftwareDTO;
import com.qg24.softwareplatform.po.dto.UploadNewSoftwareDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.result.PageBean;
import com.qg24.softwareplatform.po.vo.DetailedSoftwareVersionTypeVO;
import com.qg24.softwareplatform.po.vo.ShowRequiredAuthSoftwareVO;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import com.qg24.softwareplatform.po.vo.SoftwareHistoryVersionDownloadVO;

import java.io.IOException;
import java.util.List;

public interface SoftwareService {
    PageBean<SimpleSoftwareVO> homePageShowSoftware(HomePageShowSoftwareDTO homePageShowSoftwareDTO);

    List<SimpleSoftwareVO> softwareRanking();

    Software basicSoftwareInfo(int softwareId);

    List<DetailedSoftwareVersionTypeVO> detailedSoftwareInfo(int softwareId);

    List<SoftwareHistoryVersionDownloadVO> historySoftwareVersion(HistorySoftwareVersionDTO historySoftwareVersionDTO);

    int uploadNewSoftware(UploadNewSoftwareDTO uploadNewSoftwareDTO) throws IOException;

    List<ShowRequiredAuthSoftwareVO> showRequiredAuthSoftware(int softwareId);
}

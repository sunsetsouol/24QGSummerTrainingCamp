package com.qg24.softwareplatform.service;

import com.qg24.softwareplatform.po.dto.HomePageShowSoftwareDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;

import java.util.List;

public interface SoftwareService {
    List<SimpleSoftwareVO> homePageShowSoftware(HomePageShowSoftwareDTO homePageShowSoftwareDTO);

    List<SimpleSoftwareVO> softwareRanking();

    Software basicSoftwareInfo(int softwareId);
}

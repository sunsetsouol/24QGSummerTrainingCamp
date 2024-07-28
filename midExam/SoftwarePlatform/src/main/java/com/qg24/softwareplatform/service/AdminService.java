package com.qg24.softwareplatform.service;

import com.qg24.softwareplatform.po.dto.UpdateSoftwareLatestInfoDTO;
import com.qg24.softwareplatform.po.entity.Software;

public interface AdminService {
    boolean updateSoftwareBasicInfo(Software software);//更新基本软件信息
    boolean updateSoftwareLatestInfo(UpdateSoftwareLatestInfoDTO softwareLatestInfoDTO);
}

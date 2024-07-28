package com.qg24.softwareplatform.service;

import com.qg24.softwareplatform.po.dto.UpdateSoftwareLatestInfoDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.entity.SoftwareInfoTemp;

import java.util.List;

public interface AdminService {
    boolean updateSoftwareBasicInfo(Software software);//更新基本软件信息
    boolean updateSoftwareLatestInfo(UpdateSoftwareLatestInfoDTO softwareLatestInfoDTO);//更新最新软件信息
    List<SoftwareInfoTemp> getSoftwareInfoTempList(int page, int pageSize);//分页查询审核记录

    boolean updateSoftwareInfoTempStatus(int softwareInfoTempId,int status);//更新审核通过状态码
}

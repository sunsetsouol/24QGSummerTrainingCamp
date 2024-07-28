package com.qg24.softwareplatform.mapper;

import com.qg24.softwareplatform.po.dto.UpdateSoftwareLatestInfoDTO;
import com.qg24.softwareplatform.po.dto.UserLicenseDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.entity.UserSoftwareLicense;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface AdminMapper {
    //更新软件表的基本信息(软件创始人，简要描述，标签)
    int updateSoftWareBasicInfo(Software software);

    //更新软件版本下载表信息(详细信息)
    int updateSoftwareLatestInfo(UpdateSoftwareLatestInfoDTO updateSoftwareLatestInfoDTO);
}

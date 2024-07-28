package com.qg24.softwareplatform.mapper;

import com.qg24.softwareplatform.po.dto.UpdateSoftwareLatestInfoDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.entity.SoftwareInfoTemp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    //更新软件表的基本信息(软件创始人，简要描述，标签)
    int updateSoftWareBasicInfo(Software software);

    //更新软件版本下载表信息(详细信息)
    int updateSoftwareLatestInfo(UpdateSoftwareLatestInfoDTO updateSoftwareLatestInfoDTO);

    //分页查询管理员审查记录
    List<SoftwareInfoTemp> getSoftwareInfoTempPages(@Param("limit") int limit,@Param("offset")int offset);

    //管理员审查版本,更改审查记录的状态码
    int updateSoftwareInfoTemp(@Param("softwareInfoTempId") int softwareInfoTempId, @Param("status") int status);
}

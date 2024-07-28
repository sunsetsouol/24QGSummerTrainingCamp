package com.qg24.softwareplatform.mapper;

import com.qg24.softwareplatform.po.dto.UpdateSoftwareLatestInfoDTO;
import com.qg24.softwareplatform.po.dto.UserLicenseDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.entity.SoftwareInfoTemp;
import com.qg24.softwareplatform.po.entity.SoftwareVersionDownload;
import com.qg24.softwareplatform.po.entity.UserSoftwareLicense;
import org.apache.ibatis.annotations.*;

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

    //找到审查记录
    @Select("select * from software_info_temp where softwareInfoTempId=#{softwareInfoTempId}")
    SoftwareInfoTemp getSoftwareInfoTemp(int softwareInfoTempId);

    //插入新软件信息到软件表(返回主键)
    @Options(useGeneratedKeys = true, keyProperty = "software_id")
    @Insert("insert into software (author,userId,tags,description,softwareImage,createTime) values (#{author},#{userId},#{description},#{softwareImage},#{createTime})")
    int insertNewSoftwareTable(Software software);

    //插入新软件信息到软件历史版本下载表
    @Insert("insert into software_version_download (softwareId,versionType,version,price,winUrl,linuxUrl,macUrl,creatTime,detailedDescription) " +
            "values (#{softwareId},#{versionType},#{version},#{price}.#{WinUrl},#{LinuxUrl},#{MacUrl},#{creatTime},#{detailedDescription})")
    int insertNewSoftwareDownloadTable(SoftwareVersionDownload softwareVersionDownload);
}

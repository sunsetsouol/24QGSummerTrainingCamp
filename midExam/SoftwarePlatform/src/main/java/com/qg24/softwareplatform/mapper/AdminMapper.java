package com.qg24.softwareplatform.mapper;

import com.qg24.softwareplatform.po.dto.UpdateSoftwareLatestInfoDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.entity.SoftwareInfoTemp;
import com.qg24.softwareplatform.po.entity.SoftwareVersionDownload;
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
    @Result(property = "tagsString",column = "tags")
    @Select("select * from software_info_temp where software_info_temp_id=#{softwareInfoTempId}")
    SoftwareInfoTemp getSoftwareInfoTemp(int softwareInfoTempId);

    //插入新软件信息到软件表(返回主键)
    @Options(useGeneratedKeys = true, keyProperty = "softwareId")
    @Insert("insert into software (software_name,author,user_id,tags,description,software_image,create_time) " +
            "values (#{softwareName},#{author},#{userId},#{tagsString},#{description},#{softwareImage},#{createTime})")
    int insertNewSoftwareTable(Software software);

    //插入新软件信息到软件历史版本下载表
    @Insert("insert into software_version_download (software_id,version_type,version,price,win_url,linux_url,mac_url,create_time,detailed_description) " +
            "values (#{softwareId},#{versionType},#{version},#{price},#{winUrl},#{linuxUrl},#{macUrl},#{createTime},#{detailedDescription})")
    int insertNewSoftwareDownloadTable(SoftwareVersionDownload softwareVersionDownload);

    //查software表的softwareId
    @Select("select software_id from software where software_name=#{softwareName}")
    int getSoftwareId(String softwareName);
}

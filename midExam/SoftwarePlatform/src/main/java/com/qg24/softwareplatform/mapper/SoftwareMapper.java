package com.qg24.softwareplatform.mapper;

import com.qg24.softwareplatform.po.dto.HistorySoftwareVersionDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.entity.SoftwareInfoTemp;
import com.qg24.softwareplatform.po.entity.SoftwareVersionDownload;
import com.qg24.softwareplatform.po.vo.ShowRequiredAuthSoftwareVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface SoftwareMapper {


    /**
     * 主页分页查询软件，模糊查询
     * @return List<Software>
     */
    @Result(property = "tagString", column = "tags")
    List<Software> pagedQuerySoftwareBySoftNameAndTags(String softName, int offset, int pageSize);

    /**
     * 根据下载量判断软件最热排行
     * @return
     */
    @Select("select * from software order by download_count desc limit 10")
    List<Software> querySoftwareRankByDownload();

    /**
     * 通过软件id查找软件表的软件基本信息
     * @param softwareId
     * @return
     */
    @Result(property = "tagsString",column = "tags")
    @Select("select * from software where software_id = #{softwareId}")
    Software querySoftwareInfoBySoftId(@RequestParam("softwareId") int softwareId);

    /**
     * 通过软件id查找最新的普通版本的软件详细信息
     * @param softwareId
     * @return
     */
    @Select("select * from software_version_download where software_id = #{softwareId} And version_type = 0 order by create_time desc limit 1")
    SoftwareVersionDownload queryLastestOrdinaryDetaliedSoftware(int softwareId);

    /**
     * 通过软件id查找最新的专业版本的软件详细信息
     * @param softwareId
     * @return
     */
    @Select("select * from software_version_download where software_id = #{softwareId} And version_type = 1 order by create_time desc limit 1")
    SoftwareVersionDownload queryLastestProfessionalDetaliedSoftware(int softwareId);

    /**
     * 通过软件id和软件版本查找软件的历史下载版本(号)地址
     * @param historySoftwareVersionDTO
     * @return
     */
    @Select("select * from software_version_download where software_id = #{softwareId} And version_type = #{versionType} order by create_time desc")
    List<SoftwareVersionDownload> querySoftwareVersionDownloadBySoftwareIdAndVersionType(HistorySoftwareVersionDTO historySoftwareVersionDTO);

    /**
     * 新增软件信息申请表（上传新软件/更新新版本）
     * @param softwareInfoTemp
     * @return
     */
    @Insert("insert into software_info_temp(user_id,software_name,version_type,version,brief_description," +
            "detailed_description,win_url,linux_url,mac_url,tags,type_status,passed_status,author) " +
            "values(#{userId},#{softwareName},#{versionType},#{version},#{briefDescription},#{detailedDescription}," +
            "#{winUrl},#{linuxUrl},#{macUrl},#{tagsString},#{typeStatus},#{passedStatus},#{author})")
    int addSoftwareInfoTemp(SoftwareInfoTemp softwareInfoTemp);


    List<ShowRequiredAuthSoftwareVO> querySoftwareVersionDownloadUserNoAuth(@Param("userId") int userId);

    @Select("select software_id, version_type from user_software_auth where user_id = #{userId}")
    List<Map<String, Object>> select(String userId);

    int getTotal(@Param("softwareName") String softwareName, @Param("tags") List<String> tags);
}

package com.qg24.softwareplatform.mapper;

import com.qg24.softwareplatform.po.dto.HistorySoftwareVersionDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.entity.SoftwareVersionDownload;
import com.qg24.softwareplatform.po.vo.SoftwareHistoryVersionDownloadVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface SoftwareMapper {


    /**
     * 主页分页查询软件，模糊查询，标签查询
     * @return List<Software>
     */
//    List<Software> pagedQuerySoftwareBySoftNameAndTages();

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

}

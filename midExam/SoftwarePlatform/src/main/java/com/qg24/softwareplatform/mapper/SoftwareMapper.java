package com.qg24.softwareplatform.mapper;

import com.qg24.softwareplatform.po.entity.Software;
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
}

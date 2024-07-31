package com.qg24.softwareplatform.po.vo;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleSoftwareVO {
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 下载量
     */
    private double downloadCount;
    /**
     * 软件ID
     */
    private int softwareId;
    /**
     * 软件图标url
     */
    private String softwareImage;
    /**
     * 软件名
     */
    private String softwareName;

    private List<String> tags;

    private String description;

    private String version;

    private String tagsString;


    //将tags转为string便于存入mysql
    public void TagsToString(){
        if(tags != null){
            tagsString = JSON.toJSONString(tags);
        }
    }

    //将tagsString转为list
    public void TagsToList(){
        if(tagsString != null){
            tags = JSON.parseArray(tagsString,String.class);
            tagsString = null;
        }
    }


}

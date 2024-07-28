package com.qg24.softwareplatform.po.entity;


import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Software {

    private String author;

    private String description;

    private int downloadCount;

    private int softwareId;

    private String softwareName;

    private int status;

    private List<String> tags;

    private String softwareImage;

    private String tagsString;

    private String createTime;

    //将tags转为string便于存入mysql
    public void TagsToString(){
        if(tags!=null){
            tagsString= JSON.toJSONString(tags);
        }
    }

    //将tagsString转为list
    public void TagsToList(){
        if(tagsString!=null){
            tags=JSON.parseArray(tagsString,String.class);
        }
    }
}

package com.qg24.softwareplatform.po.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftwareInfoTemp {

    private int softwareInfoTempId;

    private String applicationTime;

    private String author;
 
    private String detailedDescription;

    private String macUrl;

    private String winUrl;

    private String linuxUrl;

    private int passedStatus;
 
    private String briefDescription;
  
    private String softwareName;

    private String version;
  
    private int versionType;

    private List<String> tags;
 
    private int typeStatus;
 
    private String userId;

    private String tagsString;

}

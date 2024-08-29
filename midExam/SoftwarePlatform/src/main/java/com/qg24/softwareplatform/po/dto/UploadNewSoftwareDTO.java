package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 上传文件接收数据模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadNewSoftwareDTO {

    private String userId;

    private String softwareName;

    private int versionType;

    private String version;

    private String briefDescription;

    private String detailedDescription;

    private String author;

    private List<String> tags;

    private int typeStatus;

    private Float price;



}

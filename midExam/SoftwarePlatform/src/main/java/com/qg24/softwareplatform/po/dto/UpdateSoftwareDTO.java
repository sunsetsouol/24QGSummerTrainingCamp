package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 上传文件接收数据模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSoftwareDTO {
    private String softwareName;

    private int versionType;

    private String version;

    private String detailedDescription;

    private String author;

    private String applicationTime;

    private MultipartFile winPackage;

    private MultipartFile linuxPackage;

    private MultipartFile macPackage;

}

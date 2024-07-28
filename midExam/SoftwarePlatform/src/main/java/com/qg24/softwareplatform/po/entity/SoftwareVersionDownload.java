package com.qg24.softwareplatform.po.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftwareVersionDownload {
    private int softwareVersionDownloadId;

    private String createTime;

    private String detailedDescription;

    private double price;

    private int softwareId;

    private String version;

    private int versionType;

    private String winUrl;

    private String linuxUrl;

    private String macUrl;

}

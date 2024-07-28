package com.qg24.softwareplatform.po.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftwareVersionDownload {
    private int softVersionId;

    private String createTime;

    private String detailedDescription;

    private String linuxUrl;

    private String macUrl;

    private double price;

    private int softId;

    private String version;

    private int versionType;

    private int winUrl;

}

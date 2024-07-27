package com.qg24.softwareplatform.po.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSoftwareDownload {

    private int softwareId;

    private String userId;

    private int userSoftwareDownloadId;

    private String version;

    private int versionType;

}

package com.qg24.softwareplatform.po.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSoftwareLicense {

    private String authTime;

    private String expiredTime;

    private String fingerprint;

    private String softwareList;

    private String userId;

    private int userSoftwareLicenseId;

    private String licenseUrl; //许可文件的地址
}

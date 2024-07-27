package com.qg24.softwareplatform.po.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSoftwareAuth {

    private String expiredTime;

    private int softwareId;

    private String userId;

    private int userSoftwareAuthId;

    private int versionType;
}

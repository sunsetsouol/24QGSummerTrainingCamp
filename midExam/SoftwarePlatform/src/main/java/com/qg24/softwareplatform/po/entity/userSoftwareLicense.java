package com.qg24.softwareplatform.po.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userSoftwareLicense {

    private String authTime;

    private String expirationTime;

    private String fingerprint;

    private String softwareList;

    private String userId;

    private int userSoftwareLicenseId;
}

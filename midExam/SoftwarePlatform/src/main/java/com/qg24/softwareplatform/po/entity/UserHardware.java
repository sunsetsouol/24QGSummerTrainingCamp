package com.qg24.softwareplatform.po.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHardware {

    private String fingerprint;

    private String hardwareName;

    private int userHardwareId;

    private String userId;
}

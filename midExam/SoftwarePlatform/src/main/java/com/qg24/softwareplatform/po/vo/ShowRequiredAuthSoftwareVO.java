package com.qg24.softwareplatform.po.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowRequiredAuthSoftwareVO {

    private int versionType;

    private int softwareId;

    private String softwareName;

    private String createTime;

    private double price;


}

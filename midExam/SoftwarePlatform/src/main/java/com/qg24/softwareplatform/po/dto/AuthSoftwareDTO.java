package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 授权时每一个软件的信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthSoftwareDTO {
    /**
     * 软件id
     */
    private int softwareId;

    /**
     * 软件名
     */
    private String softwareName;

    /**
     * 软件版本
     * 普通0
     * 专业1
     */
    private int versionType;
}

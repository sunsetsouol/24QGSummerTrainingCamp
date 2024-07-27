package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员修改最新版本软件描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSoftwareLatestInfoDTO {
    /**
     * 软件id
     */
    private int softwareId;

    /**
     * 普通0
     * 专业1
     */
    private int versionType;

    /**
     * 版本号
     */
    private String version;

    /**
     * 版本号下软件的详细描述
     */
    private String detailedDescription;
}

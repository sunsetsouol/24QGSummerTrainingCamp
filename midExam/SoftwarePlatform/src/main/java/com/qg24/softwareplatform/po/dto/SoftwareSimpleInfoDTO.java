package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 软件简要信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftwareSimpleInfoDTO {
    /**
     * 软件id
     */
    private String softwareId;
    /**
     * 软件名
     */
    private String softwareName;
    /**
     * 软件版本(普/专)
     */
    private String versionType;
}

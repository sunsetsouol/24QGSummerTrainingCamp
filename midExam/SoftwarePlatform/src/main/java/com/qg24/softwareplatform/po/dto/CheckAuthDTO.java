package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckAuthDTO {

    private int softwareId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 版本号
     */
    private String version;
    /**
     * 普0/专1
     */
    private int versionType;

}

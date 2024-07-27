package com.qg24.softwareplatform.po.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBuySoftwareVO {
    /**
     * 过期时间
     */
    private String outdateTime;
    /**
     * 软件名称
     */
    private String softwareName;
    /**
     * 版本
     */
    private String versionType;
}

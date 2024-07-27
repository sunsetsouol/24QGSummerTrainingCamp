package com.qg24.softwareplatform.po.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 软件详情页 下半部分 依据版本(普/专) 软件基本信息
 */
public class DetailedSoftwareVersionTypeVO {
    /**
     * 具体描述
     */
    private String description;
    /**
     * 版本号
     */
    private String softwareVersion;
    /**
     * 普/专
     */
    private long VersionType;
}

package com.qg24.softwareplatform.po.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private long softwareVersionType;
}

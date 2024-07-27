package com.qg24.softwareplatform.po.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoftwareHistoryVersionDownloadVO {
    /**
     * 创建时间
     */
    private String createTime;
    /**
    软件版本号
     */
    private String softwareVersion;
}

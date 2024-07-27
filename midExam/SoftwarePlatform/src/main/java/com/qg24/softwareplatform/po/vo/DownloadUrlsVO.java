package com.qg24.softwareplatform.po.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadUrlsVO {
    /**
     * Linux系统下载地址
     */
    private String linuxUrl;
    /**
     * Mac系统下载地址
     */
    private String macUrl;
    /**
     * Windows系统下载地址
     */
    private String windowsUrl;
}

package com.qg24.softwareplatform.po.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 点击下载之后返回历史版本和最新版本的所有url
 */
public class DownloadUrlsVO {
    /**
     * Linux系统下载地址
     */
    private String LinuxUrl;
    /**
     * Mac系统下载地址
     */
    private String MacUrl;
    /**
     * Windows系统下载地址
     */
    private String WindowsUrl;
}

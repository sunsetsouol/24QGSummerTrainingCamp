package com.qg24.softwareplatform.po.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 主页/排行榜 软件简要信息
 */
public class SimpleSoftwareVO {
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 下载量
     */
    private double downloadCount;
    /**
     * 软件ID
     */
    private long softwareId;
    /**
     * 软件图标url
     */
    private String softwareImage;
    /**
     * 软件名
     */
    private String softwareName;
}

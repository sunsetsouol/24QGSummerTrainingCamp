package com.qg24.softwareplatform.po.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 用户查看自己的申请记录
 */
public class UserApplicationRecordVO {
    /**
     * 创建时间
     */
    private String applicationTime;
    /**
     * 创作者
     */
    private String author;
    /**
     * 具体描述
     */
    private String detailedDescription;
    /**
     * 通过状态码(0代办/1通过/2拒绝)
     */
    private String passedStatus;
    /**
     * 软件名
     */
    private String softwareName;
    /**
     * 标签
     */
    private List<String> tags;
    /**
     * 类型状态码(0上传/1更新)
     */
    private String typeStatus;
    /**
     * 版本号
     */
    private String version;
    /**
     * 版本(普通0/专业1)
     */
    private String versionType;
}

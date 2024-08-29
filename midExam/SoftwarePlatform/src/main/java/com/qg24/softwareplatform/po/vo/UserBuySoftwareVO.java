package com.qg24.softwareplatform.po.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 用户授权过(买过的)软件，过不过期都展示，前端判断是否过期，进行提示
 */
public class UserBuySoftwareVO {
    /**
     * 过期时间
     */
    private String expiredTime;
    /**
     * 软件名称
     */
    private String softwareName;
    /**
     * 版本
     */
    private int versionType;

    private String description;

    private String softwareImage;

}

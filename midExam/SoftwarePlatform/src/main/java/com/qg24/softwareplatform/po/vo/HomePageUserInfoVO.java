package com.qg24.softwareplatform.po.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 首页个人信息视图
 */
public class HomePageUserInfoVO {
    /**
     * 头像url
     */
    private String image;
    /**
     * 用户名
     */
    private String username;

    private String description;
}

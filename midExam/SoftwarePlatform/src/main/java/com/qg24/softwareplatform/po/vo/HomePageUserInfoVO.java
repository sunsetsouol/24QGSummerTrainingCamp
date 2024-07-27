package com.qg24.softwareplatform.po.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomePageUserInfoVO {
    /**
     * 头像url
     */
    private String image;
    /**
     * 用户名
     */
    private String username;
}

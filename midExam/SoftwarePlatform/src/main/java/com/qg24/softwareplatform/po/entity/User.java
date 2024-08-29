package com.qg24.softwareplatform.po.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String createTime;

    private String email;

    private String image;

    private String password;

    private String description;

    private String updateTime;

    private String userId;

    private String username;
}

package com.qg24.softwareplatform.po.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndToken {
    /**
     * 登陆后返回的token
     */
    private String token;

    /**
     * 用户id
     */
    private String userId;
}


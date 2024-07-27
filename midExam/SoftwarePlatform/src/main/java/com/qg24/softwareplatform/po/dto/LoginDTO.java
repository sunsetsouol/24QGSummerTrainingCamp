package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    /**
     * 登陆凭证
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户1
     * 管理员0
     */
    private int role;
}

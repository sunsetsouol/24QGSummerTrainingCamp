package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注册时传输给后台的数据模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    /**
     * 邮箱，邮箱
     */
    private String email;
    /**
     * 密码，密码
     */
    private String password;
    /**
     * 验证码，六位验证码
     */
    private String verificationCode;
}

package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户授权许可
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLicenseDTO {
    /**
     * 授权时间
     */
    private String authTime;
    /**
     * 指纹信息
     */
    private String fingerprint;
    /**
     * 过期时间
     */
    private String expireTime;
    /**
     * 软件信息列表
     */
    private List<SoftwareSimpleInfoDTO> softwareList;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 表id
     */
    private String userLicenseId;
}



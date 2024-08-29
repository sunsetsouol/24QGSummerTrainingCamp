package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserInfoDTO {
    private String username;
    private String description;
    private String userId;
    private String image;
    private String updateTime;
}

package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserInfoDTO {
    private String username;
    private String description;
    private String userId;
    private MultipartFile headImage;
    private String image;
}

package com.qg24.softwareplatform.po.vo;

import com.qg24.softwareplatform.po.dto.AuthSoftwareDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowLicenseVO {
    private List<AuthSoftwareDTO> softwareList;
    private String expiredTime;
    private String authTime;
    private String licenseUrl;
}

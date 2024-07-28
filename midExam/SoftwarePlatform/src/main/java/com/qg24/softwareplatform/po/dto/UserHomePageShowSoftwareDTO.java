package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHomePageShowSoftwareDTO {
    private int page;
    private String userId;
    private int pageSize;
}

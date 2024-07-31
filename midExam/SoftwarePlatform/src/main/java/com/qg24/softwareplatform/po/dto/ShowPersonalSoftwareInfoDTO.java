package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowPersonalSoftwareInfoDTO {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 页码
     */
    private int page;

    /**
     * 一页记录数
     */
    private int pageSize;
}

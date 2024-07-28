package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageShowSoftwareDTO {
    /**
     * 页码
     */
    private int page;

    /**
     * 记录数
     */
    private int pageSize;

    /**
     * 软件名
     */
    private String softwareName;

    /**
     * 标签
     */
    private List<String> tags;

}

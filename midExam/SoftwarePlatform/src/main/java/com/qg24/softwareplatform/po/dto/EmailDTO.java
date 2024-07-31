package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接收邮箱
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    /**
     * 邮箱
     */
    private String email;
}

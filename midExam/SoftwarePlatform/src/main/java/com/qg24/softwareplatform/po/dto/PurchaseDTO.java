package com.qg24.softwareplatform.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {

    /**
     * 硬件指纹
     */
    private String fingerprint;
    /**
     * 软件信息列表
     */
    private List<AuthSoftwareDTO> softwareList;
    /**
     * 总金额
     */
    private float totalPrize;
    /**
     * 用户id
     */
    private String userId;

}

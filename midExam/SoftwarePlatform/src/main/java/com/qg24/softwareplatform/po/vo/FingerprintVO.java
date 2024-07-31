package com.qg24.softwareplatform.po.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FingerprintVO {
    /**
     *硬件别名
     */
    private String hardwareName;
    /**
     * 硬件表主键
     */
    private long userFingerprintId;
}

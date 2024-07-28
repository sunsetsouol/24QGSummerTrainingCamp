package com.qg24.softwareplatform.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "huawei-obs")
public class HuaweiOBSProperties {
    private String ak;
    private String sk;
    private String endPoint;
    private String bucketName;
}

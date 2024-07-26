package com.qg24.softwareplatform.config;


import com.qg24.softwareplatform.properties.HuaweiOBSProperties;
import com.qg24.softwareplatform.util.HuaweiOBSUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HuaweiOBSConfig {


    @Bean
    @ConditionalOnMissingBean
    public HuaweiOBSUtils huaweiOBSUtils(HuaweiOBSProperties huaweiOBSProperties) {
        return new HuaweiOBSUtils(
                huaweiOBSProperties.getAk(),
                huaweiOBSProperties.getSk(),
                huaweiOBSProperties.getEndPoint(),
                huaweiOBSProperties.getBucketName()
        );
    }

}

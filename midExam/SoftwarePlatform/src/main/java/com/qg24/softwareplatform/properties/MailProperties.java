package com.qg24.softwareplatform.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "email")
public class MailProperties {

    private String host;

    private Integer port;

    private String username;

    private String password;

}

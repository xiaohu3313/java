package com.youzan.ad.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "adconf.mysql")
public class BinlogConfig {

    @Value("${port}")
    private int port;

    @Value("${host}")
    private String host;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;
}

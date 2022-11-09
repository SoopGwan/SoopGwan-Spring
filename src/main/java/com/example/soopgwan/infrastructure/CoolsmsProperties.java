package com.example.soopgwan.infrastructure;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "coolsms")
public class CoolsmsProperties {

    private final String apiKey;
    private final String secretKey;
    private final String sender;

    public CoolsmsProperties(String apiKey, String secretKey, String sender) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.sender = sender;
    }
}

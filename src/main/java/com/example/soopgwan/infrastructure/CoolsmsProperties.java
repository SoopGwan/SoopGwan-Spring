package com.example.soopgwan.infrastructure;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "coolsms")
public class CoolsmsProperties {

    private final String apiKey;
    private final String apiSecret;
    private final String sender;

    public CoolsmsProperties(String apiKey, String apiSecret, String sender) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.sender = sender;
    }
}

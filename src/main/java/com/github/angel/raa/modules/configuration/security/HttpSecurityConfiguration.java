package com.github.angel.raa.modules.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HttpSecurityConfiguration {
    private String cloudName;
    private String apiKey;
    private String apiSecret;
}

package com.development.commons.shared.spring.rest_template;

import org.springframework.boot.ssl.SslBundles;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder, SslBundles sslBundles) {
        try {
            return restTemplateBuilder.setSslBundle(sslBundles.getBundle("tls_consumer")).build();
        } catch (Exception e) {
            return restTemplateBuilder.build();
        }
    }

}

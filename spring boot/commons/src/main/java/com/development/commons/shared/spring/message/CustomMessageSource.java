package com.development.commons.shared.spring.message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class CustomMessageSource {
    private final Properties props = new Properties();

    public CustomMessageSource(@Value("${spring.config.location}") String messagesUrl) throws IOException {
        var in = new FileInputStream(messagesUrl);
        props.load(in);
    }

    public String getMessage(String property) {
        return props.getProperty(property);
    }
}

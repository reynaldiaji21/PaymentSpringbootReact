package com.rad.spring.react.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "file")
@Data
public class FileStorageProperties {
    public String uploadDir;
}

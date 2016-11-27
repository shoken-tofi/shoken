package com.bsuir.shoken;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter

@ConfigurationProperties(prefix = "shoken")
public class ShokenConfigurationProperties {

    private final ImageServer imageServer = new ImageServer();

    @Getter
    @Setter
    public static class ImageServer {

        private String name = "http://localhost:9000";
    }
}

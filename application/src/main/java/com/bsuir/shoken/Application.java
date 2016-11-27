package com.bsuir.shoken;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ShokenConfiguration.class, CorsFilterConfiguration.class})
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }
}

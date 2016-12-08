package com.bsuir.shoken;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;
import org.springframework.web.util.UriTemplateHandler;

import java.util.Collections;

@Configuration
@PropertySource("classpath:/mockaroo.properties")
class MockarooConfiguration {

    @Bean
    public RestTemplate restTemplate() {

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setUriTemplateHandler(uriTemplateHandler());

        return restTemplate;
    }

    @Bean
    public UriTemplateHandler uriTemplateHandler() {

        final DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl("https://www.mockaroo.com");
        uriTemplateHandler.setDefaultUriVariables(Collections.singletonMap("key", "36a52730"));
        uriTemplateHandler.setStrictEncoding(true);

        return uriTemplateHandler;
    }
}

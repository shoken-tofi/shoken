package com.bsuir.shoken.iam;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public abstract class UserRestServiceGateway implements UserServiceGateway {

    private final RestTemplate restTemplate;

    @Value("${user.rest.service-gateway.create}")
    private String userCreateUrl;

    @Override
    public List<RegisterDto> create(int count) {
        return create(userCreateUrl + "&count={count}", count);
    }

    private List<RegisterDto> create(String url, int count) {
        return Arrays.asList(restTemplate.getForObject(url, RegisterDto[].class,
                Collections.singletonMap("count", count)));
    }
}

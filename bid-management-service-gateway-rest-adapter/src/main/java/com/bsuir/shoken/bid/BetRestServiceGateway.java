package com.bsuir.shoken.bid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public abstract class BetRestServiceGateway implements BetServiceGateway {

    private final RestTemplate restTemplate;

    @Value("${bet.rest.service-gateway.create}")
    private String betCreateUrl;

    @Override
    public List<BetCreateDto> create(int count) {
        return create(betCreateUrl + "&count={count}", count);
    }

    private List<BetCreateDto> create(String url, int count) {
        return Arrays.asList(restTemplate.getForObject(url, BetCreateDto[].class,
                Collections.singletonMap("count", count)));
    }
}

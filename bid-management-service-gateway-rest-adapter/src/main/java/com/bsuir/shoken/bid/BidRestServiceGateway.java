package com.bsuir.shoken.bid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public abstract class BidRestServiceGateway implements BidServiceGateway {

    private final RestTemplate restTemplate;

    @Value("${bid.rest.service-gateway.create}")
    private String bidCreateUrl;

    @Override
    public List<BidCreateDto> create(int count) {
        return create(bidCreateUrl + "&count={count}", count);
    }

    private List<BidCreateDto> create(String url, int count) {
        return Arrays.asList(restTemplate.getForObject(url, BidCreateDto[].class,
                Collections.singletonMap("count", count)));
    }
}

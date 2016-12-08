package com.bsuir.shoken;

import com.bsuir.shoken.bid.BidRestServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BidMockarooServiceGateway extends BidRestServiceGateway {

    @Autowired
    public BidMockarooServiceGateway(RestTemplate restTemplate) {
        super(restTemplate);
    }
}

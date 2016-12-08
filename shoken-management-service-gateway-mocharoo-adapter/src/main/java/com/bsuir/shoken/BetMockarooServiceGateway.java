package com.bsuir.shoken;

import com.bsuir.shoken.bid.BetRestServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BetMockarooServiceGateway extends BetRestServiceGateway {

    @Autowired
    public BetMockarooServiceGateway(RestTemplate restTemplate) {
        super(restTemplate);
    }
}

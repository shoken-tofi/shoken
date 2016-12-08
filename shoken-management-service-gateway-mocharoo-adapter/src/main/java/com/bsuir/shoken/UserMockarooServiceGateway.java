package com.bsuir.shoken;

import com.bsuir.shoken.iam.UserRestServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class UserMockarooServiceGateway extends UserRestServiceGateway {

    @Autowired
    UserMockarooServiceGateway(RestTemplate restTemplate) {
        super(restTemplate);
    }
}

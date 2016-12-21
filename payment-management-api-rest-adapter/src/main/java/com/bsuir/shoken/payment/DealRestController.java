package com.bsuir.shoken.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DealRestController extends DealController {

    @Autowired
    DealRestController(DealService dealService) {
        super(dealService);
    }
}

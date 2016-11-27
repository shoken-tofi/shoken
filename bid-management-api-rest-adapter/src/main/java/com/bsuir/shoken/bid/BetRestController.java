package com.bsuir.shoken.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BetRestController extends BetController {

    @Autowired
    BetRestController(BetService betService, BetConverter betConverter) {
        super(betService, betConverter);
    }
}

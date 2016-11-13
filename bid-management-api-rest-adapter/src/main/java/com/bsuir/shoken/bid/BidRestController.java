package com.bsuir.shoken.bid;

import org.springframework.web.bind.annotation.RestController;

@RestController
class BidRestController extends BidController {

    BidRestController(BidService bidService, BidConverter bidConverter) {
        super(bidService, bidConverter);
    }
}

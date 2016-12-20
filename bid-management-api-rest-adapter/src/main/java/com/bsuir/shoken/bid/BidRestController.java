package com.bsuir.shoken.bid;

import com.bsuir.shoken.iam.SecurityContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BidRestController extends BidController {

    @Autowired
    BidRestController(BidService bidService, BidConverter bidConverter, BetService betService, BetConverter betConverter,
                      SearchCriteriaConverter searchCriteriaConverter, SecurityContextService securityContextService,
                      SellerService sellerService) {
        super(bidService, bidConverter, betService, betConverter, searchCriteriaConverter, securityContextService,
                sellerService);
    }
}

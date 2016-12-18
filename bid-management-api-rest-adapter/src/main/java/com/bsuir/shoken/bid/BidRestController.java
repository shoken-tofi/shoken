package com.bsuir.shoken.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BidRestController extends BidController {

    @Autowired
    BidRestController(BidService bidService, BidConverter bidConverter, BetService betService, BetConverter betConverter,
                      SearchCriteriaConverter searchCriteriaConverter,
                      InvestorService investorService, SellerService sellerService) {
        super(bidService, bidConverter, betService, betConverter, searchCriteriaConverter, investorService, sellerService);
    }
}

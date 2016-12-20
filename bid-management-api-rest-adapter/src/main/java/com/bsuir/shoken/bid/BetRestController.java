package com.bsuir.shoken.bid;

import com.bsuir.shoken.iam.SecurityContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BetRestController extends BetController {

    @Autowired
    BetRestController(BetService betService, BetConverter betConverter, SecurityContextService securityContextService, SellerService sellerService, InvestorService investorService) {
        super(betService, betConverter, securityContextService, sellerService, investorService);
    }
}

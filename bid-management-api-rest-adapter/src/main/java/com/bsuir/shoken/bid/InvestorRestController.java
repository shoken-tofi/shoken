package com.bsuir.shoken.bid;

import com.bsuir.shoken.iam.SecurityContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class InvestorRestController extends InvestorController {

    @Autowired
    InvestorRestController(InvestorService investorService, InvestorConverter investorConverter, BidService bidService,
                           BidConverter bidConverter, SecurityContextService securityContextService,
                           SearchCriteriaConverter searchCriteriaConverter) {
        super(investorService, investorConverter, bidService, bidConverter, securityContextService, searchCriteriaConverter);
    }
}

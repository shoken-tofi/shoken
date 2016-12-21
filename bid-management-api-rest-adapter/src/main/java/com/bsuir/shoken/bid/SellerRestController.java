package com.bsuir.shoken.bid;

import com.bsuir.shoken.iam.SecurityContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SellerRestController extends SellerController {

    @Autowired
    SellerRestController(SellerService sellerService, SellerConverter sellerConverter, BidService bidService,
                         BidConverter bidConverter, SecurityContextService securityContextService,
                         SearchCriteriaConverter searchCriteriaConverter) {
        super(sellerService, sellerConverter, bidService, bidConverter, securityContextService, searchCriteriaConverter);
    }
}

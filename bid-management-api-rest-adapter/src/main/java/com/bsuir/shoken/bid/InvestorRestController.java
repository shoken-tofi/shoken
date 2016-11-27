package com.bsuir.shoken.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class InvestorRestController extends InvestorController {

    @Autowired
    InvestorRestController(InvestorService investorService, InvestorConverter investorConverter) {
        super(investorService, investorConverter);
    }
}

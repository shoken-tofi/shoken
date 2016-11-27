package com.bsuir.shoken.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SellerRestController extends SellerController {

    @Autowired
    SellerRestController(SellerService sellerService, SellerConverter sellerConverter) {
        super(sellerService, sellerConverter);
    }
}

package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/sellers")
abstract class SellerController {

    private final SellerService sellerService;

    private final SellerConverter sellerConverter;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SellerFindAllDto get(@PathVariable Long id) {

        final Seller seller = sellerService.findById(id);

        return sellerConverter.toFindAllDto(seller);
    }
}

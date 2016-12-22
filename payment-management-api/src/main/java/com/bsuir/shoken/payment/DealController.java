package com.bsuir.shoken.payment;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/deals")
abstract class DealController {

    private final DealService dealService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String get(@PathVariable Long bidId) throws Exception {

        final Deal deal = dealService.findByBidId(bidId);

        return deal.getStatus().toString();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/pay")
    public String pay(Long bidId) throws Exception {

        final Deal deal = dealService.pay(bidId);

        return deal.getStatus().toString();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/transfer")
    public String transfer(Long bidId) throws Exception {

        final Deal deal = dealService.transfer(bidId);

        return deal.getStatus().toString();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/confirm")
    public String confirm(Long bidId) throws Exception {

        final Deal deal = dealService.confirm(bidId);

        return deal.getStatus().toString();
    }
}

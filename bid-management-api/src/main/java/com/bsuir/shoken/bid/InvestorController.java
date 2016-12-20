package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/investors")
abstract class InvestorController {

    private final InvestorService investorService;

    private final InvestorConverter investorConverter;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public InvestorFindAllDto get(@PathVariable Long id) {

        final Investor investor = investorService.findById(id);

        return investorConverter.toFindAllDto(investor);
    }
}

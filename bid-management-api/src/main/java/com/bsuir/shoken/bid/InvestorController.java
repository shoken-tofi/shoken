package com.bsuir.shoken.bid;

import com.bsuir.shoken.NoSuchEntityException;
import com.bsuir.shoken.iam.SecurityContextService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/investors")
abstract class InvestorController {

    private final InvestorService investorService;
    private final InvestorConverter investorConverter;

    private final BidService bidService;
    private final BidConverter bidConverter;

    private final SecurityContextService securityContextService;

    private final SearchCriteriaConverter searchCriteriaConverter;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public InvestorFindAllDto get(@PathVariable Long id) {

        final Investor investor = investorService.findById(id);

        return investorConverter.toFindAllDto(investor);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/me/bids", method = RequestMethod.GET)
    public BidsFindAllDto getBids(@RequestParam(required = false, defaultValue = BidController.PAGE) int page,
                                  @RequestParam(required = false, defaultValue = BidController.SIZE) int size,
                                  @ModelAttribute SearchCriteriaDto searchCriteriaDto) throws NoSuchEntityException {

        final String username = securityContextService.getUsername();
        final Investor investor = investorService.findByName(username);

        final Pageable pageRequest = new PageRequest(--page, size);
        final SearchCriteria searchCriteria = searchCriteriaConverter.toEntity(searchCriteriaDto);
        final BidsVO bids = bidService.findAllForInvestor(investor.getId(), searchCriteria, pageRequest);

        return new BidsFindAllDto(bidConverter.toFindAllDTOs(bids.getBids()), bids.getTotalElements());
    }
}

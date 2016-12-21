package com.bsuir.shoken.bid;

import com.bsuir.shoken.NoSuchEntityException;
import com.bsuir.shoken.iam.SecurityContextService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/sellers")
abstract class SellerController {

    private final SellerService sellerService;
    private final SellerConverter sellerConverter;

    private final BidService bidService;
    private final BidConverter bidConverter;

    private final SecurityContextService securityContextService;

    private final SearchCriteriaConverter searchCriteriaConverter;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SellerFindAllDto get(@PathVariable Long id) {

        final Seller seller = sellerService.findById(id);

        return sellerConverter.toFindAllDto(seller);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/me/bids", method = RequestMethod.GET)
    public BidsFindAllDto getBids(@RequestParam(required = false, defaultValue = BidController.PAGE) int page,
                                  @RequestParam(required = false, defaultValue = BidController.SIZE) int size,
                                  @ModelAttribute SearchCriteriaDto searchCriteriaDto) throws NoSuchEntityException {

        final String username = securityContextService.getUsername();
        final Seller seller = sellerService.findByName(username);

        final Pageable pageRequest = new PageRequest(--page, size);
        final SearchCriteria searchCriteria = searchCriteriaConverter.toEntity(searchCriteriaDto);
        final Page<Bid> bids = bidService.findAllForSeller(seller.getId(), searchCriteria, pageRequest);

        return new BidsFindAllDto(bidConverter.toFindAllDTOs(bids.getContent()), bids.getTotalElements());
    }
}

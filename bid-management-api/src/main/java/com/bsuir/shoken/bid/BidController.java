package com.bsuir.shoken.bid;

import com.bsuir.shoken.NoSuchEntityException;
import com.bsuir.shoken.ValidationException;
import com.bsuir.shoken.iam.SecurityContextService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/bids")
abstract class BidController {

    final static String PAGE = "1";
    final static String SIZE = "10";

    private final BidService bidService;
    private final BidConverter bidConverter;

    private final BetService betService;
    private final BetConverter betConverter;

    private final SearchCriteriaConverter searchCriteriaConverter;

    private final SecurityContextService securityContextService;

    private final SellerService sellerService;

    @GetMapping
    public BidsFindAllDto getAll(@RequestParam(required = false, defaultValue = PAGE) int page,
                                 @RequestParam(required = false, defaultValue = SIZE) int size,
                                 @ModelAttribute SearchCriteriaDto searchCriteriaDto) {

        final Pageable pageRequest = new PageRequest(--page, size);
        final SearchCriteria searchCriteria = searchCriteriaConverter.toEntity(searchCriteriaDto);
        final Page<Bid> bids = bidService.findAll(searchCriteria, pageRequest);

        return new BidsFindAllDto(bidConverter.toFindAllDTOs(bids.getContent()), bids.getTotalElements());
    }

    @GetMapping(value = "/{id}")
    public BidFindOneDto get(@PathVariable Long id) throws Exception {

        final BidVO bid = bidService.findOne(id);

        return bidConverter.toFindOneDto(bid);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public BidFindAllDto create(@RequestBody BidCreateDto dto)
            throws ValidationException, NoSuchEntityException {

        final String username = securityContextService.getUsername();
        final Seller seller = sellerService.findByName(username);
        dto.setSellerId(seller.getId());

        final Bid bid = bidConverter.toEntity(dto);
        final Bid bidFromDatabase = bidService.create(bid);

        return bidConverter.toFindAllDto(bidFromDatabase);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) throws ValidationException, NoSuchEntityException {
        bidService.delete(id);
    }

    @GetMapping("/{id}/bets")
    public BetsFindAllDto getBetsByBidId(@PathVariable(name = "id") Long bidId,
                                         @RequestParam(required = false, defaultValue = BetController.PAGE) int page,
                                         @RequestParam(required = false, defaultValue = BetController.SIZE) int size) {

        final Pageable pageRequest = new PageRequest(--page, size, new Sort(DESC, "value"));
        final Page<Bet> bets = betService.findByBidId(bidId, pageRequest);

        return new BetsFindAllDto(betConverter.toFindAllDTOs(bets.getContent()));
    }
}

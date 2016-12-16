package com.bsuir.shoken.bid;

import com.bsuir.shoken.NoSuchEntityException;
import com.bsuir.shoken.ValidationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/bids")
abstract class BidController {

    private final static String PAGE = "1";
    private final static String SIZE = "10";

    private final BidService bidService;
    private final BidConverter bidConverter;

    private final BetService betService;
    private final BetConverter betConverter;

    @GetMapping
    BidsFindAllDto getAll(@RequestParam(required = false, defaultValue = PAGE) int page,
                       @RequestParam(required = false, defaultValue = SIZE) int size) {

        final Pageable pageRequest = new PageRequest(--page, size);
        final Page<Bid> bids = bidService.findAll(pageRequest);

        return new BidsFindAllDto(bidConverter.toFindAllDTOs(bids.getContent()));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{id}")
    BidFindOneDto get(@PathVariable Long id) throws Exception {

        final Bid bid = bidService.findOne(id);

        return bidConverter.toFindOneDto(bid);
    }

    @PostMapping
    BidFindAllDto create(@RequestBody BidCreateDto dto) throws ValidationException, NoSuchEntityException {

        final Bid bid = bidConverter.toEntity(dto);
        final Bid bidFromDatabase = bidService.create(bid);

        return bidConverter.toFindAllDto(bidFromDatabase);
    }

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable Long id) throws ValidationException, NoSuchEntityException {
        bidService.delete(id);
    }

    @GetMapping("/{id}/bets")
    BetsFindAllDto getBetsByBidId(@PathVariable(name = "id") Long bidId,
                                  @RequestParam(required = false, defaultValue = BetController.PAGE) int page,
                                  @RequestParam(required = false, defaultValue = BetController.SIZE) int size) {

        final Pageable pageRequest = new PageRequest(--page, size);
        final Page<Bet> bets = betService.findByBidId(bidId, pageRequest);

        return new BetsFindAllDto(betConverter.toFindAllDTOs(bets.getContent()));
    }
}

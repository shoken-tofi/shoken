package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    BidsFindAllDto get(@RequestParam(required = false, defaultValue = PAGE) int page,
                       @RequestParam(required = false, defaultValue = SIZE) int size) {

        final Pageable pageRequest = new PageRequest(--page, size);
        final Page<Bid> bids = bidService.findAll(pageRequest);

        return new BidsFindAllDto(bidConverter.toFindAllDtos(bids.getContent()));
    }

    @GetMapping(value = "/{id}")
    BidFindOneDto get(@PathVariable Long id) {

        final Bid bid = bidService.findOne(id);

        return bidConverter.toFindOneDto(bid);
    }

    @PostMapping
    BidFindOneDto create(@RequestBody BidCreateDto dto) {

        final Bid newBid = bidConverter.toEntity(dto);
        final Bid createdBid = bidService.create(newBid);

        return bidConverter.toFindOneDto(createdBid);
    }

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable Long id) {
        bidService.delete(id);
    }

    @PostMapping(value = "/{id}/bets")
    BetFindAllDto create(@PathVariable Long id, @RequestBody BetCreateDto dto) {

        dto.setBidId(id);

        final Bet newBet = betConverter.toEntity(dto);
        final Bet createdBet = betService.create(newBet);

        return betConverter.toFindAllDto(createdBet);
    }
}
package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/bids")
abstract class BidController {

    private final static String PAGE = "1";

    private final static String SIZE = "10";

    private final BidService bidService;

    private final BidConverter bidConverter;

    @RequestMapping(method = RequestMethod.GET)
    BidsDto get(@RequestParam(required = false, defaultValue = PAGE) int page,
                @RequestParam(required = false, defaultValue = SIZE) int size) {

        final Pageable pageRequest = new PageRequest(--page, size);
        final Page<Bid> bids = bidService.findAll(pageRequest);

        return new BidsDto(bidConverter.convertToDtos(bids.getContent()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    BidDetailsDto get(@PathVariable Long id) {

        final Bid bid = bidService.findOne(id);

        return bidConverter.convertToDetailsDto(bid);
    }
}

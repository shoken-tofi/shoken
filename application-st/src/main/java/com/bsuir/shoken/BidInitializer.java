package com.bsuir.shoken;

import com.bsuir.shoken.bid.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Service
class BidInitializer {

    private static final int BID_COUNT = 1000;

    private final BidServiceGateway bidServiceGateway;

    private final BidConverter bidConverter;

    private final BidService bidService;

    void init() {

        final List<BidCreateDto> createDTOs = bidServiceGateway.create(BID_COUNT);
        final List<Bid> bids = bidConverter.toEntities(createDTOs);
        bids.forEach(bidService::create);
    }
}

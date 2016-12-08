package com.bsuir.shoken;

import com.bsuir.shoken.bid.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Service
class BetInitializer {

    private static final int BET_COUNT = 1000;

    private final BetServiceGateway betServiceGateway;

    private final BetConverter betConverter;

    private final BetService betService;

    void init() {

        final List<BetCreateDto> createDTOs = betServiceGateway.create(BET_COUNT);
        final List<Bet> bets = betConverter.toEntities(createDTOs);
        bets.forEach(betService::create);
    }
}

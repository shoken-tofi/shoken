package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/bets")
abstract class BetController {

    private final BetService betService;

    private final BetConverter betConverter;

    @PostMapping
    PriceDto create(@RequestBody BetCreateDto dto) {

        final Bet newBet = betConverter.toEntity(dto);
        final Bet createdBet = betService.create(newBet);

        final BigDecimal price = createdBet.getValue();
        final PriceDto priceDto = new PriceDto(price);
        priceDto.setStep(StepFactory.define(price));

        return priceDto;
    }
}

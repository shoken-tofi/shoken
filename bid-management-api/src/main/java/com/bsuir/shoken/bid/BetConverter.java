package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@Component
class BetConverter {

    private final InvestorService investorService;

    private final InvestorConverter investorConverter;

    List<BetFindAllDto> toFindAllDtos(final List<Bet> bets) {

        if (bets == null) {
            return Collections.emptyList();
        }

        return bets.stream().map(this::toFindAllDto).collect(Collectors.toList());
    }

    private BetFindAllDto toFindAllDto(final Bet bet) {

        if (bet == null) {
            return null;
        }

        final BetFindAllDto dto = new BetFindAllDto();
        dto.setId(bet.getId());
        dto.setInvestor(toInvestorFindAllDto(bet.getInvestorId()));
        dto.setValue(bet.getValue());
        dto.setDate(bet.getDate());

        return dto;
    }

    private InvestorFindAllDto toInvestorFindAllDto(final Long id) {

        final Investor investor = investorService.findOne(id);

        return investorConverter.toFindAllDto(investor);
    }
}

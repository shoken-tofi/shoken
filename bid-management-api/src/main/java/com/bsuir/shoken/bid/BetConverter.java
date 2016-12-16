package com.bsuir.shoken.bid;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Component
public class BetConverter {

    private final InvestorService investorService;

    private final InvestorConverter investorConverter;

    Bet toEntity(final BetCreateDto dto) {

        if (dto == null) {
            return null;
        }

        return new Bet(dto.getInvestorId(), dto.getBidId(), dto.getValue());
    }

    public List<Bet> toEntities(final List<BetCreateDto> dtoList) {

        if (dtoList == null) {
            return Collections.emptyList();
        }

        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    List<BetFindAllDto> toFindAllDTOs(final List<Bet> bets) {

        if (bets == null) {
            return Collections.emptyList();
        }

        return bets.stream().map(this::toFindAllDto).collect(Collectors.toList());
    }

    BetFindAllDto toFindAllDto(final Bet bet) {

        if (bet == null) {
            return null;
        }

        final BetFindAllDto dto = new BetFindAllDto();
        dto.setId(bet.getId());
        dto.setInvestor(toInvestorFindAllDto(bet.getInvestorId()));
        dto.setValue(bet.getValue());
        dto.setDateTime(bet.getDateTime());

        return dto;
    }

    private InvestorFindAllDto toInvestorFindAllDto(final Long id) {

        final Investor investor = investorService.findOne(id);

        return investorConverter.toFindAllDto(investor);
    }
}

package com.bsuir.shoken.bid;

import org.springframework.stereotype.Component;

@Component
class InvestorConverter {

    InvestorFindAllDto toFindAllDto(final Investor investor) {

        if (investor == null) {
            return null;
        }

        final InvestorFindAllDto dto = new InvestorFindAllDto();
        dto.setId(investor.getId());
        dto.setUsername(investor.getName());

        return dto;
    }
}

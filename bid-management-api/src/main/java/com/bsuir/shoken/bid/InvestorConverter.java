package com.bsuir.shoken.bid;

import org.springframework.stereotype.Component;

@Component
class InvestorConverter {

    InvestorDto convert(Investor investor) {

        if (investor == null) {
            return null;
        }

        final InvestorDto dto = new InvestorDto();
        dto.setId(investor.getId());
        dto.setUsername(investor.getName());

        return dto;
    }
}

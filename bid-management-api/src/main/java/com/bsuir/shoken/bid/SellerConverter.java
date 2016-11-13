package com.bsuir.shoken.bid;

import org.springframework.stereotype.Component;

@Component
class SellerConverter {

    SellerDto convert(Seller seller) {

        if (seller == null) {
            return null;
        }

        final SellerDto dto = new SellerDto();
        dto.setId(seller.getId());
        dto.setUsername(seller.getName());

        return dto;
    }
}

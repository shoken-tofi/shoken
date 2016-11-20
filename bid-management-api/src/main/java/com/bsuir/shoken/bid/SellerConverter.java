package com.bsuir.shoken.bid;

import org.springframework.stereotype.Component;

@Component
class SellerConverter {

    SellerFindAllDto toFindAllDto(Seller seller) {

        if (seller == null) {
            return null;
        }

        final SellerFindAllDto dto = new SellerFindAllDto();
        dto.setId(seller.getId());
        dto.setUsername(seller.getName());

        return dto;
    }
}

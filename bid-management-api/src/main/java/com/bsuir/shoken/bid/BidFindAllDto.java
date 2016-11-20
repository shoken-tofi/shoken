package com.bsuir.shoken.bid;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
class BidFindAllDto {

    private Long id;

    private String title;

    private String type;

    private Integer quantity;

    private PriceDto price;

    private String imageUrl;

    private TimeLeftDto timeLeft;

    private String paymentType;

    private SellerFindAllDto seller;

    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    @Getter
    @EqualsAndHashCode
    @ToString
    static class PriceDto {

        private BigDecimal value;

        private BigDecimal step;
    }
}

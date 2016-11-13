package com.bsuir.shoken.bid;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(value = AccessLevel.PACKAGE)
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
class BidDto {

    private Long id;

    private String title;

    private Bid.Type type;

    private Integer quantity;

    private BigDecimal price;

    private String imageUrl;

    private TimeLeftDto timeLeft;

    private Bid.PaymentType paymentType;

    private SellerDto seller;

    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    static class TimeLeftDto {

        private long hours;

        private long minutes;

        private long seconds;
    }
}

package com.bsuir.shoken.bid;

import lombok.*;

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

    private boolean canDelete;

    private boolean canBet;
}

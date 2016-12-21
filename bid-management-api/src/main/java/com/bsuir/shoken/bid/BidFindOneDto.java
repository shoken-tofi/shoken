package com.bsuir.shoken.bid;

import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
class BidFindOneDto {

    private Long id;

    private String title;

    private String type;

    private Integer quantity;

    private String description;

    private PriceDto price;

    private String imageUrl;

    private TimeLeftDto timeLeft;

    private String paymentType;

    private SellerFindAllDto seller;

    private String comment;

    private List<BetFindAllDto> bets;

    private boolean canDelete;

    private boolean canBet;
}

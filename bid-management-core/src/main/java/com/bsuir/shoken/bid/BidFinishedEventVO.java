package com.bsuir.shoken.bid;

import lombok.*;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
class BidFinishedEventVO {

    private final Bid bid;

    private final Bet maxBet;
}

package com.bsuir.shoken.bid;

import lombok.*;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
class FinishedBidVO {

    private final Bid bid;

    private final Bet maxBet;
}

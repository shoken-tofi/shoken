package com.bsuir.shoken.bid;

import lombok.*;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
class BidVO {

    private final Bid bid;

    private final boolean canDelete;

    private final boolean canBet;
}

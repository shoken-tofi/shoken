package com.bsuir.shoken.bid;

import lombok.*;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
class BidsVO {

    private final List<BidVO> bids;

    private final long totalElements;
}

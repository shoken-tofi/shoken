package com.bsuir.shoken.bid;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
class BidsFindAllDto {

    private List<BidFindAllDto> bids;

    private long totalElements;
}

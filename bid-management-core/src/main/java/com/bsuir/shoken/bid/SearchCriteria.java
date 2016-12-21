package com.bsuir.shoken.bid;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
class SearchCriteria {

    private final String query;

    private final BigDecimal minBetPrice;
    private final BigDecimal maxBetPrice;

    private final List<Bid.Type> bidTypes;

    private final LocalDateTime minStartDate;
    private final LocalDateTime maxStartDate;

    private final LocalDateTime minExpirationDate;
    private final LocalDateTime maxExpirationDate;

    private final Bid.Status status;
}

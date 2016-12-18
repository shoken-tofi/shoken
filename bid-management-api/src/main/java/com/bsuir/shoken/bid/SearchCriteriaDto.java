package com.bsuir.shoken.bid;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode
@ToString
class SearchCriteriaDto {

    private String query;

    private BigDecimal minBetPrice;
    private BigDecimal maxBetPrice;

    private List<String> bidTypes;

    private BigDecimal minStartDateHoursGone;
    private BigDecimal maxStartDateHoursGone;

    private BigDecimal minExpirationDateHoursLeft;
    private BigDecimal maxExpirationDateHoursLeft;
}

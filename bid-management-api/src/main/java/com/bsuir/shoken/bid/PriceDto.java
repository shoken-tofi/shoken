package com.bsuir.shoken.bid;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
class PriceDto {

    private BigDecimal value;

    private BigDecimal step;
}

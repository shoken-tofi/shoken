package com.bsuir.shoken.bid;

import lombok.*;

import java.math.BigDecimal;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode
@ToString
class PriceDto {

    private final BigDecimal value;

    private final BigDecimal step;
}

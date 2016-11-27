package com.bsuir.shoken.bid;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode
@ToString
class BetCreateDto {

    private Long bidId;

    private BigDecimal value;
}

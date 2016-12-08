package com.bsuir.shoken.bid;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BetCreateDto {

    private Long investorId;

    private Long bidId;

    private BigDecimal value;
}

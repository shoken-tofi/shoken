package com.bsuir.shoken.bid;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
class BetFindAllDto {

    private Long id;

    private InvestorFindAllDto investor;

    private BigDecimal value;

    private LocalDateTime date;
}

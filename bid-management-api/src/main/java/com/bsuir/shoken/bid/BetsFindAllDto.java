package com.bsuir.shoken.bid;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
class BetsFindAllDto {

    private List<BetFindAllDto> bets;
}

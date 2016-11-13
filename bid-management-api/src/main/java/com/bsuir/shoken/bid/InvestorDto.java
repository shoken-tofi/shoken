package com.bsuir.shoken.bid;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(value = AccessLevel.PACKAGE)
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
class InvestorDto {

    private Long id;

    private String username;
}

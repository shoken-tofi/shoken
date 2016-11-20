package com.bsuir.shoken.bid;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
class SellerFindAllDto {

    private Long id;

    private String username;
}

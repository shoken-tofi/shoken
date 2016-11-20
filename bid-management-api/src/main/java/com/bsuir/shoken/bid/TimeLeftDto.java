package com.bsuir.shoken.bid;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
class TimeLeftDto {

    private Long hours;

    private Long minutes;

    private Long seconds;
}

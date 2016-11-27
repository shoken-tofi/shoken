package com.bsuir.shoken.bid;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
class ImageDto {

    private String name;

    private String extension;
}

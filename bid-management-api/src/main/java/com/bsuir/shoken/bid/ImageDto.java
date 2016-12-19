package com.bsuir.shoken.bid;

import lombok.*;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
class ImageDto {

    private final String url;

    private final String imageName;
}

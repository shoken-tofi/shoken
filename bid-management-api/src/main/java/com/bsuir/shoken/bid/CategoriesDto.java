package com.bsuir.shoken.bid;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
class CategoriesDto {

    private List<CategoryDto> categories;
}

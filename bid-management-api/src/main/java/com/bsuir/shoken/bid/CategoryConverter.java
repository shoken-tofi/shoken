package com.bsuir.shoken.bid;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
class CategoryConverter {

    CategoryDto convert(Category category) {

        if (category == null) {
            return null;
        }

        final CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());

        return dto;
    }

    List<CategoryDto> convert(List<Category> categories) {

        if (categories == null) {
            return Collections.emptyList();
        }

        return categories.stream().map(this::convert).collect(Collectors.toList());
    }
}

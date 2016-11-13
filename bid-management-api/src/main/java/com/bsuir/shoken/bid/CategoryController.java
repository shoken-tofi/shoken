package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/categories")
abstract class CategoryController {

    private final CategoryService categoryService;

    private final CategoryConverter categoryConverter;

    @RequestMapping(method = RequestMethod.GET)
    CategoriesDto get() {

        final List<Category> categories = categoryService.findAll();

        return new CategoriesDto(categoryConverter.convert(categories));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    CategoryDto get(@PathVariable Long id) {

        final Category category = categoryService.findOne(id);

        return categoryConverter.convert(category);
    }
}

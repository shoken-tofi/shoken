package com.bsuir.shoken.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CategoryRestController extends CategoryController {

    @Autowired
    CategoryRestController(CategoryService categoryService, CategoryConverter categoryConverter) {
        super(categoryService, categoryConverter);
    }
}

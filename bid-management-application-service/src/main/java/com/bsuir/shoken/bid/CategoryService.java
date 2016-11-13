package com.bsuir.shoken.bid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Service
@Transactional
class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }
}

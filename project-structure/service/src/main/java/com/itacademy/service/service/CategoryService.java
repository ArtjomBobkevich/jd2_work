package com.itacademy.service.service;

import com.itacademy.service.dto.CategoryFullDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.itacademy.database.dao.CategoryDao.getCategoryDao;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryService {

    private static final CategoryService CATEGORY_SERVICE =new CategoryService();

    public List<CategoryFullDto> findAll() {
        return getCategoryDao().getAll().stream()
                .map(it -> new CategoryFullDto(it.getCategoryName()))
                .collect(Collectors.toList());
    }

    public static CategoryService getCategoryService() {
        return CATEGORY_SERVICE;
    }
}

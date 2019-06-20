package com.itacademy.service.service;

import com.itacademy.database.dao.CategoryDao;
import com.itacademy.database.entity.Category;
import com.itacademy.service.dto.CategoryFullDto;
import com.itacademy.service.dto.CreateCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryDao categoryDao;

    public List<CategoryFullDto> findAll() {
        return categoryDao.getAll().stream()
                .map(it -> new CategoryFullDto(it.getId(), it.getCategoryName(),it.getVersion()))
                .collect(Collectors.toList());
    }

    public Category findById(Long id) {
        return categoryDao.get(id).orElse(null);
    }

    @Transactional
    public Long saveCategory(CreateCategoryDto categoryDto) {
        return categoryDao.save(Category.builder()
                .categoryName(categoryDto.getCategoryName())
                .fotoUrl(categoryDto.getFoto())
                .build());
    }

    @Transactional
    public void deleteCategory(Category category) {
        categoryDao.delete(category);
    }

    @Transactional
    public void updateCategory(CreateCategoryDto categoryDto) {
        categoryDao.update(Category.builder()
                .id(categoryDto.getId())
                .categoryName(categoryDto.getCategoryName())
                .fotoUrl(categoryDto.getFoto())
                .version(categoryDto.getVersion())
                .build());
    }
}
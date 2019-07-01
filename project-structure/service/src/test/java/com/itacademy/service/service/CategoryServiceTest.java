package com.itacademy.service.service;

import com.itacademy.database.entity.Category;
import com.itacademy.service.config.ServiceConfigTest;
import com.itacademy.service.dto.CreateCategoryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceConfigTest.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void save () {
        CreateCategoryDto createCategoryDto = CreateCategoryDto.builder()
                .categoryName("test22")
                .foto("bung")
                .build();

        categoryService.saveCategory(createCategoryDto);
        assertNotNull(categoryService.findById(25L));
    }

    @Test
    public void getById () {
        assertNotNull(categoryService.findById(2L));
    }

    @Test
    public void findAll () {
        assertTrue(categoryService.findAll().size()>0);
    }

    @Test
    public void update () {
        Category category = categoryService.findById(25L);
        category.setCategoryName("www");
        CreateCategoryDto createCategoryDto = CreateCategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .foto(category.getFotoUrl())
                .version(category.getVersion())
                .build();
        categoryService.updateCategory(createCategoryDto);
        Category categoryChange = categoryService.findById(25L);
        assertTrue( categoryChange.getVersion()>0);
    }

    @Test
    public void delete () {
        Category category = categoryService.findById(25L);
        categoryService.deleteCategory(category);
        assertNull(categoryService.findById(25L));
    }

    @Test
    public void getByIdCache () {
        categoryService.findById(2L);
        categoryService.findById(2L);
    }
}
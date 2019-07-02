package com.itacademy.service.service;

import com.itacademy.database.dao.CategoryDao;
import com.itacademy.database.entity.Category;
import com.itacademy.service.config.ServiceConfigTest;
import com.itacademy.service.dto.CategoryFullDto;
import com.itacademy.service.dto.CreateCategoryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceConfigTest.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void save () {
        CreateCategoryDto createCategoryDto = CreateCategoryDto.builder()
                .categoryName("test22")
                .foto("bung")
                .version(0L)
                .build();

        categoryService.saveCategory(createCategoryDto);

        List<CategoryFullDto> allCategory = categoryService.findAll();
        for (CategoryFullDto categoryFullDto: allCategory) {
            if (categoryFullDto.getCategoryName().equals(createCategoryDto.getCategoryName())) {
                assertEquals(categoryFullDto.getCategoryName(), createCategoryDto.getCategoryName());
            }
        }

    }

    @Test
    public void getById () {
        assertNotNull(categoryService.findById(1L));
    }

    @Test
    public void findAll () {
        assertTrue(categoryService.findAll().size()>0);
    }

    @Test
    public void update () {
        CreateCategoryDto createCategoryDto = CreateCategoryDto.builder()
                .categoryName("test22")
                .foto("bung")
                .version(0L)
                .build();
        categoryService.saveCategory(createCategoryDto);
        Category category = categoryService.findById(createCategoryDto.getId());
        category.setCategoryName("www");
        categoryService.updateCategory(createCategoryDto);
        Category categoryChange = categoryService.findById(createCategoryDto.getId());
        assertEquals("www", categoryChange.getCategoryName());
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
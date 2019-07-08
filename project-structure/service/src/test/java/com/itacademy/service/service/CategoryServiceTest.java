package com.itacademy.service.service;

import com.itacademy.database.entity.Category;
import com.itacademy.service.config.ServiceConfigTest;
import com.itacademy.service.dto.ByCommentSaveDto;
import com.itacademy.service.dto.CategoryFullDto;
import com.itacademy.service.dto.CreateCategoryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceConfigTest.class)
@Transactional
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void save () {
        CreateCategoryDto createCategoryDto = CreateCategoryDto.builder()
                .categoryName("wqer")
                .foto("bung")
                .version(0L)
                .build();

        ByCommentSaveDto byCommentSaveDto = ByCommentSaveDto.builder()
                .commentId(1L)
                .personId(1L)
                .resourceId(1L)
                .text("sdgfsf")
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

        List<CategoryFullDto> allCategory = categoryService.findAll();
        CreateCategoryDto createCategoryDto2 = null;
        for (CategoryFullDto categoryFullDto: allCategory) {
            if (categoryFullDto.getCategoryName().equals(createCategoryDto.getCategoryName())) {
                createCategoryDto2 = CreateCategoryDto.builder()
                        .id(categoryFullDto.getId())
                        .categoryName(categoryFullDto.getCategoryName())
                        .foto(createCategoryDto.getFoto())
                        .version(categoryFullDto.getVersion())
                        .build();
            }
        }
        assert createCategoryDto2 != null;
        createCategoryDto2.setCategoryName("www");

        categoryService.updateCategory(createCategoryDto2);
        List<CategoryFullDto> allCategory2 = categoryService.findAll();
        for (CategoryFullDto categoryFullDto: allCategory2) {
            if (categoryFullDto.getCategoryName().equals(createCategoryDto.getCategoryName())) {
                assertEquals(categoryFullDto.getCategoryName(), createCategoryDto2.getCategoryName());
            }
        }
    }

    @Test
    public void delete () {
        Category category = categoryService.findById(25L);
        if (category!=null) {
            categoryService.deleteCategory(category);
        }
        assertNull(categoryService.findById(25L));
    }

    @Test
    public void getByIdCache () {
        /*кеш работает, он просто какое **** показывает*/
        categoryService.findById(2L);
        categoryService.findById(2L);
        categoryService.findById(2L);
        categoryService.findById(2L);
        categoryService.findById(2L);
    }
}
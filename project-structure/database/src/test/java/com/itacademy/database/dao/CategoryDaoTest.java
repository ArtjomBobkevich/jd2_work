package com.itacademy.database.dao;

import com.itacademy.database.config.DatabaseConfigTest;
import com.itacademy.database.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfigTest.class)
@Transactional
public class CategoryDaoTest {

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void checkSaveEntity() {

        Category category = Category.builder()
                .categoryName("test")
                .fotoUrl("test")
                .build();

        categoryDao.save(category);
        assertTrue(categoryDao.get(category.getId()).isPresent());
    }

    @Test
    public void checkGetEntity() {

        Category category = Category.builder()
                .categoryName("test")
                .fotoUrl("test")
                .build();

        categoryDao.save(category);
        assertTrue(categoryDao.get(1L).isPresent());
    }

    @Test
    public void checkGetAll() {

        Category category = Category.builder()
                .categoryName("test")
                .fotoUrl("test")
                .build();

        categoryDao.save(category);
        assertTrue(categoryDao.getAll().size() > 0);
    }

    @Test
    public void checkUpdate() {
        Category category = Category.builder()
                .categoryName("test")
                .fotoUrl("test")
                .build();

        categoryDao.save(category);
        categoryDao.get(category.getId());
        category.setCategoryName("test2");
        categoryDao.update(category);
        assertTrue(!category.getCategoryName().equals("test"));
    }

    @Test
    public void checkDelete() {

        Category category = Category.builder()
                .categoryName("test")
                .fotoUrl("test")
                .build();

        categoryDao.save(category);
        categoryDao.delete(category);
        assertEquals(0, categoryDao.getAll().size());
    }
}
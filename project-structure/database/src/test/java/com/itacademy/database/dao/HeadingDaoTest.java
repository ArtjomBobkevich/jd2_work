package com.itacademy.database.dao;

import com.itacademy.database.config.DatabaseConfigTest;
import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Heading;
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
public class HeadingDaoTest {

    @Autowired
    private HeadingDao headingDao;

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void checkSaveEntity() {

        Category category = Category.builder()
                .categoryName("test")
                .fotoUrl("test")
                .build();

        categoryDao.save(category);

        Heading heading = Heading.builder()
                .headingName("test")
                .category(category)
                .build();

        headingDao.save(heading);
        assertTrue(headingDao.get(heading.getId()).isPresent());
    }

    @Test
    public void checkGetEntity() {
        Category category = Category.builder()
                .categoryName("test")
                .fotoUrl("test")
                .build();

        categoryDao.save(category);

        Heading heading = Heading.builder()
                .headingName("test")
                .category(category)
                .build();

        headingDao.save(heading);
        assertTrue(headingDao.get(1L).isPresent());
    }

    @Test
    public void checkGetAll() {
        Category category = Category.builder()
                .categoryName("test")
                .fotoUrl("test")
                .build();

        categoryDao.save(category);

        Heading heading = Heading.builder()
                .headingName("test")
                .category(category)
                .build();

        headingDao.save(heading);
        assertTrue(headingDao.getAll().size() > 0);
    }

    @Test
    public void checkUpdate() {
        Category category = Category.builder()
                .categoryName("test")
                .fotoUrl("test")
                .build();

        categoryDao.save(category);

        Heading heading = Heading.builder()
                .headingName("test")
                .category(category)
                .build();

        headingDao.save(heading);
        headingDao.get(heading.getId());
        heading.setHeadingName("test5");
        headingDao.update(heading);
        assertTrue(!heading.getHeadingName().equals("test"));
    }

    @Test
    public void checkDelete() {
        Category category = Category.builder()
                .categoryName("test")
                .fotoUrl("test")
                .build();

        categoryDao.save(category);

        Heading heading = Heading.builder()
                .headingName("test")
                .category(category)
                .build();

        headingDao.save(heading);
        headingDao.delete(heading);
        assertEquals(0, headingDao.getAll().size());
    }
}
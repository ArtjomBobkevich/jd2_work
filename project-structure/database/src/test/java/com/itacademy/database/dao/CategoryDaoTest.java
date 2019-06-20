package com.itacademy.database.dao;

import com.itacademy.database.config.DatabaseConfigTest;
import com.itacademy.database.entity.Category;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfigTest.class)
public class CategoryDaoTest {

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void test() {
        try (Session session = categoryDao.getSessionFactory().openSession()) {
            session.beginTransaction();
            Category category = Category.builder()
                    .categoryName("www")
                    .fotoUrl("www")
                    .build();
            session.save(category);
            System.out.println(category);
            session.getTransaction().commit();
        }
        try (Session session = categoryDao.getSessionFactory().openSession()) {
            session.beginTransaction();
            Category category = session.get(Category.class, 1L);
            System.out.println(category);
            session.getTransaction().commit();
        }
        try (Session session = categoryDao.getSessionFactory().openSession()) {
            session.beginTransaction();
            Category category = session.get(Category.class, 1L);
            System.out.println(category);
            session.getTransaction().commit();
        }
    }
}
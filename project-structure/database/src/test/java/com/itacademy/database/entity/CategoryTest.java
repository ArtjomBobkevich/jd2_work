package com.itacademy.database.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

public class CategoryTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void close() {
        FACTORY.close();
    }

    @Test
    public void checkSaveFactory() {
        try (Session session = FACTORY.openSession()) {

            Category category = new Category("test", "url");
            Serializable id = session.save(category);
            assertNotNull(id);
        }
    }

    @Test
    public void checkGetFactory() {
        try (Session session = FACTORY.openSession()) {

            Category category = new Category("test", "url");

            Serializable id = session.save(category);
            Category category1 = session.load(Category.class, id);
            assertNotNull(category1);
        }
    }

    @Before
    public void cleanTable() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Category c").executeUpdate();
            session.getTransaction().commit();
        }
    }

}
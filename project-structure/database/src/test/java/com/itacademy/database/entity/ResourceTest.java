package com.itacademy.database.entity;

import com.itacademy.database.config.DatabaseConfigTest;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfigTest.class)
@Transactional
public class ResourceTest  {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void checkSaveFactory() {
        Resource resource = Resource.builder()
                .resourceName("test")
                .foto("url")
                .category(sessionFactory.getCurrentSession().get(Category.class, 1L))
                .person(sessionFactory.getCurrentSession().get(Person.class, 2L))
                .price(300)
                .build();

        Serializable id = sessionFactory.getCurrentSession().save(resource);
        assertNotNull(id);

        }

    @Test
    public void checkGetFactory() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            Serializable id = session.save(createResource());
            session.getTransaction().commit();
            Resource resource1 = session.load(Resource.class, id);
            assertNotNull(resource1);
        }
    }

    @Before
    public void cleanTable() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Resource r").executeUpdate();
            session.getTransaction().commit();
        }
    }

    private static Resource createResource() {
        @Cleanup Session session = FACTORY.openSession();
        Resource resource = Resource.builder()
                .resourceName("test")
                .foto("url")
                .category(session.get(Category.class, 1L))
                .person(session.get(Person.class, 2L))
                .price(300)
                .build();
        return resource;
    }
}
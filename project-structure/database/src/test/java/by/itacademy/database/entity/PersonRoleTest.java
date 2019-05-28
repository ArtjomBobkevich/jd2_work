package by.itacademy.database.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;


public class PersonRoleTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void close() {
        FACTORY.close();
    }

    @Test
    public void checkSaveFactory() {
        try (Session session = FACTORY.openSession()) {
            session.getTransaction().begin();
            PersonRole role = new PersonRole("test");
            Serializable id = session.save(role);
            session.getTransaction().commit();
            assertNotNull(id);
        }
    }

    @Test
    public void checkGetFactory() {
        try (Session session = FACTORY.openSession()) {
            session.getTransaction().begin();
            PersonRole role = new PersonRole("test");
            Serializable id = session.save(role);
            session.getTransaction().commit();
            PersonRole personRole = session.load(PersonRole.class, id);
            assertNotNull(personRole);
        }
    }

    @Before
    public void cleanTable() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from PersonRole pr").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
package by.itacademy.database.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;


public class PersonRoleTest {

    private static final SessionFactory FACTORY =new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void close() {
        FACTORY.close();
    }

    @Test
    public void checkSaveFactory () {
        try (Session session = FACTORY.openSession()) {

            PersonRole role = new PersonRole("test");
            Serializable id = session.save(role);
            assertNotNull(id);
        }
    }
}
package by.itacademy.database.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

public class HeadingTest {
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void close() {
        FACTORY.close();
    }

    @Test
    public void checkSaveFactory() {
        try (Session session = FACTORY.openSession()) {

            Heading heading = Heading.builder()
                    .headingName("test")
                    .category(Category.builder()
                            .categoryName("test")
                            .fotoUrl("url")
                            .build())
                    .build();
            Serializable id = session.save(heading);
            assertNotNull(id);
        }
    }

    @Test
    public void checkGetFactory() {
        try (Session session = FACTORY.openSession()) {

            Heading heading = Heading.builder()
                    .headingName("test")
                    .category(Category.builder()
                            .categoryName("test")
                            .fotoUrl("url")
                            .build())
                    .build();
            Serializable id = session.save(heading);
            Heading heading1 = session.load(Heading.class, id);
            assertNotNull(heading1);
        }
    }

    @Before
    public void cleanTable() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Heading h").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
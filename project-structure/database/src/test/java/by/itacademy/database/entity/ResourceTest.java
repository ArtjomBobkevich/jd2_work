package by.itacademy.database.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

public class ResourceTest {
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void close() {
        FACTORY.close();
    }

    @Test
    public void checkSaveFactory() {
        try (Session session = FACTORY.openSession()) {

            Resource resource = Resource.builder()
                    .resourceName("test")
                    .foto("url")
                    .heading(Heading.builder()
                            .headingName("test")
                            .category(Category.builder()
                                    .categoryName("test")
                                    .fotoUrl("url")
                                    .build())
                            .build())
                    .category(Category.builder()
                            .categoryName("test")
                            .fotoUrl("url")
                            .build())
                    .person(Person.builder()
                            .avatar("foto")
                            .login("login")
                            .identification(Identification.builder()
                                    .lastName("name")
                                    .firstName("www")
                                    .build())
                            .age(12)
                            .mail("mail")
                            .password("password")
                            .build())
                    .price(300)
                    .text("tratatata")
                    .build();
            Serializable id = session.save(resource);
            assertNotNull(id);
        }
    }

    @Test
    public void checkGetFactory() {
        try (Session session = FACTORY.openSession()) {

            Resource resource = Resource.builder()
                    .resourceName("test")
                    .foto("url")
                    .heading(Heading.builder()
                            .headingName("test")
                            .category(Category.builder()
                                    .categoryName("test")
                                    .fotoUrl("url")
                                    .build())
                            .build())
                    .category(Category.builder()
                            .categoryName("test")
                            .fotoUrl("url")
                            .build())
                    .person(Person.builder()
                            .avatar("foto")
                            .login("login")
                            .identification(Identification.builder()
                                    .lastName("name")
                                    .firstName("www")
                                    .build())
                            .age(12)
                            .mail("mail")
                            .password("password")
                            .build())
                    .price(300)
                    .build();
            Serializable id = session.save(resource);
            Resource resource1 = session.get(Resource.class, id);
            assertNotNull(resource1);
        }
    }

    @Before
    public void cleanTable() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int count = session.createQuery("delete from Resource r").executeUpdate();
            System.out.println("Deleted rows: " + count);
            session.getTransaction().commit();
        }
    }

}
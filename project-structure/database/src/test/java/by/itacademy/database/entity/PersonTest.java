package by.itacademy.database.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

public class PersonTest {

    private static final SessionFactory FACTORY =new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void close() {
        FACTORY.close();
    }

    @Test
    public void checkSaveFactory () {
        try (Session session = FACTORY.openSession()) {

            Person person = Person.builder()
                    .avatar("url")
                    .login("login")
                    .identification(Identification.builder()
                            .firstName("was")
                            .lastName("gen")
                            .build())
                    .age(16)
                    .mail("mail")
                    .password("password")
                    .personRole(PersonRole.builder()
                            .nameOfRole("test")
                            .build())
                    .build();
            Serializable id = session.save(person);
            assertNotNull(id);
        }
    }

    @Test
    public void checkGetFactory () {
        try (Session session = FACTORY.openSession()) {

            Person person = Person.builder()
                    .avatar("url")
                    .login("login")
                    .identification(Identification.builder()
                            .firstName("was")
                            .lastName("gen")
                            .build())
                    .age(16)
                    .mail("mail")
                    .password("password")
                    .personRole(PersonRole.builder()
                            .nameOfRole("test")
                            .build())
                    .build();
            Serializable id = session.save(person);
            session.get(Person.class,id);
        }
    }

    @Before
    public void cleanTable() {
        try (Session session = FACTORY.openSession()){
            session.beginTransaction();
            int count = session.createQuery("delete from Person p").executeUpdate();
            System.out.println("Deleted rows: "+count);
            session.getTransaction().commit();
        }
    }

}
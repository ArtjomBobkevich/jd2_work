package com.itacademy.database.entity;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

public class PersonTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void close() {
        FACTORY.close();
    }

    @Test
    public void checkSaveFactory() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            Serializable id = session.save(createPerson());
            session.getTransaction().commit();
            assertNotNull(id);
        }
    }

    @Test
    public void checkGetFactory() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            Serializable id = session.save(createPerson());
            session.getTransaction().commit();
            Person person1 = session.load(Person.class, id);
            assertNotNull(person1);
        }
    }

    @Before
    public void cleanTable() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Person p").executeUpdate();
            session.getTransaction().commit();
        }
    }

    private static Person createPerson() {
        @Cleanup Session session = FACTORY.openSession();
        return Person.builder()
//                .id(1L)
                .avatar("url")
                .login("login")
                .identification(Identification.builder()
                        .firstName("was")
                        .lastName("gen")
                        .build())
                .age(16)
                .mail("mail")
                .password("password")
                .personRole(session.get(PersonRole.class, 2L))
                .build();
    }
}
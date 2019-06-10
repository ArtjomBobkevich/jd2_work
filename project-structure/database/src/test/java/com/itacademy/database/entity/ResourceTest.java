//package com.itacademy.database.entity;
//
//import lombok.Cleanup;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.Serializable;
//
//import static org.junit.Assert.assertNotNull;
//
//public class ResourceTest {
//    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();
//
//    @AfterClass
//    public static void close() {
//        FACTORY.close();
//    }
//
//    @Test
//    public void checkSaveFactory() {
//        try (Session session = FACTORY.openSession()) {
//            session.beginTransaction();
//            Serializable id = session.save(createResource());
//            session.getTransaction().commit();
//            assertNotNull(id);
//        }
//    }
//
//    @Test
//    public void checkGetFactory() {
//        try (Session session = FACTORY.openSession()) {
//            session.beginTransaction();
//            Serializable id = session.save(createResource());
//            session.getTransaction().commit();
//            Resource resource1 = session.load(Resource.class, id);
//            assertNotNull(resource1);
//        }
//    }
//
//    @Before
//    public void cleanTable() {
//        try (Session session = FACTORY.openSession()) {
//            session.beginTransaction();
//            session.createQuery("delete from Resource r").executeUpdate();
//            session.getTransaction().commit();
//        }
//    }
//
//    private static Resource createResource() {
//        @Cleanup Session session = FACTORY.openSession();
//        Resource resource = Resource.builder()
//                .resourceName("test")
//                .foto("url")
//                .heading(session.get(Heading.class, 1L))
//                .category(session.get(Category.class, 1L))
//                .person(session.get(Person.class, 2L))
//                .price(300)
//                .build();
//        return resource;
//    }
//}
//package com.itacademy.database.entity;
//
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
//public class BlockResourceTest {
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
//            Serializable id = session.save(new BlockResource("222", "asfdsdf", (session.get(Heading.class, 1L)),
//                    (session.get(Category.class, 1L)), (session.get(Person.class, 2L)), 3, "sdfs", "qwe"));
//            session.getTransaction().commit();
//            assertNotNull(id);
//        }
//    }
//
//    @Test
//    public void checkGetFactory() {
//        try (Session session = FACTORY.openSession()) {
//            session.beginTransaction();
//            Serializable id = session.save(new BlockResource("222", "asfdsdf", (session.get(Heading.class, 1L)),
//                    (session.get(Category.class, 1L)), (session.get(Person.class, 2L)), 3, "sdfs", "qwe"));
//            session.getTransaction().commit();
//            BlockResource blockResource = session.load(BlockResource.class, id);
//            assertNotNull(blockResource);
//        }
//    }
//
//    @Before
//    public void cleanTable() {
//        try (Session session = FACTORY.openSession()) {
//            session.beginTransaction();
//            session.createQuery("delete from BlockResource r").executeUpdate();
//            session.getTransaction().commit();
//        }
//    }
//
//}
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
//public class CommentTest {
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
//            session.getTransaction().begin();
//            Serializable id = session.save(createComment());
//            session.getTransaction().commit();
//            assertNotNull(id);
//        }
//    }
//
//    @Test
//    public void checkGetFactory() {
//        try (Session session = FACTORY.openSession()) {
//            session.getTransaction().begin();
//            Serializable id = session.save(createComment());
//            Comment comment1 = session.get(Comment.class, id);
//            session.getTransaction().commit();
//            assertNotNull(comment1);
//        }
//    }
//
//    @Before
//    public void cleanTable() {
//        try (Session session = FACTORY.openSession()) {
//            session.beginTransaction();
//            session.createQuery("delete from Comment c").executeUpdate();
//            session.getTransaction().commit();
//        }
//    }
//
//    private static Comment createComment() {
//        @Cleanup Session session = FACTORY.openSession();
//        return Comment.builder()
//                .person(session.get(Person.class, 2L))
//                .resource(session.get(Resource.class, 2L))
//                .build();
//    }
//}
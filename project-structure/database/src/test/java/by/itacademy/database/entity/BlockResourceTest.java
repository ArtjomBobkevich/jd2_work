package by.itacademy.database.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

public class BlockResourceTest {
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void close() {
        FACTORY.close();
    }

    @Test
    public void checkSaveFactory() {
        try (Session session = FACTORY.openSession()) {

            Serializable id = session.save(new BlockResource("222", "asfdsdf", (new Heading())
                    , (new Category()), (new Person()), 3, "sdfs", "qwe"));
            assertNotNull(id);
        }
    }

    @Test
    public void checkGetFactory() {
        try (Session session = FACTORY.openSession()) {

            Serializable id = session.save(new BlockResource("222", "asfdsdf", (new Heading())
                    , (new Category()), (new Person()), 3, "sdfs", "qwe"));
            BlockResource blockResource = session.get(BlockResource.class, id);
            assertNotNull(blockResource);
        }
    }

    @Before
    public void cleanTable() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int count = session.createQuery("delete from BlockResource r").executeUpdate();
            System.out.println("Deleted rows: " + count);
            session.getTransaction().commit();
        }
    }

}
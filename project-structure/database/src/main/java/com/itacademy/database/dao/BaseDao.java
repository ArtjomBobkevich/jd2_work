//package by.itacademy.database.dao;
//
//import lombok.Cleanup;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//import javax.persistence.Entity;
//
//public class BaseDao<T extends > {
//
//    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();
//
//    public T findAll () {
//        @Cleanup Session session = FACTORY.openSession();
//        return null;
//    }
//}

//package com.itacademy.database;
//
//import com.itacademy.database.entity.Person;
//import com.itacademy.database.entity.Resource;
//import lombok.Cleanup;
//import lombok.extern.log4j.Log4j;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//@Log4j
//public class HibernateRunner {
//
//    public static void main(String[] args) {
//        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//        @Cleanup Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        Person person1 = Person.builder().id(3L).build();
//        Person person = session.get(Person.class, person1.getId());
//        person.getResources().add(session.get(Resource.class, person.getId()));
//        person.getResources().add(session.get(Resource.class, person.getId()));
//
//        session.save(person);
//
//        session.getTransaction().commit();
//    }
//}

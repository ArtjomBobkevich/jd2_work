package by.itacademy.database;

import by.itacademy.database.entity.Person;
import by.itacademy.database.entity.Resource;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Log4j
public class HibernateRunner {

    public static void main(String[] args) {
        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Person person = session.get(Person.class, 3L);
        person.getResources().add(session.get(Resource.class, 2L));
        person.getResources().add(session.get(Resource.class, 3L));

        session.save(person);

        session.getTransaction().commit();
    }
}
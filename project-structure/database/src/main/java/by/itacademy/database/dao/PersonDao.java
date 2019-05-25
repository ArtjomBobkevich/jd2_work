package by.itacademy.database.dao;

import by.itacademy.database.entity.Person;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class PersonDao {

    private static final PersonDao PERSON_DAO = new PersonDao();
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @SneakyThrows
    public Optional<Person> findById(Long id) {
        Optional<Person> person = Optional.empty();
        try (Session session = FACTORY.openSession()) {
            session.load(Person.class, id);

            return person;
        }
    }

    @SneakyThrows
    public List<Person> findAll() {

        try (Session session = FACTORY.openSession()) {
            return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        }
    }

    public static PersonDao getPersonDao() {
        return PERSON_DAO;
    }
}

package by.itacademy.database.dao;

import by.itacademy.database.entity.Person;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class PersonDao {

    private static final PersonDao PERSON_DAO = new PersonDao();
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @SneakyThrows
    public List<Person> findById(Long id) {
        try (Session session = FACTORY.openSession()) {
            return session.createQuery("SELECT p FROM Person p WHERE p.id =: id",Person.class)
                    .setParameter("id",id).list();
        }
    }

    @SneakyThrows
    public List<Person> findAll() {
        try (Session session = FACTORY.openSession()) {
            return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        }
    }

    @SneakyThrows
    public boolean delete(Person person) {
        boolean result = false;
        @Cleanup Session session = FACTORY.openSession();
          Query query =  session.createQuery("DELETE FROM Person p WHERE p.login =:login", Person.class)
                    .setParameter("login",person.getLogin());
          int count = query.executeUpdate();
        if (count>0){
            result=true;
        }
        return result;
    }

    @SneakyThrows
    public List<Person> update(Person person) {
        try (Session session = FACTORY.openSession()) {
            return session.createQuery("UPDATE  Person  set avatar = :avatar, login =:login, " +
                    "identification.firstName =: firstName, identification.lastName =: lastName, age =: age," +
                    "mail =: mail, password =: password where id =:id", Person.class)
                    .setParameter("avatar",person.getAvatar())
                    .setParameter("login",person.getLogin())
                    .setParameter("firstName",person.getIdentification().getFirstName())
                    .setParameter("lastName",person.getIdentification().getLastName())
                    .setParameter("age",person.getAge())
                    .setParameter("mail",person.getMail())
                    .setParameter("password",person.getPassword())
                    .setParameter("id",person.getId()).list();
        }
    }

    @SneakyThrows
    public Serializable save(Person person) {
        try (Session session = FACTORY.openSession()) {
            return session.save(person);
        }
    }

    public static PersonDao getPersonDao() {
        return PERSON_DAO;
    }
}

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
    private static final SessionFactory FACTORY =new Configuration().configure().buildSessionFactory();

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
//        List<Person> personList = new ArrayList<>();

        try (Session session = FACTORY.openSession()) {
            return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        }
    }
//
//    @SneakyThrows
//    private Person getPersonFromResultSet(ResultSet resultSet) {
//        return Person.builder()
//                .id(resultSet.getLong("person_id"))
//                .avatar(resultSet.getString("avatar"))
//                .login(resultSet.getString("login"))
//                .firstName(resultSet.getString("firstName"))
//                .lastName(resultSet.getString("lastName"))
//                .age(resultSet.getString("age"))
//                .mail(resultSet.getString("mail"))
//                .password(resultSet.getString("password"))
//                .personRole(PersonRole.builder()
//                        .id(resultSet.getLong("id"))
//                        .nameOfRole(resultSet.getString("role_name"))
//                        .build())
//                .build();
//    }

    public static PersonDao getPersonDao() {
        return PERSON_DAO;
    }
}

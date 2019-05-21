package by.itacademy.database.dao;

import by.itacademy.database.entity.PersonRole;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

//@Log4j
public class PersonRoleDao {

    private static final PersonRoleDao PERSON_ROLE_DAO = new PersonRoleDao();
    private static final SessionFactory FACTORY =new Configuration().configure().buildSessionFactory();
//    private static Logger logger = Logger.getLogger("PersonRoleDao");   /*херня какая то*/

    @SneakyThrows
    public Optional<PersonRole> findById(Long id) {
        Optional<PersonRole> personList = Optional.empty();
        try (Session session =FACTORY.openSession()) {
            session.get(PersonRole.class,id);
        }
        return personList;
    }

    public static PersonRoleDao getPersonRoleDao() {
        return PERSON_ROLE_DAO;
    }
}

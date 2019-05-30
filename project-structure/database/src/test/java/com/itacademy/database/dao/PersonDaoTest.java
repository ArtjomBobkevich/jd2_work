package com.itacademy.database.dao;

import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.PersonRole;
import com.itacademy.database.util.SessionManager;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class PersonDaoTest {

    private static SessionFactory factory = SessionManager.getFactory();
    private final PersonDao personDao = PersonDao.getPersonDao();


//    @AfterClass
//    public static void clear() {
//        factory.close();
//    }

    @Test
    public void checkSaveEntity() {
      @Cleanup Session session = factory.openSession();
        PersonRole role = session.get(PersonRole.class, 2L);
        Person person = Person.builder()
                .avatar("qwer")
                .login("1234")
                .identification(Identification.builder()
                        .firstName("qqq")
                        .lastName("www")
                        .build())
                .age(2)
                .mail("wqeq")
                .password("222233")
                .personRole(role)
                .build();

        personDao.save(person);
        assertTrue(personDao.get(person.getId()).isPresent());
    }

    @Test
    public void checkGetEntity () {
        @Cleanup Session session = factory.openSession();
        PersonRole role = session.get(PersonRole.class, 2L);
        Person person = Person.builder()
                .avatar("qwer")
                .login("1234")
                .identification(Identification.builder()
                        .firstName("qqq")
                        .lastName("www")
                        .build())
                .age(2)
                .mail("wqeq")
                .password("222233")
                .personRole(role)
                .build();

        personDao.save(person);
        assertTrue(personDao.get(1L).isPresent());
    }

    @Test
    public void checkGetAll () {
        @Cleanup Session session = factory.openSession();
        PersonRole role = session.get(PersonRole.class, 2L);
        Person person = Person.builder()
                .avatar("qwer")
                .login("1234")
                .identification(Identification.builder()
                        .firstName("qqq")
                        .lastName("www")
                        .build())
                .age(2)
                .mail("wqeq")
                .password("222233")
                .personRole(role)
                .build();

        personDao.save(person);
        assertTrue(personDao.getAll().size()>0);
    }

    @Test
    public void checkUpdate () {
        @Cleanup Session session = factory.openSession();
        PersonRole role = session.get(PersonRole.class, 2L);
        Person person = Person.builder()
                .avatar("qwer")
                .login("1234")
                .identification(Identification.builder()
                        .firstName("qqq")
                        .lastName("www")
                        .build())
                .age(2)
                .mail("wqeq")
                .password("222233")
                .personRole(role)
                .build();

        personDao.save(person);
        personDao.get(person.getId());
        person.setAge(3);
        personDao.update(person);
        assertTrue(person.getAge()!=2);
    }

    @Test
    public void checkDelete () {
        @Cleanup Session session = factory.openSession();
        PersonRole role = session.get(PersonRole.class, 2L);
        Person person = Person.builder()
                .avatar("qwer")
                .login("1234")
                .identification(Identification.builder()
                        .firstName("qqq")
                        .lastName("www")
                        .build())
                .age(2)
                .mail("wqeq")
                .password("222233")
                .personRole(role)
                .build();

        personDao.save(person);
        personDao.delete(person);
        assertEquals(0, personDao.getAll().size());
    }
}
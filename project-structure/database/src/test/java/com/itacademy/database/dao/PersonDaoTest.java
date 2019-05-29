package com.itacademy.database.dao;

import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.PersonRole;
import com.itacademy.database.util.SessionManager;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Test;

public class PersonDaoTest {

    private static SessionFactory factory = SessionManager.getFactory();
    private final PersonDao personDao = PersonDao.getPersonDao();

    @AfterClass
    public static void clear() {
        factory.close();
    }

    @Test
    public void checkSaveEntity () {
      @Cleanup Session session = factory.openSession();
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
                .personRole(session.get(PersonRole.class,2L))
                .build();
        personDao.save(person);
    }
}
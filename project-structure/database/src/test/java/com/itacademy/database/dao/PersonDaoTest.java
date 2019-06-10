package com.itacademy.database.dao;

import com.itacademy.database.config.DatabaseConfigTest;
import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.PersonRole;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfigTest.class)
@Transactional
public class PersonDaoTest {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private RoleDao roleDao;

    @Test
    public void checkSaveEntity() {


        PersonRole role = PersonRole.builder()
                .nameOfRole("test")
                .build();
        roleDao.save(role);
        Person person = Person.builder()
                .avatar("qwerqwe")
                .login("1234")
                .identification(Identification.builder()
                        .firstName("qqq")
                        .lastName("www")
                        .build())
                .age(2)
                .mail("wqeq")
                .password("222233")
                .personRole(roleDao.get(1L).orElse(null))
                .build();

        personDao.save(person);
        assertTrue(personDao.get(person.getId()).isPresent());
    }

    @Test
    public void checkGetEntity() {
        @Cleanup Session session = personDao.getSessionFactory().openSession();
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
    public void checkGetAll() {
        PersonRole role = PersonRole.builder()
                .nameOfRole("test")
                .build();
        roleDao.save(role);
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
        assertTrue(personDao.getAll().size() > 0);
    }

    @Test
    public void checkUpdate() {
        PersonRole role = PersonRole.builder()
                .nameOfRole("test")
                .build();
        roleDao.save(role);
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
        assertTrue(person.getAge() != 2);
    }

    @Test
    public void checkDelete() {
        PersonRole role = PersonRole.builder()
                .nameOfRole("test")
                .build();
        roleDao.save(role);
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
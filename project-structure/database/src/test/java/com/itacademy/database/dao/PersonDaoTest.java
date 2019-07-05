package com.itacademy.database.dao;

import com.itacademy.database.config.DatabaseConfigTest;
import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.Category;
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

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfigTest.class)
@Transactional
public class PersonDaoTest {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ResourceDao resourceDao;

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
        assertNotNull(personDao.get(1L));
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

    @Test
    public void checkFindByName() {
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
        assertNotNull(personDao.findByName(person.getLogin()));
    }

    @Test
    public void checkAddResource() {
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

        Category category = Category.builder()
                .categoryName("www")
                .build();
        categoryDao.save(category);

        BlockResource resource = new BlockResource("test", "www",
                categoryDao.get(1L).orElse(null),
                personDao.get(1L).orElse(null),
                222, "sss", "sdg");
        resourceDao.save(resource);

        personDao.save(person);
        person.setStoreBasketResources(new ArrayList<>());
        personDao.addResource(person,resource);
        assertEquals(1,person.getStoreBasketResources().size());
    }

    @Test
    public void checkAllResourcesAtBasket() {
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

        Category category = Category.builder()
                .categoryName("www")
                .build();
        categoryDao.save(category);

        BlockResource resource = new BlockResource("test", "www",
                categoryDao.get(1L).orElse(null),
                personDao.get(1L).orElse(null),
                222, "sss", "sdg");
        resourceDao.save(resource);

        personDao.save(person);
        person.setStoreBasketResources(new ArrayList<>());
        personDao.addResource(person,resource);
        assertNotNull(personDao.allResourcesAtBasket(person));
    }

    @Test
    public void checkDeleteResourceAtBasket() {
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

        Category category = Category.builder()
                .categoryName("www")
                .build();
        categoryDao.save(category);

        BlockResource resource = new BlockResource("test", "www",
                categoryDao.get(1L).orElse(null),
                personDao.get(1L).orElse(null),
                222, "sss", "sdg");
        resourceDao.save(resource);

        personDao.save(person);
        person.setStoreBasketResources(new ArrayList<>());
        personDao.addResource(person,resource);
        personDao.deleteResourceAtBasket(person,resource);
        assertEquals(0,personDao.allResourcesAtBasket(person).size());
    }
}
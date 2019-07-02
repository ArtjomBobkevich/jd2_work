package com.itacademy.database.dao;

import com.itacademy.database.config.DatabaseConfigTest;
import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Comment;
import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.PersonRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfigTest.class)
@Transactional
public class CommentDaoTest {

    @Autowired
    PersonDao personDao;

    @Autowired
    ResourceDao resourceDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    RoleDao roleDao;


    @Test
    public void checkSaveEntity() {

        Category category = Category.builder()
                .categoryName("www")
                .build();
        categoryDao.save(category);

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
        BlockResource resource = new BlockResource("test", "www",
                categoryDao.get(1L).orElse(null),
                personDao.get(1L).orElse(null),
                222, "sss", "sdg");
        resourceDao.save(resource);

        Comment comment = Comment.builder()
                .person(person)
                .resource(resource)
                .comment("test")
                .build();

        commentDao.save(comment);
        assertTrue(commentDao.get(comment.getId()).isPresent());
    }

    @Test
    public void checkGetEntity() {
        Category category = Category.builder()
                .categoryName("www")
                .build();
        categoryDao.save(category);

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
        BlockResource resource = new BlockResource("test", "www",
                categoryDao.get(1L).orElse(null),
                personDao.get(1L).orElse(null),
                222, "sss", "sdg");
        resourceDao.save(resource);

        Comment comment = Comment.builder()
                .person(person)
                .resource(resource)
                .comment("test")
                .build();

        commentDao.save(comment);
        assertTrue(commentDao.get(1L).isPresent());
    }

    @Test
    public void checkGetAll() {
        Category category = Category.builder()
                .categoryName("www")
                .build();
        categoryDao.save(category);

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
        BlockResource resource = new BlockResource("test", "www",
                categoryDao.get(1L).orElse(null),
                personDao.get(1L).orElse(null),
                222, "sss", "sdg");
        resourceDao.save(resource);

        Comment comment = Comment.builder()
                .person(person)
                .resource(resource)
                .comment("test")
                .build();

        commentDao.save(comment);
        assertTrue(commentDao.getAll().size() > 0);
    }

    @Test
    public void checkUpdate() {
        Category category = Category.builder()
                .categoryName("www")
                .build();
        categoryDao.save(category);

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
        BlockResource resource = new BlockResource("test", "www",
                categoryDao.get(1L).orElse(null),
                personDao.get(1L).orElse(null),
                222, "sss", "sdg");
        resourceDao.save(resource);

        Comment comment = Comment.builder()
                .person(person)
                .resource(resource)
                .comment("test")
                .build();

        commentDao.save(comment);
        commentDao.get(comment.getId());
        comment.setComment("test2");
        commentDao.update(comment);
        assertTrue(!comment.getComment().equals("test"));
    }

    @Test
    public void checkDelete() {
        Category category = Category.builder()
                .categoryName("www")
                .build();
        categoryDao.save(category);

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
        BlockResource resource = new BlockResource("test", "www",
                categoryDao.get(1L).orElse(null),
                personDao.get(1L).orElse(null),
                222, "sss", "sdg");
        resourceDao.save(resource);

        Comment comment = Comment.builder()
                .person(person)
                .resource(resource)
                .comment("test")
                .build();

        commentDao.save(comment);
        commentDao.delete(comment);
        assertEquals(0, commentDao.getAll().size());
    }

    @Test
    public void checkFindByResourceIdAndPersonId() {
        Category category = Category.builder()
                .categoryName("www")
                .build();
        categoryDao.save(category);

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
        BlockResource resource = new BlockResource("test", "www",
                categoryDao.get(1L).orElse(null),
                personDao.get(1L).orElse(null),
                222, "sss", "sdg");
        resourceDao.save(resource);

        Comment comment = Comment.builder()
                .person(person)
                .resource(resource)
                .comment("test")
                .build();
        commentDao.save(comment);

        assertNotNull( commentDao.findByResourceIdAndPersonId(1L, 1L));
    }

    @Test
    public void checkFindByResourceId() {
        Category category = Category.builder()
                .categoryName("www")
                .build();
        categoryDao.save(category);

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
        BlockResource resource = new BlockResource("test", "www",
                categoryDao.get(1L).orElse(null),
                personDao.get(1L).orElse(null),
                222, "sss", "sdg");
        resourceDao.save(resource);

        Comment comment = Comment.builder()
                .person(person)
                .resource(resource)
                .comment("test")
                .build();
        commentDao.save(comment);

        assertNotNull( commentDao.findByResourceId(1L));
    }
}
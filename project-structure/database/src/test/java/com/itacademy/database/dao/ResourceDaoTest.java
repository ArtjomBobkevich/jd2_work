package com.itacademy.database.dao;

import com.itacademy.database.config.DatabaseConfigTest;
import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.FilterDto;
import com.itacademy.database.entity.Heading;
import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.PersonRole;
import com.itacademy.database.entity.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfigTest.class)
@Transactional
public class ResourceDaoTest {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private HeadingDao headingDao;

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RoleDao roleDao;

    @Test
    public void findAll() {

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

        assertTrue(resourceDao.getAll().size() > 0);

    }

    @Test
    public void findResourceByCriteria() {

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
        FilterDto filterDto = FilterDto.builder()
                .resource("test")
                .category("www")
                .price("222")
                .build();
        Integer offset = 0;
        Integer limit = 2;
        List<BlockResource> resourcesOrderByAuthor = resourceDao.findResourcesOrderByAuthor(filterDto, offset, limit);
        resourcesOrderByAuthor.size();
        assertTrue(resourcesOrderByAuthor.size() > 0);
    }

    @Test
    public void checkManyToMany() {

        Category category = Category.builder()
                .categoryName("assf")
                .build();

        categoryDao.save(category);

        Heading heading = Heading.builder()
                .headingName("sfsf")
                .category(category)
                .build();

        headingDao.save(heading);

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

        Resource blockResource = new Resource("dsfsf", "sfsf", categoryDao.get(1L).orElse(null),
                personDao.get(1L).orElse(null), 222, "dfg");

        resourceDao.addHeading(heading, blockResource);

        System.out.println(blockResource.getHeadings().size());
        assertTrue(blockResource.getHeadings().size() > 0);
    }
}
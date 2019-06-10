package com.itacademy.database.dao;

import com.itacademy.database.config.DatabaseConfigTest;
import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Heading;
import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.PersonRole;
import com.itacademy.database.entity.ProxyPredicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void findResourceByCriteria() {

        Category category = Category.builder()
                .categoryName("www")
                .build();
        categoryDao.save(category);

        Heading heading = Heading.builder()
                .headingName("sadf")
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
        BlockResource resource = new BlockResource("test","www",heading,category,person,222,"sss","sdg");
            resourceDao.save(resource);
        ProxyPredicate proxyPredicate = ProxyPredicate.builder()
                .resource("test")
                .category("www")
                .price(222)
                .build();
        Integer offset = 0;
        Integer limit = 2;
        List<BlockResource> resourcesOrderByAuthor = resourceDao.findResourcesOrderByAuthor(proxyPredicate,offset,limit);
        resourcesOrderByAuthor.size();
        }
    }
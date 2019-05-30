package com.itacademy.database.dao;

import com.itacademy.database.entity.*;
import com.itacademy.database.util.SessionManager;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class ResourceDaoTest {

    private final ResourceDao resourceDao = ResourceDao.getResourceDao();
    private static SessionFactory factory = SessionManager.getFactory();

    @Test
    public void testAllFindByOrganizationName() {
        @Cleanup Session session = factory.openSession();
        session.beginTransaction();
        Person person = Person.builder()
                .avatar("qwer")
                .login("2")
                .identification(Identification.builder()
                        .firstName("qqq")
                        .lastName("www")
                        .build())
                .age(2)
                .mail("wqeq")
                .password("222233")
                .personRole(session.get(PersonRole.class,1L))
                .build();
        session.save(person);
        session.flush();
        Resource resource = Resource.builder()
                .resourceName("www")
                .foto("www")
                .heading(session.get(Heading.class,1L))
                .category(session.get(Category.class,1L))
                .person(person)
                .price(22)
                .text("sss")
                .build();
        session.getTransaction().commit();
            resourceDao.save(resource);
        List<Resource> resourcesOrderByAuthor = resourceDao.findResourcesOrderByAuthor("2", 0, 2);
        resourcesOrderByAuthor.size();
        assertTrue(resourcesOrderByAuthor.size()>0);
        }
    }
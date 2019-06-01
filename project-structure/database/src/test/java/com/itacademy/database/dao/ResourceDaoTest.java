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
    public void findResourceByCriteria() {
        @Cleanup Session session = factory.openSession();
        session.beginTransaction();
        Category category = Category.builder()
                .categoryName("www")
                .build();
        session.save(category);
        session.flush();
        Resource resource = Resource.builder()
                .resourceName("test")
                .foto("www")
                .heading(session.get(Heading.class,1L))
                .category(category)
                .person(session.get(Person.class,2L))
                .price(222)
                .text("sss")
                .build();
        session.getTransaction().commit();
            resourceDao.save(resource);
        List<Resource> resourcesOrderByAuthor = resourceDao.findResourcesOrderByAuthor("test","www",222, 0, 2);
        resourcesOrderByAuthor.size();
        assertTrue(resourcesOrderByAuthor.size()>0);
        }
    }
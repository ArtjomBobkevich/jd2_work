package com.itacademy.database.dao;

import com.itacademy.database.config.DatabaseConfigTest;
import com.itacademy.database.entity.PersonRole;
import com.itacademy.database.util.SessionManager;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfigTest.class)
@Transactional
public class RoleDaoTest {

    private static SessionFactory factory = SessionManager.getFactory();

    @Autowired
    private RoleDao roleDao;

    @AfterClass
    public static void clear() {
        factory.close();
    }

    @Test
    public void checkSaveFactory() {
        PersonRole role = PersonRole.builder()
                .nameOfRole("test")
                .build();
        roleDao.save(role);
        }
    }
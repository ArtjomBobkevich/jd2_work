package com.itacademy.database.dao;

import com.itacademy.database.entity.PersonRole;
import com.itacademy.database.util.SessionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

public class RoleDaoTest {

    private static SessionFactory factory = SessionManager.getFactory();
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    private final RoleDao roleDao = RoleDao.getRoleDao();

    @AfterClass
    public static void clear() {
        factory.close();
    }

    @Test
    public void checkSaveFactory() {
        Session session = factory.openSession();
            session.getTransaction().begin();
            PersonRole personRole = new PersonRole("test");
            Serializable id = roleDao.save(personRole);
            session.getTransaction().commit();
            assertNotNull(id);
        }
    }


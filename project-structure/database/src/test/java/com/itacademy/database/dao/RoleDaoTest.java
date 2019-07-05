package com.itacademy.database.dao;

import com.itacademy.database.config.DatabaseConfigTest;
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
public class RoleDaoTest {

    @Autowired
    private RoleDao roleDao;

    @Test
    public void checkSaveFactory() {
        PersonRole role = PersonRole.builder()
                .nameOfRole("test")
                .build();
        roleDao.save(role);
    }

    @Test
    public void checkGetEntity() {

        PersonRole role = PersonRole.builder()
                .nameOfRole("test")
                .build();
        roleDao.save(role);
        assertNotNull(roleDao.get(1L));
    }

    @Test
    public void checkGetAll() {

        PersonRole role = PersonRole.builder()
                .nameOfRole("test")
                .build();
        roleDao.save(role);
        assertTrue(roleDao.getAll().size() > 0);
    }

    @Test
    public void checkUpdate() {
        PersonRole role = PersonRole.builder()
                .nameOfRole("test")
                .build();
        roleDao.save(role);
        roleDao.get(role.getId());
        role.setNameOfRole("test2");
        roleDao.update(role);
        assertTrue(!role.getNameOfRole().equals("test"));
    }

    @Test
    public void checkDelete() {

        PersonRole role = PersonRole.builder()
                .nameOfRole("test")
                .build();
        roleDao.save(role);
        roleDao.delete(role);
        assertEquals(0, roleDao.getAll().size());
    }
}
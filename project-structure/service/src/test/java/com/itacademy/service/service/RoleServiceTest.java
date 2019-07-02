package com.itacademy.service.service;

import com.itacademy.service.config.ServiceConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceConfigTest.class)
@Transactional
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void getById () {
        assertNotNull(roleService.findById(1L));
    }

    @Test
    public void findAll () {
        assertTrue(roleService.getAll().size()>0);
    }

}
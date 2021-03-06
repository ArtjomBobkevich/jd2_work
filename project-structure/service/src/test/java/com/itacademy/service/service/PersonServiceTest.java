package com.itacademy.service.service;

import com.itacademy.database.entity.Identification;
import com.itacademy.service.config.ServiceConfigTest;
import com.itacademy.service.dto.CreateNewPersonDto;
import com.itacademy.service.dto.ViewPersonFullInfoDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceConfigTest.class)
@Transactional
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

    @Test
    public void save() {

        CreateNewPersonDto personDto = CreateNewPersonDto.builder()
                .avatar("bung")
                .login("bung")
                .identification(Identification.builder()
                        .firstName("bung")
                        .lastName("bung")
                        .build())
                .age(22)
                .mail("bung")
                .password("bung")
                .personRole(roleService.findById(1L))
                .build();

        personService.savePerson(personDto);

        List<ViewPersonFullInfoDto> allPerson = personService.findAll();
        for (ViewPersonFullInfoDto viewPersonFullInfoDto : allPerson) {
            if (viewPersonFullInfoDto.getLogin().equals(personDto.getLogin())) {
                assertEquals(viewPersonFullInfoDto.getLogin(), personDto.getLogin());
            }
        }

    }

    @Test
    public void getById() {
        assertNotNull(personService.findById(1L));
    }

    @Test
    public void findAll() {
        assertTrue(personService.findAll().size() > 0);
    }

    @Test
    public void update() {
        CreateNewPersonDto build = CreateNewPersonDto.builder()
                .id(1L)
                .build();
        personService.update(build);
    }
}
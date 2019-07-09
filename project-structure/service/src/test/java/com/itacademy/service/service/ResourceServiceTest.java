package com.itacademy.service.service;

import com.itacademy.service.config.ServiceConfigTest;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.dto.HeadingFullDto;
import com.itacademy.service.dto.ResourceFullDto;
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
public class ResourceServiceTest {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private PersonService personService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HeadingService headingService;

    @Test
    public void save() {
        CreateResourceDto createResourceDto = CreateResourceDto.builder()
                .resourceName("bung")
                .category(categoryService.findById(1L))
                .foto("bung")
                .person(personService.findByIdEntity(1L))
                .price(22)
                .text("bung")
                .block("bung")
                .build();

        resourceService.saveResource(createResourceDto);

        List<ResourceFullDto> allResources = resourceService.findAll();
        for (ResourceFullDto resourceFullDto : allResources) {
            if (resourceFullDto.getResourceName().equals(createResourceDto.getResourceName())) {
                assertEquals(resourceFullDto.getResourceName(), createResourceDto.getResourceName());
            }
        }
    }

    @Test
    public void getById() {
        assertNotNull(resourceService.findById(1L));
    }

    @Test
    public void findAll() {
        assertTrue(resourceService.findAll().size() > 0);
    }

    @Test
    public void findByLogin() {
        CreateResourceDto createResourceDto = CreateResourceDto.builder()
                .resourceName("bung")
                .category(categoryService.findById(1L))
                .foto("bung")
                .person(personService.findByIdEntity(1L))
                .price(22)
                .text("bung")
                .block("bung")
                .build();

        resourceService.saveResource(createResourceDto);
        List<ResourceFullDto> resourceByLogin = resourceService.findResourceByLogin(createResourceDto.getPerson().getLogin());
        ResourceFullDto resourceFullDto = resourceByLogin.get(0);
        assertTrue(createResourceDto.getPerson().getLogin().equals(resourceFullDto.getPerson()));
    }

    @Test
    public void findByHeading() {
        HeadingFullDto byId = headingService.findById(1L);
        List<ResourceFullDto> byHeading = resourceService.findByHeading(byId.getHeadingName());
        ResourceFullDto resourceFullDto = byHeading.get(0);
        assertTrue(byHeading.get(0).getCategory().equals(resourceFullDto.getCategory()));
    }
}
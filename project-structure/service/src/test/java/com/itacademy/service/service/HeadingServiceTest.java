package com.itacademy.service.service;

import com.itacademy.service.config.ServiceConfigTest;
import com.itacademy.service.dto.CreateHeadingDto;
import com.itacademy.service.dto.HeadingFullDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceConfigTest.class)
@Transactional
public class HeadingServiceTest {

    @Autowired
    private HeadingService headingService;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void save() {
        CreateHeadingDto createHeadingDto = CreateHeadingDto.builder()
                .headingName("bung")
                .category(categoryService.findById(1L))
                .build();

        headingService.saveHeading(createHeadingDto);

        List<HeadingFullDto> allHeading = headingService.findAll();
        for (HeadingFullDto headingFullDto : allHeading) {
            if (headingFullDto.getHeadingName().equals(createHeadingDto.getHeadingName())) {
                assertEquals(headingFullDto.getHeadingName(), createHeadingDto.getHeadingName());
            }
        }
    }

    @Test
    public void getById() {
        assertNotNull(headingService.findById(1L));
    }

    @Test
    public void findAll() {
        assertTrue(headingService.findAll().size() > 0);
    }
}
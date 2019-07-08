package com.itacademy.service.service;

import com.itacademy.service.config.ServiceConfigTest;
import com.itacademy.service.dto.CategoryDtoByUpdate;
import com.itacademy.service.dto.CategoryFullDto;
import com.itacademy.service.dto.ChangeRoleDto;
import com.itacademy.service.dto.CommentFullDto;
import com.itacademy.service.dto.FileDto;
import com.itacademy.service.dto.FilterPredicateParametersDto;
import com.itacademy.service.dto.HeadingAddDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceConfigTest.class)
@Transactional
public class DtoTest {

    @Test
    public void testDto() {
        CategoryDtoByUpdate test = CategoryDtoByUpdate.builder()
                .categoryId(1L)
                .categoryName("test")
                .build();

        assertEquals("test", test.getCategoryName());

        CategoryFullDto test1 = CategoryFullDto.builder()
                .id(1L)
                .categoryName("test")
                .version(0L)
                .build();

        assertEquals("test", test1.getCategoryName());

        ChangeRoleDto build = ChangeRoleDto.builder()
                .personId(1L)
                .roleId(1L)
                .build();

        CommentFullDto build1 = CommentFullDto.builder()
                .person("dsf")
                .resource("dsf")
                .comment("sdfsf")
                .build();

        assertEquals("dsf", build1.getPerson());

        FilterPredicateParametersDto build2 = FilterPredicateParametersDto.builder()
                .category("asd")
                .constLimit(2)
                .limit(3)
                .offset(0)
                .resource("sdf")
                .price("222")
                .build();
        FileDto build3 = FileDto.builder()
                .file(new File("D:/jd2_work/log4j.log"))
                .build();
        assertEquals("d", build3.getFile());
        assertEquals("asd", build2.getCategory());

        HeadingAddDto.builder().build();
    }
}

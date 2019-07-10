package com.itacademy.service.service;

import com.itacademy.service.config.ServiceConfigTest;
import com.itacademy.service.dto.ByCommentSaveDto;
import com.itacademy.service.dto.CategoryDtoByUpdate;
import com.itacademy.service.dto.CategoryFullDto;
import com.itacademy.service.dto.ChangeRoleDto;
import com.itacademy.service.dto.CommentFullDto;
import com.itacademy.service.dto.CountDto;
import com.itacademy.service.dto.FilterPredicateParametersDto;
import com.itacademy.service.dto.HeadingAddDto;
import com.itacademy.service.dto.HeadingBySaveCategoryDto;
import com.itacademy.service.dto.HeadingFullDto;
import com.itacademy.service.dto.HeadingUpdateDto;
import com.itacademy.service.dto.PersonDtoByUpdate;
import com.itacademy.service.dto.ResourceAddDto;
import com.itacademy.service.dto.ResourceCriteriaDto;
import com.itacademy.service.dto.ResourceFullDto;
import com.itacademy.service.dto.RoleDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

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
//        FileDto build3 = FileDto.builder()
//                .file(new File("log4j.log"))
//                .build();
//        assertEquals("log4j.log", build3.getFile());
        assertEquals("asd", build2.getCategory());

        HeadingAddDto build4 = HeadingAddDto.builder()
                .headingId(1L)
                .build();

        assertTrue(build4.getHeadingId() == 1L);

        HeadingBySaveCategoryDto wrw = HeadingBySaveCategoryDto.builder()
                .category(1L)
                .headingName("wrw")
                .id(1L)
                .build();
        assertEquals("wrw", wrw.getHeadingName());

        HeadingUpdateDto build5 = HeadingUpdateDto.builder()
                .categoryId(1L)
                .build();

        assertTrue(build5.getCategoryId() == 1L);

        PersonDtoByUpdate sdfds = PersonDtoByUpdate.builder()
                .login("sdfds")
                .personId(1L)
                .build();

        assertEquals("sdfds", sdfds.getLogin());

        ResourceAddDto www = ResourceAddDto.builder()
                .login("www")
                .resourceId(1L)
                .build();

        assertEquals("www", www.getLogin());

        ResourceCriteriaDto build6 = ResourceCriteriaDto.builder()
                .resourceName("q")
                .limit(1)
                .offset(2)
                .build();

        assertEquals("q", build6.getResourceName());

        ResourceFullDto build7 = ResourceFullDto.builder()
                .resourceName("qwer")
                .foto("qwer")
                .category("asdf")
                .person("sdf")
                .price(222)
                .text("asdfsd")
                .block("dsfs")
                .build();

        assertEquals("qwer", build7.getResourceName());

        ByCommentSaveDto byCommentSaveDto = new ByCommentSaveDto(1L, 1L);

        assertTrue(byCommentSaveDto.getCommentId() == null);

        CountDto build = CountDto.builder()
                .page(1)
                .prevPage(2)
                .build();

        assertTrue(build.getPage() == 1);

        CountDto countDto = new CountDto(1, 2);

        assertTrue(countDto.getPage() == 1);

        FilterPredicateParametersDto filterPredicateParametersDto = new FilterPredicateParametersDto("sdf", "dsf");
        assertTrue(filterPredicateParametersDto.getResource().equals("sdf"));
        HeadingFullDto headingFullDto = new HeadingFullDto("sdf", "dsf");
        assertTrue(headingFullDto.getHeadingName().equals("sdf"));
        ResourceAddDto resourceAddDto = new ResourceAddDto(1L);
        assertTrue(resourceAddDto.getResourceId() == 1L);

        ResourceCriteriaDto resourceCriteriaDto = new ResourceCriteriaDto("dsfdsf", 1, 2);
        resourceCriteriaDto.setLimit(2);
        resourceCriteriaDto.setOffset(3);
        assertTrue(resourceCriteriaDto.getLimit() == 2);
        ResourceFullDto build8 = ResourceFullDto.builder()
                .id(1L)
                .resourceName("qwert")
                .foto("asdfs")
                .category("adsfsd")
                .person("dasfs")
                .price(222)
                .text("sdfs")
                .build();

        ResourceFullDto resourceFullDto = new ResourceFullDto("qwerty", "qwe", "qwe", "qwe", 222, "213", "qe");
        ResourceFullDto resourceFullDto1 = new ResourceFullDto(1L, "sdf", "dsfdfs", "sdf", "sdf", 222, "dsf");
        assertEquals("sdf", resourceFullDto1.getResourceName());
        assertEquals("qwerty", resourceFullDto.getResourceName());
        assertEquals("qwert", build8.getResourceName());

        RoleDto asd = RoleDto.builder()
                .role("asd")
                .build();

        assertEquals("asd", asd.getRole());

        CommentFullDto commentFullDto = new CommentFullDto("sdf", "sdaf", "sadf");
        assertTrue(commentFullDto.getPerson().equals("sdf"));

        ChangeRoleDto build3 = ChangeRoleDto.builder()
                .personId(2L)
                .roleId(1L)
                .build();

        assertTrue(build3.getPersonId() == 1L);
    }
}

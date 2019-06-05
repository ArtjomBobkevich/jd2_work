package com.itacademy.service;

import com.itacademy.database.dao.CategoryDao;
import com.itacademy.database.dao.HeadingDao;
import com.itacademy.database.dao.PersonDao;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.service.ResourceService;

public class ResourceServiceTest {

    public static void main(String[] args) {

//        List<Object> parametrs = new ArrayList<>();
//        parametrs.add("test");
//        parametrs.add("www");
//        parametrs.add(222);
//        parametrs.add(0);
//        parametrs.add(2);
//        List<ResourceFullDto> list = ResourceService.getResourceService().findResourceByCriteria(parametrs);
//        System.out.println(list.size());
        CreateResourceDto createNewGenreDto = CreateResourceDto.builder()
                .resourceName("resourceName1")
                .foto("foto2")
                .heading(HeadingDao.getHeadingDao().get(2L).orElse(null))
                .category(CategoryDao.getCategoryDao().get(2L).orElse(null))
                .person(PersonDao.getPersonDao().get(2L).orElse(null))
                .price(223)
                .text("text2")
                .block("NO")
                .build();
        ResourceService.getResourceService().saveResource(createNewGenreDto);
    }
}
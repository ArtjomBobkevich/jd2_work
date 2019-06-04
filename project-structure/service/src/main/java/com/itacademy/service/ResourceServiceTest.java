package com.itacademy.service;

import com.itacademy.service.dto.ResourceFullDto;
import com.itacademy.service.service.ResourceService;

import java.util.ArrayList;
import java.util.List;

public class ResourceServiceTest {

    public static void main(String[] args) {

        List<Object> parametrs = new ArrayList<>();
        parametrs.add("test");
        parametrs.add("www");
        parametrs.add(222);
        parametrs.add(0);
        parametrs.add(2);
        List<ResourceFullDto> list = ResourceService.getResourceService().findResourceByCriteria(parametrs);
        System.out.println(list.size());
    }
}
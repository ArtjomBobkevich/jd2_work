package com.itacademy.service;

import com.itacademy.service.dto.ResourceFullDto;
import com.itacademy.service.service.ResourceService;

import java.util.List;

public class ResourceServiceTest {

    public static void main(String[] args) {

        List<ResourceFullDto> list = ResourceService.getResourceService().findResourceByCriteria("test","www",222, 0, 2);
        System.out.println(list.size());
    }
}
package com.itacademy.service.service;

import com.itacademy.database.dao.ResourceDao;
import com.itacademy.service.dto.ResourceFullDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceService {
    private static final ResourceService RESOURCE_SERVICE = new ResourceService();

    public List<ResourceFullDto> findResourceByCriteria(String author, Integer offset, Integer limit) {
        return ResourceDao.getResourceDao().findResourcesOrderByAuthor(author,offset,limit).stream()
                .map(it -> new ResourceFullDto(it.getId(), it.getResourceName(), it.getFoto(), it.getHeading(), it.getCategory(),
                        it.getPerson(), it.getPrice(), it.getText()))
                .collect(Collectors.toList());
    }

    public static ResourceService getResourceService() {
        return RESOURCE_SERVICE;
    }
}
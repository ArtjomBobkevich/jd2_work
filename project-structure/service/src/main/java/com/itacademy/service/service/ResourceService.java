package com.itacademy.service.service;

import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.ProxyPredicate;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.dto.PredicateDto;
import com.itacademy.service.dto.ResourceFullDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.itacademy.database.dao.ResourceDao.getResourceDao;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceService {
    private static final ResourceService RESOURCE_SERVICE = new ResourceService();

    public List<ResourceFullDto> findAll() {
        return getResourceDao().getAll().stream()
                .map(it -> new ResourceFullDto(it.getId(), it.getResourceName(), it.getFoto(), it.getHeading().getHeadingName(), it.getCategory().getCategoryName(),
                        it.getPerson().getLogin(), it.getPrice(), it.getText(), it.getBlock()))
                .collect(Collectors.toList());
    }

    public ResourceFullDto findById(Long id) {
        return getResourceDao().get(id).map(it -> new ResourceFullDto(it.getResourceName(), it.getFoto(), it.getHeading().getHeadingName(),
                it.getCategory().getCategoryName(), it.getPerson().getLogin(), it.getPrice(), it.getText(), it.getBlock()))
                .orElse(null);
    }

    public Long saveResource(CreateResourceDto createResource) {

        return getResourceDao().save(new BlockResource(createResource.getResourceName(), createResource.getFoto(), createResource.getHeading(),
                createResource.getCategory(), createResource.getPerson(), createResource.getPrice(), createResource.getText(),
                createResource.getBlock()));
    }

    public void delete(BlockResource blockResource) {
        getResourceDao().delete(blockResource);
    }

    public void update(BlockResource blockResource) {
        getResourceDao().update(blockResource);
    }

    public List<ResourceFullDto> findResourceByCriteria(PredicateDto predicateDto, Integer offset, Integer limit) {
        ProxyPredicate proxyPredicate = new ProxyPredicate(predicateDto.getResource(), predicateDto.getCategory(), predicateDto.getPrice());
        return getResourceDao().findResourcesOrderByAuthor(proxyPredicate,offset,limit).stream()
                .map(it -> new ResourceFullDto(it.getResourceName(), it.getFoto(), it.getHeading().getHeadingName(), it.getCategory().getCategoryName(),
                        it.getPerson().getLogin(), it.getPrice(), it.getText(), it.getBlock()))
                .collect(Collectors.toList());
    }

    public Map<Integer,List<BlockResource>> allByPages (PredicateDto predicateDto, Integer limit){
        ProxyPredicate proxyPredicate = new ProxyPredicate(predicateDto.getResource(), predicateDto.getCategory(), predicateDto.getPrice());
        return getResourceDao().allPages(proxyPredicate,limit);
    }

    public List<Integer> countPages (PredicateDto predicateDto, Integer limit) {
        ProxyPredicate proxyPredicate = new ProxyPredicate(predicateDto.getResource(), predicateDto.getCategory(), predicateDto.getPrice());
        return getResourceDao().countPages(proxyPredicate,limit);
    }

//    public List<ResourceFullDto> allResourceByCriteria(List<Resource>list,Integer offset, Integer limit) {
//        return ResourceDao.getResourceDao().allResourceByCriteria(list,offset,limit).stream()
//                .map(it -> new ResourceFullDto(it.getId(), it.getResourceName(), it.getFoto(), it.getHeading(), it.getCategory(),
//                        it.getPerson(), it.getPrice(), it.getText()))
//                .collect(Collectors.toList());
//    }

//    public void allPages () {
//        ResourceDao.getResourceDao().allPages()
//    }

    public static ResourceService getResourceService() {
        return RESOURCE_SERVICE;
    }
}
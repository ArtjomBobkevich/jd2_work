package com.itacademy.service.service;

import com.itacademy.database.dao.ResourceDao;
import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.ProxyPredicate;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.dto.PredicateDto;
import com.itacademy.service.dto.ResourceFullDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
public class ResourceService {

    private final ResourceDao resourceDao;

    public List<ResourceFullDto> findAll() {
        return resourceDao.getAll().stream()
                .map(it -> new ResourceFullDto(it.getId(), it.getResourceName(), it.getFoto(), it.getHeading().getHeadingName(), it.getCategory().getCategoryName(),
                        it.getPerson().getLogin(), it.getPrice(), it.getText(), it.getBlock()))
                .collect(Collectors.toList());
    }

    public ResourceFullDto findById(Long id) {
        return resourceDao.get(id).map(it -> new ResourceFullDto(it.getResourceName(), it.getFoto(), it.getHeading().getHeadingName(),
                it.getCategory().getCategoryName(), it.getPerson().getLogin(), it.getPrice(), it.getText(), it.getBlock()))
                .orElse(null);
    }

    @Transactional
    public Long saveResource(CreateResourceDto createResource) {

        return resourceDao.save(new BlockResource(createResource.getResourceName(), createResource.getFoto(), createResource.getHeading(),
                createResource.getCategory(), createResource.getPerson(), createResource.getPrice(), createResource.getText(),
                createResource.getBlock()));
    }

    @Transactional
    public void delete(BlockResource blockResource) {
        resourceDao.delete(blockResource);
    }

    @Transactional
    public void update(BlockResource blockResource) {
        resourceDao.update(blockResource);
    }

    public List<ResourceFullDto> findResourceByCriteria(PredicateDto predicateDto, Integer offset, Integer limit) {
        ProxyPredicate proxyPredicate;
        if (predicateDto.getPrice()!=null){
             proxyPredicate = new ProxyPredicate(predicateDto.getResource(), predicateDto.getCategory(), predicateDto.getPrice());
        } else
             proxyPredicate = new ProxyPredicate(predicateDto.getResource(), predicateDto.getCategory());

        return resourceDao.findResourcesOrderByAuthor(proxyPredicate,offset,limit).stream()
                .map(it -> new ResourceFullDto(it.getResourceName(), it.getFoto(), it.getHeading().getHeadingName(), it.getCategory().getCategoryName(),
                        it.getPerson().getLogin(), it.getPrice(), it.getText(), it.getBlock()))
                .collect(Collectors.toList());
    }

    public Map<Integer,List<BlockResource>> allByPages (PredicateDto predicateDto, Integer limit){
        ProxyPredicate proxyPredicate = new ProxyPredicate(predicateDto.getResource(), predicateDto.getCategory(), predicateDto.getPrice());
        return resourceDao.allPages(proxyPredicate,limit);
    }

    public List<Integer> countPages (PredicateDto predicateDto, Integer limit) {
        ProxyPredicate proxyPredicate = new ProxyPredicate(predicateDto.getResource(), predicateDto.getCategory(), predicateDto.getPrice());
        return resourceDao.countPages(proxyPredicate,limit);
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

//    public static ResourceService getResourceService() {
//        return RESOURCE_SERVICE;
//    }
}
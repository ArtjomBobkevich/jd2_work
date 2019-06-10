package com.itacademy.service.service;

import com.itacademy.database.dao.ResourceDao;
import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.FilterDto;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.dto.PredicateDto;
import com.itacademy.service.dto.ResourceFullDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
public class ResourceService {

    private final ResourceDao resourceDao;

    public List<ResourceFullDto> findAll() {
        return resourceDao.getAll().stream()
                .map(it -> new ResourceFullDto(it.getId(), it.getResourceName(), it.getFoto(), it.getCategory().getCategoryName(),
                        it.getPerson().getLogin(), it.getPrice(), it.getText(), it.getBlock()))
                .collect(Collectors.toList());
    }

    public ResourceFullDto findById(Long id) {
        return resourceDao.get(id).map(it -> new ResourceFullDto(it.getResourceName(), it.getFoto(),
                it.getCategory().getCategoryName(), it.getPerson().getLogin(), it.getPrice(), it.getText(), it.getBlock()))
                .orElse(null);
    }

    @Transactional
    public Long saveResource(CreateResourceDto createResource) {

        return resourceDao.save(new BlockResource(createResource.getResourceName(), createResource.getFoto(),
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
        FilterDto filterDto;
            filterDto = new FilterDto(predicateDto.getResource(), predicateDto.getCategory(), predicateDto.getPrice());

        return resourceDao.findResourcesOrderByAuthor(filterDto, offset, limit).stream()
                .map(it -> new ResourceFullDto(it.getResourceName(), it.getFoto(), it.getCategory().getCategoryName(),
                        it.getPerson().getLogin(), it.getPrice(), it.getText(), it.getBlock()))
                .collect(Collectors.toList());
    }

    public Integer countPages(PredicateDto predicateDto, Integer limit) {
        FilterDto filterDto = new FilterDto(predicateDto.getResource(), predicateDto.getCategory(), predicateDto.getPrice());
        return resourceDao.countPages(filterDto, limit);
    }

   /* public boolean addHeading (CreateHeadingDto createHeadingDto, CreateResourceDto createResourceDto) {

        BlockResource blockResource

        return resource.getHeadings().add(heading);
    }*/
}
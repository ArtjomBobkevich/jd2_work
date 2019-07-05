package com.itacademy.service.service;

import com.itacademy.database.dao.HeadingDao;
import com.itacademy.database.dao.ResourceDao;
import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.FilterDto;
import com.itacademy.database.entity.Resource;
import com.itacademy.service.dto.CountDto;
import com.itacademy.service.dto.CreateHeadingDto;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.dto.FilterPredicateParametersDto;
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
    private final HeadingDao headingDao;

    public List<ResourceFullDto> findByHeading(String nameOfHeading) {
        return resourceDao.findByHeading(nameOfHeading).stream()
                .map(it -> new ResourceFullDto(it.getId(), it.getResourceName(), it.getFoto(), it.getCategory().getCategoryName(),
                        it.getPerson().getLogin(), it.getPrice(), it.getText())).collect(Collectors.toList());
    }

    public List<ResourceFullDto> findAll() {
        return resourceDao.getAll().stream()
                .map(it -> new ResourceFullDto(it.getId(), it.getResourceName(), it.getFoto(), it.getCategory().getCategoryName(),
                        it.getPerson().getLogin(), it.getPrice(), it.getText(), it.getBlock()))
                .collect(Collectors.toList());
    }

    public ResourceFullDto findById(Long id) {
        return resourceDao.get(id).map(it -> new ResourceFullDto(it.getId(), it.getResourceName(), it.getFoto(),
                it.getCategory().getCategoryName(), it.getPerson().getLogin(), it.getPrice(), it.getText(), it.getBlock()))
                .orElse(null);
    }

    public Resource findByIdEntity(Long id) {
        return resourceDao.get(id).orElse(null);
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

    public List<ResourceFullDto> findResourceByCriteria(FilterPredicateParametersDto filterParametersDto) {
        FilterDto filterDto;
        filterDto = new FilterDto(filterParametersDto.getResource(), filterParametersDto.getCategory(), filterParametersDto.getPrice());

        return resourceDao.findResourcesOrderByAuthor(filterDto, filterParametersDto.getOffset(), filterParametersDto.getLimit()).stream()
                .map(it -> new ResourceFullDto(it.getId(), it.getResourceName(), it.getFoto(), it.getCategory().getCategoryName(),
                        it.getPerson().getLogin(), it.getPrice(), it.getText(), it.getBlock()))
                .collect(Collectors.toList());
    }

    public List<CountDto> countPages(FilterPredicateParametersDto filterPredicateParametersDto, int limit) {
        FilterDto filterDto = new FilterDto(filterPredicateParametersDto.getResource(), filterPredicateParametersDto.getCategory(), filterPredicateParametersDto.getPrice());
        return resourceDao.countPages(filterDto, limit).stream().map(it -> new CountDto(it.getCount())).collect(Collectors.toList());
    }

    @Transactional
    public void addHeading(CreateHeadingDto createHeadingDto, CreateResourceDto createResourceDto) {

        resourceDao.addHeading(headingDao.get(createHeadingDto.getId()).orElse(null),
                resourceDao.get(createResourceDto.getId()).orElse(null));
    }

    public Resource findByResourceName(String resourceName) {
        return resourceDao.findByResourceName(resourceName);
    }

    public List<ResourceFullDto> findResourceByLogin(String login) {
        return resourceDao.findResourceByLogin(login).stream()
                .map(it -> new ResourceFullDto(it.getId(), it.getResourceName(), it.getFoto(), it.getCategory().getCategoryName(),
                        it.getPerson().getLogin(), it.getPrice(), it.getText()))
                .collect(Collectors.toList());
    }
}
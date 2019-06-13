package com.itacademy.service.service;

import com.itacademy.database.dao.ResourceDao;
import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.FilterDto;
import com.itacademy.database.entity.Heading;
import com.itacademy.database.entity.Resource;
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

    public List<ResourceFullDto> findResourceByCriteria(FilterPredicateParametersDto filterParametersDto) {
        FilterDto filterDto;
            filterDto = new FilterDto(filterParametersDto.getResource(), filterParametersDto.getCategory(), filterParametersDto.getPrice());

        return resourceDao.findResourcesOrderByAuthor(filterDto, filterParametersDto.getOffset(), filterParametersDto.getLimit()).stream()
                .map(it -> new ResourceFullDto(it.getResourceName(), it.getFoto(), it.getCategory().getCategoryName(),
                        it.getPerson().getLogin(), it.getPrice(), it.getText(), it.getBlock()))
                .collect(Collectors.toList());
    }

    public Integer countPages(FilterPredicateParametersDto filterPredicateParametersDto, Integer limit) {
        FilterDto filterDto = new FilterDto(filterPredicateParametersDto.getResource(), filterPredicateParametersDto.getCategory(), filterPredicateParametersDto.getPrice());
        return resourceDao.countPages(filterDto, limit);
    }

    @Transactional
    public void addHeading (CreateHeadingDto createHeadingDto, CreateResourceDto createResourceDto) {
        Resource resource = Resource.builder()
                .id(createResourceDto.getId())
                .resourceName(createResourceDto.getResourceName())
                .foto(createResourceDto.getFoto())
                .category(createResourceDto.getCategory())
                .person(createResourceDto.getPerson())
                .price(createResourceDto.getPrice())
                .text(createResourceDto.getText())
                .build();

        Heading heading = new Heading(createHeadingDto.getId(),createHeadingDto.getHeadingName(),createHeadingDto.getCategory());

        resourceDao.addHeading(heading, resource);
    }
}
package com.itacademy.service.service;

import com.itacademy.database.dao.HeadingDao;
import com.itacademy.database.entity.Heading;
import com.itacademy.service.dto.CreateHeadingDto;
import com.itacademy.service.dto.HeadingFullDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
public class HeadingService {

    private final HeadingDao headingDao;

    public List<HeadingFullDto> findAll() {
        return headingDao.getAll().stream()
                .map(it -> new HeadingFullDto(it.getId(), it.getHeadingName(), it.getCategory().getCategoryName()))
                .collect(Collectors.toList());
    }

    public List<HeadingFullDto> findByCategoryId(Long categoryId) {
        return headingDao.findByCategoryId(categoryId).stream()
                .map(it -> new HeadingFullDto(it.getId(), it.getHeadingName(), it.getCategory().getCategoryName()))
                .collect(Collectors.toList());
    }


    public HeadingFullDto findById(Long id) {
        return headingDao.get(id).map(it -> new HeadingFullDto(it.getId(), it.getHeadingName(), it.getCategory().getCategoryName())).orElse(null);
    }

    @Transactional
    public Long saveHeading(CreateHeadingDto createHeadingDto) {
        return headingDao.save(Heading.builder()
                        .headingName(createHeadingDto.getHeadingName())
                        .category(createHeadingDto.getCategory())
                        .build());
    }

    @Transactional
    public void deleteHeading(Heading heading) {
        headingDao.delete(heading);
    }

    @Transactional
    public void updateHeading(CreateHeadingDto createHeadingDto) {
        headingDao.update(Heading.builder()
                .id(createHeadingDto.getId())
                .headingName(createHeadingDto.getHeadingName())
                .category(createHeadingDto.getCategory())
                .build());
    }
}
package com.itacademy.service.service;

import com.itacademy.service.dto.HeadingFullDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.itacademy.database.dao.HeadingDao.getHeadingDao;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeadingService {

    private static final  HeadingService HEADING_SERVICE = new HeadingService();

    public List<HeadingFullDto> findAll() {
        return getHeadingDao().getAll().stream()
                .map(it -> new HeadingFullDto(it.getId(),it.getHeadingName(),it.getCategory().getCategoryName()))
                .collect(Collectors.toList());
    }

    public static HeadingService getHeadingService() {
        return HEADING_SERVICE;
    }
}

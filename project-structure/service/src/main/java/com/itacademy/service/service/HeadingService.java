package com.itacademy.service.service;

import com.itacademy.database.dao.HeadingDao;
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
                .map(it -> new HeadingFullDto(it.getId(),it.getHeadingName(),it.getCategory().getCategoryName()))
                .collect(Collectors.toList());
    }

//    public static HeadingService getHeadingService() {
//        return HEADING_SERVICE;
//    }
}

package com.itacademy.service.service;

import com.itacademy.database.dao.RoleDao;
import com.itacademy.database.entity.PersonRole;
import com.itacademy.service.dto.RoleFullDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
public class RoleService {

    private final RoleDao roleDao;

    public List<RoleFullDto> getAll () {
        return roleDao.getAll().stream().map(it->new RoleFullDto(it.getId(),it.getNameOfRole())).collect(Collectors.toList());
    }

    public PersonRole findById(Long id) {
        return roleDao.get(id).orElse(null);
    }
}
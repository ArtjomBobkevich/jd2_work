package com.itacademy.service.service;

import com.itacademy.database.dao.RoleDao;
import com.itacademy.database.entity.PersonRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
public class RoleService {

    private final RoleDao roleDao;

    public PersonRole findById(Long id) {
        return roleDao.get(id).orElse(null);
    }
}
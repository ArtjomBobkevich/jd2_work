package com.itacademy.database.dao;

import com.itacademy.database.entity.PersonRole;

public class RoleDao implements BaseDao<Long,PersonRole> {

    private static final RoleDao ROLE_DAO = new RoleDao();



    public static RoleDao getRoleDao() {
        return ROLE_DAO;
    }
}

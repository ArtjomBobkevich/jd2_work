package com.itacademy.database.dao;

import com.itacademy.database.entity.Heading;

public class HeadingDao implements BaseDao<Long,Heading>{

    private static final HeadingDao HEADING_DAO = new HeadingDao();

    public static HeadingDao getHeadingDao() {
        return HEADING_DAO;
    }
}

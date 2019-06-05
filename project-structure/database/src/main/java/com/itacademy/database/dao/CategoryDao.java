package com.itacademy.database.dao;

import com.itacademy.database.entity.Category;

public class CategoryDao implements BaseDao<Long,Category> {

    private static final CategoryDao CATEGORY_DAO = new CategoryDao();

    public static CategoryDao getCategoryDao() {
        return CATEGORY_DAO;
    }
}

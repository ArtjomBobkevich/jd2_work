package com.itacademy.database.dao;

import com.itacademy.database.entity.Heading;
import com.itacademy.database.entity.Resource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HeadingDao extends BaseDaoImpl<Long, Heading> {

    public List<Heading> findByCategoryId(Long categoryId) {
        return new ArrayList<>(getSessionFactory().getCurrentSession()
                .createQuery("select h from Heading h join h.category c where c.id= :categoryId", Heading.class)
                .setParameter("categoryId", categoryId)
                .list());
    }
}
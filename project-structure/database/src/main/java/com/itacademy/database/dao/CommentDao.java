package com.itacademy.database.dao;

import com.itacademy.database.entity.Comment;
import com.itacademy.database.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDao extends BaseDaoImpl<Long, Comment> {

    public List<Comment> findByResourceId(Long id) {
        return new ArrayList<>(getSessionFactory().getCurrentSession()
                .createQuery("select c from Comment c join c.resource r where r.id= :id", Comment.class)
                .setParameter("id", id)
                .list());
    }

    public Comment findByResourceIdAndPersonId(Long resourceId, Long personId) {

        return getSessionFactory().getCurrentSession()
                .createQuery("select c from Comment c join c.resource r on r.id = c.resource_id join c.person p on p.id = c.person_id where r.id= :resourceId and p.id= :personId", Comment.class)
                .setParameter("resourceId", resourceId)
                .setParameter("personId", personId)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }
}

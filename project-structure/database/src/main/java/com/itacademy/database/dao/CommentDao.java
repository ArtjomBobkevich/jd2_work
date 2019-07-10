package com.itacademy.database.dao;

import com.itacademy.database.entity.Comment;
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

    public List<Comment> findPersonId(Long personId) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select c from Comment c join c.person p where p.id= :personId", Comment.class)
                .setParameter("personId", personId)
                .list();
    }
}

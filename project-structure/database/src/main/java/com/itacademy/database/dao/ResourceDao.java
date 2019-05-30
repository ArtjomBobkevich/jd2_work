package com.itacademy.database.dao;

import com.itacademy.database.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class ResourceDao implements BaseDao<Long,Resource> {

    private static final ResourceDao RESOURCE_DAO = new ResourceDao();
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();



    public List<Resource> findResourcesOrderByAuthor(String author, int limit, int offset) {
        Session session = FACTORY.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Resource> criteria = cb.createQuery(Resource.class);
        Root<Resource> root = criteria.from(Resource.class);
        Join<Resource, Person> personJoin = root.join(Resource_.PERSON);
        criteria.select(root).where(
                cb.equal(personJoin.get(Person_.LOGIN), author)
        ).orderBy(
                cb.asc(root.get(Resource_.resourceName))
        );

        return session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public List<Resource> findResourcesOrderByAuthor(String category,String author, int limit, int offset) {
        Session session = FACTORY.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Resource> criteria = cb.createQuery(Resource.class);
        Root<Resource> root = criteria.from(Resource.class);
        Join<Resource, Person> personJoin = root.join(Resource_.PERSON);
        Join<Resource, Category> categoryJoin = root.join(Resource_.CATEGORY);
        criteria.select(root).where(
                cb.equal(personJoin.get(Person_.LOGIN), author),
                cb.equal(categoryJoin.get(Category_.CATEGORY_NAME),category)
        ).orderBy(
                cb.asc(root.get(Resource_.resourceName))
        );

        return session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public List<Resource> findResourcesOrderByAuthor(Integer price, String category, String author, int limit, int offset) {
        Session session = FACTORY.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Resource> criteria = cb.createQuery(Resource.class);
        Root<Resource> root = criteria.from(Resource.class);
        Join<Resource, Person> personJoin = root.join(Resource_.PERSON);
        Join<Resource, Category> categoryJoin = root.join(Resource_.CATEGORY);
        criteria.select(root).where(
                cb.equal(personJoin.get(Person_.LOGIN), author),
                cb.equal(categoryJoin.get(Category_.CATEGORY_NAME),category),
                cb.equal(root.get(Resource_.PRICE),price)
        ).orderBy(
                cb.asc(root.get(Resource_.resourceName))
        );

        return session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public static ResourceDao getResourceDao() {
        return RESOURCE_DAO;
    }
}

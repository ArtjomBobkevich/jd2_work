package com.itacademy.database.dao;

import com.itacademy.database.entity.*;
import com.itacademy.database.util.SessionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class ResourceDao implements BaseDao<Long, Resource> {

    private static final ResourceDao RESOURCE_DAO = new ResourceDao();
    private static SessionFactory factory = SessionManager.getFactory();

    public List<Resource> findResourcesOrderByAuthor(String resourceName, int offset, int limit) {
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Resource> criteria = cb.createQuery(Resource.class);
        Root<Resource> root = criteria.from(Resource.class);
        criteria.select(root).where(
                cb.equal(root.get(Resource_.resourceName), resourceName)
        )
                .orderBy(
                        cb.asc(root.get(Resource_.resourceName))
                );
        return session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public List<Resource> findResourcesOrderByAuthor(String resourceName, String category, int offset, int limit) {
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Resource> criteria = cb.createQuery(Resource.class);
        Root<Resource> root = criteria.from(Resource.class);
//        Join<Resource, Person> personJoin = root.join(Resource_.person);
        Join<Resource, Category> categoryJoin = root.join(Resource_.category);
        criteria.select(root).where(
                cb.equal(root.get(Resource_.resourceName), resourceName),
                cb.equal(categoryJoin.get(Category_.categoryName), category)
        ).orderBy(
                cb.asc(root.get(Resource_.resourceName))
        );
        return session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public List<Resource> findResourcesOrderByAuthor(String resourceName, String category,Integer price,  int offset, int limit) {
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Resource> criteria = cb.createQuery(Resource.class);
        Root<Resource> root = criteria.from(Resource.class);
        Join<Resource, Category> categoryJoin = root.join(Resource_.category);
        criteria.select(root).where(
                cb.equal(root.get(Resource_.resourceName), resourceName),
                cb.equal(categoryJoin.get(Category_.categoryName), category),
                cb.equal(root.get(Resource_.price), price)
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

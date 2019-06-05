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

public class ResourceDao implements BaseDao<Long, BlockResource> {

    private static final ResourceDao RESOURCE_DAO = new ResourceDao();
    private static SessionFactory factory = SessionManager.getFactory();

    public List<BlockResource> findResourcesOrderByAuthor(List<Object> parameters) {
        Session session = factory.openSession();
        String resourceName = (String) parameters.get(0);
        String category = (String) parameters.get(1);
        Integer price = (Integer) parameters.get(2);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BlockResource> criteria = cb.createQuery(BlockResource.class);
        Root<BlockResource> root = criteria.from(BlockResource.class);
        Join<BlockResource, Category> categoryJoin = root.join(BlockResource_.category);


//        criteria.select(root).where(filter.getPredicates());
        criteria.select(root).where(
                cb.equal(root.get(BlockResource_.resourceName), resourceName),
                cb.equal(categoryJoin.get(Category_.categoryName), category),
                cb.equal(root.get(BlockResource_.price), price)
        );
        criteria.select(root).where(
                cb.equal(root.get(BlockResource_.resourceName), resourceName),
                cb.equal(categoryJoin.get(Category_.categoryName), category),
                cb.equal(root.get(BlockResource_.price), price)
        );
        criteria.select(root).where(
                cb.equal(root.get(BlockResource_.resourceName), resourceName),
                cb.equal(categoryJoin.get(Category_.categoryName), category),
                cb.equal(root.get(BlockResource_.price), price)
        );
        criteria.select(root).where(
                cb.equal(root.get(BlockResource_.resourceName), resourceName),
                cb.equal(categoryJoin.get(Category_.categoryName), category),
                cb.equal(root.get(BlockResource_.price), price)
        );
        criteria.select(root).where(
                cb.equal(root.get(BlockResource_.resourceName), resourceName),
                cb.equal(categoryJoin.get(Category_.categoryName), category),
                cb.equal(root.get(BlockResource_.price), price)
        );
        criteria.select(root).where(
                cb.equal(root.get(BlockResource_.resourceName), resourceName),
                cb.equal(categoryJoin.get(Category_.categoryName), category),
                cb.equal(root.get(BlockResource_.price), price)
        );

        Integer offset = (Integer) parameters.get(3);
        Integer limit = (Integer) parameters.get(4);

        List<BlockResource> list = session.createQuery(criteria).list();

        return session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public void pages() {
    }

    public static ResourceDao getResourceDao() {
        return RESOURCE_DAO;
    }
}
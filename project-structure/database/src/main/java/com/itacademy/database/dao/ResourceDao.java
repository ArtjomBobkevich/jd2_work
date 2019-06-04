package com.itacademy.database.dao;

import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Category_;
import com.itacademy.database.entity.Resource;
import com.itacademy.database.entity.Resource_;
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

    public List<Resource> findResourcesOrderByAuthor(List<Object>parameters) {
        Session session = factory.openSession();
        String resourceName = (String) parameters.get(0);
        String category = (String) parameters.get(1);
        Integer price = (Integer) parameters.get(2);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Resource> criteria = cb.createQuery(Resource.class);
        Root<Resource> root = criteria.from(Resource.class);
        Join<Resource, Category> categoryJoin = root.join(Resource_.category);
        criteria.select(root).where(
                cb.equal(root.get(Resource_.resourceName),resourceName),
                cb.equal(categoryJoin.get(Category_.categoryName), category),
                cb.equal(root.get(Resource_.price), price)
        );
        criteria.select(root).where(
                cb.equal(root.get(Resource_.resourceName),resourceName),
                cb.equal(categoryJoin.get(Category_.categoryName), category),
                cb.equal(root.get(Resource_.price), price)
        );
        criteria.select(root).where(
                cb.equal(root.get(Resource_.resourceName),resourceName),
                cb.equal(categoryJoin.get(Category_.categoryName), category),
                cb.equal(root.get(Resource_.price), price)
        );
        criteria.select(root).where(
                cb.equal(root.get(Resource_.resourceName),resourceName),
                cb.equal(categoryJoin.get(Category_.categoryName), category),
                cb.equal(root.get(Resource_.price), price)
        );
        criteria.select(root).where(
                cb.equal(root.get(Resource_.resourceName),resourceName),
                cb.equal(categoryJoin.get(Category_.categoryName), category),
                cb.equal(root.get(Resource_.price), price)
        );
        criteria.select(root).where(
                cb.equal(root.get(Resource_.resourceName),resourceName),
                cb.equal(categoryJoin.get(Category_.categoryName), category),
                cb.equal(root.get(Resource_.price), price)
        );

        Integer offset = (Integer) parameters.get(3);
        Integer limit = (Integer) parameters.get(4);

        List<Resource> list = session.createQuery(criteria).list();



        return session.createQuery(criteria)
                .setFirstResult( offset)
                .setMaxResults(limit)
                .list();
    }

    public void pages () {
    }




    public static ResourceDao getResourceDao() {
        return RESOURCE_DAO;
    }
}
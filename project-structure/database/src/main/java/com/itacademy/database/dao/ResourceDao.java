package com.itacademy.database.dao;

import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.BlockResource_;
import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Category_;
import com.itacademy.database.entity.ProxyPredicate;
import com.itacademy.database.util.SessionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceDao implements BaseDao<Long, BlockResource> {

    private static final ResourceDao RESOURCE_DAO = new ResourceDao();
    private static SessionFactory factory = SessionManager.getFactory();

    public List<BlockResource> findResourcesOrderByAuthor(ProxyPredicate proxyPredicate, Integer offset, Integer limit) {

        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BlockResource> criteria = cb.createQuery(BlockResource.class);
        Root<BlockResource> root = criteria.from(BlockResource.class);
        Join<BlockResource, Category> categoryJoin = root.join(BlockResource_.category);
//        criteria.select(root).where(build(cb, proxyPredicate));
        if (!proxyPredicate.getResource().equals("") && !proxyPredicate.getCategory().equals("") && proxyPredicate.getPrice() != null){
            criteria.select(root).where(
                    cb.equal(root.get(BlockResource_.resourceName), proxyPredicate.getResource()),
                    cb.equal(categoryJoin.get(Category_.categoryName), proxyPredicate.getCategory()),
                    cb.equal(root.get(BlockResource_.price), proxyPredicate.getPrice()));
        } else if (!proxyPredicate.getResource().equals("") && !proxyPredicate.getCategory().equals("")&& proxyPredicate.getPrice() == null) {
            criteria.select(root).where(
            cb.equal(root.get(BlockResource_.resourceName), proxyPredicate.getResource()),
                    cb.equal(categoryJoin.get(Category_.categoryName), proxyPredicate.getCategory()));
        } else if (!proxyPredicate.getResource().equals("")){
            criteria.select(root).where(
                    cb.equal(root.get(BlockResource_.resourceName), proxyPredicate.getResource()));
        } else if (!proxyPredicate.getCategory().equals("")&& proxyPredicate.getPrice() == null) {
            criteria.select(root).where(
                    cb.equal(categoryJoin.get(Category_.categoryName), proxyPredicate.getCategory()));
        } else if (proxyPredicate.getPrice() != null) {
            criteria.select(root).where(
                    cb.equal(root.get(BlockResource_.price), proxyPredicate.getPrice()));
        }

        return session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }


    public Predicate[] build(CriteriaBuilder cb, ProxyPredicate proxyPredicate) {
        List<Predicate> predicates = new ArrayList<>();
        CriteriaQuery<BlockResource> criteria = cb.createQuery(BlockResource.class);
        Root<BlockResource> root = criteria.from(BlockResource.class);
        Join<BlockResource, Category> categoryJoin = root.join(BlockResource_.category);
        if (!proxyPredicate.getResource().equals("") && !proxyPredicate.getCategory().equals("") && proxyPredicate.getPrice() != null) {
            predicates.add(cb.equal(root.get(BlockResource_.resourceName), proxyPredicate.getResource()));
            predicates.add(cb.equal(categoryJoin.get(Category_.categoryName), proxyPredicate.getCategory()));
            predicates.add(cb.equal(root.get(BlockResource_.price), proxyPredicate.getPrice()));
        } else if (!proxyPredicate.getResource().equals("") && !proxyPredicate.getCategory().equals("")) {
            predicates.add(cb.equal(root.get(BlockResource_.resourceName), proxyPredicate.getResource()));
            predicates.add(cb.equal(categoryJoin.get(Category_.categoryName), proxyPredicate.getCategory()));
        } else if (!proxyPredicate.getResource().equals("")) {
            predicates.add(cb.equal(root.get(BlockResource_.resourceName), proxyPredicate.getResource()));
        } else if (!proxyPredicate.getCategory().equals("")) {
            predicates.add(cb.equal(categoryJoin.get(Category_.categoryName), proxyPredicate.getCategory()));
        } else if (proxyPredicate.getPrice() != null) {
            predicates.add(cb.equal(root.get(BlockResource_.price), proxyPredicate.getPrice()));
        }
        System.out.println(Arrays.toString(predicates.toArray()));
        return predicates.toArray(new Predicate[]{});
    }

    public Map<Integer, List<BlockResource>> allPages(ProxyPredicate proxyPredicate, Integer limit) {
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BlockResource> criteria = cb.createQuery(BlockResource.class);
        Root<BlockResource> root = criteria.from(BlockResource.class);
//        Join<BlockResource, Category> categoryJoin = root.join(BlockResource_.category);
        criteria.select(root).where(build(cb, proxyPredicate));
//        criteria.select(root).where(
//                cb.equal(root.get(BlockResource_.resourceName), proxyPredicate.getResource()),
//                cb.equal(categoryJoin.get(Category_.categoryName), proxyPredicate.getCategory()),
//                cb.equal(root.get(BlockResource_.price), proxyPredicate.getPrice()));
        List<BlockResource> allByCriteria = session.createQuery(criteria).list();
        Integer pages = allByCriteria.size() / limit;
        Map<Integer, List<BlockResource>> informationOnPages = new HashMap<>();
        Integer offset = 0;
        Integer limitOnPage = limit;
        for (int count = 0; count < pages; count++) {
            List<BlockResource> listOnThisPage = new ArrayList<>();
            for (int count2 = offset; count2 < count2 + limit; count2++) {
                listOnThisPage.add(allByCriteria.get(count2));
            }
                offset = offset + limitOnPage;
            limitOnPage = limit + limit;
            informationOnPages.put(count, listOnThisPage);
        }
        return informationOnPages;
    }

    public List<Integer> countPages (ProxyPredicate proxyPredicate, Integer limit) {
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BlockResource> criteria = cb.createQuery(BlockResource.class);
        Root<BlockResource> root = criteria.from(BlockResource.class);
        Join<BlockResource, Category> categoryJoin = root.join(BlockResource_.category);
//        criteria.select(root).where(build(cb, proxyPredicate));
        criteria.select(root).where(
                cb.equal(root.get(BlockResource_.resourceName), proxyPredicate.getResource()),
                cb.equal(categoryJoin.get(Category_.categoryName), proxyPredicate.getCategory()),
                cb.equal(root.get(BlockResource_.price), proxyPredicate.getPrice()));
        List<BlockResource> allByCriteria = session.createQuery(criteria).list();
        Integer pages = allByCriteria.size() / limit;
        List<Integer> countPages = new ArrayList<>();
        for (int count = 0;count<pages;count++){
            countPages.add(count);
        }
        return countPages;
    }

    public static ResourceDao getResourceDao() {
        return RESOURCE_DAO;
    }
}

//        public Map<Integer, List<BlockResource>> allPages(ProxyPredicate proxyPredicate, Integer limit) {
//        Session session = factory.openSession();
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<BlockResource> criteria = cb.createQuery(BlockResource.class);
//        Root<BlockResource> root = criteria.from(BlockResource.class);
//        criteria.select(root).where(build(cb, proxyPredicate));
//        List<BlockResource> allByCriteria = session.createQuery(criteria).list();
//        Integer pages = allByCriteria.size() / limit;
//        Map<Integer, List<BlockResource>> informationOnPages = new HashMap<>();
//        Integer offset = 0;
//        Integer limitOnPage = limit;
//        for (int count = 0; count < pages; count++) {
//            List<BlockResource> listOnThisPage = new ArrayList<>();
//            for (int count2 = offset; count2 < count2 + limit; count2++) {
//                listOnThisPage.add(allByCriteria.get(count2));
//            }
//                offset = offset + limitOnPage;
//            limitOnPage = limit + limit;
//            informationOnPages.put(count, listOnThisPage);
//        }
//        return informationOnPages;
//    }
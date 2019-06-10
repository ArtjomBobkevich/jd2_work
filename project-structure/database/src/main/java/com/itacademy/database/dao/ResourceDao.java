package com.itacademy.database.dao;

import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.BlockResource_;
import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Category_;
import com.itacademy.database.entity.ProxyPredicate;
import lombok.Cleanup;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ResourceDao extends BaseDaoImpl<Long, BlockResource> {

    public List<BlockResource> findResourcesOrderByAuthor(ProxyPredicate proxyPredicate, Integer offset, Integer limit) {

        @Cleanup Session session = getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BlockResource> criteria = cb.createQuery(BlockResource.class);
        Root<BlockResource> root = criteria.from(BlockResource.class);
        criteria.select(root).where(build(cb, root, proxyPredicate));

        return session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }


    public Predicate[] build(CriteriaBuilder cb, Root<BlockResource> root, ProxyPredicate proxyPredicate) {
        List<Predicate> predicates = new ArrayList<>();
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
        return predicates.toArray(new Predicate[0]);
    }

    public Integer countPages(ProxyPredicate proxyPredicate, Integer limit) {
        @Cleanup Session session = getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BlockResource> criteria = cb.createQuery(BlockResource.class);
        Root<BlockResource> root = criteria.from(BlockResource.class);
        criteria.select(root).where(build(cb, root, proxyPredicate));
        List<BlockResource> allByCriteria = session.createQuery(criteria).list();

        return allByCriteria.size() / limit;
    }
}
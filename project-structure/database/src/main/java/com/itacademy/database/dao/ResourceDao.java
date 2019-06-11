package com.itacademy.database.dao;

import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.BlockResource_;
import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Category_;
import com.itacademy.database.entity.FilterDto;
import com.itacademy.database.entity.Heading;
import com.itacademy.database.entity.Resource;
import lombok.Cleanup;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResourceDao extends BaseDaoImpl<Long, BlockResource> {

    public List<BlockResource> findResourcesOrderByAuthor(FilterDto filterDto, Integer offset, Integer limit) {

        @Cleanup Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BlockResource> criteria = cb.createQuery(BlockResource.class);
        Root<BlockResource> root = criteria.from(BlockResource.class);
        criteria.select(root).where(build(cb, root, filterDto));

        return session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public Predicate[] build(CriteriaBuilder cb, Root<BlockResource> root, FilterDto filterDto) {
        List<Predicate> predicates = new ArrayList<>();
        Join<BlockResource, Category> categoryJoin = root.join(BlockResource_.category);
        if (!"".equals(filterDto.getResource()) && !"".equals(filterDto.getCategory()) && null != filterDto.getPrice()) {
            predicates.add(cb.equal(root.get(BlockResource_.resourceName), filterDto.getResource()));
            predicates.add(cb.equal(categoryJoin.get(Category_.categoryName), filterDto.getCategory()));
            predicates.add(cb.equal(root.get(BlockResource_.price), filterDto.getPrice()));
        } else if (!"".equals(filterDto.getResource()) && !"".equals(filterDto.getCategory())) {
            predicates.add(cb.equal(root.get(BlockResource_.resourceName), filterDto.getResource()));
            predicates.add(cb.equal(categoryJoin.get(Category_.categoryName), filterDto.getCategory()));
        } else if (!"".equals(filterDto.getResource())) {
            predicates.add(cb.equal(root.get(BlockResource_.resourceName), filterDto.getResource()));
        } else if (!"".equals(filterDto.getCategory())) {
            predicates.add(cb.equal(categoryJoin.get(Category_.categoryName), filterDto.getCategory()));
        } else if (null != filterDto.getPrice()) {
            predicates.add(cb.equal(root.get(BlockResource_.price), filterDto.getPrice()));
        }
        return predicates.toArray(new Predicate[0]);
    }

    public Integer countPages(FilterDto filterDto, Integer limit) {
        @Cleanup Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BlockResource> criteria = cb.createQuery(BlockResource.class);
        Root<BlockResource> root = criteria.from(BlockResource.class);
        criteria.select(root).where(build(cb, root, filterDto));
        List<BlockResource> allByCriteria = session.createQuery(criteria).list();

        return allByCriteria.size() / limit;
    }

    public boolean addHeading (Heading heading, Resource resource) {
        getSessionFactory().getCurrentSession();
        return resource.getHeadings().add(heading);
    }
}
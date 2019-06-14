package com.itacademy.database.dao;

import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.BlockResource_;
import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Category_;
import com.itacademy.database.entity.Count;
import com.itacademy.database.entity.FilterDto;
import com.itacademy.database.entity.Heading;
import com.itacademy.database.entity.Resource;
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

        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BlockResource> criteria = cb.createQuery(BlockResource.class);
        Root<BlockResource> root = criteria.from(BlockResource.class);
        criteria.select(root).where(build(cb, root, filterDto));

        List<BlockResource> list = session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
        list.size();
        return session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public Predicate[] build(CriteriaBuilder cb, Root<BlockResource> root, FilterDto filterDto) {
        List<Predicate> predicates = new ArrayList<>();
        Join<BlockResource, Category> categoryJoin = root.join(BlockResource_.category);
        if (null!=(filterDto.getResource()) && null!=(filterDto.getCategory()) && null != filterDto.getPrice()) {
            predicates.add(cb.equal(root.get(BlockResource_.resourceName), filterDto.getResource()));
            predicates.add(cb.equal(categoryJoin.get(Category_.categoryName), filterDto.getCategory()));
            predicates.add(cb.equal(root.get(BlockResource_.price), filterDto.getPrice()));
        } else if (null!=(filterDto.getResource()) && null!=(filterDto.getCategory())) {
            predicates.add(cb.equal(root.get(BlockResource_.resourceName), filterDto.getResource()));
            predicates.add(cb.equal(categoryJoin.get(Category_.categoryName), filterDto.getCategory()));
        } else if (null!=(filterDto.getResource())) {
            predicates.add(cb.equal(root.get(BlockResource_.resourceName), filterDto.getResource()));
        } else if (null!=(filterDto.getCategory())) {
            predicates.add(cb.equal(categoryJoin.get(Category_.categoryName), filterDto.getCategory()));
        } else if (null != filterDto.getPrice()) {
            predicates.add(cb.equal(root.get(BlockResource_.price), filterDto.getPrice()));
        }
        return predicates.toArray(new Predicate[0]);
    }

    public List<Count> countPages(FilterDto filterDto, Integer limit) {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BlockResource> criteria = cb.createQuery(BlockResource.class);
        Root<BlockResource> root = criteria.from(BlockResource.class);
        criteria.select(root).where(build(cb, root, filterDto));
        List<BlockResource> allByCriteria = session.createQuery(criteria).list();
        int countPage;
        if (allByCriteria.size() != 0 && allByCriteria.size() / limit >= 1 && allByCriteria.size() % limit == 0) {
            countPage = allByCriteria.size() / limit;
        } else if (allByCriteria.size() != 0 && allByCriteria.size() / limit >= 1 && allByCriteria.size() % limit != 0) {
            countPage = (allByCriteria.size() / limit) + 1;
        } else if (allByCriteria.size() != 0 && allByCriteria.size() / limit < 1) {
            countPage = 1;
        }
        else {
            countPage = 1;
        }
        List<Count> allCount = new ArrayList<>();
        for (int count = 1; count <= countPage; count++) {
            allCount.add(Count.builder()
                    .count(count)
                    .build());
        }
        return allCount;
    }

    public void addHeading(Heading heading, Resource resource) {
        getSessionFactory().getCurrentSession();
        resource.getHeadings().add(heading);
    }
}
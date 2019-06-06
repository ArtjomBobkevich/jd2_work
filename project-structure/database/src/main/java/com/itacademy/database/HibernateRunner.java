package com.itacademy.database;

import com.itacademy.database.dao.ResourceDao;
import com.itacademy.database.entity.ProxyPredicate;
import com.itacademy.database.util.SessionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;

public class HibernateRunner {

    private static SessionFactory factory = SessionManager.getFactory();

    public static void main(String[] args) {
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        ProxyPredicate proxyPredicate = new ProxyPredicate();
        proxyPredicate.setResource("test");
        proxyPredicate.setCategory("www");
        proxyPredicate.setPrice(222);

        Predicate [] predicates = ResourceDao.getResourceDao().build(cb, proxyPredicate);
        System.out.println(Arrays.toString(predicates));
    }
}

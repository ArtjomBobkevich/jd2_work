package com.itacademy.database.dao;

import com.itacademy.database.entity.BaseEntity;
import lombok.Cleanup;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import static com.itacademy.database.util.SessionManager.getSession;

public interface BaseDao <T extends Serializable,E extends BaseEntity<T>> {

    default T save(E entity) {
        Session session = getSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        return entity.getId();
    }

    default Optional<E> get(T id) {
        return Optional.ofNullable(getSession().get(getClazz(), id));
    }

    default void update(E entity) {
        Session session = getSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    default void delete(E entity) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

     default List<E> getAll() {
        @Cleanup Session session = getSession();
        Class<E> clazz = getClazz();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<E> criteria = cb.createQuery(clazz);
        Root<E> root = criteria.from(clazz);
        criteria.select(root);
        return session.createQuery(criteria).list();
    }

    @SuppressWarnings("unchecked")
    default Class<E> getClazz() {
        Type entityType = ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[1];
        return  (Class<E>) entityType;
    }
}
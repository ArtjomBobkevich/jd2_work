package com.itacademy.database.dao;

import com.itacademy.database.entity.BaseEntity;
import org.hibernate.SessionFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface BaseDao <T extends Serializable,E extends BaseEntity<T>> {

    SessionFactory getSessionFactory();

    default T save(E entity) {
        getSessionFactory().getCurrentSession().save(entity);
        return entity.getId();
    }

    default Optional<E> get(T id) {
        return Optional.ofNullable(getSessionFactory().getCurrentSession().get(getClazz(), id));
    }

    default void update(E entity) {
        getSessionFactory().getCurrentSession().update(entity);
    }

    default void delete(E entity) {
        getSessionFactory().getCurrentSession().delete(entity);
    }

    default List<E> getAll() {
        Class<E> clazz = getClazz();
        return getSessionFactory().getCurrentSession()
                .createQuery(String.format("select e from %s e", clazz.getSimpleName()), clazz)
                .list();
    }

    @SuppressWarnings("unchecked")
    default Class<E> getClazz() {
        return (Class<E>) GenericTypeResolver.resolveTypeArguments(getClass(),BaseDao.class)[1];
    }
}
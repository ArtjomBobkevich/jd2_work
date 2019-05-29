package com.itacademy.database.dao;

import com.itacademy.database.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseDao <T extends Serializable,E extends BaseEntity<T>> {

   T save(E entity);

    Optional<E> get (T id);

    void update (E entity);

    void delete (E entity);

    List<E> getAll();
}

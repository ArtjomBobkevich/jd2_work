package com.itacademy.database.dao;

import com.itacademy.database.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao extends BaseDaoImpl<Long, Person> {

    public Person findByName (String name) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select p from Person p where p.login = :name", Person.class)
                .setParameter("name",name)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
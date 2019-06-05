package com.itacademy.database.dao;

import com.itacademy.database.entity.Person;

public class PersonDao implements BaseDao<Long, Person> {

    private static final PersonDao PERSON_DAO = new PersonDao();

    public static PersonDao getPersonDao() {
        return PERSON_DAO;
    }
}
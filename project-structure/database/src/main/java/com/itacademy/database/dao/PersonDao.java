package com.itacademy.database.dao;

import com.itacademy.database.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao extends BaseDaoImpl<Long, Person> {
}
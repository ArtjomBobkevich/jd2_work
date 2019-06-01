package com.itacademy.database;

import com.itacademy.database.dao.ResourceDao;
import com.itacademy.database.entity.Resource;

import java.util.List;

public class HibernateRunner {

    public static void main(String[] args) {
//        List<Person> personOrderByAuthor = ResourceDao.getResourceDao().findPersonByLoginOrderByLogin("beast", 0, 2);
        List<Resource> resourcesOrderByAuthor = ResourceDao.getResourceDao().findResourcesOrderByAuthor("ss","www",222,0,2);
        System.out.println(resourcesOrderByAuthor);
//        System.out.println(personOrderByAuthor.size());
    }
}

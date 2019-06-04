package com.itacademy.database;

import com.itacademy.database.dao.ResourceDao;
import com.itacademy.database.entity.Resource;

import java.util.ArrayList;
import java.util.List;

public class HibernateRunner {

    public static void main(String[] args) {
        List<Object> parametrs = new ArrayList<>();
        parametrs.add("test");
        parametrs.add("www");
        parametrs.add(222);
        parametrs.add(0);
        parametrs.add(2);
        List<Resource> list = ResourceDao.getResourceDao().findResourcesOrderByAuthor(parametrs);
        System.out.println(list.size());
        List<Resource> list2 = ResourceDao.getResourceDao().getAll();
        System.out.println(list2.size());
        Resource resource = ResourceDao.getResourceDao().get(1L).orElse(null);
        System.out.println(resource);
    }
}

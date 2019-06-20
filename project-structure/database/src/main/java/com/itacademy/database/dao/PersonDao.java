package com.itacademy.database.dao;

import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.Resource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDao extends BaseDaoImpl<Long, Person> {

    public Person findByName(String name) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select p from Person p where p.login = :name", Person.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public void addResource(Person person, Resource resource) {
        getSessionFactory().getCurrentSession();
        person.getStoreBasketResources().add(resource);
    }

    public List<Resource> allResourcesAtBasket (Person person) {
        List<Resource> allResources = new ArrayList<>();
        int sizeBasket = person.getStoreBasketResources().size();
        for (int count=0; count<sizeBasket;count++) {
            allResources.add(person.getStoreBasketResources().get(count));
        }
        return allResources;
    }

    public void deleteResourceAtBasket(Person person, Resource resource) {
        getSessionFactory().getCurrentSession();
        person.getStoreBasketResources().remove(resource);
    }
}
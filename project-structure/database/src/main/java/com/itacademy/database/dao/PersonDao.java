package com.itacademy.database.dao;

import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.PersonRole;
import com.itacademy.database.entity.PersonRole_;
import com.itacademy.database.entity.Person_;
import com.itacademy.database.util.SessionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class PersonDao implements BaseDao<Long, Person> {

    private static final PersonDao PERSON_DAO = new PersonDao();
    private static SessionFactory factory = SessionManager.getFactory();


    public List<Person> findPersonByLoginOrderByLogin(String login, int offset, int limit) {
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);
        criteria.select(root).where(
                cb.equal(root.get(Person_.login), login)
        )
                .orderBy(
                        cb.asc(root.get(Person_.login))
                );
        return session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public List<Person> findPersonByLoginOrderByLogin(String login, String mail, int offset, int limit) {
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);
        criteria.select(root).where(
                cb.equal(root.get(Person_.login), login),
                cb.equal(root.get(Person_.mail), mail)
        )
                .orderBy(
                        cb.asc(root.get(Person_.login))
                );
        return session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public List<Person> findPersonByLoginOrderByLogin( String mail, String role, int offset, int limit,int size) {
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);
        Join<Person, PersonRole> roleJoin = root.join(Person_.personRole);
        criteria.select(root).where(
                cb.equal(root.get(Person_.mail), mail),
                cb.equal(roleJoin.get(PersonRole_.NAME_OF_ROLE), role)
        )
                .orderBy(
                        cb.asc(root.get(Person_.login))
                );
        return session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .setFetchSize(size)
                .list();
    }

    public static PersonDao getPersonDao() {
        return PERSON_DAO;
    }
}
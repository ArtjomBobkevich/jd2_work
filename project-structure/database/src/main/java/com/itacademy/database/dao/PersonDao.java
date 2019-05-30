package com.itacademy.database.dao;

import com.itacademy.database.entity.Person;

public class PersonDao extends BaseDaoImpl <Long,Person> {

    private static final PersonDao PERSON_DAO = new PersonDao();

    public static PersonDao getPersonDao() {
        return PERSON_DAO;
    }
}


//@SneakyThrows
//    public List<Person> findById(Long id) {
//        try (Session session = FACTORY.openSession()) {
//            return session.createQuery("SELECT p FROM Person p WHERE p.id =: id", Person.class)
//                    .setParameter("id", id).list();
//        }
//    }
//
//
//    @SneakyThrows
//    public List<Person> findAll() {
//        try (Session session = FACTORY.openSession()) {
//            return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
//        }
//    }
//
//    @SneakyThrows
//    public Query delete(Person person) {
//
//        @Cleanup Session session = FACTORY.openSession();
//        return session.createQuery("DELETE FROM Person p WHERE p.login =:login", Person.class)
//                .setParameter("login", person.getLogin());
////                .executeUpdate();
//    }
//
//    @SneakyThrows
//    public List<Person> update(Person person) {
//        try (Session session = FACTORY.openSession()) {
//            return session.createQuery("UPDATE  Person  set avatar = :avatar, login =:login, "
//                    + "identification.firstName =: firstName, identification.lastName =: lastName, age =: age,"
//                    + "mail =: mail, password =: password where id =:id", Person.class)
//                    .setParameter("avatar", person.getAvatar())
//                    .setParameter("login", person.getLogin())
//                    .setParameter("firstName", person.getIdentification().getFirstName())
//                    .setParameter("lastName", person.getIdentification().getLastName())
//                    .setParameter("age", person.getAge())
//                    .setParameter("mail", person.getMail())
//                    .setParameter("password", person.getPassword())
//                    .setParameter("id", person.getId()).list();
//        }
//    }
//
//    @SneakyThrows
//    public Serializable save(Person person) {
//        try (Session session = FACTORY.openSession()) {
//            return session.save(person);
//        }
//    }
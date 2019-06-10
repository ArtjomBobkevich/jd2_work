package com.itacademy.database.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ {

	public static volatile SingularAttribute<Person, String> password;
	public static volatile SingularAttribute<Person, Identification> identification;
	public static volatile SingularAttribute<Person, String> mail;
	public static volatile SingularAttribute<Person, PersonRole> personRole;
	public static volatile SingularAttribute<Person, Long> id;
	public static volatile SingularAttribute<Person, String> avatar;
	public static volatile SingularAttribute<Person, String> login;
	public static volatile SingularAttribute<Person, Integer> age;
	public static volatile ListAttribute<Person, Resource> storeBasketResources;

	public static final String PASSWORD = "password";
	public static final String IDENTIFICATION = "identification";
	public static final String MAIL = "mail";
	public static final String PERSON_ROLE = "personRole";
	public static final String ID = "id";
	public static final String AVATAR = "avatar";
	public static final String LOGIN = "login";
	public static final String AGE = "age";
	public static final String STORE_BASKET_RESOURCES = "storeBasketResources";

}


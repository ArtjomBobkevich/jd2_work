package com.itacademy.database.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Resource.class)
public abstract class Resource_ {

	public static volatile ListAttribute<Resource, Person> storeBasketPerson;
	public static volatile ListAttribute<Resource, Comment> comments;
	public static volatile SingularAttribute<Resource, String> foto;
	public static volatile SingularAttribute<Resource, Person> person;
	public static volatile SingularAttribute<Resource, Integer> price;
	public static volatile SetAttribute<Resource, Heading> headings;
	public static volatile SingularAttribute<Resource, String> resourceName;
	public static volatile SingularAttribute<Resource, Long> id;
	public static volatile SingularAttribute<Resource, String> text;
	public static volatile SingularAttribute<Resource, Category> category;

	public static final String STORE_BASKET_PERSON = "storeBasketPerson";
	public static final String COMMENTS = "comments";
	public static final String FOTO = "foto";
	public static final String PERSON = "person";
	public static final String PRICE = "price";
	public static final String HEADINGS = "headings";
	public static final String RESOURCE_NAME = "resourceName";
	public static final String ID = "id";
	public static final String TEXT = "text";
	public static final String CATEGORY = "category";

}


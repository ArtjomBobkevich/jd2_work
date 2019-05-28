package com.itacademy.database.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Heading.class)
public abstract class Heading_ {

	public static volatile SingularAttribute<Heading, String> headingName;
	public static volatile SingularAttribute<Heading, Long> id;
	public static volatile SingularAttribute<Heading, Category> category;

	public static final String HEADING_NAME = "headingName";
	public static final String ID = "id";
	public static final String CATEGORY = "category";

}


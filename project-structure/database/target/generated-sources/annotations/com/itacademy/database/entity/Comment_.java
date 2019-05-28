package com.itacademy.database.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comment.class)
public abstract class Comment_ {

	public static volatile SingularAttribute<Comment, Resource> resource;
	public static volatile SingularAttribute<Comment, Person> person;
	public static volatile SingularAttribute<Comment, String> comment;
	public static volatile SingularAttribute<Comment, Long> id;

	public static final String RESOURCE = "resource";
	public static final String PERSON = "person";
	public static final String COMMENT = "comment";
	public static final String ID = "id";

}


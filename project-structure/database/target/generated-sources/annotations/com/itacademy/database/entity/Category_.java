package com.itacademy.database.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ {

	public static volatile SingularAttribute<Category, String> fotoUrl;
	public static volatile ListAttribute<Category, Heading> headings;
	public static volatile SingularAttribute<Category, Long> id;
	public static volatile SingularAttribute<Category, String> categoryName;

	public static final String FOTO_URL = "fotoUrl";
	public static final String HEADINGS = "headings";
	public static final String ID = "id";
	public static final String CATEGORY_NAME = "categoryName";

}


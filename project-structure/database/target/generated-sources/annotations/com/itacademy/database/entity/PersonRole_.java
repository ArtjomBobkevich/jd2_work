package com.itacademy.database.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PersonRole.class)
public abstract class PersonRole_ {

	public static volatile ListAttribute<PersonRole, Person> personList;
	public static volatile SingularAttribute<PersonRole, Long> id;
	public static volatile SingularAttribute<PersonRole, String> nameOfRole;

	public static final String PERSON_LIST = "personList";
	public static final String ID = "id";
	public static final String NAME_OF_ROLE = "nameOfRole";

}


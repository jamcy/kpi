package com.epam.mykhailo_lisevych.wp.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-02T02:35:27.865+0200")
@StaticMetamodel(Manager.class)
public class Manager_ {
	public static volatile SingularAttribute<Manager, Integer> managerId;
	public static volatile SingularAttribute<Manager, User> user;
	public static volatile SingularAttribute<Manager, String> name;
	public static volatile SingularAttribute<Manager, String> phone;
}

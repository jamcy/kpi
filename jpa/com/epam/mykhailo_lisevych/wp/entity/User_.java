package com.epam.mykhailo_lisevych.wp.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-02T03:29:12.591+0200")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> userId;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, UserRole> role;
	public static volatile SingularAttribute<User, Manager> manager;
	public static volatile SingularAttribute<User, Company> company;
}

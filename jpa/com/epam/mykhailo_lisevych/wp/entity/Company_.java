package com.epam.mykhailo_lisevych.wp.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-02T02:35:17.276+0200")
@StaticMetamodel(Company.class)
public class Company_ {
	public static volatile SingularAttribute<Company, Integer> companyId;
	public static volatile SingularAttribute<Company, User> user;
	public static volatile SingularAttribute<Company, String> name;
	public static volatile SingularAttribute<Company, String> address;
	public static volatile SingularAttribute<Company, String> phone;
	public static volatile SingularAttribute<Company, String> contactPerson;
	public static volatile ListAttribute<Company, Order> orders;
}

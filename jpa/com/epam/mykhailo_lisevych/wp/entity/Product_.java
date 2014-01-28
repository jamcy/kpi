package com.epam.mykhailo_lisevych.wp.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-29T00:00:54.866+0200")
@StaticMetamodel(Product.class)
public class Product_ {
	public static volatile SingularAttribute<Product, Integer> poductId;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, String> details;
	public static volatile SingularAttribute<Product, String> picture;
	public static volatile SingularAttribute<Product, Integer> wholesaleFloor;
	public static volatile SingularAttribute<Product, String> units;
	public static volatile SingularAttribute<Product, Double> priceRetail;
	public static volatile SingularAttribute<Product, Double> priceWholesale;
}

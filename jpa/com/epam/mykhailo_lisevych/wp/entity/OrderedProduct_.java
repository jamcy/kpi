package com.epam.mykhailo_lisevych.wp.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-29T00:00:54.860+0200")
@StaticMetamodel(OrderedProduct.class)
public class OrderedProduct_ {
	public static volatile SingularAttribute<OrderedProduct, Integer> orderedProductId;
	public static volatile SingularAttribute<OrderedProduct, Product> product;
	public static volatile SingularAttribute<OrderedProduct, Order> order;
	public static volatile SingularAttribute<OrderedProduct, Integer> amount;
}

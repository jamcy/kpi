package com.epam.mykhailo_lisevych.wp.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-29T00:00:54.857+0200")
@StaticMetamodel(Order.class)
public class Order_ {
	public static volatile SingularAttribute<Order, Integer> orderId;
	public static volatile SingularAttribute<Order, Manager> manager;
	public static volatile SingularAttribute<Order, Company> company;
	public static volatile SingularAttribute<Order, Deal> deal;
	public static volatile SingularAttribute<Order, Date> timeCreated;
	public static volatile SingularAttribute<Order, BigDecimal> totalCost;
	public static volatile SingularAttribute<Order, String> summary;
	public static volatile SingularAttribute<Order, String> paymentRequisitions;
	public static volatile ListAttribute<Order, OrderedProduct> orderedProducts;
	public static volatile ListAttribute<Order, OrderStatus> orderStatuses;
}

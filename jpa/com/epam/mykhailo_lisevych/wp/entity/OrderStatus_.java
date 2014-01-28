package com.epam.mykhailo_lisevych.wp.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-29T00:00:54.863+0200")
@StaticMetamodel(OrderStatus.class)
public class OrderStatus_ {
	public static volatile SingularAttribute<OrderStatus, Integer> orderStatusId;
	public static volatile SingularAttribute<OrderStatus, Order> order;
	public static volatile SingularAttribute<OrderStatus, OrderStatusValue> status;
	public static volatile SingularAttribute<OrderStatus, Date> timeFrom;
	public static volatile SingularAttribute<OrderStatus, Date> timeTo;
	public static volatile SingularAttribute<OrderStatus, String> comment;
}

package com.epam.mykhailo_lisevych.wp.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-02T02:35:23.982+0200")
@StaticMetamodel(Deal.class)
public class Deal_ {
	public static volatile SingularAttribute<Deal, Integer> dealId;
	public static volatile SingularAttribute<Deal, Date> timeCreated;
	public static volatile SingularAttribute<Deal, String> details;
	public static volatile SingularAttribute<Deal, Date> timeLastChanged;
	public static volatile SingularAttribute<Deal, Date> timeConfirmed;
	public static volatile ListAttribute<Deal, Order> orders;
}

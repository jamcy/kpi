package com.epam.mykhailo_lisevych.wp.ejb;

import javax.ejb.Stateless;

import com.epam.mykhailo_lisevych.wp.entity.Order;
import com.epam.mykhailo_lisevych.wp.entity.Product;

@Stateless
public class OrderPriceProcessor {
	
	public double getProductsSummary(Order o) {
		//TODO implement
		return 10.0;
	}
	
	public boolean isWholesale(Product p, Order o) {
		//TODO implement
		return false;
	}
	
	public double getTotalPrice(Order o) {
		//TODO implement
		return 100.0;
	}
	
	
	
}

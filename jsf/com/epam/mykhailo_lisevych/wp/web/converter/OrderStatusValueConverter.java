package com.epam.mykhailo_lisevych.wp.web.converter;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import com.epam.mykhailo_lisevych.wp.entity.OrderStatusValue;

@FacesConverter("orderStatusValueConverter")
public class OrderStatusValueConverter extends EnumConverter {
	public OrderStatusValueConverter() {
		super(OrderStatusValue.class);
	}
}

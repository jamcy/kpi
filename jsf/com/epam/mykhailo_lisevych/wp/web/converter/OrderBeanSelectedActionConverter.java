package com.epam.mykhailo_lisevych.wp.web.converter;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import com.epam.mykhailo_lisevych.wp.web.bean.OrderBean;

@FacesConverter("orderBeanSelectedActionConverter")
public class OrderBeanSelectedActionConverter extends EnumConverter {

	public OrderBeanSelectedActionConverter() {
		super(OrderBean.SelectedAction.class);
	}

}

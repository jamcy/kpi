package com.epam.mykhailo_lisevych.wp.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.mykhailo_lisevych.wp.dao.OrderDao;
import com.epam.mykhailo_lisevych.wp.entity.Order;

@Named("converter.orderIdConverter")
public class OrderFromId implements Converter {

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private OrderDao odao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return odao.read(Integer.parseInt(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && value instanceof Order) {
			return Integer.toString(((Order) value).getOrderId());
		}
		return "";
	}

}

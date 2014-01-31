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

	@Inject
	private OrderDao odao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Integer oid = null;
		try {
			oid = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO log
			e.printStackTrace();
			return null;
		}
		try {
			if (odao == null) {
				System.out.println("NULL");
			}
			Order result = odao.read(oid);
			return result;
		} catch (Exception e) {
			// TODO log
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && value instanceof Order) {
			return "Order #" + ((Order) value).getOrderId();
		}
		return "";
	}

}

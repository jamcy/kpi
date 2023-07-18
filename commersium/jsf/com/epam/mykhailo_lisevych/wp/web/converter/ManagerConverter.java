package com.epam.mykhailo_lisevych.wp.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.mykhailo_lisevych.wp.dao.ManagerDao;
import com.epam.mykhailo_lisevych.wp.entity.Manager;

@Named("converter.managerConverter")
public class ManagerConverter implements Converter {

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private ManagerDao mdao;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
			throws NumberFormatException {
		if (mdao == null) {
			System.out.println("Error");
			throw new NumberFormatException();
		}
		return mdao.read(Integer.parseInt(arg2));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return Integer.toString(((Manager) arg2).getManagerId());
	}

}

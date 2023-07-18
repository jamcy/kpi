package com.epam.mykhailo_lisevych.wp.report;

import java.util.List;

import com.epam.mykhailo_lisevych.wp.entity.OrderedProduct;
import com.sun.mail.iap.Argument;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class OrderReportDataSource implements JRDataSource {

	private List<OrderedProduct> orderLines;
	private int currentElementIndex;

	public OrderReportDataSource(List<OrderedProduct> ol) {
		if (ol == null) {
			throw new IllegalArgumentException();
		}
		orderLines = ol;
		currentElementIndex = 0;
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		return "Value" + currentElementIndex;
	}

	@Override
	public boolean next() throws JRException {
		currentElementIndex++;
		return currentElementIndex < 10;
	}

}

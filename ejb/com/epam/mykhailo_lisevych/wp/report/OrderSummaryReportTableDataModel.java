package com.epam.mykhailo_lisevych.wp.report;

import java.math.BigDecimal;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.epam.mykhailo_lisevych.wp.entity.Order;
import com.epam.mykhailo_lisevych.wp.entity.OrderedProduct;

public class OrderSummaryReportTableDataModel implements TableModel {

	private Order order;

	public OrderSummaryReportTableDataModel(Order o) {
		order = o;
	}

	@Override
	public int getRowCount() {
		return order.getOrderedProducts().size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "name";
		case 1:
			return "price";
		case 2:
			return "count";
		case 3:
			return "cost";
		case 4:
			return "id";
		}
		return "";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return BigDecimal.class;
		case 2:
			return Integer.class;
		case 3:
			return BigDecimal.class;
		case 4:
			return Integer.class;
		}
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		OrderedProduct op = order.getOrderedProducts().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return op.getProduct().getName();
		case 1:
			return (op.getAmount() >= op.getProduct().getWholesaleFloor()) ? op
					.getProduct().getPriceWholesale() : op.getProduct()
					.getPriceRetail();
		case 2:
			return op.getAmount();
		case 3:
			return ((op.getAmount() >= op.getProduct().getWholesaleFloor()) ? op
					.getProduct().getPriceWholesale() : op.getProduct()
					.getPriceRetail())
					* op.getAmount();
		case 4:
			return op.getProduct().getPoductId();
		}
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}
}

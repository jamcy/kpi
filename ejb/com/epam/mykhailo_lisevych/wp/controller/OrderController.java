package com.epam.mykhailo_lisevych.wp.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

import com.epam.mykhailo_lisevych.wp.dao.OrderDao;
import com.epam.mykhailo_lisevych.wp.entity.Company;
import com.epam.mykhailo_lisevych.wp.entity.Manager;
import com.epam.mykhailo_lisevych.wp.entity.Order;
import com.epam.mykhailo_lisevych.wp.entity.OrderStatus;
import com.epam.mykhailo_lisevych.wp.entity.OrderStatusValue;
import com.epam.mykhailo_lisevych.wp.entity.OrderedProduct;
import com.epam.mykhailo_lisevych.wp.entity.Product;
import com.epam.mykhailo_lisevych.wp.report.OrderSummaryReportTableDataModel;

public class OrderController {

	public static String REPORT_DIRECTORY = "/home/spawn/disk/projects/epam/reports";

	@Inject
	private OrderDao odao;

	public void createOrder(Company owner, Map<Product, Integer> content)
			throws JRException {
		Order o = new Order();
		for (Entry<Product, Integer> e : content.entrySet()) {
			OrderedProduct op = new OrderedProduct();
			op.setProduct(e.getKey());
			op.setAmount(e.getValue());
			o.addOrderedProduct(op);
		}
		o.setCompany(owner);

		// Count total cost of order
		double tc = 0;
		for (Product p : content.keySet()) {
			if (p.getWholesaleFloor() <= content.get(p)) {
				tc += p.getPriceWholesale() * content.get(p);
			} else {
				tc += p.getPriceWholesale() * content.get(p);
			}
		}
		o.setTotalCost(new BigDecimal(tc));

		// Generate report file name
		String filename = "";
		while (true) {
			filename = Long.toHexString(Double.doubleToLongBits(Math.random()));
			File f = new File(REPORT_DIRECTORY + "/" + filename);
			if (!f.exists()) {
				break;
			}
		}
		o.setSummary(filename);
		odao.create(o);
		fillSummary(o);
	}

	private void fillSummary(Order o) throws JRException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", o.getOrderId());
		params.put("tableDataSource", new JRTableModelDataSource(
				new OrderSummaryReportTableDataModel(o)));
		params.put("totalCost", o.getTotalCost());
		params.put("companyName", o.getCompany().getName());
		params.put("companyUrl", "");
		params.put("orderUrl", "");
		params.put("productBaseUrl", "");
		params.put("productUrlIdParamName", "oid");

		JasperFillManager.fillReportToFile("/home/spawn/order.jasper",
				REPORT_DIRECTORY + "/" + o.getSummary(), params,
				new JREmptyDataSource());
	}

	public void updateDeal(Order o, String newDealContents, double newOrderPrice) {

	}

	public void updateOrder(Order o, OrderStatusValue newStatusValue,
			Manager newManager, String comment) {
		o.setManager(newManager);

		// TODO fill report
		if (newStatusValue.equals(o.getCurrentStatus().getStatus())) {
			if (o.getCurrentStatus().getComment() == null) {
				o.getCurrentStatus().setComment(comment);
			} else {
				o.getCurrentStatus().setComment(
						o.getCurrentStatus().getComment() + comment);
			}
		} else {
			OrderStatus newStatus = new OrderStatus();
			if (o.getCurrentStatus() != null) {
				o.getCurrentStatus().setTimeTo(new Date());
			}
			newStatus.setTimeFrom(new Date());
			newStatus.setStatus(newStatusValue);
			newStatus.setComment(comment);
			o.addStatus(newStatus);
		}
		o.setTimeUpdated(new Date());
		odao.merge(o);
	}
}

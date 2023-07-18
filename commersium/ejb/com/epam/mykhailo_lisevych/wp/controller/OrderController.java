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
import com.epam.mykhailo_lisevych.wp.ejb.UserStateBean;
import com.epam.mykhailo_lisevych.wp.entity.Company;
import com.epam.mykhailo_lisevych.wp.entity.Manager;
import com.epam.mykhailo_lisevych.wp.entity.Order;
import com.epam.mykhailo_lisevych.wp.entity.OrderStatus;
import com.epam.mykhailo_lisevych.wp.entity.OrderStatusValue;
import com.epam.mykhailo_lisevych.wp.entity.OrderedProduct;
import com.epam.mykhailo_lisevych.wp.entity.Product;
import com.epam.mykhailo_lisevych.wp.entity.UserRole;
import com.epam.mykhailo_lisevych.wp.report.OrderSummaryReportTableDataModel;

public class OrderController {

	public static String REPORT_DIRECTORY = "/home/spawn/disk/projects/epam/commersium-data/reports";

	public static String P_VIEW_ORDER = "vo";
	public static String P_EDIT_ORDER = "eo";
	public static String P_CREATE_DEAL = "cd";
	public static String P_ASSIGN_MANAGER = "am";

	@Inject
	private OrderDao odao;

	@Inject
	private UserStateBean userState;

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

		JasperFillManager
				.fillReportToFile(
						"/home/spawn/disk/projects/epam/commersium-data/templates/order.jasper",
						REPORT_DIRECTORY + "/" + o.getSummary(), params,
						new JREmptyDataSource());
	}

	public void updateDeal(Order o, String newDealContents, double newOrderPrice) {

	}

	public void updateOrder(Order o, OrderStatusValue newStatusValue,
			Manager newManager, String changeComment) {
		// Process changes
		if (!newStatusValue.equals(o.getCurrentStatus().getStatus())) {
			OrderStatus newStatus = new OrderStatus();
			if (o.getCurrentStatus() != null) {
				o.getCurrentStatus().setTimeTo(new Date());
			}
			newStatus.setTimeFrom(new Date());
			newStatus.setStatus(newStatusValue);
			o.addStatus(newStatus);
		}

		o.setManager(newManager);
		o.setTimeUpdated(new Date());
		// TODO Fill report
		// TODO Append report to status
		o.getCurrentStatus().appendComment(changeComment);
		// Write changes to database
		odao.merge(o);
		// TODO Send email
	}

	public boolean isAllowed(String permission, Order order) {
		UserRole ur = userState.getCurrentUser().getRole();
		if (permission.equals(P_ASSIGN_MANAGER)) {
			if (ur.equals(UserRole.MANAGER) || ur.equals(UserRole.ADMIN)) {
				return true;
			}
		}
		return false;
	}

}

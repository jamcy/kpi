package com.epam.mykhailo_lisevych.wp.web.bean;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;

import com.epam.mykhailo_lisevych.wp.controller.OrderController;
import com.epam.mykhailo_lisevych.wp.dao.ManagerDao;
import com.epam.mykhailo_lisevych.wp.entity.Manager;
import com.epam.mykhailo_lisevych.wp.entity.Order;
import com.epam.mykhailo_lisevych.wp.entity.OrderStatusValue;
import com.epam.mykhailo_lisevych.wp.web.converter.ManagerConverter;
import com.epam.mykhailo_lisevych.wp.web.converter.OrderFromId;

@ManagedBean
@ViewScoped
public class OrderBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OrderFromId converter;

	@Inject
	private ManagerConverter managerConverter;

	@Inject
	private OrderController orderController;

	@Inject
	private ManagerDao mdao;

	private String dealContents;

	private OrderStatusValue statusValue;
	private Manager selectedManager;
	private String comment;

	private List<Manager> managers;

	private Order order;

	public OrderStatusValue[] getStatusValues() {
		return OrderStatusValue.values();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderFromId getConverter() {
		return converter;
	}

	public void setConverter(OrderFromId converter) {
		this.converter = converter;
	}

	public String getDealContents() {
		return dealContents;
	}

	public void setDealContents(String dealContents) {
		this.dealContents = dealContents;
	}

	public OrderStatusValue getStatusValue() {
		statusValue = order.getCurrentStatus().getStatus();
		return statusValue;
	}

	public void setStatusValue(OrderStatusValue statusValue) {
		this.statusValue = statusValue;
	}

	public List<Manager> getManagers() {
		if (managers == null) {
			managers = mdao.selectAll();
		}
		return managers;
	}

	public void setManagers(List<Manager> managers) {
		this.managers = managers;
	}

	public Manager getSelectedManager() {
		selectedManager = order.getManager();
		return selectedManager;
	}

	public void setSelectedManager(Manager selectedManager) {
		this.selectedManager = selectedManager;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ManagerConverter getManagerConverter() {
		return managerConverter;
	}

	public void setManagerConverter(ManagerConverter managerConverter) {
		this.managerConverter = managerConverter;
	}

	public void test() throws JRException {
		JasperPrint jrPrint = (JasperPrint) (JRLoader.loadObject(new File(
				OrderController.REPORT_DIRECTORY + "/" + "3fd0a99237e21fb4")));
		JasperExportManager.exportReportToHtmlFile(jrPrint,
				"/home/spawn/test.html");
	}

	public void updateOrder() {
		orderController.updateOrder(order, statusValue, selectedManager,
				comment);
		comment = null;
	}

	public boolean isAssignManager() {
		return orderController.isAllowed(OrderController.P_ASSIGN_MANAGER,
				order);
	}

	public boolean isDeal() {
		return order.getDeal() == null;
	}

	public void createDeal() {
		// TODO implement
	}

}

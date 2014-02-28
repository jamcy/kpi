package com.epam.mykhailo_lisevych.wp.web.bean;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import com.epam.mykhailo_lisevych.wp.controller.OrderController;
import com.epam.mykhailo_lisevych.wp.dao.ManagerDao;
import com.epam.mykhailo_lisevych.wp.dao.OrderDao;
import com.epam.mykhailo_lisevych.wp.ejb.UserStateBean;
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

	@Inject
	private OrderDao odao;

	@Inject
	private UserStateBean userState;

	private String dealContents;

	private OrderStatusValue statusValue;
	private Manager selectedManager;
	private String comment;

	private List<Manager> managers;

	private Order order;

	public OrderStatusValue[] getStatusValues() {
		return getAvailableOrderStatuses(order.getCurrentStatus().getStatus());
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

	public void updateOrder() {
		orderController.updateOrder(order, statusValue, selectedManager,
				comment);
		comment = null;
	}

	public boolean isOrderEditable() {
		if (order.getCurrentStatus().getStatus()
				.equals(OrderStatusValue.CLOSED)
				|| order.getCurrentStatus().getStatus()
						.equals(OrderStatusValue.CANCELLED)) {
			return false;
		}
		return true;
	}

	// Status buttons actions
	public void assignManager() {
		assignManager(userState.getCurrentUser().getManager().get(0));
	}

	public void assignManager(Manager m) {

	}

	public void cancelOrder() {
		// TODO implement
	}

	public void createDeal() {
		// TODO implement
	}

	public void confirmDeal() {
		// TODO implement
	}

	public void closeOrder() {
		// TODO implement
	}

	public void commentOrder() {
		// TODO implement
	}

	public OrderStatusValue[] getAvailableOrderStatuses(
			OrderStatusValue currentValue) {
		switch (userState.getCurrentUser().getRole()) {
		case ADMIN:
			return OrderStatusValue.values();
		case COMPANY:
			OrderStatusValue[] values = new OrderStatusValue[2];
			values[0] = OrderStatusValue.CANCELLED;
			values[1] = OrderStatusValue.CONFIRMED;
			return values;
		case MANAGER:

		default:
			return new OrderStatusValue[0];
		}
	}

	public void viewOrderDeails() throws IOException, JRException {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext
				.getResponse();
		JasperPrint jrPrint = (JasperPrint) (JRLoader.loadObject(new File(
				OrderController.REPORT_DIRECTORY + "/" + order.getSummary())));
		exportReportAsHtml(jrPrint, response.getWriter());
		FacesContext.getCurrentInstance().responseComplete();
	}

	private void exportReportAsHtml(JasperPrint jasperPrint, PrintWriter out)
			throws JRException {
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
				Boolean.FALSE);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		exporter.setParameter(
				JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING,
				"UTF-8");
		// exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, false);
		// exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, false);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		exporter.exportReport();
	}
}

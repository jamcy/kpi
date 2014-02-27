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

import org.xml.sax.Attributes;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
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
		if (orderController.isAllowed(orderController.P_CREATE_DEAL, order)) {
			// TODO create deal
		}
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
		/*
		 * ServletContext context = (ServletContext)
		 * externalContext.getContext(); HttpServletRequest request =
		 * (HttpServletRequest) externalContext .getRequest();
		 */
		HttpServletResponse response = (HttpServletResponse) externalContext
				.getResponse();
		JasperPrint jrPrint = (JasperPrint) (JRLoader.loadObject(new File(
				OrderController.REPORT_DIRECTORY + "/" + order.getSummary())));
		exportReportAsHtml(jrPrint, response.getWriter());

		/*
		 * if (getExportOption().equals(ExportOption.HTML)) {
		 *             ReportConfigUtil.exportReportAsHtml(jasperPrint,
		 * response.getWriter());         } else if
		 * (getExportOption().equals(ExportOption.EXCEL)) {
		 *             ReportConfigUtil.exportReportAsExcel(jasperPrint,
		 * response.getWriter());         } else {
		 *             request.getSession
		 * ().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE
		 * , jasperPrint);
		 *             response.sendRedirect(request.getContextPath() +
		 * "/servlets/report/" + getExportOption());         }
		 */

		FacesContext.getCurrentInstance().responseComplete();
	}

	private void updateStatus(OrderStatusValue newStatusValue, String comment,
			String action) {

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
		/*
		 * exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, false);
		 * exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, false);
		 */
		exportReport(exporter, jasperPrint, out);
	}

	private void exportReport(JRAbstractExporter exporter,
			JasperPrint jasperPrint, PrintWriter out) throws JRException {
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		exporter.exportReport();
	}
}

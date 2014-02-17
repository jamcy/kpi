package com.epam.mykhailo_lisevych.wp.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "order", schema = "public")
@SequenceGenerator(name = "orderIdSeq", sequenceName = "order_id_seq", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = "Order.selectByCompany", query = "SELECT DISTINCT o from Order o WHERE o.company=:company ORDER BY o.timeCreated DESC") })
public class Order implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "order_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderIdSeq")
	private int orderId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manager_id")
	private Manager manager;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deal_id")
	private Deal deal;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_created", nullable = false)
	private Date timeCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_updated", nullable = true)
	private Date timeUpdated;

	@Column(name = "total_cost", nullable = false, precision = 131089, scale = 0)
	private BigDecimal totalCost;

	@Column(name = "summary", nullable = true)
	private String summary;

	@Column(name = "payment_requisitions")
	private String paymentRequisitions;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST)
	private List<OrderedProduct> orderedProducts = new ArrayList<OrderedProduct>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderStatus> orderStatuses = new ArrayList<OrderStatus>();

	public Order() {
	}

	public Order(int orderId, Company company, Date timeCreated,
			BigDecimal totalCost) {
		this.orderId = orderId;
		this.company = company;
		this.timeCreated = timeCreated;
		this.totalCost = totalCost;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Deal getDeal() {
		return this.deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}

	public Date getTimeCreated() {
		return this.timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public BigDecimal getTotalCost() {
		return this.totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public String getPaymentRequisitions() {
		return this.paymentRequisitions;
	}

	public void setPaymentRequisitions(String paymentRequisitions) {
		this.paymentRequisitions = paymentRequisitions;
	}

	public List<OrderedProduct> getOrderedProducts() {
		return this.orderedProducts;
	}

	public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public List<OrderStatus> getOrderStatuses() {
		return this.orderStatuses;
	}

	public void setOrderStatuses(List<OrderStatus> orderStatuses) {
		this.orderStatuses = orderStatuses;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getTimeUpdated() {
		return timeUpdated;
	}

	public void setTimeUpdated(Date timeUpdated) {
		this.timeUpdated = timeUpdated;
	}

	public OrderStatus getCurrentStatus() {
		for (OrderStatus os : orderStatuses) {
			if (os.getTimeTo() == null) {
				return os;
			}
		}
		return null;
	}

	public void addOrderedProduct(OrderedProduct op) {
		op.setOrder(this);
		orderedProducts.add(op);
	}

	public void addStatus(OrderStatus os) {
		os.setOrder(this);
		orderStatuses.add(os);
	}

}

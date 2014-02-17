package com.epam.mykhailo_lisevych.wp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "order_status", schema = "public")
@SequenceGenerator(name = "orderStatusIdSeq", sequenceName = "order_status_id_seq", allocationSize = 1)
public class OrderStatus implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "order_status_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderStatusIdSeq")
	private int orderStatusId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@Column(name = "status", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private OrderStatusValue status;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_from", nullable = false, length = 13)
	private Date timeFrom;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_to", length = 13)
	private Date timeTo;
	@Column(name = "comment")
	private String comment;

	public OrderStatus() {
	}

	public OrderStatus(int orderStatusId, Order order, OrderStatusValue status,
			Date timeFrom) {
		this.orderStatusId = orderStatusId;
		this.order = order;
		this.status = status;
		this.timeFrom = timeFrom;
	}

	public OrderStatus(int orderStatusId, Order order, OrderStatusValue status,
			Date timeFrom, Date timeTo, String comment) {
		this.orderStatusId = orderStatusId;
		this.order = order;
		this.status = status;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.comment = comment;
	}

	public int getOrderStatusId() {
		return this.orderStatusId;
	}

	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderStatusValue getStatus() {
		return this.status;
	}

	public void setStatus(OrderStatusValue status) {
		this.status = status;
	}

	public Date getTimeFrom() {
		return this.timeFrom;
	}

	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Date getTimeTo() {
		return this.timeTo;
	}

	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

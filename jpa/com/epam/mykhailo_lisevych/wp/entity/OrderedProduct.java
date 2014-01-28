package com.epam.mykhailo_lisevych.wp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ordered_product", schema = "public")
@SequenceGenerator(name = "orderedProductIdSeq", sequenceName = "ordered_product_id_seq")
public class OrderedProduct implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ordered_product_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderedProductIdSeq")
	private int orderedProductId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	@Column(name = "amount")
	private Integer amount;

	public OrderedProduct() {
	}

	public OrderedProduct(int orderedProductId, Product product, Order order) {
		this.orderedProductId = orderedProductId;
		this.product = product;
		this.order = order;
	}

	public OrderedProduct(int orderedProductId, Product product, Order order,
			Integer amount) {
		this.orderedProductId = orderedProductId;
		this.product = product;
		this.order = order;
		this.amount = amount;
	}

	public int getOrderedProductId() {
		return this.orderedProductId;
	}

	public void setOrderedProductId(int orderedProductId) {
		this.orderedProductId = orderedProductId;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}

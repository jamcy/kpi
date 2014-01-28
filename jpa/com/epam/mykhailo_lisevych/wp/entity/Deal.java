package com.epam.mykhailo_lisevych.wp.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "deal", schema = "public")
@SequenceGenerator(name = "dealIdSeq", sequenceName = "deal_id_seq")
public class Deal implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "deal_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dealIdSeq")
	private int dealId;

	@Temporal(TemporalType.DATE)
	@Column(name = "time_created", nullable = false, length = 13)
	private Date timeCreated;

	@Column(name = "details")
	private String details;

	@Temporal(TemporalType.DATE)
	@Column(name = "time_last_changed", length = 13)
	private Date timeLastChanged;

	@Temporal(TemporalType.DATE)
	@Column(name = "time_confirmed", length = 13)
	private Date timeConfirmed;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "deal")
	private List<Order> orders = new ArrayList<Order>();

	public Deal() {
	}

	public Deal(int dealId, Date timeCreated) {
		this.dealId = dealId;
		this.timeCreated = timeCreated;
	}

	public Deal(int dealId, Date timeCreated, String details,
			Date timeLastChanged, Date timeConfirmed, List<Order> orders) {
		this.dealId = dealId;
		this.timeCreated = timeCreated;
		this.details = details;
		this.timeLastChanged = timeLastChanged;
		this.timeConfirmed = timeConfirmed;
		this.orders = orders;
	}

	public int getDealId() {
		return this.dealId;
	}

	public void setDealId(int dealId) {
		this.dealId = dealId;
	}

	public Date getTimeCreated() {
		return this.timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getTimeLastChanged() {
		return this.timeLastChanged;
	}

	public void setTimeLastChanged(Date timeLastChanged) {
		this.timeLastChanged = timeLastChanged;
	}

	public Date getTimeConfirmed() {
		return this.timeConfirmed;
	}

	public void setTimeConfirmed(Date timeConfirmed) {
		this.timeConfirmed = timeConfirmed;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}

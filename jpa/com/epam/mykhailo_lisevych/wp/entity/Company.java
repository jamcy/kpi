package com.epam.mykhailo_lisevych.wp.entity;

import java.util.ArrayList;
import java.util.List;

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

@Entity
@Table(name = "company", schema = "public")
@SequenceGenerator(name = "companyIdSeq", sequenceName = "company_id_seq")
@NamedQueries({ @NamedQuery(name = "Company.selectByUser", query = "SELECT c FROM Company c WHERE c.user = :u") })
public class Company implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "company_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companyIdSeq")
	private int companyId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "name", nullable = false, length = 200)
	private String name;

	@Column(name = "address", nullable = false, length = 1000)
	private String address;

	@Column(name = "phone", nullable = false, length = 100)
	private String phone;

	@Column(name = "contact_person", nullable = false, length = 200)
	private String contactPerson;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	private List<Order> orders = new ArrayList<Order>();

	public Company() {
	}

	public Company(int companyId, String name, String address, String phone,
			String contactPerson) {
		this.companyId = companyId;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.contactPerson = contactPerson;
	}

	public Company(int companyId, User user, String name, String address,
			String phone, String contactPerson, List<Order> orders) {
		this.companyId = companyId;
		this.user = user;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.contactPerson = contactPerson;
		this.orders = orders;
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}

package com.epam.mykhailo_lisevych.wp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "manager", schema = "public")
@SequenceGenerator(name = "managerIdSeq", sequenceName = "manager_id_seq", allocationSize = 1)
public class Manager implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "manager_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "managerIdSeq")
	private int managerId;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user;

	@Column(name = "name", nullable = false, length = 200)
	private String name;

	@Column(name = "phone", nullable = false, length = 200)
	private String phone;

	public Manager() {
	}

	public Manager(int managerId, String name, String phone) {
		this.managerId = managerId;
		this.name = name;
		this.phone = phone;
	}

	public int getManagerId() {
		return this.managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Manager) {
			return ((Manager) obj).managerId == managerId;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return managerId;
	}

}

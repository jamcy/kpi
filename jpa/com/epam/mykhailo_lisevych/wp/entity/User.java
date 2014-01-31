package com.epam.mykhailo_lisevych.wp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "public")
@SequenceGenerator(name = "userIdSeq", sequenceName = "user_id_seq", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = "User.selectByEmail", query = "SELECT u FROM User u WHERE u.email = :email") })
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdSeq")
	private int userId;
	@Column(name = "email", nullable = false, length = 1024)
	private String email;
	@Column(name = "password", nullable = false, length = 128)
	private String password;
	@Column(name = "role", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<Company> company;

	public User() {
	}

	public User(int userId, String email, String password, UserRole role) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return this.role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public List<Company> getCompany() {
		return company;
	}

	public void setCompany(List<Company> company) {
		this.company = company;
	}

}

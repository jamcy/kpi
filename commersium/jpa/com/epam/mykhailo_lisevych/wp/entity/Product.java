package com.epam.mykhailo_lisevych.wp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "product", schema = "public")
@SequenceGenerator(name = "productIdSeq", sequenceName = "product_id_seq", allocationSize=1)
public class Product implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "poduct_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productIdSeq")
	private int poductId;
	@Column(name = "name", nullable = false, length = 200)
	private String name;
	@Column(name = "details", nullable = false)
	private String details;
	@Column(name = "picture", length = 400)
	private String picture;
	@Column(name = "wholesale_floor", nullable = false)
	private int wholesaleFloor;
	@Column(name = "units", nullable = false, length = 20)
	private String units;

	@Column(name = "price_retail", nullable = false)
	private double priceRetail;

	@Column(name = "price_wholesale", nullable = false)
	private double priceWholesale;

	public Product() {
	}

	public Product(int poductId, String name, String details,
			int wholesaleFrom, String units) {
		this.poductId = poductId;
		this.name = name;
		this.details = details;
		this.wholesaleFloor = wholesaleFrom;
		this.units = units;
	}

	public int getPoductId() {
		return this.poductId;
	}

	public void setPoductId(int poductId) {
		this.poductId = poductId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUnits() {
		return this.units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public int getWholesaleFloor() {
		return wholesaleFloor;
	}

	public void setWholesaleFloor(int wholesaleFloor) {
		this.wholesaleFloor = wholesaleFloor;
	}

	public double getPriceRetail() {
		return priceRetail;
	}

	public void setPriceRetail(double priceRetail) {
		this.priceRetail = priceRetail;
	}

	public double getPriceWholesale() {
		return priceWholesale;
	}

	public void setPriceWholesale(double priceWholesale) {
		this.priceWholesale = priceWholesale;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Product)) {
			return false;
		}
		return poductId == ((Product) obj).getPoductId();
	}

	@Override
	public int hashCode() {
		return poductId;
	}

}

package com.rihui.state;

import java.io.Serializable;

public class Orders implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Double price;
	private Customer customer;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}

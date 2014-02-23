package com.rihui.composite02;

import java.io.Serializable;

public class Customer implements Serializable{

	private CustomerID id;
	private String desription;
	
	public CustomerID getId() {
		return id;
	}
	public void setId(CustomerID id) {
		this.id = id;
	}
	public String getDesription() {
		return desription;
	}
	public void setDesription(String desription) {
		this.desription = desription;
	}
}

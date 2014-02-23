package com.rihui.many2onedouble;

import java.io.Serializable;
import java.util.Set;

public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer cid;
	private String description;
	private String name;
	private Set<Orders> orders ;
	
	
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Orders> getOrders() {
		return orders;
	}
	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}
}

package com.rihui.search;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Customer implements Serializable{

	private Integer id;
	private String name;
	private Integer age;
	private Set<Orders> orders = new HashSet<Orders>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Set<Orders> getOrders() {
		return orders;
	}
	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}
}

package com.rihui.component;

public class Customer {

	private Integer id;
	private String name;
	private Address homeAddr;
	private Address comAddr;
	
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
	public Address getHomeAddr() {
		return homeAddr;
	}
	public void setHomeAddr(Address homeAddr) {
		this.homeAddr = homeAddr;
	}
	public Address getComAddr() {
		return comAddr;
	}
	public void setComAddr(Address comAddr) {
		this.comAddr = comAddr;
	}
}

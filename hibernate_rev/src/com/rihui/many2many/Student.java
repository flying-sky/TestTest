package com.rihui.many2many;

import java.util.HashSet;
import java.util.Set;

public class Student{

	private Integer id;
	private String name;
	private Set<Course> courses = new HashSet<Course>();
	
	public Student(){
	}
	
	public Student(String name) {
		super();
		this.name = name;
	}
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
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
}

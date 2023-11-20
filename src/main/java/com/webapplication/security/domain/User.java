package com.webapplication.security.domain;

public class User {

	private String userId;
	private String name;
	private Integer salary;

	public String getUserId() {
		return userId;
	}

	public void setUser_id(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

}

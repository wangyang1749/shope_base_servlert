package com.shop.model;

public class User {
	private int id;
	private String username;
	private String password;
	private String phone;
	private String address;
	private Integer type;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public User (String username, String password, String phone, String address) {
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.address = address;
	}
	public User() {
		super();
	}
	
	
	
}

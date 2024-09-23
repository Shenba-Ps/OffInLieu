package com.OffInLieuManagement.entity;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String staffName;
	private String staffId;
	private String password;
	private String role;
	
	
	
	
	public User(String staffName, String staffId, String password, String role) {
		super();
		this.staffName = staffName;
		this.staffId = staffId;
		this.password = password;
		this.role = role;
	}
	
	
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	@Override
	public String toString() {
		return "User [staffName=" + staffName + ", staffId=" + staffId
				+ ", password=" + password + ", role=" + role + "]";
	}
	
	

}

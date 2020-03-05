package com.neoSoft.dto;

import java.util.Date;

public class UserDto {

	private int userId;
	private String fullName;
	private String userName;
	private long contactNo;
	private String password;
	//private String confirmPassword;
	private String department;
	private String emailId;
	private String accountStatus;
	private Date created;
	private Date updated;
	private String userRole;
	private String photoPath;
	
	
	
	public String getPhotoPath() {
		return photoPath;
	}


	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}


	public String getUserRole() {
		return userRole;
	}


	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}


	public UserDto(int userId, String fullName, String department, String emailId, String accountStatus, Date created, Date updated) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.department = department;
		this.emailId = emailId;
		this.accountStatus = accountStatus;
		this.created = created;
		this.updated = updated;
	}
	
	
	// --------------- whenever you generate constructor like above, then MUST generate default constructor like below! ---------------------------
	
	
	  public UserDto() {
		  super(); 
	 }
	 
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public Date getUpdated() {
		return updated;
	}


	public void setUpdated(Date updated) {
		this.updated = updated;
	}


	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public long getContactNo() {
		return contactNo;
	}
	
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	/*
	 * public String getConfirmPassword() { return confirmPassword; }
	 * 
	 * public void setConfirmPassword(String confirmPassword) { this.confirmPassword
	 * = confirmPassword; }
	 */
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	
}

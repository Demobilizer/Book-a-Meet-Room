package com.neoSoft.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "user_master")
public class UserBean {

	@Id
	@GeneratedValue
	private int userId;
	private String fullName;
	private String userName;
	private String password;
	private long contactNo;
	private String emailId;
	private String department;
	
	//@Value("inactive")
	private String accountStatus;
	
	//@Value("user")
	private String userRole;
	
	private Date created;
	  private Date updated;
	  private String photoPath;	
	  
	  public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public UserBean(int userId, String fullName, String emailId, String department, String accountStatus, Date created,
				Date updated) {
			super();
			this.userId = userId;
			this.fullName = fullName;
			this.emailId = emailId;
			this.department = department;
			this.accountStatus = accountStatus;
			this.created = created;
			this.updated = updated;
		}

	  public UserBean() {

	  }

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

	@PrePersist
	  protected void onCreate() {
	    created = new Date();
	  }

	  @PreUpdate
	  protected void onUpdate() {
	    updated = new Date();
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", fullName=" + fullName + ", userName=" + userName + ", password="
				+ password + ", contactNo=" + contactNo + ", emailId=" + emailId + ", department=" + department
				+ ", accountStatus=" + accountStatus + ", userRole=" + userRole + ", created=" + created + ", updated="
				+ updated + "]";
	}


	
	
}

package com.neoSoft.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class FeedbacksBean {

	@Id
	@GeneratedValue
	@Column
	private int id;
	
	private String feedBack;
	
	@OneToOne(targetEntity = UserBean.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
	private UserBean userBean;
	
	@Column
	private Date created;
	
	
	private boolean readStatus;
	

	public boolean isReadStatus() {
		return readStatus;
	}

	public void setReadStatus(boolean readStatus) {
		this.readStatus = readStatus;
	}

	@PrePersist
	  protected void onCreate() {
	    created = new Date();
	  }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	
	
	
	
}

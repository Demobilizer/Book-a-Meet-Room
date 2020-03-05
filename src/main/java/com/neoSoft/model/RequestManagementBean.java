package com.neoSoft.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.neoSoft.misc.RequestTypes;
import com.neoSoft.misc.Status;

@Entity
@Table(name = "request_management")
public class RequestManagementBean {

	@Id
	@GeneratedValue
	private int req_id;
	
	@Enumerated(EnumType.STRING)
	private RequestTypes requestTypes;
	private String reason;
	private String remarksByUpperHirarchy;
	private String tempNewData;
	private String tempOldData;
	
	@Enumerated(EnumType.STRING)
	private Status requestStatus;
	
	@ManyToOne(targetEntity = UserBean.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "from_user_id")
	private UserBean fromUser;
	
	@ManyToOne(targetEntity = UserBean.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "to_user_id")
	private UserBean toUser;
	
	private Date created;
	  private Date updated;

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

	public int getReq_id() {
		return req_id;
	}

	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}

	public RequestTypes getRequestTypes() {
		return requestTypes;
	}

	public void setRequestTypes(RequestTypes requestTypes) {
		this.requestTypes = requestTypes;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemarksByUpperHirarchy() {
		return remarksByUpperHirarchy;
	}

	public void setRemarksByUpperHirarchy(String remarksByUpperHirarchy) {
		this.remarksByUpperHirarchy = remarksByUpperHirarchy;
	}

	public String getTempNewData() {
		return tempNewData;
	}

	public void setTempNewData(String tempNewData) {
		this.tempNewData = tempNewData;
	}

	public String getTempOldData() {
		return tempOldData;
	}

	public void setTempOldData(String tempOldData) {
		this.tempOldData = tempOldData;
	}

	public Status getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(Status requestStatus) {
		this.requestStatus = requestStatus;
	}

	public UserBean getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserBean fromUser) {
		this.fromUser = fromUser;
	}

	public UserBean getToUser() {
		return toUser;
	}

	public void setToUser(UserBean toUser) {
		this.toUser = toUser;
	}

	@Override
	public String toString() {
		return "RequestManagementBean [req_id=" + req_id + ", requestTypes=" + requestTypes + ", reason=" + reason
				+ ", remarksByUpperHirarchy=" + remarksByUpperHirarchy + ", tempNewData=" + tempNewData
				+ ", tempOldData=" + tempOldData + ", requestStatus=" + requestStatus + ", fromUser=" + fromUser
				+ ", toUser=" + toUser + ", created=" + created + ", updated=" + updated + "]";
	}

	
	  
	  
	
}

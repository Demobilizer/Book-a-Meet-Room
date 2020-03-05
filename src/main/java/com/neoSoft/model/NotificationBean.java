package com.neoSoft.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.neoSoft.misc.NotificationTypes;

@Entity
@Table(name = "notification_master")
public class NotificationBean {

	@Id
	@GeneratedValue
	@Column
	private int notificationId;
	
	@Enumerated(EnumType.STRING)
	private NotificationTypes notificationType;
	
	private String notificationContent;
	
	@ManyToOne(targetEntity = UserBean.class, fetch = FetchType.EAGER)
    @JoinColumn
	private UserBean generatedBy;
	
	@ManyToOne(targetEntity = UserBean.class, fetch = FetchType.EAGER)
    @JoinColumn
	private UserBean generatedFor;
	
	private boolean readNotification;
	private boolean deleteNotification;
	
	private Date created;
	
	@PrePersist
	  protected void onCreate() {
	    created = new Date();
	  }
	
	
	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public int getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	
	public NotificationTypes getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(NotificationTypes notificationType) {
		this.notificationType = notificationType;
	}
	public String getNotificationContent() {
		return notificationContent;
	}
	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent;
	}
	
	public UserBean getGeneratedBy() {
		return generatedBy;
	}
	public void setGeneratedBy(UserBean generatedBy) {
		this.generatedBy = generatedBy;
	}
	public UserBean getGeneratedFor() {
		return generatedFor;
	}
	public void setGeneratedFor(UserBean generatedFor) {
		this.generatedFor = generatedFor;
	}
	public boolean isReadNotification() {
		return readNotification;
	}
	public void setReadNotification(boolean readNotification) {
		this.readNotification = readNotification;
	}
	public boolean isDeleteNotification() {
		return deleteNotification;
	}
	public void setDeleteNotification(boolean deleteNotification) {
		this.deleteNotification = deleteNotification;
	}
	
	
	
}

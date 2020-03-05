package com.neoSoft.dto;

public class NotificationDto {

	private int notificationId;
	private String notificationType;
	private String notificationContent;
	private int generatedBy;
	private int generatedFor;
	private boolean readNotification;
	private boolean deleteNotification;
	
	
	public int getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	public String getNotificationContent() {
		return notificationContent;
	}
	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent;
	}
	public int getGeneratedBy() {
		return generatedBy;
	}
	public void setGeneratedBy(int generatedBy) {
		this.generatedBy = generatedBy;
	}
	public int getGeneratedFor() {
		return generatedFor;
	}
	public void setGeneratedFor(int generatedFor) {
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

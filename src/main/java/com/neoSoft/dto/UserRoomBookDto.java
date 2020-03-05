package com.neoSoft.dto;

import java.util.Date;

public class UserRoomBookDto {

	private int urBookId;
	private String date;
	private String startTime;
	private String endTime;
	private UserDto userDto;
	private RoomDto roomDto;
	private String requestStatus;
	private String approvedBy;
	private Date created;
	private Date updated;

	
	
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public int getUrBookId() {
		return urBookId;
	}
	public void setUrBookId(int urBookId) {
		this.urBookId = urBookId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public RoomDto getRoomDto() {
		return roomDto;
	}
	public void setRoomDto(RoomDto roomDto) {
		this.roomDto = roomDto;
	}
	
	@Override
	public String toString() {
		return "UserRoomBookDto [urBookId=" + urBookId + ", date=" + date + ", startTime=" + startTime + ", endTime="
				+ endTime + ", userDto=" + userDto + ", roomDto=" + roomDto + ", requestStatus=" + requestStatus
				+ ", approvedBy=" + approvedBy + ", created=" + created + ", updated=" + updated + "]";
	}
	
	
	
	
	
}

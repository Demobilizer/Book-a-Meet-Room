package com.neoSoft.dto;

public class RoomFacilityDto {
	
	private int id;
	private int facilityId;
	private int roomId;
	private String facilityName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	@Override
	public String toString() {
		return "RoomFacilityDto [id=" + id + ", facilityId=" + facilityId + ", roomId=" + roomId + ", facilityName="
				+ facilityName + "]";
	}
	

}

package com.neoSoft.dto;

import java.util.ArrayList;
import java.util.List;

public class RoomDto {

	private int roomId;
	private String roomName;
	private String roomType;
	private String roomLocation;
	private String otherDescription;
	List<RoomFacilityDto> roomFacilities = new ArrayList<RoomFacilityDto>();
	
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getRoomLocation() {
		return roomLocation;
	}
	public void setRoomLocation(String roomLocation) {
		this.roomLocation = roomLocation;
	}
	public String getOtherDescription() {
		return otherDescription;
	}
	public void setOtherDescription(String otherDescription) {
		this.otherDescription = otherDescription;
	}
	public List<RoomFacilityDto> getRoomFacilities() {
		return roomFacilities;
	}
	public void setRoomFacilities(List<RoomFacilityDto> roomFacilities) {
		this.roomFacilities = roomFacilities;
	}
	
	@Override
	public String toString() {
		return  roomId + "| " + roomName + "| " + roomType + "|" + roomLocation + "|" + otherDescription;
	}
}

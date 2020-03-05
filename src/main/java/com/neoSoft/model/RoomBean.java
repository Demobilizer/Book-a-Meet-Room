package com.neoSoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "room_master")
public class RoomBean {

	@Id
	@GeneratedValue
	@Column(name = "roomid")
	private int roomId;
	
	@Column(name = "roomname")
	private String roomName;
	
	@Column(name = "roomtype")
	private String roomType;
	
	@Column(name = "roomlocation")
	private String roomLocation;
	

	@Column(name = "description")
	private String otherDescription;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomBean")
	Set<RoomFacilities> roomFacilities = new HashSet<RoomFacilities>();
	
	
		public Set<RoomFacilities> getRoomFacilities() {
		return roomFacilities;
	}

	public void setRoomFacilities(Set<RoomFacilities> roomFacilities) {
		this.roomFacilities = roomFacilities;
	}

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

	@Override
	public String toString() {
		return "RoomBean [roomId=" + roomId + ", roomName=" + roomName + ", roomType=" + roomType + ", roomLocation="
				+ roomLocation + ", otherDescription=" + otherDescription + ", created=" + created + ", updated="
				+ updated + "]";
	}

	
	
	
	
	
}

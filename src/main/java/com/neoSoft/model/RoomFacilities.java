package com.neoSoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "room_facilities")
public class RoomFacilities {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roomid", referencedColumnName = "roomid")
	private RoomBean roomBean;
	
	@Column(name = "facilityid")
	private int facilityId;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public RoomBean getRoom() {
		return roomBean;
	}
	
	public void setRoom(RoomBean room) {
		this.roomBean = room;
	}
	
	
	public int getFacilityId() {
		return facilityId;
	}
	
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	
}

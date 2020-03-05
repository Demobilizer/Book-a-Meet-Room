package com.neoSoft.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user_room_book")
public class UserRoomBookBean {

	@Id
	@GeneratedValue
	@Column(name = "urbookid")
	private int urBookId;
	
	@Column(name = "start_time")
	private int startTime;
	
	@Column(name = "end_time")
	private int endTime;
	
	@Column(name = "date")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date;
	
	@JoinColumn(name = "userId")
	@OneToOne(cascade = CascadeType.PERSIST)
	private UserBean userBean;
	
	/* ----------------------------------------------------------------------------------------------------------------------------------------------
														following cascadeType is kept PERSIST, 
														because if not kept so, 
														then it will update ROMMBEAN as soon as data inserted to this BEAN 
	----------------------------------------------------------------------------------------------------------------------------------------------*/
	
	@JoinColumn(name = "roomId")
	@OneToOne(cascade = CascadeType.PERSIST)
	private RoomBean roomBean;
	
	@Column
	private String approvedBy;
	
	@Column
	private Date created;
	
	@Column
	private Date updated;

	@Column
	private String requestStatus;

	@PrePersist
	  protected void onCreate() {
	    created = new Date();
	  }
	
	@PreUpdate
	  protected void onUpdate() {
	    updated = new Date();
	  }
	
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

	public String getApprovedBy() {
		return approvedBy;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
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

	

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public RoomBean getRoomBean() {
		return roomBean;
	}

	public void setRoomBean(RoomBean roomBean) {
		this.roomBean = roomBean;
	}

	public static void main(String[] args) {
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");
		Date d = new Date();
		System.out.println("now--------"+df.format(d));
		
	}
	
	
}


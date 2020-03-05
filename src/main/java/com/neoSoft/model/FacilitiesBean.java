package com.neoSoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "facility_master")
public class FacilitiesBean {

	@Id
	@GeneratedValue
	@Column(name = "facilityid")
	private int facilityId;
	
	@Column(name = "facitlityname")
	private String facilityName;
	


	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	@Override
	public String toString() {
		return "FacilitiesBean [facilityId=" + facilityId + ", facilityName=" + facilityName + "]";
	}

	
	
}

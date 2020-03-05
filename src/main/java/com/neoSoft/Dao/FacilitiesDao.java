package com.neoSoft.Dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neoSoft.model.FacilitiesBean;

@Repository
public class FacilitiesDao {
	
	@Autowired
	SessionFactory mySessionFactory;

	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}

	public List<FacilitiesBean> getAllFacilities() {
			
		return mySessionFactory.getCurrentSession().createQuery("from FacilitiesBean",FacilitiesBean.class).getResultList();
	}

	public FacilitiesBean getFacilityById(int id) {

		return mySessionFactory.getCurrentSession().createQuery("select f from FacilitiesBean f where f.facilityId="+id,FacilitiesBean.class).getSingleResult();
	}

	
	public FacilitiesBean addFacility(FacilitiesBean facility) {

		mySessionFactory.getCurrentSession().save(facility);
		
		return facility;
	}

	
	public FacilitiesBean updateFacility(FacilitiesBean facility) {

		mySessionFactory.getCurrentSession().update(facility);
		
		return facility;

	}

	
	public FacilitiesBean deleteFicility(int id) {

		FacilitiesBean facility = this.getFacilityById(id);
		mySessionFactory.getCurrentSession().delete(facility);
		return facility;
	}
	
	

	/*
	 * public String getFacilitiesOfSingleRoom(int facilityId) {
	 * 
	 * return mySessionFactory.getCurrentSession().
	 * createQuery("select f.facilityName from FacilitiesBean f where f.facilityId="
	 * +facilityId,String.class).getSingleResult();
	 * 
	 * }
	 */
}

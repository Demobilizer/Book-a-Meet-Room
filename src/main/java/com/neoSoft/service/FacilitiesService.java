package com.neoSoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neoSoft.Dao.FacilitiesDao;
import com.neoSoft.model.FacilitiesBean;


@Service
@Transactional
public class FacilitiesService {
	
	@Autowired
	FacilitiesDao facilitiesDao;

	public List<FacilitiesBean> getFacilities() {

		return facilitiesDao.getAllFacilities();
	}

	public FacilitiesBean getFacilityById(int id) {

		return facilitiesDao.getFacilityById(id);
	}

	public FacilitiesBean addFacility(FacilitiesBean facility) {

		return facilitiesDao.addFacility(facility);
	}

	public FacilitiesBean updateFacility(FacilitiesBean facility) {
		
		return facilitiesDao.updateFacility(facility);
	}

	public FacilitiesBean deleteFacility(int id) {
		
		return facilitiesDao.deleteFicility(id);
	}

	

}

package com.neoSoft.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neoSoft.model.FacilitiesBean;
import com.neoSoft.service.FacilitiesService;



@RestController
public class FacilitiesController {

	@Autowired
	FacilitiesService facilityService;
	
	
	@RequestMapping(value = "/facilities", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<FacilitiesBean> getAllFacilities()
	{
		return facilityService.getFacilities();
	}
	
	@RequestMapping(value = "/facilities/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public FacilitiesBean getFacilityById(@PathVariable("id") int id)
	{
		return facilityService.getFacilityById(id);
	}
	
	@RequestMapping(value = "facilities", method = RequestMethod.POST, headers = "Accept=application/json")
	public FacilitiesBean addFacility(@RequestBody FacilitiesBean facility)
	{
		return facilityService.addFacility(facility);
	}
	
	@RequestMapping(value = "facilities", method = RequestMethod.PUT, headers = "Accept=application/json")
	public FacilitiesBean updateFacility(@RequestBody FacilitiesBean facility)
	{
		return facilityService.updateFacility(facility);	
	}
	
	@RequestMapping(value = "facilities/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public FacilitiesBean deleteFacility(@PathVariable("id") int id)
	{
		return facilityService.deleteFacility(id);	
	}
	
	
}

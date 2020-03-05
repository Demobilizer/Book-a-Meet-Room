package com.neoSoft.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neoSoft.dto.RoomDto;
import com.neoSoft.model.FacilitiesBean;
import com.neoSoft.model.RoomBean;
import com.neoSoft.service.RoomService;

@Controller
public class RoomController {
	
	@Autowired
	RoomService roomservice;
	
	@RequestMapping(value = "/createRoom", method = RequestMethod.GET)
	public String dashboard(Model m) {
		
			List<FacilitiesBean> facilitiesList = roomservice.getFacilities();
			
			m.addAttribute("facilitiesList", facilitiesList);

			String msg = (String)m.asMap().get("msg");
		    
			if(msg != null) {
				m.addAttribute("msg", msg);
			}
			
		return "createroom";
		
	}
	
	@RequestMapping(value = "/manageFacilities", method = RequestMethod.GET)
	public String addFacilities() {
		
		return "managefacility";
		
	}
	
	@RequestMapping(value = "/manageDepartments")
	public String addDepartments() {
		
		return "managedepartments";
		
	}
	
	@RequestMapping(value = "/addRoom", method = RequestMethod.POST)
	public String createRoom(@ModelAttribute("roomDto") RoomDto room, Model m, final RedirectAttributes redirectAttributes, BindingResult bindingResult)
	{
		roomservice.createRoom(room);
		
		String msg = "Room added successfully..";
		redirectAttributes.addFlashAttribute("msg", msg);

		return "redirect:createRoom";
		
	}
	
	@RequestMapping(value = "/manageRoom", method = RequestMethod.GET)
	public String manageRoom(Model m) {
			
		String msg = (String)m.asMap().get("msg");
	    
		if(msg != null) {
			m.addAttribute("msg", msg);
		}
		
		List<RoomBean> roomsList = roomservice.getRooms();
		m.addAttribute("roomsList", roomsList);
		
		return "manageroom";
		
	}
	
	@RequestMapping(value = "/viewRoomById", method = RequestMethod.GET)
	public String viewRoomById(@RequestParam("id") int id, Model m )
	{
		
		RoomDto roomDto = roomservice.getSingleRoomDetails(id);

		m.addAttribute("roomDto", roomDto);
		
		return "viewsingleroom";
	}
	
	@RequestMapping(value = "/getRoomToEdit", method = RequestMethod.GET)
	public String getRoomToEdit(@RequestParam("id") int id, Model m)
	{
		
		RoomDto roomToEdit = roomservice.getSingleRoomDetails(id);
		List<FacilitiesBean> facilitiesAll =  roomservice.getFacilities();
		
		m.addAttribute("facilitiesAll", facilitiesAll);
		m.addAttribute("roomToEdit", roomToEdit);
		
		return "editroom";
	}
	

	@RequestMapping(value = "/updateRoom", method = RequestMethod.POST)
	public String updateRoom(@ModelAttribute("roomDto") RoomDto room, BindingResult bindingResult, Model m, final RedirectAttributes redirectAttributes)
	{
			
		//System.out.println("room id in controller:---------------------------------"+room.getRoomId());
		roomservice.updateRoom(room);
		
		String msg = "Room updated successfully..";
		redirectAttributes.addFlashAttribute("msg", msg);

		return "redirect:manageRoom";
		
	}
	
	
	@RequestMapping(value = "/deleteRoom", method = RequestMethod.GET)
	public String deleteRoom(@RequestParam("id") int id, Model m)
	{
		
		roomservice.deleteRoom(id);
		
		return "redirect:manageRoom";
	}



}

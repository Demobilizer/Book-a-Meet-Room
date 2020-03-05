package com.neoSoft.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neoSoft.dto.RoomDto;
import com.neoSoft.dto.UserRoomBookDto;
import com.neoSoft.service.RoomService;
import com.neoSoft.service.UserRoomBookService;

@Controller
public class UserRoomBookController {

	
	@Autowired
	UserRoomBookService userRoomBookService;
	
	@Autowired
	RoomService roomService;
	
	@RequestMapping("/bookRoom")
	public String bookRoom() {
		
		return "bookaroom";
		
	}
	
	@RequestMapping(value = "/getAvailRooms", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody List<RoomDto> getAvailRooms(@RequestBody UserRoomBookDto userRoomBookDto, Model m) {

		/*
		 * System.out.println("controller date---------" + userRoomBookDto.getDate());
		 * System.out.println("controller start time------------" +
		 * userRoomBookDto.getStartTime());
		 * System.out.println("controller end time----------" +
		 * userRoomBookDto.getEndTime());
		 */
		
		List<RoomDto> availableRoomList = new ArrayList<RoomDto>();

		try 
			{
				
			availableRoomList = userRoomBookService.getAvailableRooms(userRoomBookDto);
			
			/*if(availableRoomList.size()==0)
			{
				String msg = "Sorry! No Room is Available for this time duration! Please try to search with different Timings!";
				m.addAttribute("msg", msg);
			}
			else 
			{
				m.addAttribute("availableRoomList", availableRoomList);
			}
			
			m.addAttribute("availableRoomList", availableRoomList); */
				
			} 
		catch (ParseException e) 
			{
				e.printStackTrace();
			}

		return availableRoomList;
	}
	 
	
	@RequestMapping(value = "/viewSingleAvailRoom", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody RoomDto getSingleAvailRoom(@RequestParam("id") int id, Model m) {
		
		//System.out.println("from controller id:------------"+id);
		
		RoomDto roomDto = roomService.getSingleRoomDetails(id);
		
		//m.addAttribute("roomDto", roomDto);
		
		return roomDto;
		
	}
	
	@RequestMapping(value = "/bookThisRoom", method = RequestMethod.POST)
	@ResponseBody
	public String bookThisRoom(@RequestBody UserRoomBookDto userRoomBookDto) throws ParseException {
		
		//RoomDto roomDto = userRoomBookDto.getRoomDto();
		//userRoomBookDto.getRoomDto().setRoomId();
		//roomDto.setRoomId(roomId);
		//int roomId = roomDto.getRoomId();
		
		/*
		 * System.out.println("start time from book this room controller----------"+
		 * userRoomBookDto.getStartTime());
		 * System.out.println("end time from book this room controller----------"+
		 * userRoomBookDto.getEndTime());
		 * System.out.println("Date from book this room controller----------"+
		 * userRoomBookDto.getDate());
		 * System.out.println("room Id Jugad from book this room controller----------"
		 * +userRoomBookDto.getRoomDto().getRoomId());
		 * System.out.println("user Id Jugad from book this room controller----------"
		 * +userRoomBookDto.getUserDto().getUserId());
		 */
		
		
		
		 int id = userRoomBookService.bookThisRoom(userRoomBookDto);
		
		 Optional<Integer> checkNull = Optional.ofNullable(id);   
		   if (checkNull.isPresent()) {   
		       
			   userRoomBookService.sendMail(userRoomBookDto);
			   
		   } 
		 
		return "bookaroom";
		
	}
	
	
	@RequestMapping(value = "/manageBookingRequest")
	public String manageRoomBookReq(@RequestParam("pageId") int pageId, Model m) {
		
		int total=5;    
        if(pageId==1){}    
        else{    
            pageId=(pageId-1)*total+1;    
        }    
		pageId--;
		
		List<UserRoomBookDto> userRoomBookDtos = userRoomBookService.getPendingRequests(pageId, total);
		int totalPages = userRoomBookService.getTotalPagesForPendingRequests(total);
		
		m.addAttribute("totalPages", totalPages);
		m.addAttribute("userRoomBookDtos", userRoomBookDtos);
		
		String msg = (String)m.asMap().get("msg");
	    
		if(msg != null) {
			m.addAttribute("msg", msg);
		}
		
		return "managebookingreq";
		
	}
	
	
	@RequestMapping(value = "/approveBookingRequest")
	public String approveBookRequest(@RequestParam("urBookId") int urBookId, final RedirectAttributes redirectAttributes) {
		
		userRoomBookService.approveRequest(urBookId);
		
		String msg = "Booking Request Approved successfully...";
		redirectAttributes.addFlashAttribute("msg", msg);
		
		return "redirect:manageBookingRequest?pageId=1";
	}
	
	@RequestMapping(value = "/rejectBookingRequest")
	public String rejectBookRequest(@RequestParam("urBookId") int urBookId, final RedirectAttributes redirectAttributes) {
		
		userRoomBookService.rejectRequest(urBookId);
		
		String msg = "Booking Request Rejected successfully...";
		redirectAttributes.addFlashAttribute("msg", msg);
		
		return "redirect:manageBookingRequest?pageId=1";
	}
	
	
	
	
}

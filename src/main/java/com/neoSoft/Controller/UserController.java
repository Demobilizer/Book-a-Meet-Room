package com.neoSoft.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neoSoft.Dao.UserDao;
import com.neoSoft.dto.RoomDto;
import com.neoSoft.dto.UserDto;
import com.neoSoft.dto.UserRoomBookDto;
import com.neoSoft.misc.RequestTypes;
import com.neoSoft.misc.Status;
import com.neoSoft.model.FeedbacksBean;
import com.neoSoft.model.NotificationBean;
import com.neoSoft.model.RequestManagementBean;
import com.neoSoft.model.UserBean;
import com.neoSoft.service.UserRoomBookService;
import com.neoSoft.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserRoomBookService userRoomBookService;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUserDetails(@ModelAttribute("userDto") UserDto user, BindingResult bindingResult, Model m) {
		
		//System.out.println("request on controller-------------"+user.getFullName());
		
		int id = userService.createUser(user);
		
		System.out.println("id after insert useeeeer--------------"+id);
		
		 Optional<Integer> checkNull = Optional.ofNullable(id);   
		 
					 if (checkNull.isPresent()) {   
			       
				   // mail method calling
				   
				   userService.sendMail(user);
				   
				   // generate notification
				   
				   user.setUserId(id);
				   
				   userService.setNotificationForAdmin(user);
				   
			   } 
					 
		m.addAttribute("regSuccess", "Your registration request is sent successfully. You'll get notified via "
				+ "registered mail Id when any action is taked to your registration request!");	 
					 
		return "registration";
		
	}
	
	
	/*
	 * @RequestMapping(value = "/manageUsers", method = RequestMethod.GET) public
	 * String manageUsers(@RequestParam("pageId") int pageId, Model m) {
	 * 
	 * int total=5; if(pageId==1){} else{ pageId=(pageId-1)*total+1; } pageId--;
	 * List<UserDto> userDto = userService.getUsers(pageId, total); int totalPages =
	 * userService.getTotalPages(total);
	 * 
	 * m.addAttribute("totalPages", totalPages); m.addAttribute("userDto", userDto);
	 * 
	 * String msg = (String)m.asMap().get("msg");
	 * 
	 * if(msg != null) { m.addAttribute("msg", msg); }
	 * 
	 * return "manageuserrequest"; }
	 */
	
	@RequestMapping(value = "/manageUserRequest", method = RequestMethod.GET)
	public String manageUserReq(@RequestParam("pageId") int pageId, Model m) {
		
		int total=5;    
        if(pageId==1){}    
        else{    
            pageId=(pageId-1)*total+1;    
        }    
		pageId--;
		List<UserDto> userDto = userService.getInactiveUsers(pageId, total);
		int totalPages = userService.getTotalPagesForInactiveUsers(total);
		
		m.addAttribute("totalPages", totalPages);
		m.addAttribute("userDto", userDto);
		
		String msg = (String)m.asMap().get("msg");
	    
		if(msg != null) {
			m.addAttribute("msg", msg);
		}
		
		return "manageuserrequest";
	}
	
	@RequestMapping(value = "/approveUser", method = RequestMethod.GET)
	public String userApprove(@ModelAttribute("userDtoo") UserDto userDto, BindingResult bindingResult, 
			@RequestParam("userId") int userId, Model m, final RedirectAttributes redirectAttributes){
		
		// int userId = userDto.getUserId();
		
		System.out.println("userID from controller of approve user---------"+userId);
		
		String userRole = userDto.getUserRole();
		
		userService.approveUser(userId, userRole);
		
		userService.sendApproveUserReqMail(userDto);
		
		String msg = "User Approved successfully...";
		redirectAttributes.addFlashAttribute("msg", msg);
		
		return "redirect:manageUserRequest?pageId=1";
	}
	
	
	@RequestMapping(value = "/rejectUser")
	public String rejectUser(@RequestParam("userId") int userId) {
		
		userService.rejectUser(userId);
		
		userService.sendRejectUserReqMail(userId);
		
		return "redirect:manageUserRequest?pageId=1";
	}
	
	@RequestMapping(value = "/viewAllUsers", method = RequestMethod.GET)
	public String getAllUsers(@RequestParam("pageId") int pageId, Model m) {
		
		int pageNo = pageId;
		int total=5;    
        if(pageId==1){}    
        else{    
            pageId=(pageId-1)*total+1;    
        }    
		pageId--;
		List<UserDto> userDto = userService.getUsers(pageId, total);
		int totalPages = userService.getTotalPages(total);
		
		m.addAttribute("totalPages", totalPages);
		m.addAttribute("userDto", userDto);
		m.addAttribute("pageNo", pageNo);
		
		String msg = (String)m.asMap().get("msg");
	    
		if(msg != null) {
			m.addAttribute("msg", msg);
		}
		
		return "viewallusers";
	}
	
	/*
	 * @RequestMapping(value = "/viewAllRejectedUsers", method = RequestMethod.GET,
	 * headers = "Accept=application/json") public @ResponseBody Map<String, Object>
	 * viewAllRejectedUsers(@RequestParam("pageId") int pageId, Model m) {
	 * 
	 * int pageNo = pageId; int total=5; if(pageId==1){} else{
	 * pageId=(pageId-1)*total+1; } pageId--; List<UserDto> userDto =
	 * userService.getRejectedUsers(pageId, total); int totalPages =
	 * userService.getTotalPagesForRejectedUsers(total);
	 * 
	 * Map<String, Object> map = new HashMap<>();
	 * 
	 * map.put("totalPages", totalPages); map.put("userDto", userDto);
	 * map.put("pageNo", pageNo);
	 * 
	 * return map;
	 * 
	 * }
	 */
	
	@RequestMapping(value = "/viewAllActiveRejectedDeletedUsers", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Map<String, Object> viewAllActiveRejectedDeletedUsers(@RequestParam("pageId") int pageId,
			@RequestParam("type") String type, Model m) {
		
		int totalPages = 0;
		List<UserDto> userDto1 = new ArrayList<UserDto>();
		int pageNo = pageId;
		int total=5;    
        if(pageId==1){}    
        else{    
            pageId=(pageId-1)*total+1;    
        }    
		pageId--;
		
		if(type.equals("active"))
			{
				userDto1 = userService.getActiveUsers(pageId, total);
				totalPages = userService.getTotalPagesForActiveUsers(total);
			}
		else if(type.equals("rejected"))
			{
				userDto1 = userService.getRejectedUsers(pageId, total);
				totalPages = userService.getTotalPagesForRejectedUsers(total);
			}
		else if(type.equals("deleted"))
			{
				userDto1 = userService.getDeletedUsers(pageId, total);
				totalPages = userService.getTotalPagesForDeletedUsers(total);
			}
		else
			{
				userDto1 = userService.getUsers(pageId, total);
				totalPages = userService.getTotalPages(total);
			}
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("totalPages", totalPages);
		map.put("userDto1", userDto1);
		map.put("pageNo", pageNo);
		
		/*
		 * m.addAttribute("totalPages", totalPages); m.addAttribute("userDto", userDto);
		 * m.addAttribute("pageNo", pageNo);
		 * 
		 * String msg = (String)m.asMap().get("msg");
		 * 
		 * if(msg != null) { m.addAttribute("msg", msg); }
		 */
		
		return map;
		
	}
	
	/*
	 * @RequestMapping(value = "/viewAllDeletedUsers", method = RequestMethod.GET,
	 * headers = "Accept=application/json") public @ResponseBody Map<String, Object>
	 * viewAllDeletedUsers(@RequestParam("pageId") int pageId, Model m) {
	 * 
	 * int pageNo = pageId; int total=5; if(pageId==1){} else{
	 * pageId=(pageId-1)*total+1; } pageId--; List<UserDto> userDto1 =
	 * userService.getDeletedUsers(pageId, total); int totalPages =
	 * userService.getTotalPagesForDeletedUsers(total);
	 * 
	 * Map<String, Object> map = new HashMap<>();
	 * 
	 * map.put("totalPages", totalPages); map.put("userDto1", userDto1);
	 * map.put("pageNo", pageNo);
	 * 
	 * return map;
	 * 
	 * }
	 */
	
	@RequestMapping(value = "/viewUserById", method = RequestMethod.GET)
	public String getUserbyId(@RequestParam("userId") int userId, @RequestParam("pageNo") int pageNo, Model m) {
		
		UserDto userDto = userService.getUserById(userId);
		
		m.addAttribute("userDto", userDto);
		m.addAttribute("pageNo", pageNo);
		
		return "viewsingleuser";
	}
	
	@RequestMapping(value = "/getUserToEdit")
	public String getUserToEdit(@RequestParam("userId") int userId,@RequestParam("pageNo") int pageNo, Model m) {
		
		UserDto userToEdit = userService.getUserById(userId);
		m.addAttribute("userToEdit", userToEdit);
		m.addAttribute("pageNo", pageNo);
		
		return "edituserdetail";
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("userDtoo") UserDto userDto ,Model m, final RedirectAttributes redirectAttributes) {
		
		userService.updateUser(userDto);
		
		String msg = "User updated successfully...";
		
		redirectAttributes.addFlashAttribute("msg", msg);
		
		return "redirect:viewAllUsers?pageId=1";
	}
	
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteRoom(@RequestParam("userId") int userId, @RequestParam("pageNo") int pageNo, Model m)
	{
		
		userService.deleteUser(userId);
		
		return "redirect:viewAllUsers?pageId="+pageNo;
	}
	
	
	@RequestMapping(value = "/editPersonalDetails")
	public String editUserDetails(Model m) {
		
		String success = (String)m.asMap().get("success");
		if (success != null) {
			m.addAttribute("success", success);
		}
		
		String successMailReqSent = (String)m.asMap().get("successMailReqSent");
		m.addAttribute("successMailReqSent", successMailReqSent);
		
		String fail = (String)m.asMap().get("fail");
		m.addAttribute("fail", fail);
		
		String incorrectExt = (String)m.asMap().get("incorrectExt");
		m.addAttribute("incorrectExt", incorrectExt);
		
		return "editpersonaldetail";
	}
	
	
	@RequestMapping(value = "/saveFile", method = RequestMethod.POST)
	public String saveImage(@RequestParam("imgFile") CommonsMultipartFile imgFile, @RequestParam("userId") int userId,
			HttpSession session, Model m, Principal principal,
			final RedirectAttributes redirectAttributes) {
		
		try {
			
			//ServletContext context = session.getServletContext();  
		    //String path = context.getRealPath(UPLOAD_DIRECTORY);
			String path = "C:/Users/Neosoft/Documents/workspace-sts-3.9.9.RELEASE/Book_a_Meet_Room/src/main/webapp/images";
		    
		    String filename = imgFile.getOriginalFilename();
		    
		    String extension = FilenameUtils.getExtension(imgFile.getOriginalFilename());
		    
		    //System.out.println("extension is--------------"+extension);
		  
		    //System.out.println(path+"/"+filename);      
		    
		    if (extension.equals("jpg")  || extension.equals("jpeg") || extension.equals("png") || 
		    		extension.equals("JPG") || extension.equals("JPEG") || extension.equals("PNG")) 
		    {
				System.out.println("---------------coming to extension if-------------");
		    	byte[] bytes = imgFile.getBytes();  
			    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
			         new File(path + File.separator + filename)));  
			    stream.write(bytes);  
			    
			   //String filePath = path+"/"+filename;
			   	    
			    userService.saveImage(userId, filename);
			    
			    String userName = principal.getName();
				UserBean userBean = userDao.getUserByUserName(userName);
				session.setAttribute("userBean", userBean);
			   
			    stream.flush();  
			    stream.close();
			    
			    redirectAttributes.addFlashAttribute("success", "File Uploaded!");
		    	
			}
		    
		    else {
		    	
		    	redirectAttributes.addFlashAttribute("incorrectExt", "Please select only .jpg, .jpeg or .png files!");
		    	
		    }
		    
		} 
		
	    catch (Exception e) {
			
	    	 redirectAttributes.addFlashAttribute("fail", "Please select an image first!");
	    	
		}
	    
		return "redirect:editPersonalDetails";
	}
	
	@RequestMapping(value = "/saveEditedPersonalDetails", method = RequestMethod.POST)
	public String updatePersonalDetails(@ModelAttribute("userDtoo") UserDto userDto,
			HttpSession session, Principal principal, final RedirectAttributes redirectAttributes) {

		userService.updatePersonalDetails(userDto);
		
		String userName = userDto.getUserName();
		UserBean userBean = userDao.getUserByUserName(userName);
		session.setAttribute("userBean", userBean);
		String msg = "your Details updated successfully...";
		
		redirectAttributes.addFlashAttribute("msg", msg);
		
		return "redirect:editPersonalDetails";
	}
	
	@RequestMapping(value = "/onGoingRequests")
	public String onGoingRequests(Principal principal, Model m, HttpSession session) {
		
		String userName = principal.getName();
		UserBean userBean = userDao.getUserByUserName(userName);
		int userId = userBean.getUserId();
		
		session.setAttribute("userBean", userBean);
		
		List<UserRoomBookDto> bookedOrPendingRoomsList = userRoomBookService.getBookedRoomsByUserId(userId);
		
		if(bookedOrPendingRoomsList.size() == 0) {
			
			return "no-data-found";
			
		}
		
		else {
			
			System.out.println("list from controller before view----"+bookedOrPendingRoomsList);
			
			m.addAttribute("bookedOrPendingRoomsList", bookedOrPendingRoomsList);
			
			String msg = (String)m.asMap().get("msg");
		    
			if(msg != null) {
				m.addAttribute("msg", msg);
			}
			
			m.addAttribute("userBean", userBean);
			
			return "ongoingrequests";
		}
		
	}
	
	@RequestMapping(value = "/rejectedRequests")
	public String rejectedRequests(Principal principal, Model m) {
		
		String userName = principal.getName();
		UserBean userBean = userDao.getUserByUserName(userName);
		int userId = userBean.getUserId();
		
		List<UserRoomBookDto> rejectedRequestRoomsList = userRoomBookService.getRejectedRoomsByUserId(userId);
		
		if (rejectedRequestRoomsList.size() == 0) {

			return "no-data-found";

		}

		else {

			System.out.println("list from controller before view----"+rejectedRequestRoomsList);
			
			m.addAttribute("rejectedRequestRoomsList", rejectedRequestRoomsList);
			
			return "rejectedrequests";
		}
		
	}
	
	@RequestMapping(value = "/bookingHistory")
	public String bookingHistory(Principal principal, Model m) {
		
		String userName = principal.getName();
		UserBean userBean = userDao.getUserByUserName(userName);
		int userId = userBean.getUserId();
		
		List<UserRoomBookDto> roomBookingHistoryList = userRoomBookService.getBookingsByUserId(userId);
		
		if (roomBookingHistoryList.size() == 0) {

			return "no-data-found";

		}

		else {

			System.out.println("list from controller before view----"+roomBookingHistoryList);
			
			m.addAttribute("roomBookingHistoryList", roomBookingHistoryList);
			
			return "bookinghistory";
		}
		
	}
	
	@RequestMapping(value = "/cancelRequest")
	public String cancelRequest(@RequestParam("urBookId") int urBookId, final RedirectAttributes redirectAttributes) {
		
		userRoomBookService.cancelRequest(urBookId);
		
		String msg = "Booking Request Cancelled successfully...";
		redirectAttributes.addFlashAttribute("msg", msg);
		
		return "redirect:onGoingRequests";
		
	}
	
	@RequestMapping(value = "/changePassword")
	public String changePassword(Model m) {
		
		String passwordChanged = (String)m.asMap().get("passwordChanged");
		String wrongOldPwd = (String)m.asMap().get("wrongOldPwd");
		
		m.addAttribute("wrongOldPwd", wrongOldPwd);
		m.addAttribute("passwordChanged", passwordChanged);
	    
		return "changepassword";
		
	}
	
	@RequestMapping(value = "/setPassword", method = RequestMethod.POST)
	public String setPassword(Model m, HttpServletRequest request, Principal principal, final RedirectAttributes redirectAttributes) {
		
		String userName = principal.getName();
		UserBean userBean = userDao.getUserByUserName(userName);
		String oldPass = request.getParameter("oldPassword");
		String newPass = request.getParameter("newPassword");
		
		boolean result = userService.verifyOldPassword(oldPass, userBean);
		
		if(result == true)
		{
			userService.updatePassword(newPass, userBean);
			redirectAttributes.addFlashAttribute("passwordChanged", "your password has been changed successfully!!");
		}
		else 
		{
			redirectAttributes.addFlashAttribute("wrongOldPwd", "your old password is wrong!!");
		}
		
		return "redirect:changePassword";
		
	}
	
	@RequestMapping(value = "/feedBackForm")
	public String feedBackForm(Model m) {
		
		String msg = (String)m.asMap().get("msg");
		
		m.addAttribute("msg", msg);
		return "feedbackform";
		
	}
	
	@RequestMapping(value = "/submitFeedBack")
	public String submitFeedBack(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		String feedBack = request.getParameter("feedBack");
		
		userService.sendFeedBack(userId, feedBack);
		
		redirectAttributes.addFlashAttribute("msg", "Feedback sent successfully!");
		
		return "redirect:feedBackForm";
		
	}
	
	@RequestMapping(value = "/viewFeedBacks")
	public String viewFeedBacks(@RequestParam("pageId") int pageId, Model m) {
		
		int total=5;    
        if(pageId==1){}    
        else{    
            pageId=(pageId-1)*total+1;    
        }    
		pageId--;
		List<FeedbacksBean> feedBacks = userService.getAllFeedbacks(pageId, total);
		int totalPages = userService.getTotalPagesForAllFeedbacks(total);
		
		m.addAttribute("totalPages", totalPages);
		m.addAttribute("feedBacks", feedBacks);
		
		String responseMailSent = (String)m.asMap().get("responseMailSent");
		m.addAttribute("responseMailSent", responseMailSent);
		
		return "viewfeedbacks";
		
	}
	
	@RequestMapping(value = "/viewAllReadUnReadFeedbacks", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Map<String, Object> viewAllReadUnReadFeedbacks(@RequestParam("pageId") int pageId,
			@RequestParam("type") String type, Model m) {
		
		int totalPages = 0;
		List<FeedbacksBean> feedBacks1 = new ArrayList<>();
		int pageNo = pageId;
		int total=5;    
        if(pageId==1){}    
        else{    
            pageId=(pageId-1)*total+1;    
        }    
		pageId--;
		
		if(type.equals("all"))
			{
				feedBacks1 = userService.getAllFeedbacks(pageId, total);
				totalPages = userService.getTotalPagesForAllFeedbacks(total);
			}
		else if(type.equals("read"))
			{
				feedBacks1 = userService.getAllReadFeedbacks(pageId, total);
				totalPages = userService.getTotalPagesForReadFeedbacks(total);
			}
		else if(type.equals("unread"))
			{
				feedBacks1 = userService.getAllUnreadFeedbacks(pageId, total);
				totalPages = userService.getTotalPagesForUnreadFeedbacks(total);
			}
		
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("totalPages", totalPages);
		map.put("feedBacks1", feedBacks1);
		map.put("pageNo", pageNo);
		
		/*
		 * m.addAttribute("totalPages", totalPages); m.addAttribute("userDto", userDto);
		 * m.addAttribute("pageNo", pageNo);
		 * 
		 * String msg = (String)m.asMap().get("msg");
		 * 
		 * if(msg != null) { m.addAttribute("msg", msg); }
		 */
		
		return map;
		
	}
	
	
	@RequestMapping(value = "/readFeedBack")
	public String readFeedBack(@RequestParam("id") int id) {
		
		userService.changeReadStatus(id);
		
		return "redirect:viewFeedBacks?pageId=1";
		
	}
	
	@RequestMapping(value = "/readNotification")
	public String readNotification(@RequestParam("id") int id) {
		
		userService.changeNotificationStatus(id);
		
		UserBean user = userDao.getSingleNotificationById(id).getGeneratedFor();
		
		return "redirect:seeAllNotification?userId="+user.getUserId();
		
	}
	
	@RequestMapping(value = "/deleteNotification")
	public String deleteNotification(@RequestParam("id") int id) {
		
		userService.deleteNotification(id);
		
		UserBean user = userDao.getSingleNotificationById(id).getGeneratedFor();
		
		return "redirect:seeAllNotification?userId="+user.getUserId();
		
	}
	
	@RequestMapping(value = "/getSingleFeedback", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody FeedbacksBean getSingleFeedback(@RequestParam("id") int id, Model m) {
		
		FeedbacksBean feedbackBean = userService.getSingleFeedbackById(id);
		
		return feedbackBean;
		
	}
	
	@RequestMapping(value = "/submitFeedBackResponse", method = RequestMethod.POST)
	public String submitFeedBackResponse(@RequestParam("id") int id, 
			HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		
		String responseToFeedback = request.getParameter("feedBackResponse");
		String fromEmailId = request.getParameter("fromEmailId");
		
		FeedbacksBean feedbackBean = userService.getSingleFeedbackById(id);
		
		userService.sendRsponseToFeedbackInMail(responseToFeedback, feedbackBean, fromEmailId);
			
		redirectAttributes.addFlashAttribute("responseMailSent", "response mail sent successfully!");
		return "redirect:viewFeedBacks?pageId=1";
		
	}
	
	@RequestMapping(value = "/checkAvailUserName", method = RequestMethod.GET)
	public @ResponseBody boolean checkAvailUserName(@RequestParam("uName") String uName) {
		
		System.out.println("coming to controller on blur, with uname---------------- "+uName);
		
		boolean result = userService.checkForUserName(uName);
		
		return result;
		
	}
	
	@RequestMapping(value = "/checkAvailEmail", method = RequestMethod.GET)
	public @ResponseBody boolean checkAvailEmail(@RequestParam("uEmail") String uEmail) {
		
		System.out.println("coming to controller on blur, with uemail---------------- "+uEmail);
		
		boolean result = userService.checkForEmail(uEmail);
		
		return result;
		
	}
	
	@RequestMapping(value = "/checkAvailContactNo", method = RequestMethod.GET)
	public @ResponseBody boolean checkAvailContactNo(@RequestParam("uContact") String uContact) {
		
		System.out.println("coming to controller on blur, with ucontact---------------- "+uContact);
		
		boolean result = userService.checkForContactNo(uContact);
		
		return result;
		
	}
	
	@RequestMapping(value = "/seeAllNotification")
	public String seeAllNotification(@RequestParam("userId") int userId, Model m) {
		
		//deleted notification
		
		List<NotificationBean> notificationList = userDao.getNotificationsByUserId(userId);
		m.addAttribute("notificationList", notificationList);
		
		int listCount = notificationList.size();
		m.addAttribute("count", listCount);
		
		return "see-all-notification";
		
	}
	
	
	
	
	
	
	
	
}

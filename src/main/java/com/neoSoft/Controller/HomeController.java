package com.neoSoft.Controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neoSoft.Dao.UserDao;
import com.neoSoft.model.NotificationBean;
import com.neoSoft.model.UserBean;
import com.neoSoft.model.UserRoomBookBean;
import com.neoSoft.service.UserRoomBookService;
import com.neoSoft.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRoomBookService userRoomBookService;
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String registrationPageLoad(Model m) {
		
		String passwordResetSuccess = (String)m.asMap().get("passwordResetSuccess");
		
		m.addAttribute("passwordResetSuccess", passwordResetSuccess);
		
		return "registration";
		
	}
	
	@RequestMapping(value = "/adminDashboard")
	public String adminHomePage(Model m) {
		
		int pendingBookingReqCount = userDao.getAllPendingBookReqCount();
		int pendingUserReqCount = userDao.getAllInactiveUsersCount();
		int unreadFeedbacksCount = userDao.getAllUnreadFeedbacksCount();
		
		m.addAttribute("pendingBookingReqCount", pendingBookingReqCount);
		m.addAttribute("pendingUserReqCount", pendingUserReqCount);
		m.addAttribute("unreadFeedbacksCount", unreadFeedbacksCount);
		
		return "admin-dashboard";
	}
	
	@RequestMapping(value = "/userDashboard")
	public String userHomePage(Model m) {
		
		//String fullNameForView = (String)m.asMap().get("fullName");
		//m.addAttribute("fullNameForView", fullNameForView);
		return "user-dashboard";
		
	}
	
	/*
	 * @RequestMapping(value = "/tlDashboard") public String tlHomePage(Model m) {
	 * 
	 * //String fullNameForView = (String)m.asMap().get("fullName");
	 * //m.addAttribute("fullNameForView", fullNameForView); return "tl-dashboard";
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/pmDashboard") public String pmHomePage(Model m) {
	 * 
	 * //String fullNameForView = (String)m.asMap().get("fullName");
	 * //m.addAttribute("fullNameForView", fullNameForView); return "pm-dashboard";
	 * 
	 * }
	 */
	
	@RequestMapping(value = "/logout")
	public String logOut() {
		
		//HttpSession session = request.getSession();
		//session.invalidate();
		return "redirect:/login";
		
	}
	
	@RequestMapping(value = "/default")
	public String defaultDashboard(HttpServletRequest request, Principal principal) {
		
		String userName = principal.getName();
		UserBean userBean = userDao.getUserByUserName(userName);
		
		//-------------------------------------------------------- TO MAINTAIN SESSION--------------------------------------------------------
		
		HttpSession session = request.getSession();
		session.setAttribute("userBean", userBean);
		
		List<NotificationBean> notificationList = userDao.getNotificationsByUserId(userBean.getUserId());
		session.setAttribute("notificationList", notificationList);
		
		List<UserRoomBookBean> urbList = userRoomBookService.getLastTwoMonthsActivitiesByUserId(userBean.getUserId());
		session.setAttribute("urbList", urbList);
		
		int listCount = notificationList.size();
		session.setAttribute("count", listCount);
		
		
		
		if (request.isUserInRole("ROLE_ADMIN")) {
			
			return "redirect:/adminDashboard";
			
        }
		
		else {
			
			return "redirect:/userDashboard";
			
		}
		
		/*
		 * else if(request.isUserInRole("ROLE_T")) {
		 * 
		 * return "redirect:/tlDashboard";
		 * 
		 * }
		 * 
		 * return "redirect:/pmDashboard";
		 */
		
	}
	
	@RequestMapping(value = "/403AccessDenied")
	public String accessDenied() {
		
		 return "403-forbidden";
	}
	
	@RequestMapping(value = "/requestToResetPassword", method = RequestMethod.GET)
	public String getEmailToResetPassword(Model m) {
		
			String mailSent = (String)m.asMap().get("mailSent");
			String mailNotSent = (String)m.asMap().get("mailNotSent");
			String wrongReq = (String)m.asMap().get("wrongReq");
			String expiredReq = (String)m.asMap().get("expiredReq");
			//String usedToken = (String)m.asMap().get("usedToken");
		
			m.addAttribute("mailSent", mailSent);
			m.addAttribute("mailNotSent", mailNotSent);
			m.addAttribute("wrongReq", wrongReq);
			m.addAttribute("expiredReq", expiredReq);
			//m.addAttribute("usedToken", usedToken);
			
		return "resetpasswordemail";
		
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String resetPassword(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
			    
		String userEmail = request.getParameter("email");
		
		UserBean userBean = userService.findUserByEmail(userEmail);
		
		if(userBean == null)
		{
			redirectAttributes.addFlashAttribute("mailNotSent", "Please Enter registered Email or you're not registred yet!!");
		    
		    return "redirect:requestToResetPassword";
		}
		
		/*
		 * if (userBean == null) { throw new UsernameNotFoundException(userEmail); }
		 */
			    String token = UUID.randomUUID().toString();
			    
			    userService.createPasswordResetTokenForUser(userBean, token);
			    
			    // to send a mail to particular user
			    
			    StringBuffer url = request.getRequestURL();
			    String uri = request.getRequestURI();
			    String ctx = request.getContextPath();
			    String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
			    
			    String newURL = base + "setNewPassword?id=" + userBean.getUserId() + "&token=" + token;
			    
			    userService.sendMailToResetPassword(userBean, newURL);
			    
		/*
		 * mailSender.send(constructResetTokenEmail(getAppUrl(request),
		 * request.getLocale(), token, user));
		 */
			    
			    redirectAttributes.addFlashAttribute("mailSent", "Mail has been sent to your id, please check your e-mails now!");
			    
			    return "redirect:requestToResetPassword";
			    
			}
	
	@RequestMapping(value = "/setNewPassword")
	public String setNewPassword(@RequestParam("id") long userId, @RequestParam("token") String token, Model m,
			final RedirectAttributes redirectAttributes) {
		
		int tokenValidationStatus = userService.validatePasswordResetToken(userId, token);
		
		if(tokenValidationStatus == 1)
		{
			redirectAttributes.addFlashAttribute("wrongReq", "Sorry! Not able to find your Password reset reuqst!"
					+ " or you already used a link once to change the password! Please request again!");
			return "redirect:requestToResetPassword";
		}
		
		if(tokenValidationStatus == 2)
		{
			redirectAttributes.addFlashAttribute("expiredReq", "Sorry! your request to reset password is expired! Please request again!");
			return "redirect:requestToResetPassword";
		}
		
		/*
		 * if(tokenValidationStatus == 3) {
		 * redirectAttributes.addFlashAttribute("usedToken",
		 * "Sorry! you already used this link once to change the password! Please request again!"
		 * ); return "redirect:requestToResetPassword"; }
		 */
		
		m.addAttribute("userId", userId);
		
		userService.disableTokenStatus(token);
		
		return "setnewpassword";
		
	}
	
	@RequestMapping(value = "/saveNewPassword")
	public String saveNewPassword(final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		UserBean userBean = new UserBean();
		userBean.setUserId(userId);
		
		String password = request.getParameter("newPassword");
		
		userDao.updateNewPassword(password, userBean);
		
		redirectAttributes.addFlashAttribute("passwordResetSuccess",
				"YaY! Your password is successfully changed! Please login with new password now!");
		
		return "redirect:login";
	}
	
	public void getNotifications() {
		
		
		
	}
	
	
	
	
	
}

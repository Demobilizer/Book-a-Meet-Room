package com.neoSoft.Controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neoSoft.Dao.UserDao;
import com.neoSoft.misc.RequestTypes;
import com.neoSoft.misc.Status;
import com.neoSoft.model.RequestManagementBean;
import com.neoSoft.model.UserBean;
import com.neoSoft.service.RequestManagementService;

@Controller
public class RequestManagementController {

	@Autowired
	RequestManagementService requestManagementService;
	
	@Autowired
	UserDao userDao;
	
	
	
	@RequestMapping(value = "/requestToChangeEmailId", method = RequestMethod.POST)
	public String requestToChangeEmailId(HttpServletRequest request, final RedirectAttributes redirectAttributes) {

		String oldEmailId = request.getParameter("oldEmailId");
		String newEmailId = request.getParameter("newEmailId");
		String reason = request.getParameter("reason");
		int fromUserId = Integer.parseInt(request.getParameter("userId"));
		
		RequestManagementBean requestManagementBean = new RequestManagementBean();
		
		System.out.println("enum val from controller layer-----"+Status.PENDING);
		
		//requestManagementBean.setFromUser(fromUser);
		requestManagementBean.setTempOldData(oldEmailId);
		requestManagementBean.setTempNewData(newEmailId);
		requestManagementBean.setReason(reason);
		requestManagementBean.setRequestStatus(Status.PENDING);
		requestManagementBean.setRequestTypes(RequestTypes.CHANGE_MAIL_REQ);
		
		String controller = "mail";
		
		requestManagementService.sendRequest(requestManagementBean, fromUserId, controller);
		
		redirectAttributes.addFlashAttribute("successMailReqSent", "Request sent successfully!");
		return "redirect:editPersonalDetails";
		
	}
	
	@RequestMapping(value = "/requestToChangeDept", method = RequestMethod.POST)
	public String requestToChangeDept(HttpServletRequest request, final RedirectAttributes redirectAttributes) {

		String oldDept = request.getParameter("oldDept");
		String newDept = request.getParameter("newDept");
		String reason = request.getParameter("reason");
		int fromUserId = Integer.parseInt(request.getParameter("userId"));
		
		RequestManagementBean requestManagementBean = new RequestManagementBean();
		
		requestManagementBean.setTempOldData(oldDept);
		requestManagementBean.setTempNewData(newDept);
		requestManagementBean.setReason(reason);
		requestManagementBean.setRequestStatus(Status.PENDING);
		requestManagementBean.setRequestTypes(RequestTypes.CHANGE_DEPT_REQ);
		
		String controller = "dept";
		
		requestManagementService.sendRequest(requestManagementBean, fromUserId, controller);
		
		redirectAttributes.addFlashAttribute("successMailReqSent", "Request sent successfully!");
		return "redirect:editPersonalDetails";
		
	}
	
	@RequestMapping(value = "/checkAlreadyRequestedMailId")
	public @ResponseBody boolean checkAlreadyRequestedMailId(@RequestParam("userId") int userId) {
		
		boolean result = requestManagementService.checkForAlreadyRequestedMailId(userId);
		
		return result;
		
	}
	
	@RequestMapping(value = "/checkAlreadyRequestedDept")
	public @ResponseBody boolean checkAlreadyRequestedDept(@RequestParam("userId") int userId) {
		
		boolean result = requestManagementService.checkAlreadyRequestedDept(userId);
		
		return result;
		
	}
	
	@RequestMapping(value = "/ViewChangeMailIdRequests", method = RequestMethod.GET)
	public String ViewChangeMailIdRequests(Principal principal, Model m, HttpSession session) {
		
		String userName = principal.getName();
		UserBean userBean = userDao.getUserByUserName(userName);
		int userId = userBean.getUserId();
		
		List<RequestManagementBean> pendingMailReqList = requestManagementService.getChangeMailIdPendingRequests(userId);
		
		String approveSuccess = (String)m.asMap().get("approveSuccess");
		m.addAttribute("approveSuccess", approveSuccess);
		
		String rejectSuccess = (String)m.asMap().get("rejectSuccess");
		m.addAttribute("rejectSuccess", rejectSuccess);
		
		if (pendingMailReqList.size() != 0) {
			
			m.addAttribute("pendingMailReqList", pendingMailReqList);
			return "change-mail-requests";
			
		} else {

			return "no-data-found";
			
		}
		
	}
	
	@RequestMapping(value = "/ViewChangeDeptRequests", method = RequestMethod.GET)
	public String ViewChangeDeptRequests(Principal principal, Model m, HttpSession session) {
		
		String userName = principal.getName();
		UserBean userBean = userDao.getUserByUserName(userName);
		int userId = userBean.getUserId();
		
		List<RequestManagementBean> pendingDeptReqList = requestManagementService.getChangeDeptPendingRequests(userId);
		
		String approveSuccess = (String)m.asMap().get("approveSuccess");
		m.addAttribute("approveSuccess", approveSuccess);
		
		String rejectSuccess = (String)m.asMap().get("rejectSuccess");
		m.addAttribute("rejectSuccess", rejectSuccess);
		
		if (pendingDeptReqList.size() != 0) {
			
			m.addAttribute("pendingDeptReqList", pendingDeptReqList);
			return "change-dept-requests";
			
		} else {

			return "no-data-found";
			
		}
		
	}
	
	@RequestMapping(value = "/approveDeptChangeRequest")
	public String approveDeptChangeRequest(@RequestParam("reqManId") int reqManId,
			final RedirectAttributes redirectAttributes) {
		
		String remark = "";
		String controller = "approveDept";
		requestManagementService.approveOrRejectDeptOrMailChangeRequest(reqManId, controller, remark);
		
		redirectAttributes.addFlashAttribute("approveSuccess", "Request approved successfully!");
		return "redirect:ViewChangeDeptRequests";
		
	}
	
	@RequestMapping(value = "/rejectDeptChangeRequest", method =RequestMethod.POST)
	public String rejectDeptChangeRequest(	final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		int reqManId = Integer.parseInt(request.getParameter("req_id"));
		String remark = request.getParameter("remark");
		System.out.println("remark from controller------"+remark);
		String controller = "rejectDept";
		requestManagementService.approveOrRejectDeptOrMailChangeRequest(reqManId, controller, remark);
		
		redirectAttributes.addFlashAttribute("rejectSuccess", "Request rejected successfully!");
		return "redirect:ViewChangeDeptRequests";
		
	}
	
	@RequestMapping(value = "/approveMailChangeRequest")
	public String approveMailChangeRequest(@RequestParam("reqManId") int reqManId,
			final RedirectAttributes redirectAttributes) {
		
		String remark = "";
		String controller = "approveMail";
		requestManagementService.approveOrRejectDeptOrMailChangeRequest(reqManId, controller, remark);
		
		redirectAttributes.addFlashAttribute("approveSuccess", "Request approved successfully!");
		return "redirect:ViewChangeMailIdRequests";
		
	}
	
	@RequestMapping(value = "/rejectMailChangeRequest", method =RequestMethod.POST)
	public String rejectMailChangeRequest(final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		int reqManId = Integer.parseInt(request.getParameter("req_id"));
		String remark = request.getParameter("remark");
		System.out.println("remark from controller------"+remark);
		String controller = "rejectMail";
		requestManagementService.approveOrRejectDeptOrMailChangeRequest(reqManId, controller, remark);
		
		redirectAttributes.addFlashAttribute("rejectSuccess", "Request rejected successfully!");
		return "redirect:ViewChangeMailIdRequests";
		
	}
	
	
	
	
	
}

package com.neoSoft.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neoSoft.Dao.RequestManagementDao;
import com.neoSoft.Dao.UserDao;
import com.neoSoft.misc.NotificationTypes;
import com.neoSoft.misc.Status;
import com.neoSoft.model.NotificationBean;
import com.neoSoft.model.RequestManagementBean;
import com.neoSoft.model.UserBean;

@Service
@Transactional
public class RequestManagementService {
	
	@Autowired
	RequestManagementDao requestManagementDao;
	
	@Autowired
	UserDao userDao;

	public void sendRequest(RequestManagementBean requestManagementBean, int fromUserId, String controller) {

		NotificationBean notificationBean = new NotificationBean();
		
		UserBean fromUser = userDao.getSingleUserById(fromUserId);
		
		requestManagementBean.setFromUser(fromUser);
		
		String department = fromUser.getDepartment();
		
		if(fromUser.getUserRole().equals("ROLE_USER")) {
			
			UserBean departmentTL = requestManagementDao.getTLForSameDepatment(department);
			
			requestManagementBean.setToUser(departmentTL);
			notificationBean.setGeneratedFor(departmentTL);
			
		}
		
		else if(fromUser.getUserRole().equals("ROLE_T")) {
			
			UserBean departmentPM = requestManagementDao.getPMForSameDepatment(department);
			
			requestManagementBean.setToUser(departmentPM);
			notificationBean.setGeneratedFor(departmentPM);
			
		}
		
		else {
			
			UserBean admin = userDao.getUserByUserName("admin");
			
			requestManagementBean.setToUser(admin);
			notificationBean.setGeneratedFor(admin);
			
		}
		
		String notificationContent = "";
		
		if (controller.equals("mail")) {
			
			notificationContent = ""+fromUser.getFullName()+
					" have requested to change a mail Id";
			notificationBean.setNotificationType(NotificationTypes.CHANGE_MAIL_REQUEST);
			
		} else {

			notificationContent = ""+fromUser.getFullName()+
					" have requested to change a department";
			
			notificationBean.setNotificationType(NotificationTypes.CHANGE_DEPT_REQUEST);
			
		}
		
		notificationBean.setGeneratedBy(fromUser);
		notificationBean.setNotificationContent(notificationContent);
		notificationBean.setReadNotification(false);
		notificationBean.setDeleteNotification(false);
		
		requestManagementDao.saveRequest(requestManagementBean, notificationBean);
		
	}

	public boolean checkForAlreadyRequestedMailId(int userId) {

		//UserBean userBean = userDao.getsing
		
		RequestManagementBean requestManagementBean = new RequestManagementBean();
		
		requestManagementBean = requestManagementDao.checkForAlreadyRequestedMailId(userId);
		
		if(requestManagementBean == null)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	public boolean checkAlreadyRequestedDept(int userId) {

		RequestManagementBean requestManagementBean = new RequestManagementBean();
		
		requestManagementBean = requestManagementDao.checkForAlreadyRequestedDept(userId);
		
		if(requestManagementBean == null)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	public List<RequestManagementBean> getChangeMailIdPendingRequests(int userId) {

		return requestManagementDao.getChangeMailIdPendingRequests(userId);
		
	}

	public List<RequestManagementBean> getChangeDeptPendingRequests(int userId) {

		return requestManagementDao.getChangeDeptPendingRequests(userId);
		
	}

	/*
	 * public void approveDeptChangeRequest(int reqManId) {
	 * 
	 * NotificationBean notificationBean = new NotificationBean();
	 * 
	 * RequestManagementBean requestManagementBean =
	 * requestManagementDao.getSingleRequestById(reqManId);
	 * 
	 * Calendar cal = Calendar.getInstance();
	 * System.out.println("time from service-------"+cal.getTime());
	 * requestManagementBean.setUpdated(cal.getTime());
	 * 
	 * requestManagementBean.setRequestStatus(Status.APPROVED); String
	 * notificationContent = "your request to change Department has been approved!";
	 * notificationBean.setNotificationType(NotificationTypes.CHANGE_DEPT_RESPONSE);
	 * 
	 * notificationBean.setGeneratedBy(requestManagementBean.getToUser());
	 * notificationBean.setGeneratedFor(requestManagementBean.getFromUser());
	 * notificationBean.setNotificationContent(notificationContent);
	 * notificationBean.setReadNotification(false);
	 * notificationBean.setDeleteNotification(false);
	 * 
	 * requestManagementDao.approveDeptOrMailChangeRequest(requestManagementBean,
	 * notificationBean);
	 * 
	 * }
	 * 
	 * public void approveMailChangeRequest(int reqManId) {
	 * 
	 * NotificationBean notificationBean = new NotificationBean();
	 * 
	 * RequestManagementBean requestManagementBean =
	 * requestManagementDao.getSingleRequestById(reqManId);
	 * 
	 * Calendar cal = Calendar.getInstance();
	 * System.out.println("time from service-------"+cal.getTime());
	 * requestManagementBean.setUpdated(cal.getTime());
	 * 
	 * requestManagementBean.setRequestStatus(Status.APPROVED); String
	 * notificationContent = "your request to change Mail has been approved!";
	 * notificationBean.setNotificationType(NotificationTypes.CHANGE_MAIL_RESPONSE);
	 * 
	 * notificationBean.setGeneratedBy(requestManagementBean.getToUser());
	 * notificationBean.setGeneratedFor(requestManagementBean.getFromUser());
	 * notificationBean.setNotificationContent(notificationContent);
	 * notificationBean.setReadNotification(false);
	 * notificationBean.setDeleteNotification(false);
	 * 
	 * requestManagementDao.approveDeptOrMailChangeRequest(requestManagementBean,
	 * notificationBean);
	 * 
	 * }
	 * 
	 * public void rejectMailChangeRequest(int reqManId) {
	 * 
	 * NotificationBean notificationBean = new NotificationBean();
	 * 
	 * RequestManagementBean requestManagementBean =
	 * requestManagementDao.getSingleRequestById(reqManId);
	 * 
	 * Calendar cal = Calendar.getInstance();
	 * System.out.println("time from service-------"+cal.getTime());
	 * requestManagementBean.setUpdated(cal.getTime());
	 * 
	 * requestManagementBean.setRequestStatus(Status.REJECTED); String
	 * notificationContent = "your request to change Mail has been rejected!";
	 * notificationBean.setNotificationType(NotificationTypes.CHANGE_MAIL_RESPONSE);
	 * 
	 * notificationBean.setGeneratedBy(requestManagementBean.getToUser());
	 * notificationBean.setGeneratedFor(requestManagementBean.getFromUser());
	 * notificationBean.setNotificationContent(notificationContent);
	 * notificationBean.setReadNotification(false);
	 * notificationBean.setDeleteNotification(false);
	 * 
	 * requestManagementDao.approveOrRejectDeptOrMailChangeRequest(
	 * requestManagementBean, notificationBean);
	 * 
	 * }
	 * 
	 * public void rejectDeptChangeRequest(int reqManId) {
	 * 
	 * NotificationBean notificationBean = new NotificationBean();
	 * 
	 * RequestManagementBean requestManagementBean =
	 * requestManagementDao.getSingleRequestById(reqManId);
	 * 
	 * Calendar cal = Calendar.getInstance();
	 * System.out.println("time from service-------"+cal.getTime());
	 * requestManagementBean.setUpdated(cal.getTime());
	 * 
	 * requestManagementBean.setRequestStatus(Status.REJECTED); String
	 * notificationContent = "your request to change Department has been rejected!";
	 * notificationBean.setNotificationType(NotificationTypes.CHANGE_DEPT_RESPONSE);
	 * 
	 * notificationBean.setGeneratedBy(requestManagementBean.getToUser());
	 * notificationBean.setGeneratedFor(requestManagementBean.getFromUser());
	 * notificationBean.setNotificationContent(notificationContent);
	 * notificationBean.setReadNotification(false);
	 * notificationBean.setDeleteNotification(false);
	 * 
	 * requestManagementDao.approveOrRejectDeptOrMailChangeRequest(
	 * requestManagementBean, notificationBean);
	 * 
	 * }
	 */

	public void approveOrRejectDeptOrMailChangeRequest(int reqManId, String controller, String remark) {
		
		NotificationBean notificationBean = new NotificationBean();
		String notificationContent = "";
		
		RequestManagementBean requestManagementBean = requestManagementDao.getSingleRequestById(reqManId);
		
		Calendar cal = Calendar.getInstance();
		  System.out.println("time from service-------"+cal.getTime());
		  requestManagementBean.setUpdated(cal.getTime());
		  
		  if (controller.equals("approveDept")) {
			
			  requestManagementBean.setRequestStatus(Status.APPROVED);
				notificationContent = "your request to change Department has been approved!";
				notificationBean.setNotificationType(NotificationTypes.CHANGE_DEPT_RESPONSE);
			  
		} else if (controller.equals("rejectDept")) {
			
			requestManagementBean.setRemarksByUpperHirarchy(remark);
			requestManagementBean.setRequestStatus(Status.REJECTED);
			notificationContent = "your request to change Department has been rejected!";
			notificationBean.setNotificationType(NotificationTypes.CHANGE_DEPT_RESPONSE);
			
		} else if (controller.equals("approveMail")) {
			
			requestManagementBean.setRequestStatus(Status.APPROVED);
			  notificationContent = "your request to change Mail has been approved!";
			  notificationBean.setNotificationType(NotificationTypes.CHANGE_MAIL_RESPONSE);
			
		} else if (controller.equals("rejectMail")) {
			
			requestManagementBean.setRemarksByUpperHirarchy(remark);
			requestManagementBean.setRequestStatus(Status.REJECTED);
			notificationContent = "your request to change Mail has been rejected!";
			notificationBean.setNotificationType(NotificationTypes.CHANGE_MAIL_RESPONSE);
			
		}
		  
		  	notificationBean.setGeneratedBy(requestManagementBean.getToUser());
			notificationBean.setGeneratedFor(requestManagementBean.getFromUser());
			notificationBean.setNotificationContent(notificationContent);
			notificationBean.setReadNotification(false);
			notificationBean.setDeleteNotification(false);
			
			requestManagementDao.approveOrRejectDeptOrMailChangeRequest(requestManagementBean, notificationBean); 
		 
		
	}
	
	

}

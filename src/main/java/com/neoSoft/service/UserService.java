package com.neoSoft.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neoSoft.Dao.UserDao;
import com.neoSoft.dto.UserDto;
import com.neoSoft.misc.NotificationTypes;
import com.neoSoft.model.FeedbacksBean;
import com.neoSoft.model.NotificationBean;
import com.neoSoft.model.PasswordResetToken;
import com.neoSoft.model.RequestManagementBean;
import com.neoSoft.model.UserBean;
import com.neoSoft.model.UserRoomBookBean;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	JavaMailSender mailSender;

	public int createUser(UserDto user) {
		
		System.out.println("from service Layer--------"+user.getFullName());
		System.out.println("from service Layer------------"+user.getUserName());
		
		UserBean userBean = new UserBean();
		
		userBean.setUserName(user.getUserName());
		userBean.setFullName(user.getFullName());
		userBean.setPassword(user.getPassword());
		userBean.setEmailId(user.getEmailId());
		userBean.setContactNo(user.getContactNo());
		userBean.setDepartment(user.getDepartment());
		userBean.setUserId(user.getUserId());
		userBean.setAccountStatus("inactive");
		userBean.setUserRole("user");		 		
		//userBean.setUserRole(user.getUserRole());

		return userDao.createrUser(userBean);
		
	}

	public void sendMail(UserDto user) {
		
		String mailFrom = "mehulkumar.kapadiya@neosofttech.com";
		String mailTo = "mehulkumar.kapadiya@neosofttech.com";
		String mailSubject = "New Request for User Registration";
		String mailMsgBody = "<html>" + 
				"<head>" + 
				"	<style type=\"text/css\">" + 
				"		body {background-color: #b3cccc; align-self: center;}" + 
				"		.col1 {color: blue;}" + 
				"		h1 {color: red;}" + 
				"	</style>" + 
				"</head>" + 
				"<body>" + 
				"<table align='center'>" + 
				"	<tr>" + 
				"		<td colspan='2'><h1 align='center'>NeoSoft Technologies</h1></td>" + 
				"	</tr>" + 
				"	<tr>" + 
				"		<td colspan='2'><h3 align='center'>User registration Request</h3></td>" + 
				"	</tr>" + 
				"	<tr>" + 
				"		<td class='col1'> User Fullname:</td><td>"+user.getFullName()+"</td>" + 
				"	</tr>" + 
				"	<tr>" + 
				"		<td class='col1'>User Email:</td><td>"+user.getEmailId()+"</td>" + 
				"	</tr>" + 
				"	<tr>" + 
				"		<td class='col1'>User Department:</td><td>"+user.getDepartment()+"</td>" + 
				"	</tr>" + 
				"	<tr>" + 
				"		<td class='col1'>User Contact No:</td><td>"+user.getContactNo()+"</td>" + 
				"	</tr>	" +
				"<tr>" + 
				"	<td align='right'><a href='http://localhost:8080/Book_a_Meet_Room/manageUserRequest?pageId=1'>Approve</a></td>"
				+ "<td><a href='http://localhost:8080/Book_a_Meet_Room/manageUserRequest?pageId=1'>Reject</a></td>" + 
				"	</tr>"+
				"</table>" + 
				"</body>" + 
				"</html>";
		
		
		/*
		 * InternetHeaders headers = new InternetHeaders();
		 * headers.addHeader("Content-type", "text/html; charset=UTF-8"); String html =
		 * "Test\n" + text + "\n<a href='http://test.com'>Test.com</a>"; MimeBodyPart
		 * body = new MimeBodyPart(headers, html.getBytes("UTF-8"));
		 */
		
		  MimeMessage mimeMessage = mailSender.createMimeMessage(); MimeMessageHelper
		  helper = new MimeMessageHelper(mimeMessage, "UTF-8");
		 
		
		//mimeMessage.setContent(mailMsgBody, "text/html"); /** Use this or below line **/
		try {
			
			helper.setText(mailMsgBody, true);// Use this or above line.

			helper.setTo(mailTo);
			helper.setSubject(mailSubject);
			helper.setFrom(mailFrom);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		mailSender.send(mimeMessage);
		
		/*
		 * SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		 * 
		 * simpleMailMessage.setFrom(mailFrom); simpleMailMessage.setTo(mailTo);
		 * simpleMailMessage.setSubject(mailSubject);
		 * simpleMailMessage.setText(mailMsgBody);
		 * 
		 * mailSender.send(simpleMailMessage);
		 */
		
	}

	public List<UserDto> getUsers(int pageId, int total) {
		
		List<UserBean> userBeanList = userDao.getAllUsers(pageId, total);
		
		//System.out.println("size of list="+userBeanList.size());
		
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		
		userBeanList.stream().forEach((a)->{
			UserDto userDto = null;
			
			  userDto = new UserDto(a.getUserId(), a.getFullName(), a.getDepartment(),
			  a.getEmailId(), a.getAccountStatus(), a.getCreated(), a.getUpdated());
			 
			userDtoList.add(userDto);
			
		});
		
		return userDtoList;
	}

	public int getTotalPages(int total) {
		
		return userDao.getTotalNoOfPages(total);
		
	}
	
public int getTotalPagesForInactiveUsers(int total) {
		
		return userDao.getTotalNoOfPagesForInactiveUsers(total);
		
	}

	public void approveUser(int userId, String userRole) {
		
		System.out.println("Service Layer: user id from controller:------------"+userId);
		
		UserBean userBean = new UserBean();
		
		userBean.setUserId(userId);
		
		System.out.println("Service Layer: after setting user id of userBean:------------"+userBean.getUserId());
		
		userBean.setUserRole(userRole);
		userBean.setAccountStatus("active");
		
		userDao.approveUser(userBean);
		
	}
	
public void rejectUser(int userId) {

	UserBean userBean = new UserBean();
	userBean.setUserId(userId);
	userBean.setAccountStatus("rejected");
	
	userDao.rejectUser(userBean);
		
	}
	
	public UserDto getUserById(int userId) {
		
		UserBean userBean = new UserBean();
		userBean.setUserId(userId);
		
		userBean = userDao.getSingleUserById(userBean.getUserId());
		
		UserDto userDto = new UserDto();
		
		userDto.setUserId(userBean.getUserId());
		userDto.setUserName(userBean.getUserName());
		userDto.setFullName(userBean.getFullName());
		userDto.setUserRole(userBean.getUserRole());
		userDto.setAccountStatus(userBean.getAccountStatus());
		userDto.setContactNo(userBean.getContactNo());
		userDto.setCreated(userBean.getCreated());
		userDto.setUpdated(userBean.getUpdated());
		userDto.setDepartment(userBean.getDepartment());
		userDto.setEmailId(userBean.getEmailId());
		userDto.setPassword(userBean.getPassword());
		
		//userDto.setter getter.getter..getter..getter..getter..
		
		return userDto;
	}

	public void deleteUser(int userId) {

		UserBean userBean = new UserBean();
		userBean.setUserId(userId);
		userBean.setAccountStatus("deleted");
		
		userDao.rejectUser(userBean); 	// this dao method will soft delete the user
		
	}

	public List<UserDto> getInactiveUsers(int pageId, int total) {

		List<UserBean> userBeanList = userDao.getAllInactiveUsers(pageId, total);
		
		//System.out.println("size of list="+userBeanList.size());
		
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		
		userBeanList.stream().forEach((a)->{
				
			UserDto userDto = null;
			
			  userDto = new UserDto(a.getUserId(), a.getFullName(), a.getDepartment(),
			  a.getEmailId(), a.getAccountStatus(), a.getCreated(), a.getUpdated());
			 
			userDtoList.add(userDto);
			
		});
		
		return userDtoList;
		
	}

	public void updateUser(UserDto user) {

		UserBean userBean = new UserBean();
		
		//userBean.setUserName(user.getUserName());
		userBean.setFullName(user.getFullName());
		//userBean.setPassword(user.getPassword());
		//userBean.setEmailId(user.getEmailId());
		userBean.setContactNo(user.getContactNo());
		userBean.setDepartment(user.getDepartment());
		userBean.setUserId(user.getUserId());
		//userBean.setAccountStatus(user.getAccountStatus());
		//userBean.setUpdated(user.getUpdated());
		userBean.setUserRole(user.getUserRole());
		
		userDao.updateUser(userBean);
		
	}

	public void saveImage(int userId, String filePath) {

		userDao.saveimage(userId, filePath);
		
	}

	public void updatePersonalDetails(UserDto userDto) {

		UserBean userBean = new UserBean();

		userBean.setFullName(userDto.getFullName());
		userBean.setUserName(userDto.getUserName());
		userBean.setContactNo(userDto.getContactNo());
		userBean.setUserId(userDto.getUserId());
		
		userDao.updateUserPersonalDetails(userBean);
		
	}

	public boolean verifyOldPassword(String oldPass, UserBean userBean) {
		
		String passwordInDb = userDao.getPasswordByUserId(userBean.getUserId());
		
		if(passwordInDb.equals(oldPass))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	public void updatePassword(String newPass, UserBean userBean) {

		userDao.updateNewPassword(newPass, userBean);
		
	}

	public UserBean findUserByEmail(String userEmail) {
		
		return userDao.getUserByEmail(userEmail);
		
	}

	public void createPasswordResetTokenForUser(UserBean userBean, String token) {
		
		Date date = new Date();
		long timeNow = date.getTime();
		long expiryTime = timeNow + PasswordResetToken.getExpiration();
		
		PasswordResetToken myToken = new PasswordResetToken();
		
		myToken.setToken(token);
		myToken.setUserBean(userBean);
		myToken.setExpiryDate(expiryTime);
		myToken.setStatus("ENABLE");
	    
		userDao.saveToken(myToken);
				
	}

	public void sendMailToResetPassword(UserBean userBean, String newURL) {
		
		String mailFrom = "mehulkumar.kapadiya@neosofttech.com";
		String mailTo = userBean.getEmailId();
		String mailSubject = "Reset password";
		String mailMsgBody = "<html>" + 
				"<head>" + 
				"	<style type=\"text/css\">" + 
				"		body {background-color: #b3cccc; align-self: center;}" + 
				"		.col1 {color: blue;}" + 
				"		h1 {color: red;}" + 
				"	</style>" + 
				"</head>" + 
				"<body>" + 
				
				"<h3 align='center'>Click Below link to reset the Password</h3>" + 
				
				"<h4><a href="+newURL +">"+newURL+"</a></h4>"+
				
				"</body>" + 
				"</html>";
		
		  MimeMessage mimeMessage = mailSender.createMimeMessage(); MimeMessageHelper
		  helper = new MimeMessageHelper(mimeMessage, "UTF-8");
		
		//mimeMessage.setContent(mailMsgBody, "text/html"); /** Use this or below line **/
		try {
			
			helper.setText(mailMsgBody, true);// Use this or above line.

			helper.setTo(mailTo);
			helper.setSubject(mailSubject);
			helper.setFrom(mailFrom);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		mailSender.send(mimeMessage);
		
	}

	public int validatePasswordResetToken(long userId, String token) {

		PasswordResetToken prToken = userDao.getTokenByToken(token);
		
		if(prToken == null || prToken.getUserBean().getUserId() != userId) 
		{
			return 1;
		}
		
		long timeNow = new Date().getTime();
		long expiryTime = prToken.getExpiryDate();
		if((expiryTime - timeNow) < 0)
		{
			return 2;
		}
		
		if(prToken.getStatus().equals("DISABLE"))
		{
			return 3;
		}
		
		return 4;
		
	}

	public void disableTokenStatus(String token) {
		
		userDao.disableTokenStatus(token);
		
	}

	public void sendFeedBack(int userId, String feedBack) {

		UserBean userBean = new UserBean();
		
		userBean.setUserId(userId);
		
		FeedbacksBean feedbacksBean = new FeedbacksBean();
		NotificationBean notificationBean = new NotificationBean();
		
		feedbacksBean.setFeedBack(feedBack);
		feedbacksBean.setUserBean(userBean);
		feedbacksBean.setReadStatus(false);
		
		UserBean fromUser = userDao.getSingleUserById(userBean.getUserId());
		UserBean admin = userDao.getUserByUserName("admin");
		
		String notificationContent = ""+fromUser.getFullName()+" has sent a Feedback";
		
		notificationBean.setDeleteNotification(false);
		notificationBean.setGeneratedBy(userBean);
		notificationBean.setGeneratedFor(admin);
		notificationBean.setNotificationContent(notificationContent);
		notificationBean.setNotificationType(NotificationTypes.FEEDBACK_SENT);
		notificationBean.setReadNotification(false);
		
		userDao.sendFeedBack(feedbacksBean, notificationBean);
		
	}

	public List<FeedbacksBean> getAllFeedbacks(int pageId, int total) {

		List<FeedbacksBean> feedbacksBeanList = userDao.getAllFeedbacks(pageId, total);
		
		return feedbacksBeanList;
		
	}

	public int getTotalPagesForAllFeedbacks(int total) {

		return userDao.getTotalNoOfPagesForAllFeedbacks(total);
	
	}
	
	public List<FeedbacksBean> getAllReadFeedbacks(int pageId, int total) {

		List<FeedbacksBean> feedbacksBeanList = userDao.getAllReadFeedbacks(pageId, total);
		
		return feedbacksBeanList;
		
	}
	
	public int getTotalPagesForReadFeedbacks(int total) {

		return userDao.getTotalPagesForReadFeedbacks(total);
		
	}

	public List<FeedbacksBean> getAllUnreadFeedbacks(int pageId, int total) {

		List<FeedbacksBean> feedbacksBeanList = userDao.getAllUnreadFeedbacks(pageId, total);
		
		return feedbacksBeanList;
		
	}
	
	public int getTotalPagesForUnreadFeedbacks(int total) {

		return userDao.getTotalPagesForUnreadFeedbacks(total);
		
	}

	public void changeReadStatus(int id) {
		
		FeedbacksBean feedbackBean = userDao.getSingleFeedbackById(id);
		
		if(feedbackBean.isReadStatus() == true)
		{
			feedbackBean.setReadStatus(false);
		}
		else 
		{
			feedbackBean.setReadStatus(true);
		}
		
		userDao.updateReadStatus(feedbackBean);
		
	}

	public FeedbacksBean getSingleFeedbackById(int id) {
		
		return userDao.getSingleFeedbackById(id);
		
	}

	public void sendRsponseToFeedbackInMail(String responseToFeedback, FeedbacksBean feedbackBean, String fromEmailId) {
		
		String mailFrom = fromEmailId;
		String mailTo = feedbackBean.getUserBean().getEmailId();
		String mailSubject = "Response to your feedback";
		String mailMsgBody = "<html>" + 
				"<head>" + 
				"	<style type=\"text/css\">" +
				"		h3 {color: blue;}" +
				"		body {background-color: #b3cccc; align-self: center;}" +
				"		textarea {background-color: #EDEDED; align-self: center;}" +
		"		.text-muted {" + 
		"			color: #777;" + 
		"		}" +
		"		.well-sm {" + 
		"" + 
		"    padding: 9px;" + 
		"    border-radius: 3px;" + 
		"" + 
		"}" + 
		".well {" + 
		"" + 
		"    min-height: 20px;" + 
		"    padding: 19px;" + 
		"    margin-bottom: 20px;" + 
		"    background-color: " + 
		"" + 
		"#f5f5f5;" + 
		"" + 
		"border: 1px solid" + 
		"#e3e3e3;" + 
		"" + 
		"border-radius: 4px;" + 
		"" + 
		"-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.05);" + 
		"" + 
		"box-shadow: inset 0 1px 1px" + 
		"" + 
		"    rgba(0,0,0,.05);" + 
		"" + 
		"}"+
				
				"	</style>" + 
				"</head>" + 
				"<body>" + 
				"	<div class=\"box\">" + 
				"		<h3>Response to your valuable feedback</h3>" + 
				"		<br>" + 
				"		<div style=\"margin-left: 150px;\">" + 
				"			your Feedback: <br>" + 
				"<p class=\"text-muted well well-sm no-shadow\" style=\"margin-top: 10px;\">" + 
				                           feedbackBean.getFeedBack()+
				"                          </p>"+
				"		</div>" + 
				"		<br/>" + 
				"		<div style=\"margin-right: 150px;\">" + 
				"			Response to your Feedback: <br>" + 
				"<p class=\"text-muted well well-sm no-shadow\" style=\"margin-top: 10px;\">" + 
										responseToFeedback+
                "                          </p>"+ 
				"		<p style=\"color: red;\" >Thanks! </p>" + 
				"		</div>" + 
				"	</div>" + 
				"</body>" + 
				"</html>";
		
		 MimeMessage mimeMessage = mailSender.createMimeMessage(); MimeMessageHelper
		  helper = new MimeMessageHelper(mimeMessage, "UTF-8");
		
		//mimeMessage.setContent(mailMsgBody, "text/html"); /** Use this or below line **/
		try {
			
			helper.setText(mailMsgBody, true);// Use this or above line.

			helper.setTo(mailTo);
			helper.setSubject(mailSubject);
			helper.setFrom(mailFrom);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		mailSender.send(mimeMessage);
		
		/*
		 * System.out.println("from mail id--------------"+fromEmailId);
		 * System.out.println("response from service layer---------"+responseToFeedback)
		 * ; System.out.println("uid from service--------"+feedbackBean.getUserBean().
		 * getUserId());
		 * System.out.println("to mail from service-----------"+feedbackBean.getUserBean
		 * ().getEmailId());
		 */
		
	}

	public boolean checkForUserName(String uName) {

		UserBean userBean = new UserBean();
		
		userBean = userDao.checkIfUserNameAvail(uName);
		
		if(userBean == null)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	public void setNotificationForAdmin(UserDto user) {

		NotificationBean notificationBean = new NotificationBean();
		
		UserBean userBean = userDao.getSingleUserById(user.getUserId());
		
		UserBean admin = userDao.getUserByUserName("admin");
		
		String notificationContent = ""+userBean.getFullName()+" has requested for registration in a team "+userBean.getDepartment();
		
		notificationBean.setDeleteNotification(false);
		notificationBean.setGeneratedBy(userBean);
		notificationBean.setGeneratedFor(admin);
		notificationBean.setNotificationContent(notificationContent);
		notificationBean.setNotificationType(NotificationTypes.USER_REGISTRATION_REQUEST);
		notificationBean.setReadNotification(false);
		
		userDao.setNotificationForUserRegistration(notificationBean);
		
	}

	public void changeNotificationStatus(int id) {

			NotificationBean notificationBean = userDao.getSingleNotificationById(id);
			
			if(notificationBean.isReadNotification() == true)
			{
				notificationBean.setReadNotification(false);
			}
			else 
			{
				notificationBean.setReadNotification(true);
			}
			
			userDao.updateNotificationStatus(notificationBean);
			
	}

	public void deleteNotification(int id) {

		NotificationBean notificationBean = userDao.getSingleNotificationById(id);
		
		notificationBean.setDeleteNotification(true);
		
		userDao.deleteNotificationStatus(notificationBean);
		
	}

	public boolean checkForEmail(String uEmail) {

		UserBean userBean = new UserBean();
		
		userBean = userDao.checkIfEmailAvail(uEmail);
		
		if(userBean == null)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	public boolean checkForContactNo(String uContact) {

		UserBean userBean = new UserBean();
		
		userBean = userDao.checkIfContactNoAvail(uContact);
		
		if(userBean == null)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	public void sendApproveUserReqMail(UserDto userDto) {
		
		String url = "http://localhost:8080/Book_a_Meet_Room/login#signin";
		
		String mailFrom = "mehulkumar.kapadiya@neosofttech.com";
		//String mailTo = userDto.getEmailId();     //   <---------------- actual
		String mailTo = "mehulkumar.kapadiya@neosofttech.com";     //   <---------------- for testing
		String mailSubject = "Registration Request approved";
		String mailMsgBody = "<html>" + 
				"<head>" + 
				" <style type=\"text/css\">" + 
				"		" + 
				"		.alert-success {" + 
				"    color: " + 
				"#fff;" + 
				"background-color:" + 
				"rgba(38,185,154,.88);" + 
				"border-color:" + 
				"    rgba(38,185,154,.88);" + 
				"}" + 
				".alert-success {" + 
				"    color: #3c763d;" + 
				"    background-color: #dff0d8;" + 
				"    border-color: #d6e9c6;" + 
				"}" + 
				"" + 
				".alert-danger, .alert-error {" + 
				"    color:#E9EDEF;" + 
				"	background-color:rgba(231,76,60,.88);" + 
				"	border-color: rgba(231,76,60,.88);" + 
				"}" + 
				"" + 
				".alert-dismissable, .alert-dismissible {" + 
				"    padding-right: 35px;" + 
				"}" + 
				".alert {" + 
				"    " + 
				"    margin-bottom: 20px;" + 
				"    border: 1px solid " + 
				"    " + 
				"    border-radius: 4px;" + 
				"}" + 
				"" + 
				"}" + 
				"	</style>	" + 
				"</head>" + 
				"<body>" + 
				"" + 
				"	<div class='alert alert-success alert-dismissible fade in' role='alert' align='center'>" + 
				"                    " + 
				"        <strong>hey User!</strong> your registration request is approved successfully!" + 
				"        <br>" + 
				"                    " + 
				"" + 
				"    </div>	" + 
				"" + 
				"        <h4 align='center'>please click below link to login to your account.</h4>" + 
				
				"" + 
				"        <h5 align='center'><a href="+url+">"+url+"</a></h5>" + 
				"" + 
				"</body>" + 
				"</html>";
		
		  MimeMessage mimeMessage = mailSender.createMimeMessage(); MimeMessageHelper
		  helper = new MimeMessageHelper(mimeMessage, "UTF-8");
		 
		
		//mimeMessage.setContent(mailMsgBody, "text/html"); /** Use this or below line **/
		try {
			
			helper.setText(mailMsgBody, true);// Use this or above line.

			helper.setTo(mailTo);
			helper.setSubject(mailSubject);
			helper.setFrom(mailFrom);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		mailSender.send(mimeMessage);
		
	}

	public void sendRejectUserReqMail(int userId) {

		UserDto userDto = this.getUserById(userId);
		String url = "http://localhost:8080/Book_a_Meet_Room/login#signup";
		
		String mailFrom = "mehulkumar.kapadiya@neosofttech.com";
		//String mailTo = userDto.getEmailId();     //   <---------------- actual
		String mailTo = "mehulkumar.kapadiya@neosofttech.com";     //   <---------------- for testing
		String mailSubject = "Registration Request rejected";
		String mailMsgBody = "<html>" + 
				"<head>" + 
				" <style type=\"text/css\">" + 
				"		" + 
				"		.alert-success {" + 
				"    color: " + 
				"#fff;" + 
				"background-color:" + 
				"rgba(38,185,154,.88);" + 
				"border-color:" + 
				"    rgba(38,185,154,.88);" + 
				"}" + 
				".alert-success {" + 
				"    color: #3c763d;" + 
				"    background-color: #dff0d8;" + 
				"    border-color: #d6e9c6;" + 
				"}" + 
				"" + 
				".alert-danger, .alert-error {" + 
				"    color:#E9EDEF;" + 
				"	background-color:rgba(231,76,60,.88);" + 
				"	border-color: rgba(231,76,60,.88);" + 
				"}" + 
				"" + 
				".alert-dismissable, .alert-dismissible {" + 
				"    padding-right: 35px;" + 
				"}" + 
				".alert {" + 
				"    " + 
				"    margin-bottom: 20px;" + 
				"    border: 1px solid " + 
				"    " + 
				"    border-radius: 4px;" + 
				"}" + 
				"" + 
				"}" + 
				"	</style>	" + 
				"</head>" + 
				"<body>" + 
				"" + 
				"	<div class='alert alert-danger alert-dismissible' role='alert' align='center'>" + 
				"                    " + 
				"        <strong>hey User!</strong> your registration request is rejected because of one or more reason(s)!" + 
				"        <br>" + 
				"                    " + 
				"" + 
				"    </div>	" + 
				"" + 
				"        <h4 align='center'>please click below link to register again</h4>" + 
				
				"" + 
				"        <h5 align='center'><a href="+url+">"+url+"</a></h5>" + 
				"" + 
				"" + 
				"</body>" + 
				"</html>";
		
		  MimeMessage mimeMessage = mailSender.createMimeMessage(); MimeMessageHelper
		  helper = new MimeMessageHelper(mimeMessage, "UTF-8");
		 
		
		//mimeMessage.setContent(mailMsgBody, "text/html"); /** Use this or below line **/
		try {
			
			helper.setText(mailMsgBody, true);// Use this or above line.

			helper.setTo(mailTo);
			helper.setSubject(mailSubject);
			helper.setFrom(mailFrom);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		mailSender.send(mimeMessage);
		
	}

	public List<UserDto> getRejectedUsers(int pageId, int total) {

		List<UserBean> userBeanList = userDao.getRejectedUsers(pageId, total);
		
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		
		userBeanList.stream().forEach((a)->{
			UserDto userDto = null;
			
			  userDto = new UserDto(a.getUserId(), a.getFullName(), a.getDepartment(),
			  a.getEmailId(), a.getAccountStatus(), a.getCreated(), a.getUpdated());
			 
			userDtoList.add(userDto);
			
		});
		
		return userDtoList;
		
	}

	public List<UserDto> getActiveUsers(int pageId, int total) {

		List<UserBean> userBeanList = userDao.getActiveUsers(pageId, total);
		
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		
		userBeanList.stream().forEach((a)->{
			UserDto userDto = null;
			
			  userDto = new UserDto(a.getUserId(), a.getFullName(), a.getDepartment(),
			  a.getEmailId(), a.getAccountStatus(), a.getCreated(), a.getUpdated());
			 
			userDtoList.add(userDto);
			
		});
		
		return userDtoList;
		
	}

	public List<UserDto> getDeletedUsers(int pageId, int total) {

		List<UserBean> userBeanList = userDao.getDeletedUsers(pageId, total);
		
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		
		userBeanList.stream().forEach((a)->{
			UserDto userDto = null;
			
			  userDto = new UserDto(a.getUserId(), a.getFullName(), a.getDepartment(),
			  a.getEmailId(), a.getAccountStatus(), a.getCreated(), a.getUpdated());
			 
			userDtoList.add(userDto);
			
		});
		
		return userDtoList;
	
	}

	public int getTotalPagesForRejectedUsers(int total) {

		return userDao.getTotalPagesForRejectedUsers(total);
		
	}

	public int getTotalPagesForActiveUsers(int total) {

		return userDao.getTotalPagesForActiveUsers(total);
		
	}

	public int getTotalPagesForDeletedUsers(int total) {

		return userDao.getTotalPagesForDeletedUsers(total);
		
	}

	

	

		
	

	

	
	
	
}






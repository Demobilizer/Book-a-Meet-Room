package com.neoSoft.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.neoSoft.Dao.UserDao;
import com.neoSoft.Dao.UserRoomBookDao;
import com.neoSoft.dto.RoomDto;
import com.neoSoft.dto.UserDto;
import com.neoSoft.dto.UserRoomBookDto;
import com.neoSoft.misc.NotificationTypes;
import com.neoSoft.model.NotificationBean;
import com.neoSoft.model.RoomBean;
import com.neoSoft.model.UserBean;
import com.neoSoft.model.UserRoomBookBean;

@Service
@Transactional
public class UserRoomBookService {
	
	@Autowired
	UserRoomBookDao userRoomBookDao;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	UserDao userDao;
	
	
	private Date getRequiredDateFormatForDB(String date) throws ParseException {
		
		Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(date);
		return date1;
		
	}
	
	private String getRequiredDateForView(Date date) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");  
        String strDate = dateFormat.format(date);  
        return strDate;
		
	}
	
	private String getRequitedTimeConversionForView(int time) {
		
		String hours = String.valueOf(time/60);
		String mins = String.valueOf(time%60);
		
		String timeString = ""+hours+":"+mins;
		
		return timeString;
		
	}
	
	private int getRequitedTimeConversionForDB(String time) {
		
        String[] arrOfStr = time.split(":", 2);
        
        int tHours = Integer.parseInt(arrOfStr[0])*60;
        int tMins = Integer.parseInt(arrOfStr[1]);
        
        int timeAfterConversion = tHours + tMins;
        
        return timeAfterConversion;
        
	}
	

	public List<RoomDto> getAvailableRooms(UserRoomBookDto userRoomBookDto) throws ParseException {

		UserRoomBookBean userRoomBookBean = new UserRoomBookBean();
		
		String date = userRoomBookDto.getDate();
		
		Date date1 = this.getRequiredDateFormatForDB(date);
		
		// ---------------------------------  already made a method for following date conversion!  -----------------------------------
		
		/*
		 * Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(date);
		 * System.out.println("from service-------"+date+"\t is \t "+date1);
		 */
		
		/*
		 * String strDateFormat = "dd/MM/yyyy"; //Date format is Specified
		 * SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); //Date format
		 * string is passed as an argument to the Date format object
		 * 
		 * System.out.println(objSDF.format(date1));
		 */
	    
	    	
		
			userRoomBookBean.setDate(date1);
		
			String strSTime = userRoomBookDto.getStartTime();
			
			int startTime = this.getRequitedTimeConversionForDB(strSTime);
			
		/*
		 * String[] arrOfStr = strSTime.split(":", 2);
		 * 
		 * int sHours = Integer.parseInt(arrOfStr[0])*60; int sMins =
		 * Integer.parseInt(arrOfStr[1]);
		 * 
		 * int startTime = sHours + sMins;
		 */
	        
	        String strETime = userRoomBookDto.getEndTime();
	        
	        int endTime = this.getRequitedTimeConversionForDB(strETime);
	        
		/*
		 * String[] arrOfStr1 = strETime.split(":", 2);
		 * 
		 * int eHours = Integer.parseInt(arrOfStr1[0])*60; int eMins =
		 * Integer.parseInt(arrOfStr1[1]);
		 * 
		 * int endTime = eHours + eMins;
		 */
	        
	        System.out.println("Total Starting Time before dao--------"+startTime);
	        System.out.println("Total Ending Time before dao--------"+endTime);
	        
	        userRoomBookBean.setStartTime(startTime);
	        userRoomBookBean.setEndTime(endTime);
	        
	        List<Integer> availRoomIds = userRoomBookDao.getAvailableRooms(userRoomBookBean);
	        
	        List<RoomDto> availRoomList = new ArrayList<>();
	        
	        if(availRoomIds.size()==0) {
	        	
	        	System.out.println("no room avail and send this msg to controller!");
	        	
	        }
	        
	        else {
	        	
	        	System.out.println("from Service layer---"+availRoomIds+"get the details of these rooms!!");
	        	
	        	availRoomIds.stream().forEach(availableRoom -> {
	        	
	        		availRoomList.add(roomService.getSingleRoomDetails(availableRoom));
	        	
	        });
	        
	        }
	
	       return availRoomList;
		
	}


	public int bookThisRoom(UserRoomBookDto userRoomBookDto) throws ParseException {
		
		UserRoomBookBean userRoomBookBean = new UserRoomBookBean();
		NotificationBean notificationBean = new NotificationBean();
		
		int startTime = this.getRequitedTimeConversionForDB(userRoomBookDto.getStartTime());
		int endTime = this.getRequitedTimeConversionForDB(userRoomBookDto.getEndTime());
		Date date = this.getRequiredDateFormatForDB(userRoomBookDto.getDate());
		
		RoomBean roomBean = new RoomBean();
		roomBean.setRoomId(userRoomBookDto.getRoomDto().getRoomId());
		
		UserBean userBean = new UserBean();
		userBean.setUserId(userRoomBookDto.getUserDto().getUserId());
		
		//userRoomBookBean.setUrBookId(userRoomBookDto.getUrBookId());
		//System.out.println("ur-room-book-id from service layer----------"+userRoomBookDto.getUrBookId());
		
		userRoomBookBean.setStartTime(startTime);
		userRoomBookBean.setEndTime(endTime);
		userRoomBookBean.setDate(date);
		userRoomBookBean.setRoomBean(roomBean);
		userRoomBookBean.setUserBean(userBean);
		userRoomBookBean.setRequestStatus("PENDING");
		//userRoomBookBean.setStartTime(userRoomBookDto.getStartTime());
		
			String notificationContent = ""+userService.getUserById(userRoomBookDto.getUserDto().getUserId()).getFullName()+" have requested for a "+
			roomService.getSingleRoomDetails(userRoomBookDto.getRoomDto().getRoomId()).getRoomName();
		
			System.out.println("noti. content from service------"+notificationContent);
			System.out.println("room bean obj from service------"+userRoomBookBean.getRoomBean());
			
		  notificationBean.setGeneratedBy(userRoomBookBean.getUserBean());
		  
		  UserBean admin = userDao.getUserByUserName("admin");
		  notificationBean.setGeneratedFor(admin);
		  
		  notificationBean.setNotificationContent(notificationContent);
		  notificationBean.setNotificationType(NotificationTypes.ROOM_BOOK_REQUEST);
		  notificationBean.setReadNotification(false);
		  notificationBean.setDeleteNotification(false);
		  
		  return userRoomBookDao.bookThisRoom(userRoomBookBean, notificationBean);
		
	}

	public void sendMail(UserRoomBookDto userRoomBookDto) {
		
		RoomDto roomDto = new RoomDto();
		UserDto userDto = new UserDto();
		
		int roomId = userRoomBookDto.getRoomDto().getRoomId();
		roomDto = roomService.getSingleRoomDetails(roomId);
		
		int userId = userRoomBookDto.getUserDto().getUserId();
		userDto = userService.getUserById(userId);
		
		
		String mailFrom = "mehulkumar.kapadiya@neosofttech.com";
		String mailTo = "mehulkumar.kapadiya@neosofttech.com";
		String mailSubject = "New Request for Room Booking";
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
				"		<td colspan='2'><h3 align='center'>Room Book Request</h3></td>" + 
				"	</tr>" + 
				"	<tr>" + 
				"		<td class='col1'> Requested Room:</td><td>"+roomDto.getRoomName()+"</td>" + 
				"	</tr>" + 
				"	<tr>" + 
				"		<td class='col1'>Room Type:</td><td>"+roomDto.getRoomType()+"</td>" + 
				"	</tr>" + 
				"	<tr>" + 
				"		<td class='col1'>From:</td><td>"+userDto.getFullName()+"</td>" + 
				"	</tr>" + 
				"	<tr>" + 
				"	<tr>" + 
				"		<td class='col1'>Date:</td><td>"+userRoomBookDto.getDate()+"</td>" + 
				"	</tr>" + 
				"	<tr>" + 
				"		<td class='col1'>Time:</td><td>"+userRoomBookDto.getStartTime()+" to "+userRoomBookDto.getEndTime()+"</td>" + 
				"	</tr>	" +
				"<tr>" + 
				"	<td align='right'><a href='http://localhost:8080/Book_a_Meet_Room/manageBookingRequest?pageId=1'>Approve</a></td>"
				+ "<td><a href='http://localhost:8080/Book_a_Meet_Room/manageBookingRequest?pageId=1'>Reject</a></td>" + 
				"	</tr>"+
				"</table>" + 
				"</body>" + 
				"</html>";
		
		
		MimeMessage mimeMessage = mailSender.createMimeMessage(); MimeMessageHelper
		  helper = new MimeMessageHelper(mimeMessage, "UTF-8");
		 
		try {
			
			helper.setText(mailMsgBody, true);

			helper.setTo(mailTo);
			helper.setSubject(mailSubject);
			helper.setFrom(mailFrom);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		mailSender.send(mimeMessage);
		
		
	}

	public List<UserRoomBookDto> getPendingRequests(int pageId, int total) {
		
		List<UserRoomBookBean> pendingBookReqList = userRoomBookDao.getAllPendingRequests(pageId, total);
		
		List<UserRoomBookDto> userRoomBookDtoList = new ArrayList<UserRoomBookDto>();
		
		pendingBookReqList.stream().forEach((a)->{
				
			UserRoomBookDto userRoomBookDto = new UserRoomBookDto();
			RoomDto roomDto = new RoomDto();
			//roomDto.setRoomId(a.getRoomBean().getRoomId());
			
			int userId = a.getUserBean().getUserId();
			UserDto userDto = userService.getUserById(userId);
			
			int roomId = a.getRoomBean().getRoomId();
			roomDto = roomService.getSingleRoomDetails(roomId);
			 
			String dateForDto = this.getRequiredDateForView(a.getDate());
			String startTimeForDto = this.getRequitedTimeConversionForView(a.getStartTime());
			String endTimeForDto = this.getRequitedTimeConversionForView(a.getEndTime());
			
			userRoomBookDto.setDate(dateForDto);
			userRoomBookDto.setStartTime(startTimeForDto);
			userRoomBookDto.setEndTime(endTimeForDto);
			userRoomBookDto.setRequestStatus(a.getRequestStatus());
			userRoomBookDto.setUrBookId(a.getUrBookId());
			
			userRoomBookDto.setRoomDto(roomDto);
			userRoomBookDto.setUserDto(userDto);
			
			userRoomBookDtoList.add(userRoomBookDto);
			
		});
		
		return userRoomBookDtoList;
	}

	
	public int getTotalPagesForPendingRequests(int total) {
		
		return userRoomBookDao.getTotalPagesForPendingRequests(total);
		
	}

	public void approveRequest(int urBookId) {

		NotificationBean notificationBean = new NotificationBean();
		UserRoomBookBean userRoomBookBean = new UserRoomBookBean();
		userRoomBookBean.setUrBookId(urBookId);
		userRoomBookBean.setRequestStatus("APPROVED");
		
		userRoomBookBean.setApprovedBy("ADMIN");
		
		Calendar cal = Calendar.getInstance();
		  System.out.println("time from service-------"+cal.getTime());
		userRoomBookBean.setUpdated(cal.getTime());
		
		// make a notification
		
		UserRoomBookBean userRoomBookBean1 = userRoomBookDao.getUserRoomBookByURBId(urBookId);
		UserBean userBean = userDao.getSingleUserById(userRoomBookBean1.getUserBean().getUserId());
		
		UserBean admin = userDao.getUserByUserName("admin");
		
		int roomId = userRoomBookBean1.getRoomBean().getRoomId();
		RoomDto roomDto = roomService.getSingleRoomDetails(roomId);
		
		String notificationContent = "your request to book a "+ roomDto.getRoomName() +" is approved";
		
		notificationBean.setNotificationType(NotificationTypes.ROOM_BOOK_RESPONSE);
		notificationBean.setDeleteNotification(false);
		notificationBean.setReadNotification(false);
		notificationBean.setGeneratedBy(admin);
		notificationBean.setGeneratedFor(userBean);
		notificationBean.setNotificationContent(notificationContent);
		
		userRoomBookDao.getRequestApproved(userRoomBookBean, notificationBean);
		
	}

	public void rejectRequest(int urBookId) {

		NotificationBean notificationBean = new NotificationBean();
		
		UserRoomBookBean userRoomBookBean = new UserRoomBookBean();
		userRoomBookBean.setUrBookId(urBookId);
		userRoomBookBean.setRequestStatus("REJECTED");
		
		userRoomBookBean.setApprovedBy("ADMIN");
		
		Calendar cal = Calendar.getInstance();
		  System.out.println("time from service-------"+cal.getTime());
		userRoomBookBean.setUpdated(cal.getTime());
		
		// make a notification here..
		
		UserRoomBookBean userRoomBookBean1 = userRoomBookDao.getUserRoomBookByURBId(urBookId);
		UserBean userBean = userDao.getSingleUserById(userRoomBookBean1.getUserBean().getUserId());
		
		UserBean admin = userDao.getUserByUserName("admin");
		
		int roomId = userRoomBookBean1.getRoomBean().getRoomId();
		RoomDto roomDto = roomService.getSingleRoomDetails(roomId);
		
		String notificationContent = "your request to book a "+ roomDto.getRoomName() +" is rejected";
		
		notificationBean.setNotificationType(NotificationTypes.ROOM_BOOK_RESPONSE);
		notificationBean.setDeleteNotification(false);
		notificationBean.setReadNotification(false);
		notificationBean.setGeneratedBy(admin);
		notificationBean.setGeneratedFor(userBean);
		notificationBean.setNotificationContent(notificationContent);
		
		userRoomBookDao.getRequestRejected(userRoomBookBean, notificationBean);
		
	}

	public List<UserRoomBookDto> getBookedRoomsByUserId(int userId) {
		
		List<Integer> getBookingApprovedOrPendingRoomIds = 
				userRoomBookDao.getBookingApprovedOrPendingRoomsByUserId(userId);
        
        List<UserRoomBookDto> bookingApprovedOrPendingRoomList = new ArrayList<>();
        
        if(getBookingApprovedOrPendingRoomIds.size()==0) {
        	
        	System.out.println("from service layer ------------ no request avail and send this msg to controller!");
        	
        }
        
        else {
        	
        	
        	getBookingApprovedOrPendingRoomIds.stream().forEach(available -> {
        	
        		UserRoomBookBean userRoomBookBean = userRoomBookDao.getUserRoomBookByURBId(available);
        		
        		UserRoomBookDto userRoomBookDto = new UserRoomBookDto();
        		
        		userRoomBookDto.setUrBookId(userRoomBookBean.getUrBookId());
        		userRoomBookDto.setApprovedBy(userRoomBookBean.getApprovedBy());
        		userRoomBookDto.setCreated(userRoomBookBean.getCreated());
        		userRoomBookDto.setDate(this.getRequiredDateForView(userRoomBookBean.getDate()));
        		userRoomBookDto.setEndTime(this.getRequitedTimeConversionForView(userRoomBookBean.getEndTime()));
        		userRoomBookDto.setStartTime(this.getRequitedTimeConversionForView(userRoomBookBean.getStartTime()));
        		userRoomBookDto.setRequestStatus(userRoomBookBean.getRequestStatus());
        		userRoomBookDto.setUpdated(userRoomBookBean.getUpdated());
        		
        		userRoomBookDto.setRoomDto(roomService.getSingleRoomDetails(userRoomBookBean.getRoomBean().getRoomId()));
        		
        		bookingApprovedOrPendingRoomList.add(userRoomBookDto); 
        		
        });
        
        }

       return bookingApprovedOrPendingRoomList;
		
	}

	public List<UserRoomBookDto> getRejectedRoomsByUserId(int userId) {

		List<Integer> getRejectedRoomIds = 
				userRoomBookDao.getRejectedRoomsByUserId(userId);
        
        List<UserRoomBookDto> rejectedRoomList = new ArrayList<>();
        
        if(getRejectedRoomIds.size()==0) {
        	
        	System.out.println("from service layer ------------ no request avail and send this msg to controller!");
        	
        }
        
        else {
        	
        	getRejectedRoomIds.stream().forEach(available -> {
        	
        		UserRoomBookBean userRoomBookBean = userRoomBookDao.getUserRoomBookByURBId(available);
        		
        		UserRoomBookDto userRoomBookDto = new UserRoomBookDto();
        		
        		userRoomBookDto.setUrBookId(userRoomBookBean.getUrBookId());
        		userRoomBookDto.setApprovedBy(userRoomBookBean.getApprovedBy());
        		userRoomBookDto.setCreated(userRoomBookBean.getCreated());
        		userRoomBookDto.setDate(this.getRequiredDateForView(userRoomBookBean.getDate()));
        		userRoomBookDto.setEndTime(this.getRequitedTimeConversionForView(userRoomBookBean.getEndTime()));
        		userRoomBookDto.setStartTime(this.getRequitedTimeConversionForView(userRoomBookBean.getStartTime()));
        		userRoomBookDto.setRequestStatus(userRoomBookBean.getRequestStatus());
        		userRoomBookDto.setUpdated(userRoomBookBean.getUpdated());
        		
        		System.out.println("urb-dto-id-------------"+userRoomBookDto.getUrBookId());
        		
        		userRoomBookDto.setRoomDto(roomService.getSingleRoomDetails(userRoomBookBean.getRoomBean().getRoomId()));
        		
        		rejectedRoomList.add(userRoomBookDto); 
        		
        });
        
        }

       return rejectedRoomList;

	}

	public List<UserRoomBookDto> getBookingsByUserId(int userId) {
		
		List<Integer> getAllBookedIds = 
				userRoomBookDao.getAllBookedRoomsByUserId(userId);
        
        List<UserRoomBookDto> allBookedRoomList = new ArrayList<>();
        
        if(getAllBookedIds.size()==0) {
        	
        	System.out.println("from service layer ------------ no request avail and send this msg to controller!");
        	
        }
        
        else {
        	
        	getAllBookedIds.stream().forEach(available -> {
        	
        		UserRoomBookBean userRoomBookBean = userRoomBookDao.getUserRoomBookByURBId(available);
        		
        		UserRoomBookDto userRoomBookDto = new UserRoomBookDto();
        		
        		userRoomBookDto.setUrBookId(userRoomBookBean.getUrBookId());
        		userRoomBookDto.setApprovedBy(userRoomBookBean.getApprovedBy());
        		userRoomBookDto.setCreated(userRoomBookBean.getCreated());
        		userRoomBookDto.setDate(this.getRequiredDateForView(userRoomBookBean.getDate()));
        		userRoomBookDto.setEndTime(this.getRequitedTimeConversionForView(userRoomBookBean.getEndTime()));
        		userRoomBookDto.setStartTime(this.getRequitedTimeConversionForView(userRoomBookBean.getStartTime()));
        		userRoomBookDto.setRequestStatus(userRoomBookBean.getRequestStatus());
        		userRoomBookDto.setUpdated(userRoomBookBean.getUpdated());
        		
        		System.out.println("urb-dto-id-------------"+userRoomBookDto.getUrBookId());
        		
        		userRoomBookDto.setRoomDto(roomService.getSingleRoomDetails(userRoomBookBean.getRoomBean().getRoomId()));
        		
        		allBookedRoomList.add(userRoomBookDto); 
        		
        });
        
        }

       return allBookedRoomList;
				
	}

	public void cancelRequest(int urBookId) {

		UserRoomBookBean userRoomBookBean = new UserRoomBookBean();
		userRoomBookBean.setUrBookId(urBookId);
		userRoomBookBean.setRequestStatus("CANCELLED");
		
		Calendar cal = Calendar.getInstance();
		  System.out.println("time from service-------"+cal.getTime());
		userRoomBookBean.setUpdated(cal.getTime());
		
		userRoomBookDao.cancelRequest(userRoomBookBean);
		
	}

	public List<UserRoomBookBean> getLastTwoMonthsActivitiesByUserId(int userId) {
		
		return userRoomBookDao.getLastTwoMonthsActivitiesByUserId(userId);
		
	}
	
	

}

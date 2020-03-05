package com.neoSoft.Dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neoSoft.model.FeedbacksBean;
import com.neoSoft.model.NotificationBean;
import com.neoSoft.model.PasswordResetToken;
import com.neoSoft.model.RequestManagementBean;
import com.neoSoft.model.UserBean;
import com.neoSoft.model.UserRoomBookBean;

@Repository
public class UserDao {
	
	@Autowired
	SessionFactory mysessionFactory;

	public int createrUser(UserBean userBean) {
		
		return (Integer) mysessionFactory.getCurrentSession().save(userBean);
		
	}

	public List<UserBean> getAllInactiveUsers(int pageId, int total) {

		Query selectQuery = mysessionFactory.getCurrentSession().createQuery("select u from UserBean u where u.accountStatus='inactive'");
	    selectQuery.setFirstResult(pageId);
	    selectQuery.setMaxResults(total);
	    List<UserBean> listOfUsers = selectQuery.getResultList();
		
		return listOfUsers;
	}
	
public int getTotalNoOfPagesForInactiveUsers(int total) {
		
		long recordCount = (Long) mysessionFactory.getCurrentSession()
				.createQuery("select count(*) from UserBean u where u.accountStatus='inactive'").uniqueResult();
		//System.out.println("total count="+recordCount);
		
		int pageCount = ((Double)Math.ceil(recordCount/total)).intValue();
		//System.out.println("total Pages="+pageCount);
		
		return pageCount;
		
	}
	
	

	public List<UserBean> getAllUsers(int pageId, int total) {

		Query selectQuery = mysessionFactory.getCurrentSession().createQuery("from UserBean");
	    selectQuery.setFirstResult(pageId);
	    selectQuery.setMaxResults(total);
	    List<UserBean> listOfUsers = selectQuery.getResultList();
		
		return listOfUsers;
	}
	
	public int getTotalNoOfPages(int total) {
		
		long recordCount = (Long) mysessionFactory.getCurrentSession().createQuery("select count(*) from UserBean u").uniqueResult();
		//System.out.println("total count="+recordCount);
		
		int pageCount = ((Double)Math.ceil(recordCount/total)).intValue();
		//System.out.println("total Pages="+pageCount);
		
		//return pageCount;
		if((recordCount%total)!=0) {
			
			return pageCount+1;
			
		}
		else {
			
			return pageCount;
			
		}
		
	}

	public void approveUser(UserBean userBean) {

		//mysessionFactory.getCurrentSession().saveOrUpdate(userBean);
		
		/*
		 * Query query = mysessionFactory.getCurrentSession().
		 * createQuery("update UserBean set accountStatus =:"+userBean.getAccountStatus(
		 * ) + "userRole=:"+userBean.getUserRole() +
		 * "where userId=:"+userBean.getUserId());
		 * 
		 * query.executeUpdate();
		 */
		
		String hql = "update UserBean set accountStatus = :accountStatus, "+
				"userRole = :userRole "+
				"where userId = :userId";
				Query query = mysessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("accountStatus", userBean.getAccountStatus());
				query.setParameter("userRole", userBean.getUserRole());
				query.setParameter("userId", userBean.getUserId());
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " + result);
		
	}

	public UserBean getSingleUserById(int id) {

		return mysessionFactory.getCurrentSession().createQuery("select u from UserBean u where u.userId="+id, UserBean.class).getSingleResult();
		
	}

	public void updateUser(UserBean userBean) {
		
		String hql = "update UserBean set fullName = :fullName, "+
				"userRole = :userRole, "+
				"department = :department, "+
				"contactNo = :contactNo "+
				"where userId = :userId";
				Query query = mysessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("fullName", userBean.getFullName());
				query.setParameter("userRole", userBean.getUserRole());
				query.setParameter("department", userBean.getDepartment());
				query.setParameter("contactNo", userBean.getContactNo());
				query.setParameter("userId", userBean.getUserId());
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " +result);
		
	}

	public void rejectUser(UserBean userBean) {

		String hql = "update UserBean set accountStatus = :accountStatus "+
				"where userId = :userId";
				Query query = mysessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("accountStatus", userBean.getAccountStatus());
				query.setParameter("userId", userBean.getUserId());
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " + result);
		
	}

	public void deleteUser(UserBean userBean) {

		String hql = "update UserBean set accountStatus = :accountStatus "+
				"where userId = :userId";
				Query query = mysessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("accountStatus", userBean.getAccountStatus());
				query.setParameter("userId", userBean.getUserId());
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " + result);
		
	}

	@Transactional
	public UserBean getUserByUserName(String userName) {

		return mysessionFactory.getCurrentSession().createQuery("select u from UserBean u where u.userName='"+userName+"'", UserBean.class).getSingleResult();
		
	}

	public void saveimage(int userId, String filePath) {

		String photoPath = filePath;
		
		String hql = "update UserBean set photoPath = :photoPath "+
				"where userId = :userId";
				Query query = mysessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("photoPath", photoPath);
				query.setParameter("userId", userId);
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " + result);
		
	}

	public void updateUserPersonalDetails(UserBean userBean) {

		String hql = "update UserBean set fullName = :fullName, "+
				"userName = :userName, "+
				"contactNo = :contactNo "+
				"where userId = :userId";
				Query query = mysessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("fullName", userBean.getFullName());
				query.setParameter("userName", userBean.getUserName());
				query.setParameter("contactNo", userBean.getContactNo());
				query.setParameter("userId", userBean.getUserId());
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " +result);
				
	}

	public String getPasswordByUserId(int id) {

		UserBean userBean = mysessionFactory.getCurrentSession().createQuery("select u from UserBean u where u.userId="+id, UserBean.class).getSingleResult();
		
		String passwordInDb = userBean.getPassword();
		
		return passwordInDb;
		
	}

	@Transactional
	public void updateNewPassword(String newPass, UserBean userBean) {

		userBean.setPassword(newPass);
		
		String hql = "update UserBean set password = :password "+
				"where userId = :userId";
				Query query = mysessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("password", userBean.getPassword());
				query.setParameter("userId", userBean.getUserId());
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " + result);
		
	}

	public UserBean getUserByEmail(String userEmail) {
		
		try 
		{
				return mysessionFactory.getCurrentSession().createQuery("select u from UserBean u where u.emailId='"+userEmail+"'", UserBean.class).getSingleResult();
		} 
		catch (NoResultException e) {
				return null;
		}
		
	}

	public void saveToken(PasswordResetToken myToken) {

		mysessionFactory.getCurrentSession().save(myToken);
		
	}

	@Transactional
	public PasswordResetToken getTokenByToken(String token) {

		try 
		{
				return mysessionFactory.getCurrentSession().createQuery("select prt from PasswordResetToken prt where prt.token='"+token+"' and prt.status='ENABLE'", PasswordResetToken.class).getSingleResult();
		} 
		catch (NoResultException e) {
				return null;
		}
		
	}

	public void disableTokenStatus(String token) {

		PasswordResetToken ptr = this.getTokenByToken(token);
		
		ptr.setStatus("DISABLE");
		//Long id = ptr.getId();
				
		String hql = "update PasswordResetToken set status = :status "+
				"where id = :id";
				Query query = mysessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("status", ptr.getStatus());
				query.setParameter("id", ptr.getId());
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " + result);
		
	}

	public void sendFeedBack(FeedbacksBean feedbacksBean, NotificationBean notificationBean) {

			mysessionFactory.getCurrentSession().save(feedbacksBean);
			mysessionFactory.getCurrentSession().save(notificationBean);
		
	}

	public List<FeedbacksBean> getAllFeedbacks(int pageId, int total) {

		Query selectQuery = mysessionFactory.getCurrentSession().createQuery("from FeedbacksBean");
	    selectQuery.setFirstResult(pageId);
	    selectQuery.setMaxResults(total);
	    List<FeedbacksBean> listOfFeedbacks = selectQuery.getResultList();
		
		return listOfFeedbacks;
		
	}

	public int getTotalNoOfPagesForAllFeedbacks(int total) {

		long recordCount = (Long) mysessionFactory.getCurrentSession()
				.createQuery("select count(*) from FeedbacksBean fb").uniqueResult();
		
		int pageCount = ((Double)Math.ceil(recordCount/total)).intValue();
		
		if((recordCount%total)!=0) {
			
			return pageCount+1;
			
		}
		else {
			
			return pageCount;
			
		}
		
	}
	
	public List<FeedbacksBean> getAllReadFeedbacks(int pageId, int total) {

		Query selectQuery = mysessionFactory.getCurrentSession()
				.createQuery("select fb from FeedbacksBean fb where fb.readStatus=1");
	    selectQuery.setFirstResult(pageId);
	    selectQuery.setMaxResults(total);
	    List<FeedbacksBean> listOfFeedbacks = selectQuery.getResultList();
		
		return listOfFeedbacks;
		
	}

	public int getTotalPagesForReadFeedbacks(int total) {

		long recordCount = (Long) mysessionFactory.getCurrentSession()
				.createQuery("select count(*) from FeedbacksBean fb where fb.readStatus=1").uniqueResult();
		
		int pageCount = ((Double)Math.ceil(recordCount/total)).intValue();
		
		if((recordCount%total)!=0) {
			
			return pageCount+1;
			
		}
		else {
			
			return pageCount;
			
		}
		
	}

	public List<FeedbacksBean> getAllUnreadFeedbacks(int pageId, int total) {
		
		Query selectQuery = mysessionFactory.getCurrentSession()
				.createQuery("select fb from FeedbacksBean fb where fb.readStatus=0");
	    selectQuery.setFirstResult(pageId);
	    selectQuery.setMaxResults(total);
	    List<FeedbacksBean> listOfFeedbacks = selectQuery.getResultList();
		
		return listOfFeedbacks;
		
	}

	public int getTotalPagesForUnreadFeedbacks(int total) {

		long recordCount = (Long) mysessionFactory.getCurrentSession()
				.createQuery("select count(*) from FeedbacksBean fb where fb.readStatus=0").uniqueResult();
		
		int pageCount = ((Double)Math.ceil(recordCount/total)).intValue();
		
		if((recordCount%total)!=0) {
			
			return pageCount+1;
			
		}
		else {
			
			return pageCount;
			
		}
		
	}
	

	public FeedbacksBean getSingleFeedbackById(int id) {

		return mysessionFactory.getCurrentSession().createQuery("select fb from FeedbacksBean fb where fb.id="+id, FeedbacksBean.class).getSingleResult();
		
	}

	public void updateReadStatus(FeedbacksBean feedbackBean) {
		
		String hql = "update FeedbacksBean set readStatus = :readStatus "+
				"where id = :id";
				Query query = mysessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("readStatus", feedbackBean.isReadStatus());
				query.setParameter("id", feedbackBean.getId());
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " + result);
		
	}

	public UserBean checkIfUserNameAvail(String uName) {

		try {
			
			return mysessionFactory.getCurrentSession()
					.createQuery("select u from UserBean u where u.userName='" + uName + "'", UserBean.class)
					.getSingleResult();
		}

		catch (NoResultException e) {
			return null;
		}
		
	}

	public void setNotificationForUserRegistration(NotificationBean notificationBean) {

			mysessionFactory.getCurrentSession().save(notificationBean);
		
	}

	@Transactional
	public List<NotificationBean> getNotificationsByUserId(int userId) {
		
		return mysessionFactory.getCurrentSession()
				.createQuery("select nbl from NotificationBean nbl where nbl.generatedFor="+userId+
						" and nbl.deleteNotification ='false'", NotificationBean.class).getResultList();
		
	}

	@Transactional
	public NotificationBean getSingleNotificationById(int id) {

		return mysessionFactory.getCurrentSession()
				.createQuery("select nbl from NotificationBean nbl where nbl.notificationId="+id, NotificationBean.class).getSingleResult();
		
	}

	@Transactional
	public void updateNotificationStatus(NotificationBean notificationBean) {

		String hql = "update NotificationBean set readNotification = :readNotification "+
				"where notificationId = :notificationId";
				Query query = mysessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("readNotification", notificationBean.isReadNotification());
				query.setParameter("notificationId", notificationBean.getNotificationId());
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " + result);
		
	}

	@Transactional
	public void deleteNotificationStatus(NotificationBean notificationBean) {

		String hql = "update NotificationBean set deleteNotification = :deleteNotification "+
				"where notificationId = :notificationId";
				Query query = mysessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("deleteNotification", notificationBean.isDeleteNotification());
				query.setParameter("notificationId", notificationBean.getNotificationId());
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " + result);
		
	}

	@Transactional
	public int getAllPendingBookReqCount() {

		long recordCount = (Long) mysessionFactory.getCurrentSession().
				createQuery("select count(*) from UserRoomBookBean urb where urb.requestStatus='PENDING'").uniqueResult();
		
		return Math.toIntExact(recordCount);
		
	}

	@Transactional
	public int getAllInactiveUsersCount() {

		long recordCount = (Long) mysessionFactory.
				getCurrentSession().createQuery("select count(*) from UserBean u where u.accountStatus='inactive'").uniqueResult();
		
		return Math.toIntExact(recordCount);
		
	}

	@Transactional
	public int getAllUnreadFeedbacksCount() {
		
		long recordCount = (Long) mysessionFactory.getCurrentSession().
				createQuery("select count(*) from FeedbacksBean fb where fb.readStatus='false'").uniqueResult();
		
		return Math.toIntExact(recordCount);
		
	}

	public UserBean checkIfEmailAvail(String uEmail) {

		try {
			
			return mysessionFactory.getCurrentSession()
					.createQuery("select u from UserBean u where u.emailId='" + uEmail + "' and u.accountStatus!='rejected'", UserBean.class)
					.getSingleResult();
		}

		catch (NoResultException e) {
			return null;
		}

	}

	public UserBean checkIfContactNoAvail(String uContact) {

		try {
			
			return mysessionFactory.getCurrentSession()
					.createQuery("select u from UserBean u where u.contactNo='" + uContact + "'", UserBean.class)
					.getSingleResult();
		}

		catch (NoResultException e) {
			return null;
		}
		
	}

	public List<UserBean> getRejectedUsers(int pageId, int total) {

		Query selectQuery = mysessionFactory.getCurrentSession()
				.createQuery("select u from UserBean u where u.accountStatus = 'rejected'");
	    selectQuery.setFirstResult(pageId);
	    selectQuery.setMaxResults(total);
	    List<UserBean> listOfUsers = selectQuery.getResultList();
		
		return listOfUsers;
		
	}
	
	public int getTotalPagesForRejectedUsers(int total) {

		long recordCount = (Long) mysessionFactory.getCurrentSession()
				.createQuery("select count(*) from UserBean u where u.accountStatus='rejected'").uniqueResult();
		
		int pageCount = ((Double)Math.ceil(recordCount/total)).intValue();
		
		if((recordCount%total)!=0) {
			
			return pageCount+1;
			
		}
		else {
			
			return pageCount;
			
		}
		
	}
	

	public List<UserBean> getActiveUsers(int pageId, int total) {

		Query selectQuery = mysessionFactory.getCurrentSession()
				.createQuery("select u from UserBean u where u.accountStatus = 'active'");
	    selectQuery.setFirstResult(pageId);
	    selectQuery.setMaxResults(total);
	    List<UserBean> listOfUsers = selectQuery.getResultList();
		
		return listOfUsers;
		
	}
	
	public int getTotalPagesForActiveUsers(int total) {

		long recordCount = (Long) mysessionFactory.getCurrentSession()
				.createQuery("select count(*) from UserBean u where u.accountStatus='active'").uniqueResult();
		
		int pageCount = ((Double)Math.ceil(recordCount/total)).intValue();
		
		if((recordCount%total)!=0) {
			
			return pageCount+1;
			
		}
		else {
			
			return pageCount;
			
		}
		
		
	}
	

	public List<UserBean> getDeletedUsers(int pageId, int total) {

		Query selectQuery = mysessionFactory.getCurrentSession()
				.createQuery("select u from UserBean u where u.accountStatus = 'deleted'");
	    selectQuery.setFirstResult(pageId);
	    selectQuery.setMaxResults(total);
	    List<UserBean> listOfUsers = selectQuery.getResultList();
		
		return listOfUsers;
		
	}

	public int getTotalPagesForDeletedUsers(int total) {

		long recordCount = (Long) mysessionFactory.getCurrentSession()
				.createQuery("select count(*) from UserBean u where u.accountStatus='deleted'").uniqueResult();
		
		int pageCount = ((Double)Math.ceil(recordCount/total)).intValue();
		
		if((recordCount%total)!=0) {
			
			return pageCount+1;
			
		}
		else {
			
			return pageCount;
			
		}
		
	}

	


	
	
	
		
	
	
}

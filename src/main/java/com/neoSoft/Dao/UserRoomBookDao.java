package com.neoSoft.Dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.PreUpdate;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neoSoft.model.NotificationBean;
import com.neoSoft.model.RoomBean;
import com.neoSoft.model.UserBean;
import com.neoSoft.model.UserRoomBookBean;

@Repository
public class UserRoomBookDao {

	@Autowired
	SessionFactory mySessionFactory;

	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}

	
	
public List<Integer> getAvailableRoomIds(UserRoomBookBean userRoomBookBean) {
		
	/*
	 * Query selectQuery = mySessionFactory.getCurrentSession().
	 * createQuery("select distinct roomId from user_room_book where roomId not in 
		(select  roomId from user_room_book where not (start_time>=900 or end_time<=840))"
	 * );
	 * 
	 * selectQuery.getResultList();
	 * 
	 * System.out.println("from dao:------------"+selectQuery.getResultList());
	 */
		
		int startTime = userRoomBookBean.getStartTime();
		int endTime = userRoomBookBean.getEndTime();
		Date date = userRoomBookBean.getDate();
		
		Session session= mySessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<List> query = builder.createQuery(List.class);
		
		Root<UserRoomBookBean> userRoomBookBean1 = query.from(UserRoomBookBean.class);
		//Root<RoomBean> roomBean1 = query.from(RoomBean.class);
		
		query.select(userRoomBookBean1.get("roomBean").get("roomId"));
		//query.select(roomBean1.get("roomId"));
		
		Predicate whereNew = builder.greaterThanOrEqualTo(userRoomBookBean1.get("startTime"), endTime);
		whereNew = builder.or(whereNew, builder.lessThanOrEqualTo(userRoomBookBean1.get("endTime"), startTime));
		whereNew = builder.not(whereNew);
		whereNew = builder.and(whereNew, builder.equal(userRoomBookBean1.get("date"), date));
		
		query = query.where(whereNew).distinct(true);
		
		Query queryObj=session.createQuery(query);
		
		System.out.println("Booked rooms during this time slot-----"+queryObj.getResultList());
		
		CriteriaQuery<List> query1 = builder.createQuery(List.class);
		//Root<UserRoomBookBean> userRoomBookBean2 = query1.from(UserRoomBookBean.class);
		Root<RoomBean> roomBean2 = query1.from(RoomBean.class);
		query1.select(roomBean2.get("roomId"));
		
		if(queryObj.getResultList().size()==0) {
			
			// if its 0, that means first query gives no results, that means all rooms are available! so, select all...
			
			query1.select(roomBean2.get("roomId")).distinct(true);
			
		}
		
		else {
	
			Predicate where1 = builder.not(roomBean2.get("roomId").in(queryObj.getResultList()));
			query1 = query1.where(where1).distinct(true);
		
		}
		
		Query queryObj1=session.createQuery(query1);
		
		// if condition to check second query, if queryObj1 is null !!!!!
		
		if(queryObj1.getResultList().size()==0) {
			System.out.println("Avail Rooms:-------No room is free-----"+queryObj1.getResultList());
		}
		else {
			System.out.println("Avail rooms during this time slot:-------"+queryObj1.getResultList());
		}
		
		
		return queryObj1.getResultList();
		
		
	}
	

	
	
	public List<Integer> getAvailableRooms(UserRoomBookBean userRoomBookBean){
		
		return this.getAvailableRoomIds(userRoomBookBean);
		
	}



	public int bookThisRoom(UserRoomBookBean userRoomBookBean, NotificationBean notificationBean) {

		int i = (Integer) mySessionFactory.getCurrentSession().save(userRoomBookBean);
		
		mySessionFactory.getCurrentSession().save(notificationBean);
		
		return i;
		
	}



	public List<UserRoomBookBean> getAllPendingRequests(int pageId, int total) {
		
		Query selectQuery = mySessionFactory.getCurrentSession().createQuery("select u from UserRoomBookBean u where u.requestStatus='PENDING'");
	    selectQuery.setFirstResult(pageId);
	    selectQuery.setMaxResults(total);
	    List<UserRoomBookBean> listOfPendingReq = selectQuery.getResultList();
		
		return listOfPendingReq;
		
	}



	public int getTotalPagesForPendingRequests(int total) {
		
		long recordCount = (Long) mySessionFactory.getCurrentSession().createQuery("select count(*) from UserRoomBookBean urb where urb.requestStatus='PENDING'").uniqueResult();
		
		int pageCount = ((Double)Math.ceil(recordCount/total)).intValue();
		
		return pageCount;
		
	}



	
	public void getRequestApproved(UserRoomBookBean userRoomBookBean, NotificationBean notificationBean) {

		String hql = "update UserRoomBookBean set requestStatus = :requestStatus, "+
				"updated = :updated, "+
				"approvedBy = :approvedBy "+
				"where urBookId = :urBookId";
				Query query = mySessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("requestStatus", userRoomBookBean.getRequestStatus());
				query.setParameter("updated", userRoomBookBean.getUpdated());
				query.setParameter("approvedBy", userRoomBookBean.getApprovedBy());
				query.setParameter("urBookId", userRoomBookBean.getUrBookId());
				
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " + result);		
		
				mySessionFactory.getCurrentSession().save(notificationBean);
				
	}



	public void getRequestRejected(UserRoomBookBean userRoomBookBean, NotificationBean notificationBean) {

		String hql = "update UserRoomBookBean set requestStatus = :requestStatus, "+
				"updated = :updated, "+
				"approvedBy = :approvedBy "+
				"where urBookId = :urBookId";
				Query query = mySessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("requestStatus", userRoomBookBean.getRequestStatus());
				query.setParameter("updated", userRoomBookBean.getUpdated());
				query.setParameter("approvedBy", userRoomBookBean.getApprovedBy());
				query.setParameter("urBookId", userRoomBookBean.getUrBookId());
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " + result);
		
				mySessionFactory.getCurrentSession().save(notificationBean);
				
	}



	public List<Integer> getBookingApprovedOrPendingRoomsByUserId(int userId) {

		Session session= mySessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<List> query = builder.createQuery(List.class);
		
		Root<UserRoomBookBean> userRoomBookBean1 = query.from(UserRoomBookBean.class);
		
		query.select(userRoomBookBean1.get("urBookId"));
		
		Predicate whereNew = builder.equal(userRoomBookBean1.get("requestStatus"), "PENDING");
		whereNew = builder.or(whereNew, builder.equal(userRoomBookBean1.get("requestStatus"), "APPROVED"));
		whereNew = builder.and(whereNew, builder.equal(userRoomBookBean1.get("userBean").get("userId"), userId));
		
		query = query.where(whereNew);
		
		Query queryObj=session.createQuery(query);
		
		System.out.println("Booked or pending rooms during this time slot-----"+queryObj.getResultList());
		
		return queryObj.getResultList();
		
	}
	
	public UserRoomBookBean getUserRoomBookByURBId(int urBookId) {
		
		return mySessionFactory.getCurrentSession().createQuery(
				"select urb from UserRoomBookBean urb where urb.urBookId="+urBookId, UserRoomBookBean.class).getSingleResult();
		
	}


	public List<Integer> getRejectedRoomsByUserId(int userId) {
		
		Session session= mySessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<List> query = builder.createQuery(List.class);
		
		Root<UserRoomBookBean> userRoomBookBean1 = query.from(UserRoomBookBean.class);
		
		query.select(userRoomBookBean1.get("urBookId"));
		
		Predicate whereNew = builder.equal(userRoomBookBean1.get("requestStatus"), "REJECTED");
		whereNew = builder.and(whereNew, builder.equal(userRoomBookBean1.get("userBean").get("userId"), userId));
		
		query = query.where(whereNew);
		
		Query queryObj=session.createQuery(query);
		
		System.out.println("Booked or pending rooms during this time slot-----"+queryObj.getResultList());
		
		return queryObj.getResultList();
		
	}



	public List<Integer> getAllBookedRoomsByUserId(int userId) {

		Session session= mySessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<List> query = builder.createQuery(List.class);
		
		Root<UserRoomBookBean> userRoomBookBean1 = query.from(UserRoomBookBean.class);
		
		query.select(userRoomBookBean1.get("urBookId"));
		
		Predicate whereNew = builder.equal(userRoomBookBean1.get("userBean").get("userId"), userId);
		
		query = query.where(whereNew);
		
		Query queryObj=session.createQuery(query);
		
		System.out.println("Booked or pending rooms during this time slot-----"+queryObj.getResultList());
		
		return queryObj.getResultList();
	
	}

	public void cancelRequest(UserRoomBookBean userRoomBookBean) {

		String hql = "update UserRoomBookBean set requestStatus = :requestStatus, "+
				"updated = :updated "+
				"where urBookId = :urBookId";
				Query query = mySessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("requestStatus", userRoomBookBean.getRequestStatus());
				query.setParameter("updated", userRoomBookBean.getUpdated());
				query.setParameter("urBookId", userRoomBookBean.getUrBookId());
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " + result);
		
	}



	public List<UserRoomBookBean> getLastTwoMonthsActivitiesByUserId(int userId) {

		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, 1);
		Date endDate=cal.getTime();
		
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -1);
		Date startDate = cal.getTime();
		
		return mySessionFactory.getCurrentSession().createQuery(
				"select urb from UserRoomBookBean urb where urb.userBean="+userId+
				" and urb.created BETWEEN '"+dateFormat.format(startDate)+"' AND '"+dateFormat.format(endDate)+"'", UserRoomBookBean.class).getResultList();
		
	}
	
	
	
	
}

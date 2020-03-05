package com.neoSoft.Dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neoSoft.model.NotificationBean;
import com.neoSoft.model.RequestManagementBean;
import com.neoSoft.model.UserBean;


@Repository
public class RequestManagementDao {

	@Autowired
	SessionFactory mysessionFactory;
	
	public void setMysessionFactory(SessionFactory mysessionFactory) {
		this.mysessionFactory = mysessionFactory;
	}
	
	

	public UserBean getTLForSameDepatment(String department) {

		try {
			
			return mysessionFactory.getCurrentSession()
					.createQuery("select u from UserBean u where u.department='" + department + "' and u.userRole='ROLE_T'", UserBean.class)
					.getSingleResult();
		}

		catch (NoResultException e) {
			return null;
		}
		
	}

	public UserBean getPMForSameDepatment(String department) {

		try {
			
			return mysessionFactory.getCurrentSession()
					.createQuery("select u from UserBean u where u.department='" + department + "' and u.userRole='ROLE_P'", UserBean.class)
					.getSingleResult();
		}

		catch (NoResultException e) {
			return null;
		}
		
	}

	public void saveRequest(RequestManagementBean requestManagementBean, NotificationBean notificationBean) {

		mysessionFactory.getCurrentSession().save(requestManagementBean);
		mysessionFactory.getCurrentSession().save(notificationBean);
		
	}

	public RequestManagementBean checkForAlreadyRequestedMailId(int userId) {
		
		try {
			
			return mysessionFactory.getCurrentSession()
					.createQuery("select rm from RequestManagementBean rm where rm.fromUser='" + userId + 
							"' and rm.requestTypes='CHANGE_MAIL_REQ' and rm.requestStatus='PENDING'", RequestManagementBean.class)
					.getSingleResult();
			
		}
		catch (NoResultException e) {
			return null;
		}
		
	}

	public RequestManagementBean checkForAlreadyRequestedDept(int userId) {

		try {
			
			return mysessionFactory.getCurrentSession()
					.createQuery("select rm from RequestManagementBean rm where rm.fromUser='" + userId + 
							"' and rm.requestTypes='CHANGE_DEPT_REQ' and rm.requestStatus='PENDING'", RequestManagementBean.class)
					.getSingleResult();
			
		}
		catch (NoResultException e) {
			return null;
		}
		
	}



	public List<RequestManagementBean> getChangeMailIdPendingRequests(int userId) {

		try {
			
			return mysessionFactory.getCurrentSession()
					.createQuery("select rm from RequestManagementBean rm where rm.toUser='" + userId + 
							"' and rm.requestTypes='CHANGE_MAIL_REQ' and rm.requestStatus='PENDING'", RequestManagementBean.class)
					.getResultList();
			
		}
		catch (NoResultException e) {
			return null;
		}
		
	}



	public List<RequestManagementBean> getChangeDeptPendingRequests(int userId) {

try {
			
			return mysessionFactory.getCurrentSession()
					.createQuery("select rm from RequestManagementBean rm where rm.toUser='" + userId + 
							"' and rm.requestTypes='CHANGE_DEPT_REQ' and rm.requestStatus='PENDING'", RequestManagementBean.class)
					.getResultList();
			
		}
		catch (NoResultException e) {
			return null;
		}
		
	}



	public RequestManagementBean getSingleRequestById(int reqManId) {

		return mysessionFactory.getCurrentSession()
				.createQuery("select rm from RequestManagementBean rm where rm.req_id=" + reqManId, RequestManagementBean.class)
				.getSingleResult();
		
	}

	/*
	 * public void approveDeptOrMailChangeRequest(RequestManagementBean
	 * requestManagementBean, NotificationBean notificationBean) {
	 * 
	 * String hql =
	 * "update RequestManagementBean set requestStatus = :requestStatus, "+
	 * "updated = :updated "+ "where req_id = :req_id"; Query query =
	 * mysessionFactory.getCurrentSession().createQuery(hql);
	 * query.setParameter("requestStatus",
	 * requestManagementBean.getRequestStatus()); query.setParameter("updated",
	 * requestManagementBean.getUpdated()); query.setParameter("req_id",
	 * requestManagementBean.getReq_id()); int result = query.executeUpdate();
	 * System.out.println("Rows Affected:------------- " + result);
	 * 
	 * mysessionFactory.getCurrentSession().save(notificationBean);
	 * 
	 * }
	 */


	public void approveOrRejectDeptOrMailChangeRequest(RequestManagementBean requestManagementBean,
			NotificationBean notificationBean) {

		String hql = "update RequestManagementBean set requestStatus = :requestStatus, "+
				"updated = :updated, "+
				"remarksByUpperHirarchy = :remarksByUpperHirarchy "+
				"where req_id = :req_id";
				Query query = mysessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("requestStatus", requestManagementBean.getRequestStatus());
				query.setParameter("updated", requestManagementBean.getUpdated());
				query.setParameter("remarksByUpperHirarchy", requestManagementBean.getRemarksByUpperHirarchy());
				query.setParameter("req_id", requestManagementBean.getReq_id());
				int result = query.executeUpdate();
				System.out.println("Rows Affected:------------- " + result);
		
		mysessionFactory.getCurrentSession().save(notificationBean);
		
	}
	
}

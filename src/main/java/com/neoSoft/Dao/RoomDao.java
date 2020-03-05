package com.neoSoft.Dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neoSoft.model.FacilitiesBean;
import com.neoSoft.model.RoomBean;
import com.neoSoft.model.RoomFacilities;

@Repository
public class RoomDao {
	
	@Autowired
	SessionFactory mysessionFactory;
		
	public void setMysessionFactory(SessionFactory mysessionFactory) {
		this.mysessionFactory = mysessionFactory;
	}

	public int addRoom(RoomBean room) {

		return (Integer) mysessionFactory.getCurrentSession().save(room);
		
	}

	public void addFacility(RoomFacilities roomFacilities) {
		
		mysessionFactory.getCurrentSession().save(roomFacilities);
		
	}
	
public void updateFacility(RoomFacilities roomFacilities) {
		
		mysessionFactory.getCurrentSession().saveOrUpdate(roomFacilities);
		
	}
	
public List<RoomFacilities> getFacilitiesByRoomId(int id) {
	
	return mysessionFactory.getCurrentSession().createQuery("select rf from RoomFacilities rf where rf.roomBean="+id,RoomFacilities.class).getResultList();
	
}


	public List<RoomBean> getRooms() {
		return mysessionFactory.getCurrentSession().createQuery("select r from RoomBean r",RoomBean.class).getResultList();
	}
	
	public RoomBean getRoomById(int id) {
		
		return mysessionFactory.getCurrentSession().createQuery("select r from RoomBean r where r.roomId="+id,RoomBean.class).getSingleResult();
		
	}

	public void deleteRoom(int id) {

		RoomBean roomBean = this.getRoomById(id);
		mysessionFactory.getCurrentSession().delete(roomBean);
			
	}
	
	// -------------------------------------------------------------- Criteria Builder --------------------------------------------------------------
	
		public List<Object[]> getRoomDataById(int id){
			
			Session session= mysessionFactory.getCurrentSession();
		
			CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
			Root<RoomBean> roomBean = query.from(RoomBean.class);
			Root<RoomFacilities> roomFacilities = query.from(RoomFacilities.class);
			Root<FacilitiesBean> facilitiesBean = query.from(FacilitiesBean.class);
			
			
			
			query.multiselect(roomBean.get("roomId"), roomBean.get("roomName"),roomBean.get("roomType"),roomBean.get("roomLocation"),
					roomBean.get("otherDescription"),roomFacilities.get("facilityId"),facilitiesBean.get("facilityName"));
			
			Predicate where = builder.equal(roomBean.get("roomId"),roomFacilities.get("roomBean").get("roomId"));
			where = builder.and(where, builder.equal(roomFacilities.get("facilityId"),facilitiesBean.get("facilityId")));
			where = builder.and(where,builder.equal(roomBean.get("roomId"),id));
			
			query = query.where(where);
			
			Query<Object[]> queryObj=session.createQuery(query);
			System.out.println(queryObj.getQueryString());
			return queryObj.getResultList();
			
		}

		public void updateRoom(RoomBean roomBean) {

			mysessionFactory.getCurrentSession().saveOrUpdate(roomBean);
			
		}

		public void deleteOldRoomFacilities(int roomId) {

			mysessionFactory.getCurrentSession().createQuery("delete from RoomFacilities rf where rf.roomBean="+roomId).executeUpdate();
			
			//------------------------------ following will also work, but  above is the proper way.. no need to hit DB so many times! ----------------------------
			
			/*
		 * List<RoomFacilities> roomFacilities = this.getFacilitiesByRoomId(roomId);
		 * 
		 * Iterator<RoomFacilities> it = roomFacilities.iterator(); while(it.hasNext())
		 * { RoomFacilities roomFacilitiess = it.next();
		 * mysessionFactory.getCurrentSession().delete(roomFacilitiess); }
		 */
			
		}

}

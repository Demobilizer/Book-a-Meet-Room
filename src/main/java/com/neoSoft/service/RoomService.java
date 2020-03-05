package com.neoSoft.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neoSoft.Dao.FacilitiesDao;
import com.neoSoft.Dao.RoomDao;
import com.neoSoft.dto.RoomDto;
import com.neoSoft.dto.RoomFacilityDto;
import com.neoSoft.model.FacilitiesBean;
import com.neoSoft.model.RoomBean;
import com.neoSoft.model.RoomFacilities;

@Service
@Transactional
public class RoomService {
	
	@Autowired
	RoomDao roomdao;
	
	@Autowired
	FacilitiesDao facilitiesDao;

	public void createRoom(RoomDto room) {
		
		RoomBean roomBean = new RoomBean();

		roomBean.setOtherDescription(room.getOtherDescription());
		roomBean.setRoomLocation(room.getRoomLocation());
		roomBean.setRoomName(room.getRoomName());
		roomBean.setRoomType(room.getRoomType());
		roomBean.setRoomId(room.getRoomId());
		
		int roomId=roomdao.addRoom(roomBean);
		System.out.println("---------------------------"+roomId);
		
		for(RoomFacilityDto facilities:room.getRoomFacilities()) {
			if(facilities.getFacilityId()!=0) {
			RoomFacilities  roomFacilities = new RoomFacilities();
			roomFacilities.setFacilityId(facilities.getFacilityId());
			roomFacilities.setRoom(roomBean);
			
			roomdao.addFacility(roomFacilities);
			}
		}
		
	}

	public RoomBean getRoomById(int id) {
		
		return roomdao.getRoomById(id);
	}

	public List<RoomBean> getRooms() {

		return roomdao.getRooms();
	}

	public RoomBean getRoomToEdit(int id) {
		
		return roomdao.getRoomById(id);
		
	}

	public void deleteRoom(int id) {
		
		roomdao.deleteRoom(id);
		
	}

	public List<FacilitiesBean> getFacilities() {
		
		return facilitiesDao.getAllFacilities();
		
	}

	
	public RoomDto getSingleRoomDetails(int id) {
		List<Object[]> list= roomdao.getRoomDataById(id);
		RoomDto roomDto = new RoomDto();
		roomDto.setRoomId((Integer)list.get(0)[0]);
		roomDto.setRoomName(list.get(0)[1].toString());
		roomDto.setRoomType(list.get(0)[2].toString());
		roomDto.setRoomLocation(list.get(0)[3].toString());
		roomDto.setOtherDescription(list.get(0)[4].toString());
		
		List<RoomFacilityDto> roomFacilityDtos = new ArrayList<RoomFacilityDto>();
		for(Object[] objArr : list) {
			RoomFacilityDto roomFacilityDto = new RoomFacilityDto();
			roomFacilityDto.setFacilityId((Integer)objArr[5]);
			roomFacilityDto.setFacilityName(objArr[6].toString());
			roomFacilityDtos.add(roomFacilityDto);
		}
		roomDto.setRoomFacilities(roomFacilityDtos);
		return roomDto;
	}

	public void updateRoom(RoomDto room) {
		
		RoomBean roomBean = new RoomBean();
		
		roomBean.setOtherDescription(room.getOtherDescription());
		roomBean.setRoomLocation(room.getRoomLocation());
		roomBean.setRoomName(room.getRoomName());
		roomBean.setRoomType(room.getRoomType());
		roomBean.setRoomId(room.getRoomId());
		
		roomdao.deleteOldRoomFacilities(roomBean.getRoomId());
		
		roomdao.updateRoom(roomBean);
			
		//System.out.println("---------------------------"+roomId);
		
		for(RoomFacilityDto facilities:room.getRoomFacilities()) {
			if(facilities.getFacilityId()!=0) {
				
			RoomFacilities  roomFacilities = new RoomFacilities();
			roomFacilities.setFacilityId(facilities.getFacilityId());
			roomFacilities.setRoom(roomBean);
			
			roomdao.updateFacility(roomFacilities);
			}
		}
		
	}

}

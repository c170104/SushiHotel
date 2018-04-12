package com.sushihotel.roomservice;

import java.util.List;
import java.util.Iterator;
import java.util.logging.*;

import com.sushihotel.roomservice.RSvcModel;
import com.sushihotel.roomservice.RoomSvc;

import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;

public class RoomSvcMgr {
	private static final Logger logger = Logger.getLogger(RoomSvcMgr.class.getName());
	
	public boolean addNewRoomSvc(RoomSvc roomSvc) {
		try {
			if (RSvcModel.create(roomSvc)) {
				logger.info("[CREATE SUCCESS] Room ID: " + Integer.toString(roomSvc.getRoomSvcID()) 
				+ " | Room Number: " + roomSvc.getRoomNumber());
				return true;
			} else {
				logger.info("[CREATE FAIL] Room ID: "+ Integer.toString(roomSvc.getRoomSvcID()));
			}
		} catch (DuplicateData dd) {
			logger.log(Level.WARNING, dd.getMessage());
		}
		return false;
	}
	
	public boolean deleteRoomSvc(int roomSvcID) {
		RoomSvc roomSvc;
		//int RoomSvcId;
		try {
			roomSvc = com.sushihotel.roomservice.RSvcModel.read(roomSvcID);
			if (roomSvc == null) {
				return false;
			}
			if (com.sushihotel.roomservice.RSvcModel.delete(roomSvcID)) {
				logger.info("[DELETE SUCCESS] Room ID: " + Integer.toString(roomSvc.getRoomSvcID()) 
				+ " | Room Number: " + roomSvc.getRoomNumber());
				return true;
			} else {
				logger.info("[DELETE FAIL] Room ID: "+ Integer.toString(roomSvc.getRoomSvcID()));
			}
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		} catch (InvalidEntity ie) {
			logger.log(Level.WARNING, ie.getMessage());
		}
		return false;
	}
	
	public boolean editRoomSvc(int roomSvcID, RoomSvc roomSvc) {
		try {
			if (RSvcModel.update(roomSvcID, roomSvc)) {
				logger.info("[UPDATE SUCCESS] Room ID: " + Integer.toString(roomSvc.getRoomSvcID()) 
				+ " | Room Number: " + roomSvc.getRoomNumber());
				return true;
			} else {
				logger.info("[UPDATE FAIL] Room ID: "+ Integer.toString(roomSvc.getRoomSvcID()));
			}
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		} catch (InvalidEntity ie) {
			logger.log(Level.WARNING, ie.getMessage());
		}
		return false;
	}

	public int getRoomSvcID(int roomNumber)	{
		List<RoomSvc> list;
		int roomSvcID = 0;
		try	{
			list = RSvcModel.read();
			for(int i=0; i<list.size(); i++)	{
				if(list.get(i).getRoomNumber() == roomNumber && list.get(i).getRoomSvcStatus() == RoomSvc.ROOM_SVC_STATUS.CONFIRMED)
					roomSvcID =  list.get(i).getRoomSvcID();
			}

		} catch(EmptyDB edb)	{
			logger.warning(edb.getMessage());
		}
		return roomSvcID;
	}

	public RoomSvc getRoomSvc(int roomSvcID)	{
		RoomSvc roomSvc = null;
		try	{
			roomSvc = RSvcModel.read(roomSvcID);
		} catch(EmptyDB edb)	{
			logger.warning(edb.getMessage());
		} catch(InvalidEntity ie)	{
			logger.warning(ie.getMessage());
		}
		return roomSvc;
	}
	
	

	public boolean updateRoomSvcStatus(int roomSvcID, Enum status)	{
		RoomSvc roomSvc = null;
		try	{
			roomSvc = RSvcModel.read(roomSvcID);
			roomSvc.setRoomSvcStatus(status);
		} catch(EmptyDB edb)	{
			logger.warning(edb.getMessage());
		} catch(InvalidEntity ie)	{
			logger.warning(ie.getMessage());
		}
		if(roomSvc == null)
			return false;
		return editRoomSvc(roomSvcID, roomSvc);
	}
	
	public List<RoomSvc> getRoomSvcList(int roomNumber) {
		List<RoomSvc> roomSvcList = null;
		RoomSvc roomSvc;
		Iterator iter;
    	try {
			roomSvcList = RSvcModel.read();
			iter = roomSvcList.iterator();
			
			while(iter.hasNext()) {
				roomSvc = (RoomSvc)iter.next();
				if(roomSvc.getRoomNumber() != roomNumber)
					iter.remove();
			}
			
		} catch (EmptyDB edb) {
			logger.warning(edb.getMessage());
		}
		return roomSvcList;
	}
}

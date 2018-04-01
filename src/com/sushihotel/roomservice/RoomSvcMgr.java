package com.sushihotel.roomservice;

import java.io.IOException;
import java.util.List;
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
		if (roomSvc == null) {
			return false;
		}
		try {
			if (com.sushihotel.roomservice.RSvcModel.update(roomSvcID, roomSvc)) {
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
}

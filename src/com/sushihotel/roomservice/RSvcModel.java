package com.sushihotel.roomservice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sushihotel.database.DataStoreFactory;
import com.sushihotel.database.IDataStore;
import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;

public class RSvcModel {
	private static IDataStore dataStore = DataStoreFactory.getDataStore();

	private static final String EmptyDBMsg = "RoomService DB not found.";
	
	protected static boolean create(RoomSvc roomSvc) throws DuplicateData {
		List list;
		int size;
		RoomSvc dbRoomSvc;
		
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.ROOMSERVICE);
		size = list == null ? 0 : list.size(); // size assigned to 0 if list == null else size == list.size()
		
		if(list == null)    {
            list = new ArrayList(); // declare array list without specific <obj> ref
        } 
		
		roomSvc.setRoomSvcID(size+1);
		list.add(roomSvc);
		return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.ROOMSERVICE);
		
	}

	protected static List<RoomSvc> read() throws EmptyDB	{
		List<RoomSvc> list;

		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.ROOMSERVICE);
		if(list == null)
			throw new EmptyDB(EmptyDBMsg);

		return list;
	}
	
	protected static RoomSvc read(int roomSvcId) throws EmptyDB, InvalidEntity {
		List list;
		RoomSvc roomSvc;
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.ROOMSERVICE);
		if(list == null)
            throw new EmptyDB(EmptyDBMsg);
		
		for (int i=0; i<list.size();i++) {
			roomSvc = (RoomSvc)list.get(i);
			if (roomSvc.getRoomSvcID() == roomSvcId) {
				return roomSvc;
			}
		}
		throw new InvalidEntity(roomSvcId + " not found. ", RoomSvc.ROOMSVC_SEARCH_TYPE.ROOM_SVC_ID);
	}
	
	protected static boolean update(int roomSvcId, RoomSvc roomSvc) throws EmptyDB, InvalidEntity{
		List list;
		Iterator iter;
		RoomSvc dbRoomSvc;
		boolean trigger_flag = false;
		
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.ROOMSERVICE);
		if (list == null) { 
        	throw new EmptyDB(EmptyDBMsg);
        }
		iter = list.iterator();
		while(iter.hasNext()) {
			dbRoomSvc = (RoomSvc)iter.next();
			if(dbRoomSvc.getRoomSvcID() == roomSvcId) {
				iter.remove();
				trigger_flag = true;
				break;
			}
		}
		if(!trigger_flag)
			throw new InvalidEntity(roomSvcId + " not found. ", RoomSvc.ROOMSVC_SEARCH_TYPE.ROOM_SVC_ID);
		
		list.add(roomSvc);
		
		return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.ROOMSERVICE);
	}
	
	protected static boolean delete(int roomSvcId) throws EmptyDB, InvalidEntity {
		List list;
		Iterator iter;
		RoomSvc dbRoomSvc;
		boolean trigger_flag = false;

		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.ROOMSERVICE);
		
		if(list == null) {
			throw new EmptyDB(EmptyDBMsg);
		}
		
		iter = list.iterator();
		while(iter.hasNext()) {
			dbRoomSvc = (RoomSvc)iter.next();
			if(dbRoomSvc.getRoomSvcID() == roomSvcId) {
				iter.remove();
				trigger_flag = true;
				break;
			}
		}
		if (!trigger_flag) {
			throw new InvalidEntity(roomSvcId + " not found.", RoomSvc.ROOMSVC_SEARCH_TYPE.ROOM_SVC_ID);
		}
		return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.ROOMSERVICE);
	}
}


package com.sushihotel.test;

import java.util.Date;
import com.sushihotel.roomservice.RoomSvc;
import com.sushihotel.roomservice.RoomSvcMgr;


public class rsUT {
	public static void main(String[] args) {
		Date today = new Date();
	RoomSvc r1 = new RoomSvc(01,(float) 5.00,"nil",today);
	RoomSvc r2 = new RoomSvc(02,(float) 5.00,"nil",today);
	RoomSvc r3 = new RoomSvc(03,(float) 5.00,"nil",today);
	RoomSvcMgr mgr = new RoomSvcMgr();
	mgr.addNewRoomSvc(r1);
		mgr.addNewRoomSvc(r2);
		mgr.addNewRoomSvc(r3);
		mgr.addNewRoomSvc(r1);
		mgr.addNewRoomSvc(r2);
		mgr.addNewRoomSvc(r3);
		mgr.editRoomSvc(8, r1);
	}
	
}

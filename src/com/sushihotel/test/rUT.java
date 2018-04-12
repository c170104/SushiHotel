package com.sushihotel.test;

import com.sushihotel.room.Room;
import com.sushihotel.room.Room.ROOM_TYPE;
import com.sushihotel.room.RoomMgr;
import com.sushihotel.roomservice.RoomSvc;
import com.sushihotel.roomservice.RoomSvcMgr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sushihotel.reservation.*;

public class rUT    {
    public static void main(String[] args) throws ParseException  {
    	
    	Date date = new Date();
    	
    	RoomSvcMgr rsMgr = new RoomSvcMgr();
    	RoomSvc rms1 = new RoomSvc(100, 999, "TEST", date);
    	RoomSvc rms2 = new RoomSvc(100, 999, "TEST", date);
    	RoomSvc rms3 = new RoomSvc(100, 999, "TEST", date);
    	RoomSvc rms4 = new RoomSvc(100, 999, "TEST", date);
    	RoomSvc rms5 = new RoomSvc(100, 999, "TEST", date);
    	rsMgr.addNewRoomSvc(rms1);
    	rsMgr.addNewRoomSvc(rms2);
    	rsMgr.addNewRoomSvc(rms3);
    	rsMgr.addNewRoomSvc(rms4);
    	rsMgr.addNewRoomSvc(rms5);
    	
    	

    	
        RoomMgr rMgr = new RoomMgr();
        
        // single room (20)
        Room r100 = new Room (100, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"01");
        Room r101 = new Room (101, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"02");
        Room r102 = new Room (102, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"03");
        Room r103 = new Room (103, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"04");
        Room r104 = new Room (104, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"05");
        Room r105 = new Room (105, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"06");
        Room r106 = new Room (106, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"07");
        Room r107 = new Room (107, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"08");
        Room r108 = new Room (108, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"09");
        Room r109 = new Room (109, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"10");
        Room r110 = new Room (110, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"11");
        Room r111 = new Room (111, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"12");
        Room r112 = new Room (112, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"13");
        Room r113 = new Room (113, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"14");
        Room r114 = new Room (114, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"15");
        Room r115 = new Room (115, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"16");
        Room r116 = new Room (116, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"17");
        Room r117 = new Room (117, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"18");
        Room r118 = new Room (118, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"19");
        Room r119 = new Room (119, Room.ROOM_TYPE.SINGLE, 1,1,1,1,"TEST",true,"TEST",true,"20");
        // double room (15)
        Room r200 = new Room (200, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"21");
        Room r201 = new Room (201, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"22");
        Room r202 = new Room (202, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"23");
        Room r203 = new Room (203, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"24");
        Room r204 = new Room (204, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"25");
        Room r205 = new Room (205, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"26");
        Room r206 = new Room (206, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"27");
        Room r207 = new Room (207, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"28");
        Room r208 = new Room (208, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"29");
        Room r209 = new Room (209, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"30");
        Room r210 = new Room (210, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"31");
        Room r211 = new Room (211, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"32");
        Room r212 = new Room (212, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"33");
        Room r213 = new Room (213, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"34");
        Room r214 = new Room (214, Room.ROOM_TYPE.DOUBLE, 1,1,1,1,"TEST",true,"TEST",true,"35");
        // deluxe room (8)
        Room r300 = new Room (300, Room.ROOM_TYPE.DELUXE, 1,1,1,1,"TEST",true,"TEST",true,"36");
        Room r301 = new Room (301, Room.ROOM_TYPE.DELUXE, 1,1,1,1,"TEST",true,"TEST",true,"37");
        Room r302 = new Room (302, Room.ROOM_TYPE.DELUXE, 1,1,1,1,"TEST",true,"TEST",true,"38");
        Room r303 = new Room (303, Room.ROOM_TYPE.DELUXE, 1,1,1,1,"TEST",true,"TEST",true,"39");
        Room r304 = new Room (304, Room.ROOM_TYPE.DELUXE, 1,1,1,1,"TEST",true,"TEST",true,"40");
        Room r305 = new Room (305, Room.ROOM_TYPE.DELUXE, 1,1,1,1,"TEST",true,"TEST",true,"41");
        Room r306 = new Room (306, Room.ROOM_TYPE.DELUXE, 1,1,1,1,"TEST",true,"TEST",true,"42");
        Room r307 = new Room (307, Room.ROOM_TYPE.DELUXE, 1,1,1,1,"TEST",true,"TEST",true,"43");
        // vip room (5)
        Room r400 = new Room (400, Room.ROOM_TYPE.VIP, 1,1,1,1,"TEST",true,"TEST",true,"44");
        Room r401 = new Room (401, Room.ROOM_TYPE.VIP, 1,1,1,1,"TEST",true,"TEST",true,"45");
        Room r402 = new Room (402, Room.ROOM_TYPE.VIP, 1,1,1,1,"TEST",true,"TEST",true,"46");
        Room r403 = new Room (403, Room.ROOM_TYPE.VIP, 1,1,1,1,"TEST",true,"TEST",true,"47");
        Room r404 = new Room (404, Room.ROOM_TYPE.VIP, 1,1,1,1,"TEST",true,"TEST",true,"48");
        
        rMgr.createRoom(r100);
        rMgr.createRoom(r101);
        rMgr.createRoom(r102);
        rMgr.createRoom(r103);
        rMgr.createRoom(r104);
        rMgr.createRoom(r105);
        rMgr.createRoom(r106);
        rMgr.createRoom(r107);
        rMgr.createRoom(r108);
        rMgr.createRoom(r109);
        rMgr.createRoom(r110);
        rMgr.createRoom(r111);
        rMgr.createRoom(r112);
        rMgr.createRoom(r113);
        rMgr.createRoom(r114);
        rMgr.createRoom(r115);
        rMgr.createRoom(r116);
        rMgr.createRoom(r117);
        rMgr.createRoom(r118);
        rMgr.createRoom(r119);
        rMgr.createRoom(r200);
        rMgr.createRoom(r201);
        rMgr.createRoom(r202);
        rMgr.createRoom(r203);
        rMgr.createRoom(r204);
        rMgr.createRoom(r205);
        rMgr.createRoom(r206);
        rMgr.createRoom(r207);
        rMgr.createRoom(r208);
        rMgr.createRoom(r209);
        rMgr.createRoom(r210);
        rMgr.createRoom(r211);
        rMgr.createRoom(r212);
        rMgr.createRoom(r213);
        rMgr.createRoom(r214);
        rMgr.createRoom(r300);
        rMgr.createRoom(r301);
        rMgr.createRoom(r302);
        rMgr.createRoom(r303);
        rMgr.createRoom(r304);
        rMgr.createRoom(r305);
        rMgr.createRoom(r306);
        rMgr.createRoom(r307);
        rMgr.createRoom(r400);
        rMgr.createRoom(r401);
        rMgr.createRoom(r402);
        rMgr.createRoom(r403);
        rMgr.createRoom(r404);
        
        

        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date ci = null;
        Date ci2 = null;
        Date co = null;
        Date co2 = null;
        ReservationMgr reservationMgr = new ReservationMgr();
        ci = formatter.parse("11/11/2018 14:01");
        co = formatter.parse("11/11/2019 12:00");
        ci2 = formatter.parse("11/11/1000 14:01");
        co2 = formatter.parse("11/11/1001 12:00");
        
        // single room reservation checked in (12), 112 n 113 has passed the checked in date
        Reservation re100 = new Reservation ("TEST100", 100, ci, co,1,1,1,1);
        Reservation re101 = new Reservation ("TEST101", 101, ci, co,1,1,1,1);
        Reservation re102 = new Reservation ("TEST102", 102, ci, co,1,1,1,1);
        Reservation re103 = new Reservation ("TEST103", 103, ci, co,1,1,1,1);
        Reservation re104 = new Reservation ("TEST104", 104, ci, co,1,1,1,1);
        Reservation re105 = new Reservation ("TEST105", 105, ci, co,1,1,1,1);
        Reservation re106 = new Reservation ("TEST106", 106, ci, co,1,1,1,1);
        Reservation re107 = new Reservation ("TEST107", 107, ci, co,1,1,1,1);
        Reservation re108 = new Reservation ("TEST108", 108, ci, co,1,1,1,1);
        Reservation re109 = new Reservation ("TEST109", 109, ci, co,1,1,1,1);
        Reservation re110 = new Reservation ("TEST110", 110, ci, co,1,1,1,1);
        Reservation re111 = new Reservation ("TEST111", 111, ci, co,1,1,1,1);
        Reservation re112 = new Reservation ("TEST112", 112, ci2, co2,1,1,1,1);
        Reservation re113 = new Reservation ("TEST113", 113, ci2, co2,1,1,1,1);
        
        
        // double room reservation checked in (7), 207 n 208 has passed the checked in date
        Reservation re200 = new Reservation ("TEST200", 200, ci, co,1,1,1,1);
        Reservation re201 = new Reservation ("TEST201", 201, ci, co,1,1,1,1);
        Reservation re202 = new Reservation ("TEST202", 202, ci, co,1,1,1,1);
        Reservation re203 = new Reservation ("TEST203", 203, ci, co,1,1,1,1);
        Reservation re204 = new Reservation ("TEST204", 204, ci, co,1,1,1,1);
        Reservation re205 = new Reservation ("TEST205", 205, ci, co,1,1,1,1);
        Reservation re206 = new Reservation ("TEST206", 206, ci, co,1,1,1,1);
        Reservation re207 = new Reservation ("TEST207", 207, ci2, co2,1,1,1,1);
        Reservation re208 = new Reservation ("TEST208", 208, ci2, co2,1,1,1,1);
        
        // deluxe room reservation checked in (3), 303 n 304 has passed the checked in date
        Reservation re300 = new Reservation ("TEST300", 300, ci, co,1,1,1,1);
        Reservation re301 = new Reservation ("TEST301", 301, ci, co,1,1,1,1);
        Reservation re302 = new Reservation ("TEST302", 302, ci, co,1,1,1,1);
        Reservation re303 = new Reservation ("TEST303", 303, ci2, co2,1,1,1,1);
        Reservation re304 = new Reservation ("TEST304", 304, ci2, co2,1,1,1,1);
        
        // vip room reservation checked in (2), 402 has passed the checked in date
        Reservation re400 = new Reservation ("TEST400", 400, ci, co,1,1,1,1);
        Reservation re401 = new Reservation ("TEST401", 401, ci, co,1,1,1,1);
        Reservation re402 = new Reservation ("TEST402", 402, ci2, co2,1,1,1,1);
        
        reservationMgr.beginReservation(re100);
        reservationMgr.beginReservation(re101);
        reservationMgr.beginReservation(re102);
        reservationMgr.beginReservation(re103);
        reservationMgr.beginReservation(re104);
        reservationMgr.beginReservation(re105);
        reservationMgr.beginReservation(re106);
        reservationMgr.beginReservation(re107);
        reservationMgr.beginReservation(re108);
        reservationMgr.beginReservation(re109);
        reservationMgr.beginReservation(re110);
        reservationMgr.beginReservation(re111);
        reservationMgr.beginReservation(re112);
        reservationMgr.beginReservation(re113);
        
        reservationMgr.beginReservation(re200);
        reservationMgr.beginReservation(re201);
        reservationMgr.beginReservation(re202);
        reservationMgr.beginReservation(re203);
        reservationMgr.beginReservation(re204);
        reservationMgr.beginReservation(re205);
        reservationMgr.beginReservation(re206);
        reservationMgr.beginReservation(re207);
        reservationMgr.beginReservation(re208);
        
        reservationMgr.beginReservation(re300);
        reservationMgr.beginReservation(re301);
        reservationMgr.beginReservation(re302);
        reservationMgr.beginReservation(re303);
        reservationMgr.beginReservation(re304);
        
        reservationMgr.beginReservation(re400);
        reservationMgr.beginReservation(re401);
        reservationMgr.beginReservation(re402);
        
        
//        // Create Room
//        r = new Room(1, Room.ROOM_TYPE.DOUBLE, 1, 4, 349.99f, 550.00f, "Two Super Single Bed, Two Mattress", false, "Facing City", false, Room.ROOM_STATUS.VACANT, "02-04");
//        rMgr.createRoom(r);
//
//        // Edit Room
//        r = new Room(1, Room.ROOM_TYPE.DOUBLE, 1, 4, 449.99f, 650.00f, "Two Super Single Bed, Two Mattress", false, "Facing City", false, Room.ROOM_STATUS.VACANT, "02-05");
//        rMgr.editRoom(1, r);
//
//        // Print all Room Status
//        rMgr.printRoomStatusByAvailability();
//
//        // Print by Type
//        rMgr.printRoomStatusByRoomType();

        // Delete room
        // rMgr.removeRoom(1);
    }
}
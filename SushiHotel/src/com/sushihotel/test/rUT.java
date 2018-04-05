package com.sushihotel.test;

import com.sushihotel.room.Room;
import com.sushihotel.room.RoomMgr;

public class rUT    {
    public static void main(String[] args)  {
        Room r;
        RoomMgr rMgr = new RoomMgr();

        // Create Room
        r = new Room(1, Room.ROOM_TYPE.DOUBLE, 1, 4, 349.99f, 550.00f, "Two Super Single Bed, Two Mattress", false, "Facing City", false, Room.ROOM_STATUS.VACANT, "02-04");
        rMgr.createRoom(r);

        // Edit Room
        r = new Room(1, Room.ROOM_TYPE.DOUBLE, 1, 4, 449.99f, 650.00f, "Two Super Single Bed, Two Mattress", false, "Facing City", false, Room.ROOM_STATUS.VACANT, "02-05");
        rMgr.editRoom(1, r);

        // Print all Room Status
        rMgr.printRoomStatusByAvailability();

        // Print by Type
        rMgr.printRoomStatusByRoomType();

        // Delete room
        // rMgr.removeRoom(1);
    }
}
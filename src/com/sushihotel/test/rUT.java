package com.sushihotel.test;

import com.sushihotel.room.Room;
import com.sushihotel.room.RoomMgr;

public class rUT    {
    public static void main(String[] args)  {
        Room r;
        RoomMgr rMgr = new RoomMgr();

        r = new Room(1, Room.ROOM_TYPE.SINGLE, 2, 4, 349.99f, 550.00f, "Two Super Single Bed, Two Mattress", false, "Facing City", false, Room.ROOM_STATUS.VACANT, 1);
        
        rMgr.createRoom(r);
    }
}
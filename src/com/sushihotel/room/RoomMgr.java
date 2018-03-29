package com.sushihotel.room;

import java.util.List;
import java.util.logging.Logger;

import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;
import com.sushihotel.room.Room;
import com.sushihotel.room.RoomModel;

public class RoomMgr    {
    private static final Logger logger = Logger.getLogger(RoomMgr.class.getName());

    public void createRoom(Room room)    {
        try {
            if(RoomModel.create(room))
                logger.info("Successfully added Room number " + room.getRoomNumber() + " Type: " + room.getRoomType());
            else
                logger.info("System failed to add Room number" + room.getRoomNumber() + ".");
        } catch(DuplicateData dd)   {
            logger.warning(dd.getMessage());
        }
    } 
    public void editRoom(int roomNumber , Room room) {
        try {
            if(RoomModel.update(roomNumber, room))
                logger.info("Successfully updated Room " + roomNumber + ".");
            else
                logger.info("System has failed to update Room " + roomNumber + ".");
        } catch (EmptyDB edb)   {
            logger.warning(edb.getMessage());
        } catch (InvalidEntity ie ) {
            logger.warning(ie.getMessage());
        }
    } 
    public void removeRoom(int roomNumber)  {
        try {
            if(RoomModel.delete(roomNumber))
                logger.info("Successfully removed Room " + roomNumber + ".");
            else
                logger.info("System has failed to remove Room " + roomNumber + ".");
        } catch (EmptyDB edb)   {
            logger.warning(edb.getMessage());
        } catch (InvalidEntity ie)  {
            logger.warning(ie.getMessage());
        }
    } 
    public boolean checkRoomAvailability(int roomNumber)    {
        try {
            Room room;
            
            room = RoomModel.read(roomNumber);
            if(room.getRoomStatus() == Room.ROOM_STATUS.VACANT)
                return true;
        } catch (EmptyDB edb)   {
            logger.warning(edb.getMessage());
        } catch (InvalidEntity ie)  {
            logger.warning(ie.getMessage());
        }
        return false;
    } 
    public void printRoomStatusByAvailability() {
        try {
            List<Room> list;
            Room room;
            String vacantRooms = "";
            String occupiedRooms = "";
            String reservedRooms = "";
            String underMaintanenceRooms = "";

            list = RoomModel.read();

            
            for (int i = 0; i < list.size(); i++) {
                room = list.get(i);
                if(room.getRoomStatus() == Room.ROOM_STATUS.VACANT)
                    vacantRooms += room.getUnitNumber() + ", ";
                else if(room.getRoomStatus() == Room.ROOM_STATUS.OCCUPIED)
                    occupiedRooms += room.getUnitNumber() + ", ";
                else if(room.getRoomStatus() == Room.ROOM_STATUS.RESERVED)
                    reservedRooms += room.getUnitNumber() + ", ";
                else
                    underMaintanenceRooms += room.getUnitNumber() + ", ";
            }

            System.out.println(
                "Vacant: \n" +
                "\tRooms: " + vacantRooms + "\n" +
                "Occupied: \n" + 
                "\tRooms: " + occupiedRooms + "\n" +
                "Reserved: \n" +
                "\tRooms: " + reservedRooms + "\n" +
                "Under Maintenance: \n" +
                "\tRooms: " + underMaintanenceRooms + "\n"
            );

        } catch (EmptyDB edb) {
            logger.info(edb.getMessage());
        }
    }
    public void printRoomStatusByRoomType()  {

    } 
    // public void printRoomStatsRoomStatus()  {

    // }  //list all room numbers  from each room status category
     
    // public void printRoomOccupancyPercentage(String date)   {
        
    // }
}
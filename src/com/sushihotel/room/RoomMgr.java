package com.sushihotel.room;

import java.util.List;
import java.util.logging.Logger;
import java.util.Iterator;

import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;

public class RoomMgr    {
    private static final Logger logger = Logger.getLogger(RoomMgr.class.getName());

    public boolean createRoom(Room room)    {
        try {
            if(RoomModel.create(room))  {
                logger.info("[CREATE SUCCESS] Room Number: " + room.getRoomNumber() + " |Type: " + room.getRoomType());
                return true;
            }
            else
                logger.info("[CREATE FAIL] Room Number: " + room.getRoomNumber());
        } catch(DuplicateData dd)   {
            logger.warning(dd.getMessage());
        }
        return false;
    } 
    public boolean editRoom(int roomNumber , Room room) {
        try {
            if(RoomModel.update(roomNumber, room))  {
                logger.info("[UPDATE SUCCESS] Room Number: " + roomNumber);
                return true;
            }
            else
                logger.info("[UPDATE FAIL] Room Number: " + roomNumber);
        } catch (EmptyDB edb)   {
            logger.warning(edb.getMessage());
        } catch (InvalidEntity ie ) {
            logger.warning(ie.getMessage());
        }
        return false;
    } 
    public boolean removeRoom(int roomNumber)  {
        try {
            if(RoomModel.delete(roomNumber))    {
                logger.info("[DELETE SUCCESS] Room Number: " + roomNumber);
                return true;
            }
            else
                logger.info("[DELETE FAIL] Room Number: " + roomNumber);
        } catch (EmptyDB edb)   {
            logger.warning(edb.getMessage());
        } catch (InvalidEntity ie)  {
            logger.warning(ie.getMessage());
        }
        return false;
    }
    public Room getRoom(int roomNumber) {
        Room room = null;
        try {
            room = RoomModel.read(roomNumber);
        } catch(EmptyDB edb)    {
            logger.warning(edb.getMessage());
        } catch(InvalidEntity ie)   {
            logger.warning(ie.getMessage());
        }
        return room;
    }

    public boolean setRoomToOccupied(int roomNumber, Enum roomStatus)    {
        Room room;
        try {
            room = RoomModel.read(roomNumber);
            room.setRoomStatus(roomStatus);
            
            return RoomModel.update(roomNumber, room);
        } catch(EmptyDB edb)    {
            logger.warning(edb.getMessage());
        } catch(InvalidEntity ie)   {
            logger.warning(ie.getMessage());
        }
        return false;
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
                    vacantRooms += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
                else if(room.getRoomStatus() == Room.ROOM_STATUS.OCCUPIED)
                    occupiedRooms += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
                else if(room.getRoomStatus() == Room.ROOM_STATUS.RESERVED)
                    reservedRooms += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
                else
                    underMaintanenceRooms += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
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
        try {
            List<Room> list;
            Room room;
            int singleVacant = 0, singleTotal = 0;
            int doubleVacant = 0, doubleTotal = 0;
            int deluxeVacant = 0, deluxeTotal = 0;
            int vipVacant = 0, vipTotal = 0;
            String singleUnitNumber = "";
            String doubleUnitNumber = "";
            String deluxeUnitNumber = "";
            String vipUnitNumber = "";

            list = RoomModel.read();

            for(int i=0; i<list.size(); i++)    {
                room = list.get(i);
                if(room.getRoomType() == Room.ROOM_TYPE.SINGLE) {
                    singleTotal++;
                    if(room.getRoomStatus() == Room.ROOM_STATUS.VACANT) {
                        singleVacant++;
                        singleUnitNumber += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
                    }
                }
                else if(room.getRoomType() == Room.ROOM_TYPE.DOUBLE)    {
                    doubleTotal++;
                    if(room.getRoomStatus() == Room.ROOM_STATUS.VACANT) {
                        doubleVacant++;
                        doubleUnitNumber += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
                    }
                }
                else if(room.getRoomType() == Room.ROOM_TYPE.DELUXE)    {
                    deluxeTotal++;
                    if(room.getRoomStatus() == Room.ROOM_STATUS.VACANT) {
                        deluxeVacant++;
                        deluxeUnitNumber += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
                    }
                }
                else    {
                    vipTotal++;
                    if(room.getRoomStatus() == Room.ROOM_STATUS.VACANT) {
                        vipVacant++;
                        vipUnitNumber += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
                    }
                }
            }

            System.out.println(
                "Single: Number: " + singleVacant + " out of " + singleTotal + "\n" +
                "\tRoom: " + singleUnitNumber + "\n" +
                "Double: Number: " + doubleVacant + " out of " + doubleTotal + "\n" +
                "\tRoom: " + doubleUnitNumber + "\n" +
                "Deluxe: Number: " + deluxeVacant + " out of " + deluxeTotal + "\n" +
                "\tRoom: " + deluxeUnitNumber + "\n" +
                "VIP: Number: " + vipVacant + " out of " + vipTotal + "\n" +
                "\tRoom: " + vipUnitNumber + "\n"
            );
        } catch(EmptyDB edb)    {
            logger.warning(edb.getMessage());
        }
    } 
    
    public List<Room> getOccupiedRoom() {
    	List<Room> roomList = null;
    	Iterator iter;
    	Room room;
    	int k = 1;
    	try {
    		roomList = RoomModel.read();
    		iter = roomList.iterator();
    		
    		while(iter.hasNext())	{
    			room = (Room)iter.next();
    			if(room.getRoomStatus() != Room.ROOM_STATUS.OCCUPIED)
    				iter.remove();
    		}
    	}catch (EmptyDB edb) {
    		logger.info(edb.getMessage());
    	}
    	return roomList;
    }
     
    public int getTotalByRoomType(Enum roomType)	{
    	List<Room> list;
    	Room room;
    	int counter = 0;
    	try	{
    		list = RoomModel.read();
    		
    		for(int i=0; i<list.size(); i++)	{
    			room = list.get(i);
    			if(room.getRoomType() == roomType)
    				counter++;
    		}
    	} catch (EmptyDB edb)	{
    		logger.info(edb.getMessage());
    	}
    	return counter;
    }
    
    public boolean updateRoomToReserve(int roomNumber) {
    	Room room;
    	try {
    		room = RoomModel.read(roomNumber);
    		room.setRoomStatus(Room.ROOM_STATUS.RESERVED);
    		RoomModel.update(roomNumber, room);
    	} catch (InvalidEntity ie) {
    		logger.info(ie.getMessage());
    	} catch (EmptyDB edb) {
    		logger.info(edb.getMessage());
    	}
    	return true;
    }
}
package com.sushihotel.room;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import com.sushihotel.database.DataStoreFactory;
import com.sushihotel.database.IDataStore;
import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;
import com.sushihotel.room.Room;

public class RoomModel   {
    private static IDataStore dataStore = DataStoreFactory.getDataStore();
    private static final String EmptyDBMsg = "Room DB not found.";

    protected static boolean create(Room room) throws DuplicateData  {
        List list;
        Room dbRoom;
        int size;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.ROOM);
        size = list == null ? 0 : list.size();

        // no existing room
        if (list == null) {
            list = new ArrayList();
        }

        // checks for duplicated room number
        for(int i=0; i<size; i++)   {
            dbRoom = (Room)list.get(i);
            if(dbRoom.getRoomNumber() == room.getRoomNumber())  {
                throw new DuplicateData(Integer.toString(room.getRoomNumber()), Room.ROOM_SEARCH_TYPE.ROOM_NUMBER);
            }
            else if(dbRoom.getUnitNumber().toLowerCase().equals(room.getUnitNumber().toLowerCase()))
                throw new DuplicateData(room.getUnitNumber(), Room.ROOM_SEARCH_TYPE.UNIT_NUMBER);
        }
        list.add(room);
        
        return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.ROOM);
    }

    protected static Room read(int roomNumber) throws EmptyDB, InvalidEntity  {
        List list;
        Room room;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.ROOM);
        
        if(list == null)
            throw new EmptyDB(EmptyDBMsg);

        for(int i=0; i<list.size(); i++)    {
            room = (Room)list.get(i);
            if(room.getRoomNumber() == roomNumber)
                return room;
        }
        
        throw new InvalidEntity(roomNumber + " not found.", Room.ROOM_SEARCH_TYPE.ROOM_NUMBER);
    }

    protected static List<Room> read() throws EmptyDB   {
        List<Room> list;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.ROOM);

        if(list == null)
            throw new EmptyDB(EmptyDBMsg);
        
        return list;
    }

    protected static boolean update(int roomNumber, Room room) throws EmptyDB, InvalidEntity  {
        List list;
        Iterator iter;
        Room oldRoom;
        boolean trigger_flag = false;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.ROOM);

        if(list == null)
            throw new EmptyDB(EmptyDBMsg);

        room.setRoomNumber(roomNumber);

        iter = list.iterator();
        while(iter.hasNext())   {
            oldRoom = (Room)iter.next();
            if(oldRoom.getRoomNumber() == roomNumber)   {
                iter.remove();
                trigger_flag = true;
                break;
            }
        }
        if(!trigger_flag)
            throw new InvalidEntity(roomNumber + " not found.", Room.ROOM_SEARCH_TYPE.ROOM_NUMBER);
        
        list.add(room);

        return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.ROOM);
    }

    protected static boolean delete(int roomNumber) throws EmptyDB, InvalidEntity    {
        List list;
        Iterator iter;
        Room room;
        boolean trigger_flag = false;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.ROOM);

        if(list == null)
            throw new EmptyDB(EmptyDBMsg);

        iter = list.iterator();

        while(iter.hasNext())   {
            room = (Room)iter.next();
            if(room.getRoomNumber() == roomNumber)  {
                iter.remove();
                trigger_flag = true;
                break;
            }
        }

        if(!trigger_flag)
            throw new InvalidEntity(roomNumber + " not found.", Room.ROOM_SEARCH_TYPE.ROOM_NUMBER);
        
        return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.ROOM);
    }
}
package com.sushihotel.guest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Integer;

import com.sushihotel.database.DataStoreFactory;
import com.sushihotel.database.IDataStore;
import com.sushihotel.guest.Guest;
import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;

public class GuestModel {
    private static IDataStore dataStore = DataStoreFactory.getDataStore();
    private static final String EMPTY_DB_MSG = "Guest DB not found.";
    
    protected static boolean create(Guest guest) throws DuplicateData  {
        List list;
        int size;
        Guest dbGuest;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.GUEST);

        size = list == null ? 0 : list.size();

        // if no existing guests
        if(list == null)    {
            list = new ArrayList();
        }        

        // Checks for existing user based on name and passport no.
        for(int i=0; i<size; i++)   {
            dbGuest = (Guest)list.get(i);
            if(dbGuest.getIdentificationNo().toLowerCase().equals(guest.getIdentificationNo().toLowerCase()))   {
                throw new DuplicateData(guest.getIdentificationNo(), Guest.GUEST_SEARCH_TYPE.IDENTIFICATION_NO);
            }
            if(dbGuest.getName().toLowerCase().equals(guest.getName().toLowerCase()))  {
                throw new DuplicateData(guest.getName(), Guest.GUEST_SEARCH_TYPE.GUEST_NAME);
            }
            if(dbGuest.getPassportNumber().toLowerCase().equals(guest.getPassportNumber().toLowerCase())) {
                throw new DuplicateData(guest.getPassportNumber(), Guest.GUEST_SEARCH_TYPE.PASSPORT_NO);
            }
        }

        // guestID automatically set on creation
        guest.setGuestID(size + 1);

        // append to the end of the current db list
        list.add(guest);

        return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.GUEST);
    }

    protected static Guest read(String searchDetails, Enum method) throws EmptyDB, InvalidEntity {
        List list;
        Guest guest;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.GUEST);

        if(list == null)
            new EmptyDB(EMPTY_DB_MSG);

        for(int i=0; i<list.size(); i++)    {
            guest = (Guest)list.get(i);
            
            if(Guest.GUEST_SEARCH_TYPE.IDENTIFICATION_NO == method)   {
                if(guest.getIdentificationNo().toLowerCase().equals(searchDetails.toLowerCase()))
                    return guest;
            } else if(Guest.GUEST_SEARCH_TYPE.PASSPORT_NO == method) {
                if(guest.getPassportNumber().toLowerCase().equals(searchDetails.toLowerCase()))
                    return guest;
            } else if(Guest.GUEST_SEARCH_TYPE.GUEST_NAME == method)    {
                if(guest.getName().toLowerCase().equals(searchDetails.toLowerCase()))
                    return guest;
            } else if(Guest.GUEST_SEARCH_TYPE.GUEST_ID == method)    {
                if(guest.getGuestID() == Integer.parseInt(searchDetails))
                    return guest;
            }       
        }
        throw new InvalidEntity(searchDetails + " not found.", method);
    }

    protected static List<Guest> read(String keyword) throws EmptyDB {
        List list = null;
        List<Guest> newList = new ArrayList();
        Guest guest;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.GUEST);
        
        if(list == null)
            throw new EmptyDB(EMPTY_DB_MSG);

        for(int i=0; i<list.size(); i++)    {
            guest = (Guest)list.get(i);
            if(guest.getName().toLowerCase().contains(keyword.toLowerCase()))
                newList.add(guest);
        }
        return newList;
    }

    protected static boolean update(int guestID, Guest guest) throws EmptyDB, InvalidEntity  {
        List list;
        Iterator iter;
        Guest dbGuest;
        boolean trigger_flag = false;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.GUEST);

        if(list == null)
            throw new EmptyDB(EMPTY_DB_MSG);

        guest.setGuestID(guestID);

        iter = list.iterator();
        while(iter.hasNext())   {
            dbGuest = (Guest)iter.next();
            if(dbGuest.getGuestID() == guestID) {
                iter.remove();
                trigger_flag = true;
                break;
            }
        }
        if(!trigger_flag)
            throw new InvalidEntity(guestID + " not found.", Guest.GUEST_SEARCH_TYPE.GUEST_ID);

        list.add(guest);

        return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.GUEST);

    }

    protected static boolean delete(int guestID) throws EmptyDB, InvalidEntity   {
        List list;
        Iterator iter;
        Guest guest = read(Integer.toString(guestID), Guest.GUEST_SEARCH_TYPE.GUEST_ID);
        boolean trigger_flag = false;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.GUEST);

        if(list == null)    {
           throw new EmptyDB(EMPTY_DB_MSG);
        }

        iter = list.iterator();
        while(iter.hasNext())   {
            guest = (Guest)iter.next();
            if(guest.getGuestID() == guestID)   {
                iter.remove();
                trigger_flag = true;
                break;   
            }
        }
        if(!trigger_flag)
            throw new InvalidEntity(guestID + " does not exist.", Guest.GUEST_SEARCH_TYPE.GUEST_ID);
        
        return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.GUEST);
    }
}
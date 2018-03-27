package com.sushihotel.guest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Integer;

import com.sushihotel.database.DataStoreFactory;
import com.sushihotel.database.IDataStore;
import com.sushihotel.tools.ReadPropValues;
import com.sushihotel.guest.Guest;
import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;

public class GuestModel {
    private static IDataStore dataStore = DataStoreFactory.getDataStore();
    public enum GUEST_READ_METHOD {
        IDENTIFICATION_NO,
        PASSPORT_NO,
        NAME,
        GUEST_ID
    }
    private static final String EmptyDBMsg = "Guest DB not found.";
    
    public static boolean create(Guest guest) throws DuplicateData  {
        List list;
        int size;
        Guest dbGuest;

        list = (ArrayList)dataStore.readGuest();

        size = list == null ? 0 : list.size();

        // if no existing guests
        if(list == null)    {
            list = new ArrayList();
        }        

        // Checks for existing user based on name and passport no.
        for(int i=0; i<size; i++)   {
            dbGuest = (Guest)list.get(i);
            if(dbGuest.getIdentificationNo().toLowerCase().equals(guest.getIdentificationNo().toLowerCase()))   {
                throw new DuplicateData(guest.getIdentificationNo(), GUEST_READ_METHOD.IDENTIFICATION_NO);
            }
            if(dbGuest.getName().toLowerCase().equals(guest.getName().toLowerCase()))  {
                throw new DuplicateData(guest.getName(), GUEST_READ_METHOD.NAME);
            }
            if(dbGuest.getPassportNumber().toLowerCase().equals(guest.getPassportNumber().toLowerCase())) {
                throw new DuplicateData(guest.getPassportNumber(), GUEST_READ_METHOD.PASSPORT_NO);
            }
        }

        // guestID automatically set on creation
        guest.setGuestID(size + 1);

        // append to the end of the current db list
        list.add(guest);

        return dataStore.writeGuest(list);
    }

    public static Guest read(String searchDetails, Enum method) throws EmptyDB {
        List list;
        Guest guest;

        list = (ArrayList)dataStore.readGuest();

        if(list == null)
            new EmptyDB(EmptyDBMsg);

        for(int i=0; i<list.size(); i++)    {
            guest = (Guest)list.get(i);
            
            if(GUEST_READ_METHOD.IDENTIFICATION_NO == method)   {
                if(guest.getIdentificationNo().toLowerCase().equals(searchDetails.toLowerCase()))
                    return guest;
            } else if(GUEST_READ_METHOD.PASSPORT_NO == method) {
                if(guest.getPassportNumber().toLowerCase().equals(searchDetails.toLowerCase()))
                    return guest;
            } else if(GUEST_READ_METHOD.NAME == method)    {
                if(guest.getName().toLowerCase().equals(searchDetails.toLowerCase()))
                    return guest;
            } else if(GUEST_READ_METHOD.GUEST_ID == method)    {
                if(guest.getGuestID() == Integer.parseInt(searchDetails))
                    return guest;
            }       
        }
        return null;
    }

    public static List<Guest> read(String keyword) throws EmptyDB {
        List list = null;
        List<Guest> newList = new ArrayList();
        Guest guest;

        list = (ArrayList)dataStore.readGuest();
        
        if(list == null)
            throw new EmptyDB(EmptyDBMsg);

        for(int i=0; i<list.size(); i++)    {
            guest = (Guest)list.get(i);
            if(guest.getName().toLowerCase().contains(keyword.toLowerCase()))
                newList.add(guest);
        }
        return newList;
    }

    public static boolean update(int guestID, Guest guest) throws EmptyDB  {
        List list;
        Iterator iter;
        Guest dbGuest;

        list = (ArrayList)dataStore.readGuest();

        if(list == null)
            throw new EmptyDB(EmptyDBMsg);

        guest.setGuestID(guestID);

        iter = list.iterator();
        while(iter.hasNext())   {
            dbGuest = (Guest)iter.next();
            if(dbGuest.getGuestID() == guestID) {
                iter.remove();
                break;
            }
        }
        list.add(guest);

        return dataStore.writeGuest(list);

    }

    public static boolean delete(int guestID) throws EmptyDB   {
        List list;
        Iterator iter;
        Guest guest = read(Integer.toString(guestID), GUEST_READ_METHOD.GUEST_ID);

        int trigger_flag = 0;

        list = (ArrayList)dataStore.readGuest();

        if(list == null)    {
           throw new EmptyDB(EmptyDBMsg);
        }

        iter = list.iterator();
        while(iter.hasNext())   {
            guest = (Guest)iter.next();
            if(guest.getGuestID() == guestID)   {
                iter.remove();
                trigger_flag = 1;
                break;   
            }
        }
        if(trigger_flag == 1)
            return dataStore.writeGuest(list);
        else
            throw new InvalidEntity("Invalid Guest deletion.");
    }
}
package com.sushihotel.guest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Integer;

import com.sushihotel.database.DataStoreFactory;
import com.sushihotel.database.IDataStore;
import com.sushihotel.tools.ReadPropValues;
import com.sushihotel.guest.Guest;

public class GuestModel {
    private static IDataStore dataStore = DataStoreFactory.getDataStore();
    public enum GUEST_READ_METHOD {
        IDENTIFICATION_NO,
        PASSPORT_NO,
        NAME,
        GUEST_ID
    }
    
    public static boolean create(Guest guest)   {
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
                System.out.println("Identification No: " + guest.getIdentificationNo() + " already exist.");
                return false;
            }
            if(dbGuest.getName().toLowerCase().equals(guest.getName().toLowerCase()))  {
                System.out.println("Guest " + guest.getName() +" already exist.");
                return false;
            }
            if(dbGuest.getPassportNumber().toLowerCase().equals(guest.getPassportNumber().toLowerCase())) {
                System.out.println("Passport No: " + guest.getPassportNumber() + " already exist.");
                return false;
            }
        }

        // guestID automatically set on creation
        guest.setGuestID(size + 1);

        // append to the end of the current db list
        list.add(guest);

        if (dataStore.writeGuest(list)) {
            System.out.println(guest.getName() + " successfully registered into the system.");
            return true;
        }
        else    {
            System.out.println("Guest registration failure.");  
            return false;
        }
    }

    public static Guest read(String searchDetails, Enum method)    {
        List list;
        Guest guest;

        list = (ArrayList)dataStore.readGuest();

        if(list == null)
            return null;

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

    public static List<Guest> read(String keyword)  {
        List list = null;
        List<Guest> newList = new ArrayList();
        Guest guest;

        list = (ArrayList)dataStore.readGuest();
        
        if(list == null)
            return null;

        for(int i=0; i<list.size(); i++)    {
            guest = (Guest)list.get(i);
            if(guest.getName().toLowerCase().contains(keyword.toLowerCase()))
                newList.add(guest);
        }

        return newList;
    }

    public static void update(int guestID, Guest guest)  {
        List list;
        Iterator iter;
        Guest dbGuest;

        list = (ArrayList)dataStore.readGuest();

        if(list == null)
            return;

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

        if(dataStore.writeGuest(list))  {
            System.out.println("Successfully updated guest " + guest.getName() + " information.");
        }   else    {
            System.out.println("Failed to update guest " + guest.getName() + " information.");
        }

    }

    public static void delete(int guestID)   {
        List list;
        Iterator iter;
        Guest guest = read(Integer.toString(guestID), GUEST_READ_METHOD.GUEST_ID);

        int trigger_flag = 0;

        list = (ArrayList)dataStore.readGuest();

        if(list == null)    {
            System.out.println("Guest does not exist");
            return;
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
        if(trigger_flag == 1 && dataStore.writeGuest(list))
            System.out.println("Guest " + guest.getName() + " has successfully been removed.");
        else
            System.out.println("Guest " + guest.getName() + " deletion has failed.");
    }
}
package com.sushihotel.guest;

import java.util.List;

import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;
import com.sushihotel.guest.Guest;
import com.sushihotel.guest.GuestModel;

public class GuestMgr {
    public void registerGuest(Guest guest)   {
        try {
            if(GuestModel.create(guest))
                System.out.println("Successfully registered " + guest.getName() + ".");
            else
                System.out.println("Registration for " + guest.getName() + " was unsuccessful. Please try again.");
        } catch(DuplicateData dd)   {
            System.out.println(dd.getMessage());
        } 
    }
    public List<Guest> searchGuestsByName(String guestName)    {
        List<Guest> guestList = null;
        
        try {
            guestList = GuestModel.read(guestName);
        } catch(EmptyDB edb)    {
            System.out.println(edb.getMessage());
        }
        return guestList;
    }
    public Guest searchGuest(String searchData, Enum type)    {
        Guest guest = null;
        
        try {
            guest = GuestModel.read(searchData, type);
        } catch(InvalidEntity ie)   {
            System.out.println(ie.getMessage());
        } catch(EmptyDB edb)    {
            System.out.println(edb.getMessage());
        }
        return guest;
    }
    
    public void editGuest(String searchData, Guest guest, Enum type)   {
        Guest oldGuest;
        int guestID;

        try {
            oldGuest = searchGuest(searchData, type);
            if(oldGuest == null)
                return;

            guestID = oldGuest.getGuestID();
            if(GuestModel.update(guestID, guest))
                System.out.println("Successfully updated "+ guest.getName() + " profile information.");
            else
                System.out.println("System failed to update " + guest.getName() + " profile. Please try again.");
        } catch(EmptyDB edb)  {
            System.out.println(edb.getMessage());
        }
    }
    
    public void removeGuest(String searchData, Enum type)  {
        Guest guest;
        int guestID;

        try {
            guest = searchGuest(searchData, type);
            if(guest == null)
                return;

            guestID = guest.getGuestID();
            if(GuestModel.delete(guestID))
                System.out.println("Successfully removed " + guest.getName() + " from database.");
            else
                System.out.println("System failed to remove " + guest.getName() + " from the database. Please try again.");
        } catch(EmptyDB edb)    {
            System.out.println(edb.getMessage());
        } catch(InvalidEntity ie)   {
            System.out.println(ie.getMessage());
        }
    }
    public void printGuestDetails(Guest guest)  {
        
        // ================= Lauren EDIT HERE !! ==========================
        System.println(
            "====================== Guest Detail ======================\n" +
            "Name: " + guest.getName() + "\n"
        );
        // ================================================================
    }
}
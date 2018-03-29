package com.sushihotel.guest;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;
import com.sushihotel.guest.Guest;
import com.sushihotel.guest.GuestModel;

public class GuestMgr {
    private static final Logger logger = Logger.getLogger(GuestMgr.class.getName());

    public void registerGuest(Guest guest)   {
        try {
            if(GuestModel.create(guest))
                logger.info("Successfully registered " + guest.getName() + ".");
            else
                logger.info("Registration for " + guest.getName() + " was unsuccessful. Please try again.");
            
        } catch(DuplicateData dd)   {
            logger.log(Level.WARNING, dd.getMessage());
        }
    }
    public List<Guest> searchGuestsByName(String guestName)    {
        List<Guest> guestList = null;
        
        try {
            guestList = GuestModel.read(guestName);
        } catch(EmptyDB edb)    {
            logger.log(Level.WARNING, edb.getMessage());
        }
        return guestList;
    }
    public Guest searchGuest(String searchData, Enum type)    {
        Guest guest = null;
        
        try {
            guest = GuestModel.read(searchData, type);

        } catch(InvalidEntity ie)   {
            logger.log(Level.WARNING, ie.getMessage());
        } catch(EmptyDB edb)    {
            logger.log(Level.WARNING, edb.getMessage());
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
                logger.info("Successfully updated "+ guest.getName() + " profile information.");
            else
                logger.info("System failed to update " + guest.getName() + " profile. Please try again.");

        } catch(EmptyDB edb)  {
            logger.log(Level.WARNING, edb.getMessage());
        } catch (InvalidEntity ie)  {
            logger.log(Level.WARNING, ie.getMessage());
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
                logger.info("Successfully removed " + guest.getName() + " from database.");
            else
                logger.info("System failed to remove " + guest.getName() + " from the database. Please try again.");
            
        } catch(EmptyDB edb)    {
            logger.log(Level.WARNING, edb.getMessage());
        } catch(InvalidEntity ie)   {
            logger.log(Level.WARNING, ie.getMessage());
        }
    }
}
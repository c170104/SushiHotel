
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

    public boolean registerGuest(Guest guest)   {
        try {
            if(GuestModel.create(guest))    {
                logger.info("[CREATE SUCCESS] Guest ID: " + Integer.toString(guest.getGuestID()) + " | Guest Name: " + guest.getName());
                return true;
            }
            else    {
                logger.info("[CREATE FAIL] Guest ID: " + Integer.toString(guest.getGuestID()));
            }           
        } catch(DuplicateData dd)   {
            logger.log(Level.WARNING, dd.getMessage());
        }
        return false;
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
    
    public boolean editGuest(String searchData, Guest guest, Enum type)   {
        Guest oldGuest;
        int guestID;

        try {
            oldGuest = searchGuest(searchData, type);
            if(oldGuest == null)
                return false;

            guestID = oldGuest.getGuestID();
            if(GuestModel.update(guestID, guest))   {
                logger.info("[UPDATE SUCCESS] Guest ID: " + Integer.toString(guest.getGuestID()) + " | Guest Name: " + guest.getName());
                return true;
            }
            else
                logger.info("[UPDATE FAIL] Guest ID: " + Integer.toString(guest.getGuestID()));

        } catch(EmptyDB edb)  {
            logger.log(Level.WARNING, edb.getMessage());
        } catch (InvalidEntity ie)  {
            logger.log(Level.WARNING, ie.getMessage());
        }
        return false;
    }
    
    public boolean removeGuest(String searchData, Enum type)  {
        Guest guest;
        int guestID;

        try {
            guest = searchGuest(searchData, type);
            if(guest == null)
                return false;

            guestID = guest.getGuestID();
            if(GuestModel.delete(guestID))  {
                logger.info("[DELETE SUCCESS] Guest ID: " + Integer.toString(guest.getGuestID()) + " | Guest Name: " + guest.getName());
                return true;
            }
            else
                logger.info("[DELETE FAIL] Guest ID: " + Integer.toString(guest.getGuestID()));
            
        } catch(EmptyDB edb)    {
            logger.log(Level.WARNING, edb.getMessage());
        } catch(InvalidEntity ie)   {
            logger.log(Level.WARNING, ie.getMessage());
        }
        return false;
    }
}
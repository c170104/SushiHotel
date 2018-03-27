package com.sushihotel.guest;

import com.sushihotel.exception.DuplicateData;
import com.sushihotel.guest.Guest;
import com.sushihotel.guest.GuestModel;

import sun.jvm.hotspot.debugger.posix.elf.ELFSectionHeader;

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
    // public Guest[] searchGuestsByName(String guestName)    {

    // }
    // public Guest searchGuestByName(String guestName)    {

    // }
    // public Guest searchGuestByPassport(int passportNo)  {

    // }
    // public boolean editGuestByName(String guestName, Guest guest)   {

    // }
    // public boolean editGuestByPassport(int passportNo, Guest guest) {

    // }
    // public boolean removeGuestByName(String guestName)  {

    // }
    // public boolean removeGuestByPassport(int passportNo)    {

    // }
    // public void printGuestDetails(Guest guest)  {

    // }
}
package com.sushihotel.guest.test;

import com.sushihotel.guest.GuestModel;
import com.sushihotel.guest.Guest;
import java.lang.Exception;
import java.util.List;

public class gUT {
    public static void main(String[] args)  {
        try {
            // // ------------- Create Guest ----------
            // Guest guest = new Guest("S123","Shu En", 123, "asd", "1", "2", "3", "4", 5, "a");
            // // GuestModel.create(guest);
            // // Guest g;

            // if(GuestModel.create(guest))    {
            //     System.out.println("Test");
            // }
            // else    {
            //     System.out.println("fail");
            // }

            // // --------- Guest read by Keyword ------
            // List<Guest> list = GuestModel.read("shu");
            // for(int i=0; i<list.size(); i++)    {
            //     // g = (Guest)list.get(i);
            //     System.out.println("id: " + list.get(i).getGuestID() + " name: " + list.get(i).getName());
            // }

            // // ---------- Guest read by type ---------
            // Guest guest = GuestModel.read("b", GuestModel.GUEST_READ_METHOD.PASSPORT_NO);
            // if(guest != null)
            //     System.out.println(guest.getName());
            // else
            //     System.out.println("No such");

            // // ----------- Guest Update ------------
            // Guest g = new Guest("S23445", "Shu En", 234, "asd", "1", "2", "3", "4", 5, "b");
            // Guest dbGuest = GuestModel.read("Shu En", GuestModel.GUEST_READ_METHOD.NAME);

            // GuestModel.update(dbGuest.getGuestID(), g);
            
            // Guest g1 = GuestModel.read("Shu En", GuestModel.GUEST_READ_METHOD.NAME);
            // System.out.println(g1.getIdentificationNo() + " " + g1.getGuestID());

            // // ---------- GUEST Delete -------------
            // Guest guest = GuestModel.read("Shu En", GuestModel.GUEST_READ_METHOD.NAME);
            // GuestModel.delete(guest.getGuestID());
                

        } catch (Exception e)   {
            System.out.println("Stack: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
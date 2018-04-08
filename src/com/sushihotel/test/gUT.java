package com.sushihotel.test;

/*import java.io.*;
import java.util.List;
import java.util.logging.*;

import com.sushihotel.guest.Guest;
import com.sushihotel.guest.GuestMgr;
import com.sushihotel.guest.GuestModel.GUEST_SEARCH_TYPE;;

public class gUT {
    private static final Logger logger = Logger.getLogger(gUT.class.getName());

    public static void main(String[] args) throws IOException  {
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
            // Guest guest = GuestModel.read("b", GuestModel.GUEST_SEARCH_TYPE.PASSPORT_NO);
            // if(guest != null)
            //     System.out.println(guest.getName());
            // else
            //     System.out.println("No such");

            // // ----------- Guest Update ------------
            // Guest g = new Guest("S23445", "Shu En", 234, "asd", "1", "2", "3", "4", 5, "b");
            // Guest dbGuest = GuestModel.read("Shu En", GuestModel.GUEST_SEARCH_TYPE.NAME);

            // GuestModel.update(dbGuest.getGuestID(), g);
            
            // Guest g1 = GuestModel.read("Shu En", GuestModel.GUEST_SEARCH_TYPE.NAME);
            // System.out.println(g1.getIdentificationNo() + " " + g1.getGuestID());

            // // ---------- GUEST Delete -------------
            // Guest guest = GuestModel.read("Shu En", GuestModel.GUEST_SEARCH_TYPE.NAME);
            // GuestModel.delete(guest.getGuestID());
            
            // LOGGER
            Handler files = new FileHandler("log/system.log", true);
            Handler[] handlers = Logger.getLogger("").getHandlers();

            files.setFormatter(new SimpleFormatter());
            for(Handler handler : handlers)
                Logger.getLogger("").removeHandler(handler);
            Logger.getLogger("").addHandler(files);
            
            Guest g;
            GuestMgr gMgr = new GuestMgr();

            // --------- Guest Register ------------
            g = new Guest("S234456", "Shu En 2", 234, "asd", "1", "2", "3", "4", 5, "b12");

            gMgr.registerGuest(g);

            // --------- Guests Search ----------
            String guestsSearch = "sHu";
            List<Guest> gList;

            gList = gMgr.searchGuestsByName(guestsSearch);
            if(gList != null)   {
                for(int i=0; i<gList.size(); i++)   {
                    System.out.println(gList.get(i).getName());
                }
            } 

            // --------- Guest Search by Name ----------
            String guestNameSearch = "Shu";

            g = gMgr.searchGuest(guestNameSearch, GUEST_SEARCH_TYPE.GUEST_NAME);
            if (g != null)
                System.out.println(g.getName());
            
            // --------- Guest Search by Passport No ---
            String passportNoSearch = "b12";

            g = gMgr.searchGuest(passportNoSearch, GUEST_SEARCH_TYPE.PASSPORT_NO);
            if(g != null)
                System.out.println(g.getName());

            // --------- Guest Search by Identification No ---
            String identificationNoSearch = "S234456";

            g = gMgr.searchGuest(identificationNoSearch, GUEST_SEARCH_TYPE.IDENTIFICATION_NO);
            if (g != null)
                System.out.println(g.getName());

            // --------- Guest Update -----------
            String guestNameUpdate = "Shu En 2";
            g = new Guest("S1", "Shu En 2", 234, "asd", "1", "2", "3", "4", 5, "b12");

            gMgr.editGuest(guestNameUpdate, g, GUEST_SEARCH_TYPE.GUEST_NAME);

            // --------- Guest Delete ----------
            String guestNameDelete = "Shu E";
            gMgr.removeGuest(guestNameDelete, GUEST_SEARCH_TYPE.GUEST_NAME);



        } catch (Exception e)   {
            System.out.println("Stack: " + e.getMessage());
            e.printStackTrace();
        }
    }
}*/
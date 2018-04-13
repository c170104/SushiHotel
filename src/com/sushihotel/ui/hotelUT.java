package com.sushihotel.ui;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.sushihotel.hotel.HotelMgr;

public class hotelUT    {
    private static final Logger logger = Logger.getLogger(hotelUT.class.getName());
    private static Scanner sc = new Scanner(System.in);
    private static final HotelMgr hotelMgr = new HotelMgr();

    public static void main(String[] args)  {
        int choice;
        String staffName;
        
        hotelMgr.updateExpiredStatus();
        try {
            /**
             * LOGGING HANDLER
             */
            Handler files = new FileHandler("log/system.log", true);
            Handler[] handlers = Logger.getLogger("").getHandlers();

            files.setFormatter(new SimpleFormatter());
            for(Handler handler : handlers)
                Logger.getLogger("").removeHandler(handler);
            Logger.getLogger("").addHandler(files);
            /**
             * LOGGING HANDLER END
             */

            System.out.println(
                "========= Welcome to Sushi Hotel =========\n" +
                "Please enter your name: "
                );
            staffName = sc.next();
            System.out.println("Hello, " + staffName + ".\n");
            do {
                System.out.println(
                    "Please select an option(1-18):\n" +
                    "=============== GUEST MATTERS ===============\n" +
                    "1) Register Guest\n" +
                    "2) Update Guest Details\n"+
                    "3) Search Guest\n"+
                    "============ RESERVATION MATTERS ============\n" +
                    "4) New Reservation\n"+
                    "5) Edit Reservation\n"+
                    "6) Print Reservation\n"+
                    "7) Remove Reservation\n" +
                    "================ ROOM MATTERS ===============\n" +
                    "8) Create Room\n" +
                    "9) Edit Room Details\n" +
                    "================ MENU MATTERS ===============\n" +
                    "10) Add New Menu Item\n" +
                    "11) Edit Menu Item\n" +
                    "12) Remove Menu Item\n" +
                    "============ OPERATIONAL MATTERS ============\n" +
                    "13) Order Room Service\n"+
                    "14) Check Room Availability\n" +
                    "15) Check In\n" +
                    "16) Check Out\n" +
                    "17) Print Room Occupancy Rate (One Day)\n" +
                    "18) Print Reservation List\n" +
                    "19) Check Room Service Status\n" +
                    "20) Exit\n" +
                    "Choice (1-20): "
                );
                choice = sc.nextInt();

                switch(choice)  {
                    case 1:
                        hotelMgr.guestRegistration();
                        break;
                    case 2:
                        hotelMgr.updateGuestInformation();
                        break;
                    case 3:
                        hotelMgr.searchGuests();
                        break;
                    case 4:
                        hotelMgr.newReservation();
                        break;
                    case 5:
                        hotelMgr.editReservation();
                        break;
                    case 6:
                        int reservationID;
                        System.out.println("Please input Reservation ID to print: ");
                        reservationID = sc.nextInt();
                        sc.nextLine();
                        hotelMgr.printReservation(reservationID);
                        break;
                    case 7:
                        hotelMgr.removeReservation();
                        break;
                    case 8:
                        hotelMgr.createRoom();
                        break;
                    case 9:
                        hotelMgr.updateRoomDetails();
                        break;
                    case 10:
                        hotelMgr.addMenuItem();
                        break;
                    case 11:
                        hotelMgr.editMenuItem();
                        break;
                    case 12:
                        hotelMgr.removeMenuItem();
                        break;
                    case 13:
                        hotelMgr.callRoomService();
                        break;
                    case 14:
                        hotelMgr.checkRoomAvailability();
                        break;
                    case 15:
                        hotelMgr.checkIn();
                        break;
                    case 16:
                        hotelMgr.checkOut();
                        break;
                    case 17:
                        hotelMgr.printRoomStatusStatisticReport();
                        break;
                    case 18:
                    	hotelMgr.printReservationList();     
                    	break;
                    case 19:
                    	hotelMgr.checkRoomServiceStatus();
                        break;
                    case 99:
                        hotelMgr.setDummyData();
                        break;
                    default:
                        break;
                }
            } while (choice != 20);
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println("Invalid Choice. Please try again.");
        } catch(IOException ioe)    {
            logger.severe(ioe.getMessage());
            System.out.println("System failure. Please contact the system developer.");
        }
    }
}
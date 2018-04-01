package com.sushihotel.hotel;

import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.*;

import com.sushihotel.guest.Guest;
import com.sushihotel.guest.GuestMgr;
import com.sushihotel.invoice.Invoice;
import com.sushihotel.invoice.InvoiceMgr;
import com.sushihotel.room.Room;
import com.sushihotel.room.RoomMgr;

public class HotelMgr   {
    private GuestMgr guestMgr = new GuestMgr();
    private RoomMgr roomMgr = new RoomMgr();
    private InvoiceMgr invoiceMgr = new InvoiceMgr();

    private Scanner sc = new Scanner(System.in);

    private static final Logger logger = Logger.getLogger(HotelMgr.class.getName());

    private static final String errorMsg = "Error. Invalid input. Please Try again";


    /*
     * START OF GUEST CRUD
     *
     */
    public void guestRegistration()  {
        Guest guest;
        String identificationNo;
        String name;
        int creditCardNo;
        String billingAddress;
        String address;
        String country;
        String gender;
        String nationality;
        int contactNo;
        String passportNo;

        try {
            // Guest Registration Begins
            System.out.println("============ Guest Registration ============");
            System.out.println("Please enter Identification Number: ");
            identificationNo = sc.next();
            System.out.println("Please enter Name: ");
            name = sc.next();
            System.out.println("Please enter Credit Card Number: ");
            creditCardNo = sc.nextInt();
            System.out.println("Please enter Billing Address: ");
            billingAddress = sc.next();
            System.out.println("Please enter Address: ");
            address = sc.next();
            System.out.println("Please enter Country: ");
            country = sc.next();
            System.out.println("Please enter Gender: ");
            gender = sc.next();
            System.out.println("Please enter Nationality: ");
            nationality = sc.next();
            System.out.println("Please enter Contact Number: ");
            contactNo = sc.nextInt();
            System.out.println("Please enter Passport Number: ");
            passportNo = sc.next();

            // Guest object creation
            guest = new Guest(identificationNo, name, creditCardNo, billingAddress, address, country, gender, nationality, contactNo, passportNo);

            if(guestMgr.registerGuest(guest))
                System.out.println("Guest " + name + " has been succesfully registered!");
            else
                System.out.println("System failed to register Guest " + name + ". Please try again.");
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(errorMsg);
        }
    }

    public void updateGuestInformation()   {
        Guest guest;
        int guestID;
        String identificationNo;
        String name;
        int creditCardNo;
        String billingAddress;
        String address;
        String country;
        String gender;
        String nationality;
        int contactNo;
        String passportNo;
        int choice;

        try {
            System.out.println("Please enter the exact name of the Guest to be updated: ");
            name = sc.next();

            guest = guestMgr.searchGuest(name, Guest.GUEST_SEARCH_TYPE.GUEST_NAME);

            // Set old values
            guestID = guest.getGuestID;
            identificationNo = guest.getIdentificationNo();
            name = guest.getName();
            creditCardNo = guest.getCreditCardNumber();
            billingAddress = guest.getBillingAddress();
            address = guest.getAddress();
            country = guest.getCountry();
            gender = guest.getGender();
            nationality = guest.getNationality();
            contactNo = guest.getContactNumber();
            passportNo = guest.getPassportNumber();

            do {
                System.out.println(
                    "Choose the option (1-11) to update: \n" + 
                    "1) Identification Number\n" +
                    "2) Name\n" +
                    "3) Credit Card Number\n" +
                    "4) Billing Address\n" +
                    "5) Address\n" +
                    "6) Country\n" +
                    "7) Gender\n" +
                    "8) Nationality\n" +
                    "9) Contact Number\n" +
                    "10) Passport Number\n" +
                    "11) Exit\n\n" + 
                    "Choice: "
                );
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Identification Number: ");
                        identificationNo = sc.next();
                        break;
                    case 2:
                        System.out.println("Name: ");
                        name = sc.next();
                        break;
                    case 3:
                        System.out.println("Credit Card Number: ");
                        creditCardNo = sc.nextInt();
                        break;
                    case 4:
                        System.out.println("Billing Address: ");
                        billingAddress = sc.next();
                        break;
                    case 5:
                        System.out.println("Address: ");
                        address = sc.next();
                        break;
                    case 6:
                        System.out.println("Country: ");
                        country = sc.next();
                        break;
                    case 7:
                        System.out.println("Gender: ");
                        gender = sc.next();
                        break;
                    case 8:
                        System.out.println("Nationality: ");
                        nationality = sc.next();
                        break;
                    case 9:
                        System.out.println("Contact Number: ");
                        contactNo = sc.nextInt();
                        break;
                    case 10:
                        System.out.println("Passport Number: ");
                        passportNo = sc.next();
                        break;
                    default:
                        break;
                }
            } while (choice != 11);

            // Set new values
            guest = new Guest(identificationNo, name, creditCardNo, billingAddress, address, country, gender, nationality, contactNo, passportNo);

            if(guestMgr.editGuest(guestID, guest))
                System.out.println("Succesfully updated Guest " + name + " information.");
            else
                System.out.println("System failed to update Guest " + name + ". Please try again.");

        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(errorMsg);
        } catch(NullPointerException npe)   {
            logger.severe(npe.getMessage());
            System.out.println("No such guest");
        }
    }

    public void searchGuests()  {
        List<Guest> guestList;
        Guest guest;
        String guestName;

        try {
            System.out.println("Please enter the name to search: ");
            guestName = sc.next();

            guestList = guestMgr.searchGuestsByName(guestName);

            System.out.println(
                "============ Guest Search ============"
            );
            // triggers NullPointerException if guestList is null
            for(int i=0; i<guestList.size(); i++)   {
                guest = guestList.get(i);
                System.out.println(
                    "Guest Name: \t" + guest.getName() + "\n" +
                    "Identification Number:\t" + guest.getIdentificationNo() + "\n" + 
                    "Address:\t" + guest.getAddress() + "\n" +
                    "Country:\t" + guest.getCountry() + "\n" +
                    "Gender:\t" + guest.getGender() + "\n" +
                    "Nationality:\t" + guest.getNationality() + "\n" +
                    "Contact Number:\t" + guest.getContactNumber() + "\n" +
                    "Passport Number:\t" + guest.getPassportNumber() + "\n" +
                    "====================================="
                ); 
            }
            System.out.println(
                "=========== End of Search ==========="
            );
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(errorMsg);
        } catch(NullPointerException npe)   {
            logger.severe(npe.getMessage());
            System.out.println("No results.");
        }
    }

    /*
     * END OF GUEST CRUD
     *
     */

    /*
     * START OF RESERVERATION CRUD
     * 
     */

    public void newReservation()    {

    }

    public void editReservation()   {

    }

    public void removeReservation() {

    }

    public void printReservation()  {

    }

    /**
     * START OF ROOM CRUD
     * 
     */

    public void createRoom()    {
        Room room;
        int roomNumber;
        Enum roomType;
        String roomTypeInput;
        int maxNoAdults;
        int maxNoChild;
        float rateWeekdays;
        float rateWeekends;
        String bedType;
        boolean wifiEnabled = false;
        String wifiEnabledInput;
        String facingView;
        boolean smokingAllowed = false;
        String smokingAllowedInput;
        String unitNumber;

        try {
            // Room creation begins
            System.out.println("========== Room Creation ==========");
            System.out.println("Please Enter Room Number: ");
            roomNumber = sc.nextInt();
            while(true) {
                System.out.println("Please Enter Room Type (Single/Double/Deluxe/VIP): ");
                roomTypeInput = sc.next();
                if(roomTypeInput.toLowerCase().equals("single")) {
                    roomType = Room.ROOM_TYPE.SINGLE;
                    break;
                }
                else if(roomTypeInput.toLowerCase().equals("double"))    {
                    roomType = Room.ROOM_TYPE.DOUBLE;
                    break;
                }
                else if(roomTypeInput.toLowerCase().equals("deluxe"))    {
                    roomType = Room.ROOM_TYPE.DELUXE;
                    break;
                }
                else if(roomTypeInput.toLowerCase().equals("vip"))   {
                    roomType = Room.ROOM_TYPE.VIP;
                    break;
                }
                else
                    System.out.println("Please input only (Single/Double/Deluxe/VIP).");
            }
            System.out.println("Please Enter Max number of adults: ");
            maxNoAdults = sc.nextInt();
            System.out.println("Please Enter Max number of children: ");
            maxNoChild = sc.nextInt();
            System.out.println("Please Enter Weekday Rate: ");
            rateWeekdays = sc.nextFloat();
            System.out.println("Please Enter Weekend Rate: ");
            rateWeekends = sc.nextFloat();
            System.out.println("Please Enter Bed Type Information: ");
            bedType = sc.next();
            System.out.println("Wifi Enabled (Yes/No)(default: No): ");
            wifiEnabledInput = sc.next();
            if(wifiEnabledInput.toLowerCase().equals("yes"))
                wifiEnabled = true;
            System.out.println("Please Enter Room View information: ");
            facingView = sc.next();
            System.out.println("Smoking Allowed (Yes/No)(default: No): ");
            smokingAllowedInput = sc.next();
            if(smokingAllowedInput.toLowerCase().equals("yes"))
                smokingAllowed = true;
            System.out.println("Please Enter Unit Number: ");
            unitNumber = sc.next();

            room = new Room(roomNumber, roomType, maxNoAdults, maxNoChild, rateWeekdays, rateWeekends, bedType, wifiEnabled, facingView, smokingAllowed, unitNumber);

            if(roomMgr.createRoom(room))
                System.out.println("Room number " + roomNumber + " has been succesfully created.");
            else
                System.out.println("System has failed to create Room number " + roomNumber + ". Please try again.");
            
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(errorMsg);
        }
    }

    public void updateRoomDetails() {
        Room room;
        int roomNumber;
        Enum roomType;
        int maxNoAdults;
        int maxNoChild;
        float rateWeekdays;
        float rateWeekends;
        String bedType;
        boolean wifiEnabled;
        String facingView;
        boolean smokingAllowed;
        Enum roomStatus;
        String unitNumber;
        String wifiEnabledInput;
        String smokingAllowedInput;
        String roomStatusInput;
        int choice;

        try {
            System.out.println("Please enter the Room Number you want to update: ");
            roomNumber = sc.nextInt();

            room = roomMgr.getRoom(roomNumber);

            roomType = room.getRoomType();
            maxNoAdults = room.getMaxNoAdults();
            maxNoChild = room.getMaxNoChild();
            rateWeekdays = room.getRateWeekdays();
            rateWeekends = room.getRateWeekend();
            bedType = room.getBedType();
            wifiEnabled = room.getWifiEnabled();
            facingView = room.getFacingView();
            smokingAllowed = room.getSmokingAllowed();
            roomStatus = room.getRoomStatus();
            unitNumber = room.getUnitNumber();

            do {
                System.out.println(
                    "Choose the options(1-1) to update:\n" +
                    "1) Room Type\n" +
                    "2) Max Number of Adults\n" +
                    "3) Max Number of Chilren\n" +
                    "4) Weekdays Rate\n" +
                    "5) Weekends Rate\n" +
                    "6) Bed Type\n" +
                    "7) Wifi Enabled\n" +
                    "8) View Facing information\n" +
                    "9) Smoking Allowed\n" +
                    "10) Room Status\n" +
                    "11) Unit Number\n" +
                    "12) Exit\n" +
                    "Choice: "
                );
                choice = sc.nextInt();

                switch(choice)  {
                    case 1:
                        while(true) {
                            System.out.println("Please Enter Room Type (Single/Double/Deluxe/VIP): ");
                            roomTypeInput = sc.next();
                            if(roomTypeInput.toLowerCase().equals("single")) {
                                roomType = Room.ROOM_TYPE.SINGLE;
                                break;
                            }
                            else if(roomTypeInput.toLowerCase().equals("double"))    {
                                roomType = Room.ROOM_TYPE.DOUBLE;
                                break;
                            }
                            else if(roomTypeInput.toLowerCase().equals("deluxe"))    {
                                roomType = Room.ROOM_TYPE.DELUXE;
                                break;
                            }
                            else if(roomTypeInput.toLowerCase().equals("vip"))   {
                                roomType = Room.ROOM_TYPE.VIP;
                                break;
                            }
                            else
                                System.out.println("Please input only (Single/Double/Deluxe/VIP).");
                        }
                        break;
                    case 2:
                        System.out.println("Please Enter Max number of adults: ");
                        maxNoAdults = sc.nextInt();
                        break;
                    case 3:
                        System.out.println("Please Enter Max number of children: ");
                        maxNoChild = sc.nextInt();
                        break;
                    case 4:
                        System.out.println("Please Enter Weekday Rate: ");
                        rateWeekdays = sc.nextFloat();
                        break;
                    case 5:
                        System.out.println("Please Enter Weekend Rate: ");
                        rateWeekends = sc.nextFloat();
                        break;
                    case 6:
                        System.out.println("Please Enter Bed Type Information: ");
                        bedType = sc.next();
                        break;
                    case 7:
                        System.out.println("Wifi Enabled (Yes/No)(default: No): ");
                        wifiEnabledInput = sc.next();
                        if(wifiEnabledInput.toLowerCase().equals("yes"))
                            wifiEnabled = true;
                        else if(wifiEnabledInput.toLowerCase().equals("no"))
                            wifiEnabled = false;
                        break;
                    case 8:
                        System.out.println("Please Enter Room View information: ");
                        facingView = sc.next();
                        break;
                    case 9:
                        System.out.println("Smoking Allowed (Yes/No)(default: No): ");
                        smokingAllowedInput = sc.next();
                        if(smokingAllowedInput.toLowerCase().equals("yes"))
                            smokingAllowed = true;
                        else if(smokingAllowedInput.toLowerCase().equals("no"))
                            smokingAllowed = false;
                        break;
                    case 10:
                        while(true) {
                            System.out.println("Please Enter Room Status(Vacant/Occupied/Reserved/Under Maintenance): ");
                            roomStatusInput = sc.next();
                            if(roomStatusInput.toLowerCase().equals("vacant"))   {
                                roomStatus = Room.ROOM_STATUS.VACANT;
                                break;
                            }
                            else if(roomStatusInput.toLowerCase().equals("occupied"))    {
                                roomStatus = Room.ROOM_STATUS.OCCUPIED;
                                break;
                            }
                            else if(roomStatusInput.toLowerCase().equals("reserved"))    {
                                roomStatus = Room.ROOM_STATUS.RESERVED;
                                break;
                            }
                            else if(roomStatusInput.toLowerCase().equals("under maintenance"))   {
                                roomStatus = Room.ROOM_STATUS.UNDER_MAINTENANCE;
                                break;
                            }
                            else
                                System.out.println("Please input only (Vacant/Occupied/Reserved/Under Maintenance).");
                        }
                    case 11:
                        System.out.println("Please Enter Unit Number: ");
                        unitNumber = sc.next();
                        break;
                    default:
                        break;
                }
            } while (choice != 12);

            if(roomMgr.editRoom(roomNumber, room))
                System.out.println("Successfully updated Room number " + roomNumber);
            else
                System.out.println("System failed to update Room number " + roomNumber + ". Please try again.");
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(errorMsg);
        } catch(NullPointerException npe)   {
            logger.severe(npe.getMessage());
            System.out.println("No such room");
        }
    }

    /**
     * END OF ROOM CRUD
     * 
     */

    /*
     * START OF MENU CRUD
     *
     */

    public void createMenu()    {

    }

    public void updateMenu()    {

    }

    public void removeItemFromMenu()    {

    }

    /*
     * END OF MENU CRUD
     * 
     */

    /**
     * 
     * START OF OPERATIONAL/BUSINESS LOGIC
     */

    public void callRoomService()   {
        int roomNumber;
        float roomSvc = 0.0f;
        String roomSvcDate;
        List<Meal> menu;
        int menuChoice;
        try {
            System.out.println("============== Room Service ==============");
            System.out.println("Please enter the Room number: ");
            roomNumber = sc.nextInt();

            // Print Menu by roomSvcMgr
            
            do {
                System.out.println("Choice of meal by Meal ID (0 to exit): ");
                // get individual meal by roomsvcMgr
                menuChoice = sc.nextInt();
                roomSvc += menuChoice.getPrice();
            } while (menuChoice != 0);

            if(invoiceMgr.addRoomSvc(roomNumber, roomSvc))
                System.out.println("Room Service has successfully been placed for Room number " + roomNumber + ".");
            else
                System.out.println("System has failed to place Room service for Room number " + roomNumber + ". Please try again.");
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
        }
    }

    public void checkRoomAvailability() {
        int roomNumber;
        int choice;
        try {
            System.out.println(
                "========================= Check Room Availability =========================\n" +
                "Please select an option(1-4) to check Room Availability:\n" +
                "1) By Room Number\n" +
                "2) By Room Availability\n" +
                "3) By Room Type\n" +
                "4) Exit\n" +
                "Choice: "
                );
            choice = sc.nextInt();

            switch(choice)  {
                case 1:
                    System.out.println("Please enter Room number: ");
                    roomNumber = sc.nextInt();
                    if(roomMgr.checkRoomAvailability(roomNumber))
                        System.out.println("Room number " + roomNumber + " is Available.");
                    else
                        System.out.println("Room number " + roomNumber + " is currently unavailable.");
                    break;
                case 2:
                    roomMgr.printRoomStatusByAvailability();
                    break;
                case 3:
                    roomMgr.printRoomStatusByRoomType();
                    break;
                default:
                    break;
            }
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage);
            System.out.println(errorMsg);
        }
    }

    public void checkIn()    {
        
    }

    public void checkOut()  {
        String paymentMethod = invoice.getCashPayment() ? "Cash" : "Credit Card";
        
        System.out.println(
            "========== SushiHotel Bill ==========\n" + 
            "Room number:\t\t" + invoice.getRoomNumber() + "\n" +
            "Checked in on:\t\t" + invoice.getCheckInDate() + "\n" + 
            "Checked out on:\t\t" + invoice.getCheckOutDate() + "\n" +
            "Room charges:\t\t$" + invoice.getRoomCharges() + "\n" + 
            "Room Service charges:\t$" + invoice.getRoomSvc() + "\n" +
            "Late Fees:\t\t$" + invoice.getLateFees() + "\n" +
            "Discount:\t\t" + invoice.getDiscount() + "%\n" + 
            "Tax:\t\t\t" + invoice.getTax() + "%\n" +
            "Total Bill:\t\t$" + invoice.getTotalBill() + "\n" +
            "====================================="
        );
    }

    public void printRoomStatusStatisticReport()    {

    }

    /**
     * 
     * END OF OPERATIONAL/BUSINESS LOGIC
     */
}
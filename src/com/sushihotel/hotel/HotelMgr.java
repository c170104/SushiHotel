package com.sushihotel.hotel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.sushihotel.guest.Guest;
import com.sushihotel.guest.GuestMgr;
import com.sushihotel.invoice.Invoice;
import com.sushihotel.invoice.InvoiceMgr;
import com.sushihotel.menu.Meal;
import com.sushihotel.menu.MenuMgr;
import com.sushihotel.reservation.ReservationMgr;
import com.sushihotel.room.Room;
import com.sushihotel.room.RoomMgr;
import com.sushihotel.roomservice.RoomSvc;
import com.sushihotel.roomservice.RoomSvcMgr;

public class HotelMgr   {
    private GuestMgr guestMgr = new GuestMgr();
    private RoomMgr roomMgr = new RoomMgr();
    private InvoiceMgr invoiceMgr = new InvoiceMgr();
    private MenuMgr menuMgr = new MenuMgr();
    private ReservationMgr reservationMgr = new ReservationMgr();
    private RoomSvcMgr roomSvcMgr = new RoomSvcMgr();
    private Scanner sc = new Scanner(System.in);
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final String ERROR_MSG = "Error. Invalid input. Please Try again";
    private final float TAX_RATE = 0.17f;

    private static final Logger logger = Logger.getLogger(HotelMgr.class.getName());


    /*
     * START OF GUEST CRUD
     * 
     * METHODS:
     * guestRegistration()
     * updateGuestInformation()
     * searchGuests()
     * searchGuest() private
     */
    public void guestRegistration()  {
        Guest guest;
        String identificationNo;
        String name;
        String creditCardNo;
        String billingAddress;
        String address;
        String country;
        String gender;
        String nationality;
        String contactNo;
        String passportNo;

        try {
            // Guest Registration Begins
            System.out.println("============ Guest Registration ============");
            System.out.println("Please enter Identification Number: ");
            identificationNo = sc.nextLine();
            System.out.println("Please enter Name: ");
            name = sc.nextLine();
            System.out.println("Please enter Credit Card Number: ");
            creditCardNo = sc.nextLine();
            System.out.println("Please enter Billing Address: ");
            billingAddress = sc.nextLine();
            System.out.println("Please enter Address: ");
            address = sc.nextLine();
            System.out.println("Please enter Country: ");
            country = sc.nextLine();
            System.out.println("Please enter Gender: ");
            gender = sc.nextLine();
            System.out.println("Please enter Nationality: ");
            nationality = sc.nextLine();
            System.out.println("Please enter Contact Number: ");
            contactNo = sc.nextLine();
            System.out.println("Please enter Passport Number: ");
            passportNo = sc.nextLine();

            // Guest object creation
            guest = new Guest(identificationNo, name, creditCardNo, billingAddress, address, country, gender, nationality, contactNo, passportNo);

            if(guestMgr.registerGuest(guest))
                System.out.println("Guest " + name + " has been succesfully registered!");
            else
                System.out.println("System failed to register Guest " + name + ". Please try again.");
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        }
    }

    public void updateGuestInformation()   {
        Guest guest;
        int guestID;
        String identificationNo;
        String name;
        String creditCardNo;
        String billingAddress;
        String address;
        String country;
        String gender;
        String nationality;
        String contactNo;
        String passportNo;
        int choice;

        try {
            System.out.println("Please enter the exact name of the Guest to be updated: ");
            name = sc.nextLine();

            guest = guestMgr.searchGuest(name, Guest.GUEST_SEARCH_TYPE.GUEST_NAME);

            // Set old values
            guestID = guest.getGuestID();
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
                System.out.print(
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
                    "11) Update\n\n" + 
                    "Choice: "
                );
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Identification Number: ");
                        identificationNo = sc.nextLine();
                        break;
                    case 2:
                        System.out.println("Name: ");
                        name = sc.nextLine();
                        break;
                    case 3:
                        System.out.println("Credit Card Number: ");
                        creditCardNo = sc.nextLine();
                        break;
                    case 4:
                        System.out.println("Billing Address: ");
                        billingAddress = sc.nextLine();
                        break;
                    case 5:
                        System.out.println("Address: ");
                        address = sc.nextLine();
                        break;
                    case 6:
                        System.out.println("Country: ");
                        country = sc.nextLine();
                        break;
                    case 7:
                        System.out.println("Gender: ");
                        gender = sc.nextLine();
                        break;
                    case 8:
                        System.out.println("Nationality: ");
                        nationality = sc.nextLine();
                        break;
                    case 9:
                        System.out.println("Contact Number: ");
                        contactNo = sc.nextLine();
                        break;
                    case 10:
                        System.out.println("Passport Number: ");
                        passportNo = sc.nextLine();
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
            System.out.println(ERROR_MSG);
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
            guestName = sc.nextLine();

            guestList = guestMgr.searchGuestsByName(guestName);

            System.out.println(
                "============ Guest Search ============"
            );
            // triggers NullPointerException if guestList is null
            for(int i=0; i<guestList.size(); i++)   {
                guest = guestList.get(i);
                System.out.println(
                    "Guest Name:\t\t" + guest.getName() + "\n" +
                    "Identification Number:\t" + guest.getIdentificationNo() + "\n" + 
                    "Address:\t\t" + guest.getAddress() + "\n" +
                    "Country:\t\t" + guest.getCountry() + "\n" +
                    "Gender:\t\t\t" + guest.getGender() + "\n" +
                    "Nationality:\t\t" + guest.getNationality() + "\n" +
                    "Contact Number:\t\t" + guest.getContactNumber() + "\n" +
                    "Passport Number:\t" + guest.getPassportNumber()
                ); 
            }
            System.out.println(
                "=========== End of Search ==========="
            );
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        } catch(NullPointerException npe)   {
            logger.severe(npe.getMessage());
            System.out.println("No results.");
        }
    }

    private Guest searchGuest() {
        int choice;
        Enum type = null;
        String searchData;
        Guest guest = null;
        try {
            do {
                System.out.print("Get guest details by:\n" + "1) Guest Name\n" + "2) Guest Identification Number\n"
                        + "3) Guest Passport Number\n" + "Choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                case 1:
                    type = Guest.GUEST_SEARCH_TYPE.GUEST_NAME;
                    break;
                case 2:
                    type = Guest.GUEST_SEARCH_TYPE.IDENTIFICATION_NO;
                    break;
                case 3:
                    type = Guest.GUEST_SEARCH_TYPE.PASSPORT_NO;
                    break;
                }

                if (type != null) {
                    System.out.print("Please enter Search data: ");
                    searchData = sc.nextLine();
                    guest = guestMgr.searchGuest(searchData, type);
                    if (guest == null)
                        choice = 0;
                }
            } while (choice > 3 || choice < 1);
        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        }
        return guest;
    }

    /*
     * END OF GUEST CRUD
     *
     */

    /*
     * START OF RESERVERATION CRUD
     * 
     * METHODS:
     * newReservation()
     * editReservation()
     * removeReservation()
     * printReservation()
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
     * METHODS:
     * createRoom()
     * updateRoomDetails()
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

            sc.nextLine(); // Remove line carriage

            while(true) {
                System.out.println("Please Enter Room Type (Single/Double/Deluxe/VIP): ");
                roomTypeInput = sc.nextLine();
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
            sc.nextLine();
            System.out.println("Please Enter Max number of children: ");
            maxNoChild = sc.nextInt();
            sc.nextLine();
            System.out.println("Please Enter Weekday Rate: ");
            rateWeekdays = sc.nextFloat();
            sc.nextLine();
            System.out.println("Please Enter Weekend Rate: ");
            rateWeekends = sc.nextFloat();
            sc.nextLine();
            System.out.println("Please Enter Bed Type Information: ");
            bedType = sc.nextLine();
            System.out.println("Wifi Enabled (Yes/No)(default: No): ");
            wifiEnabledInput = sc.nextLine();
            if(wifiEnabledInput.toLowerCase().equals("yes"))
                wifiEnabled = true;
            System.out.println("Please Enter Room View information: ");
            facingView = sc.nextLine();
            System.out.println("Smoking Allowed (Yes/No)(default: No): ");
            smokingAllowedInput = sc.nextLine();
            if(smokingAllowedInput.toLowerCase().equals("yes"))
                smokingAllowed = true;
            System.out.println("Please Enter Unit Number: ");
            unitNumber = sc.nextLine();

            room = new Room(roomNumber, roomType, maxNoAdults, maxNoChild, rateWeekdays, rateWeekends, bedType, wifiEnabled, facingView, smokingAllowed, unitNumber);

            if(roomMgr.createRoom(room))
                System.out.println("Room number " + roomNumber + " has been succesfully created.");
            else
                System.out.println("System has failed to create Room number " + roomNumber + ". Please try again.");
            
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
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
        String roomTypeInput;
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
                    "1) Room Type: " + roomType.toString() + "\n" +
                    "2) Max Number of Adults: " + Integer.toString(maxNoAdults) + "\n" +
                    "3) Max Number of Chilren: " + Integer.toString(maxNoChild) + "\n" +
                    "4) Weekdays Rate: " + Float.toString(rateWeekdays) + "\n" +
                    "5) Weekends Rate: " + Float.toString(rateWeekends) + "\n" +
                    "6) Bed Type: " + bedType + "\n" +
                    "7) Wifi Enabled: " + (wifiEnabled ? "Yes" : "No") + "\n" +
                    "8) View Facing information: " + facingView + "\n" +
                    "9) Smoking Allowed: " + (smokingAllowed ? "Yes" : "No") + "\n" +
                    "10) Room Status: " + roomStatus.toString() + "\n" +
                    "11) Unit Number: " + unitNumber + "\n" +
                    "12) Update\n" +
                    "Choice: "
                );
                choice = sc.nextInt();

                sc.nextLine(); // remove line carriage

                switch(choice)  {
                    case 1:
                        while(true) {
                            System.out.println("Please Enter Room Type (Single/Double/Deluxe/VIP): ");
                            roomTypeInput = sc.nextLine();
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
                        bedType = sc.nextLine();
                        break;
                    case 7:
                        System.out.println("Wifi Enabled (Yes/No)(default: No): ");
                        wifiEnabledInput = sc.nextLine();
                        if(wifiEnabledInput.toLowerCase().equals("yes"))
                            wifiEnabled = true;
                        else if(wifiEnabledInput.toLowerCase().equals("no"))
                            wifiEnabled = false;
                        break;
                    case 8:
                        System.out.println("Please Enter Room View information: ");
                        facingView = sc.nextLine();
                        break;
                    case 9:
                        System.out.println("Smoking Allowed (Yes/No)(default: No): ");
                        smokingAllowedInput = sc.nextLine();
                        if(smokingAllowedInput.toLowerCase().equals("yes"))
                            smokingAllowed = true;
                        else if(smokingAllowedInput.toLowerCase().equals("no"))
                            smokingAllowed = false;
                        break;
                    case 10:
                        while(true) {
                            System.out.println("Please Enter Room Status(Vacant/Occupied/Reserved/Under Maintenance): ");
                            roomStatusInput = sc.nextLine();
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
                        unitNumber = sc.nextLine();
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
            System.out.println(ERROR_MSG);
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
     * METHODS:
     * addMenuItem()
     * editMenuItem())
     * removeMenuItem()
     */

    public void addMenuItem()    {

    }

    public void editMenuItem()    {

    }

    public void removeMenuItem()    {

    }

    /*
     * END OF MENU CRUD
     * 
     */

    /**
     * START OF OPERATIONAL/BUSINESS LOGIC
     * 
     * METHODS:
     * callRoomService()
     * checkRoomAvailability()
     * checkIn()
     * checkOut()
     * printRoomStatusStatisticReport()
     */

    public void callRoomService()   {
        int roomNumber;
        int menuChoice;
        int roomSvcID;
        float amountPayable = 0.0f;
        RoomSvc roomSvc;
        String remarks;
        List<Meal> menu;
        Meal meal;
        Date dateTimeOrdered = new Date(); 

        try {
            System.out.println("============== Room Service ==============");
            System.out.println("Please enter the Room number: ");
            roomNumber = sc.nextInt();

            // Print Menu by roomSvcMgr
            menu = menuMgr.getMealOffered();
            System.out.println("========================== MENU ==========================");
            for(int i=0; i<menu.size(); i++)    {
                meal = menu.get(i);
                System.out.println(
                    "| ID: " + Integer.toString(meal.getMealID()) + "\tName: " + meal.getMealName() + "\tPrice ($):" + meal.getMealPrice() + "\t|\n"
                );
            }
            System.out.println("==========================================================");
            
            do {
                System.out.println("Choice of meal by Meal ID (0 to exit): ");
                // get individual meal by roomsvcMgr
                menuChoice = sc.nextInt();
                meal = menuMgr.getMealDetails(menuChoice);
                amountPayable += meal.getMealPrice();
            } while (menuChoice != 0);

            System.out.println("Please enter the room service remarks: ");
            remarks = sc.nextLine();
            roomSvc = new RoomSvc(roomNumber, amountPayable, remarks, dateTimeOrdered);

            if(roomSvcMgr.addNewRoomSvc(roomSvc))   {
                roomSvcID = roomSvcMgr.getRoomSvcID(roomNumber);
                if(invoiceMgr.addRoomSvc(roomNumber, roomSvcID))    {
                    roomSvcMgr.updateRoomSvcStatus(roomSvcID, RoomSvc.ROOM_SVC_STATUS.PREPARING);
                    System.out.println("Room Service has successfully been placed for Room number " + roomNumber + ".");
                }
                else
                    System.out.println("System has failed to place Room service for Room number " + roomNumber + ". Please try again.");
            }
            else
                System.out.println("System has failed to add new room service. Please try again");
                
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        } catch(NullPointerException npe)   {
            logger.severe(npe.getMessage());
            System.out.println("No such Meal ID.");
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
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        }
    }

    private boolean checkExistingGuest()   {
        int choice = 0;
        try {
            do {
                System.out.print(
                    "Existing Guest?\n" + 
                    "1) Yes\n" +
                    "2) No\n" +
                    "Choice: "
                );
                choice = sc.nextInt();
                sc.nextLine();
            } while (choice != 1 && choice != 2);
            
        } catch(InputMismatchException ime) {
            logger.warning(ime.getMessage());
            System.out.println(ERROR_MSG);
            return checkExistingGuest();
        }
        if (choice == 1)
            return true;
        return false;
    }

    public void checkIn()    {
        int choice;
        Guest guest;
        Invoice invoice;
        String checkInDate;
        String checkOutDate;
        int roomNumber;
        int totalWeekdays;
        int totalWeekends;
        

        try {
            System.out.println("============ CHECK IN ============");
            do {
                System.out.print(
                    "Made a prior Reservation?\n" +
                    "1) Yes\n" +
                    "2) No\n" +
                    "3) Exit\n" +
                    "Choice: "
                );
                choice = sc.nextInt();
                sc.nextLine();

                if(choice == 1) {
                    // Made prior reservations 
                }
                else if(choice == 2)    {
                    if(!checkExistingGuest())
                        guestRegistration();
                    guest = searchGuest();

                    // Create blank invoice
                    System.out.print("Please input room number: ");
                    roomNumber = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Please input Check In Date in the format (dd/MM/yyyy HH:mm): ");
                    checkInDate = sc.nextLine();
                    System.out.print("Please input Check Out Date in the format (dd/MM/yyyy HH:mm): ");
                    checkOutDate = sc.nextLine();
                    System.out.print("Please input total weekdays of stay: ");
                    totalWeekdays = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Please input total weekends of stay: ");
                    totalWeekends = sc.nextInt();
                    sc.nextLine();

                    invoice = new Invoice(guest.getGuestID(), roomNumber, formatter.parse(checkInDate), formatter.parse(checkOutDate), totalWeekdays, totalWeekends);
                    if(invoiceMgr.createBlankInvoice(invoice))  {
                        System.out.println("Check In for guest " + guest.getName() + " into Room number " 
                                + Integer.toString(roomNumber) + " is successful!");
                        break;
                    }
                    else    {
                        System.out.println("Check In for guest " + guest.getName() + " into Room number "
                                + Integer.toString(roomNumber) + " is unsuccessful. Please try again.");
                        break;
                    }
                }

            } while (choice != 3);
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        } catch(ParseException pe)  {
            logger.severe(pe.getMessage());
            System.out.println("Date Time format is wrong. Please try again.");
        }
    }

    private void printBill(Invoice invoice) {
        try {
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
        } catch(NullPointerException npe)   {
            logger.severe(npe.getMessage());
            System.out.println("An error has occured. Please contact the System administrator.");
        }
    }

    public void checkOut()  {
        Invoice invoice;
        int roomNumber;
        float roomSvcTotalPayable = 0.0f;
        List<Integer> roomServicesID;
        Room room;
        RoomSvc roomSvc;
        float weekDayRate;
        float weekEndRate;
        float lateFees;
        float discount;
        String paymentMethodInput;
        boolean cashPayment = true;

        try {
            System.out.println("Please enter Room number to check out: ");
            roomNumber = sc.nextInt();
            sc.nextLine();

            invoice = invoiceMgr.getUnpaidInvoice(roomNumber);

            // calculate total room services incurred
            roomServicesID =  invoice.getRoomSvc();
            for(int i=0; i<roomServicesID.size(); i++)    {
                roomSvc = roomSvcMgr.getRoomSvc(roomServicesID.get(i));
                roomSvcTotalPayable += roomSvc.getAmountPayable();
            }

            // retrieve room's weekday and weekend rates
            room = roomMgr.getRoom(roomNumber);
            weekDayRate = room.getRateWeekdays();
            weekEndRate = room.getRateWeekend();

            System.out.println("Please input the following:");
            System.out.print("Late Fees (if any): ");
            lateFees = sc.nextFloat();
            sc.nextLine();
            System.out.print("Discount: ");
            discount = sc.nextFloat();
            sc.nextLine();
                

            // add charges
            if(invoiceMgr.addCharges(roomNumber, discount, TAX_RATE, lateFees, roomSvcTotalPayable, weekDayRate, weekEndRate))  {
                System.out.println("Payment method (cash/card)(default: cash): ");
                paymentMethodInput = sc.nextLine();
                if(paymentMethodInput.toLowerCase().equals("card"))
                    cashPayment = false;

                if(invoiceMgr.makePayment(roomNumber, cashPayment)) {
                    System.out.println("Check Out for Room " + Integer.toString(roomNumber) + " is successful.");

                    // print bill
                    invoice = invoiceMgr.getInvoice(invoice.getInvoiceID());
                    printBill(invoice);
                }
                else
                    System.out.println("Check Out is unsuccesfull for Room " + Integer.toString(roomNumber) + ". Please try again.");
            }
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        }
    }

    public void printRoomStatusStatisticReport()    {

    }

    /**
     * 
     * END OF OPERATIONAL/BUSINESS LOGIC
     */
}
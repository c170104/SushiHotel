package com.sushihotel.hotel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.sushihotel.guest.Guest;
import com.sushihotel.guest.GuestMgr;
import com.sushihotel.invoice.Invoice;
import com.sushihotel.invoice.InvoiceMgr;
import com.sushihotel.menu.Meal;
import com.sushihotel.menu.MenuMgr;
import com.sushihotel.reservation.Reservation;
import com.sushihotel.reservation.Reservation.RESERVE_STATUS;
import com.sushihotel.reservation.ReservationMgr;
import com.sushihotel.room.Room;
import com.sushihotel.room.Room.ROOM_TYPE;
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
            System.out.println("Please enter Name: ");
            name = sc.nextLine();
            System.out.println("Please enter Gender (F/M/O): ");
            gender = sc.nextLine();
            System.out.println("Please enter Identification Number: ");
            identificationNo = sc.nextLine();
            System.out.println("Please enter Passport Number: ");
            passportNo = sc.nextLine();
            System.out.println("Please enter Nationality: ");
            nationality = sc.nextLine();
            System.out.println("Please enter Address: ");
            address = sc.nextLine();
            System.out.println("Please enter Contact Number: ");
            contactNo = sc.nextLine();
            System.out.println("Please enter Credit Card Number: ");
            creditCardNo = sc.nextLine();
            System.out.println("Please enter Billing Address: ");
            billingAddress = sc.nextLine();
            System.out.println("Please enter Country: ");
            country = sc.nextLine();

            // Guest object creation
            guest = new Guest(identificationNo, name, creditCardNo, billingAddress, address, country, gender, nationality, contactNo, passportNo);

            if(guestMgr.registerGuest(guest))
                System.out.println("Guest " + name + " has been successfully registered!");
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
                    "Choose the option (1-10) to update: \n" +
                    "1) Name\n" +
                    "2) Gender\n" +
                    "3) Identification Number\n" +
                    "4) Passport Number\n" +
                    "5) Nationality\n" +
                    "6) Address\n" +
                    "7) Contact Number\n" +
                    "8) Credit Card Number\n" +
                    "9) Billing Address\n" +
                    "10) Country\n" +
                    "11) Exit\n\n"+
                    "Choice: "
                );
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Name: ");
                        name = sc.nextLine();
                        break;
                    case 2:
                        System.out.println("Gender ");
                        gender = sc.nextLine();
                        break;
                    case 3:
                        System.out.println("Identification Number: ");
                        identificationNo = sc.nextLine();
                        break;
                    case 4:
                        System.out.println("Passport Number: ");
                        passportNo = sc.nextLine();
                        break;
                    case 5:
                        System.out.println("Nationality: ");
                        nationality = sc.nextLine();
                        break;
                    case 6:
                        System.out.println("Address: ");
                        address = sc.nextLine();
                        break;
                    case 7:
                        System.out.println("Contact Number: ");
                        contactNo = sc.nextLine();
                        break;
                    case 8:
                        System.out.println("Credit Card Number: ");
                        creditCardNo = sc.nextLine();
                        break;
                    case 9:
                        System.out.println("Billing Address ");
                        billingAddress = sc.nextLine();
                        break;
                    case 10:
                        System.out.println("Country: ");
                        country = sc.nextLine();
                        break;
                    default:
                        break;
                }
            } while (choice != 11);

            // Set new values
            guest = new Guest(identificationNo, name, creditCardNo, billingAddress, address, country, gender, nationality, contactNo, passportNo);

            if(guestMgr.editGuest(guestID, guest))
                System.out.println("Successfully updated Guest " + name + " information.");
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
                    "ID Number:\t" + guest.getIdentificationNo() + "\n" +
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

    public void newReservation() {
        Guest guest;
        Reservation reservation;
        String guestName;
        int roomNumber;
        int reservationID;
        Date checkInDate = null;
        Date checkOutDate = null;
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        Date currentDate = new Date();
        String checkInDateInput;
        String checkOutDateInput;
        int numAdults;
        int numChild;
        int numberOfWeekdays = 0;
        int numberOfWeekends = 0;
        boolean dateCheck = false;

        try {
            System.out.println("======Input Reservation Details=====");
            System.out.println("Current date time " + formatter.format(currentDate));
            while(true) {
                System.out.println("Please enter Guest Name");
                guestName = sc.nextLine();
                guest = guestMgr.searchGuest(guestName, Guest.GUEST_SEARCH_TYPE.GUEST_NAME);
                if(guest == null)
                    System.out.println("Guest does not exist");
                else
                    break;
            }
            System.out.println("Please enter Room Number");
            roomNumber = sc.nextInt();
            sc.nextLine();
            
            do {
                do {
                    System.out.println("Please enter Check In Date (dd/MM/yyyy)");
                    try {
                        checkInDateInput = sc.nextLine();
                        checkInDate = formatter.parse(checkInDateInput + " 14:00");
                        if (checkInDate.before(currentDate)) {
                        	dateCheck = false;
                        	System.out.println("Check in date has already passed current date. Try again");
                        } else {
                            dateCheck = true;
                        }
                    } catch (ParseException pe) {
                        System.out.println("Incorrect date time format");
                        dateCheck = false;
                    }
                } while (!dateCheck);

                do {
                    System.out.println("Please enter Check Out Date (dd/MM/yyyy)");
                    try {
                        checkOutDateInput =sc.nextLine();
                        checkOutDate = formatter.parse(checkOutDateInput + " 12:00");
                        dateCheck = true;
                    } catch (ParseException pe) {
                        System.out.println("Incorrect date time format");
                        dateCheck = false;
                    }
                } while (!dateCheck);

                if (checkInDate.compareTo(checkOutDate) >= 0) {
                    System.out.println("Check in date can't be the same or later than the check out date, try again!");
                    dateCheck = false;
                }
            } while (!dateCheck);
            
        	startCal.setTime(checkInDate);
        	endCal.setTime(checkOutDate);
            do {
                if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    numberOfWeekdays++;
                } else {
                	numberOfWeekends++;
                }
            	startCal.add(Calendar.DAY_OF_MONTH, 1);
            } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date
            
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                numberOfWeekdays++;
            } else {
            	numberOfWeekends++;
            }
            System.out.println("Weekdays & Weekends: " + numberOfWeekdays + " " + numberOfWeekends);
            
            
//            System.out.println("Please enter Number of Weekdays");
//            numberOfWeekdays = sc.nextInt();
//            sc.nextLine();
//            System.out.println("Please enter Number of Weekends");
//            numberOfWeekends = sc.nextInt();
//            sc.nextLine();

            System.out.println("Please enter Number of Adults");
            numAdults = sc.nextInt();
            sc.nextLine();
            System.out.println("Please enter Number of Children");
            numChild = sc.nextInt();
            sc.nextLine();
            

            reservation = new Reservation(guestName, roomNumber, checkInDate, checkOutDate, numAdults, numChild, numberOfWeekdays, numberOfWeekends);
            
            reservationID = reservationMgr.beginReservation(reservation);

            if (reservationID != -1) {
                System.out.println("Reservation has been successfully made for guest " + guestName);
                printReservation(reservationID);
            }
            else
                System.out.println("Reservation was unsuccessful. Please try again.");
        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        } catch (NullPointerException npe) {
            logger.severe(npe.getMessage());
            System.out.println("No such reservation");
        }
    }

    public void editReservation() {
        Reservation reservation;
        Guest guest = null;
        int reservationID;
        String guestName;
        int roomNumber;
        Date checkInDate;
        Date checkOutDate;
        Date currentDate = new Date();
        String checkInDateInput;
        String checkOutDateInput;
        int numAdults;
        int numChild;
        Enum reserveStatus;
        int numberOfWeekdays;
        int numberOfWeekends;
        int choice;
        int statusSelection;
        boolean dateCheck;
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();

        try {
            System.out.println("Please enter reservation ID to be edited ");
            reservationID = sc.nextInt();
            sc.nextLine();

            reservation = reservationMgr.getReservationByID(reservationID);      
        
            guestName = reservation.getGuestName();
            roomNumber = reservation.getRoomNumber();
            checkInDate = reservation.getCheckInDate();
            checkOutDate = reservation.getCheckOutDate();
            numAdults = reservation.getNumAdults();
            numChild = reservation.getNumChild();
            reserveStatus = reservation.getReserveStatus();
            numberOfWeekdays = reservation.getNoOfWeekdays();
            numberOfWeekends = reservation.getNoOfWeekends();

            do {
                System.out.print(
                    "Choose the option (1-8) to update: \n" +
                    "1) Name: " + guestName + "\n" +
                    "2) Room number: " + Integer.toString(roomNumber) + "\n" +
                    "3) Check in date: " + formatter.format(checkInDate)+ "\n" +
                    "4) Check out date: " + formatter.format(checkOutDate) + "\n" +
                    "5) Number of adults: " + Integer.toString(numAdults) + "\n" +
                    "6) Number of children: " + Integer.toString(numChild) + "\n" +
                    "7) Reserve status: " + reserveStatus.toString() + "\n" +
//                    "8) Number of weekdays: " + Integer.toString(numberOfWeekdays) + "\n" +
//                    "9) Number of weekends: " + Integer.toString(numberOfWeekends) + "\n" +
                    "8) Exit/Update\n\n" +
                    "Choice: "
                );
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        while(guest == null)  {
                            System.out.print("Input new name: ");
                            guestName = sc.nextLine();
                            guest = guestMgr.searchGuest(guestName, Guest.GUEST_SEARCH_TYPE.GUEST_NAME);
                            if(guest == null)   {
                                System.out.println("Guest does no exist, please try again.");
                            }
                            else
                                break;
                        }
                        break;
                    case 2:
                        while(true) {
                            System.out.print("Input new Room Number: ");
                            roomNumber = sc.nextInt();
                            sc.nextLine();
                            if(roomMgr.checkRoomAvailability(roomNumber))
                                break;
                            else
                                System.out.println("Room number " + Integer.toString(roomNumber) + " is not available.");
                        }
                        break;
                    case 3:
                        dateCheck = false;
                        do {
                            System.out.println("Input new check in date dd/MM/yyyy");
                            try {
                                checkInDateInput = sc.nextLine();
                                checkInDate = formatter.parse(checkInDateInput + " 14:00");
                                if (checkInDate.before(currentDate)) {
                                	dateCheck = false;
                                	System.out.println("Check in date has already passed current date. Try again");
                                } else if (checkInDate.after(checkOutDate)){
                                	System.out.println("Check in is after check out date. Try again");
                                	dateCheck = false;
                                } else {
                                	dateCheck = true;
                                }
                            } catch (ParseException pe) {
                                System.out.println("Incorrect date time format");
                                logger.severe(pe.getMessage());
                            }
                        } while (!dateCheck);
                        
                        ///// Calculates number of weekday & weekend /////
                        
                        numberOfWeekdays = 0;
                        numberOfWeekends = 0;
                        startCal.setTime(checkInDate);
                        endCal.setTime(checkOutDate);
                        System.out.println(checkInDate);
                        System.out.println(checkOutDate);
                        do {
                            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                numberOfWeekdays++;
                            } else {
                            	numberOfWeekends++;
                            }
                        	startCal.add(Calendar.DAY_OF_MONTH, 1);
                        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date
                        
                        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                            numberOfWeekdays++;
                        } else {
                        	numberOfWeekends++;
                        }
                        System.out.println("Number of weekedays: " + numberOfWeekdays + "\nNumber of Weekends: " + numberOfWeekends);
                        break;
                        
                        
                    case 4:
                        dateCheck = false;
                        do {
                            System.out.println("Input new check out date dd/MM/yyyy");
                            try {
                                checkOutDateInput = sc.nextLine();
                                checkOutDate = formatter.parse(checkOutDateInput + " 12:00");
                                if (checkOutDate.before(checkInDate)) {
                                	dateCheck = false;
                                	System.out.println("Check out date can't be before check in date. Try again");
                                } else {
                                	dateCheck = true;
                                }
                            } catch (ParseException pe) {
                                System.out.println("Incorrect date time format");
                                logger.severe(pe.getMessage());
                            }
                        } while (!dateCheck);
                        
                        ///// Calculates number of weekday & weekend /////
                        
                        numberOfWeekdays = 0;
                        numberOfWeekends = 0;
                        startCal.setTime(checkInDate);
                        endCal.setTime(checkOutDate);
                        System.out.println(checkInDate);
                        System.out.println(checkOutDate);
                        do {
                            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                numberOfWeekdays++;
                            } else {
                            	numberOfWeekends++;
                            }
                        	startCal.add(Calendar.DAY_OF_MONTH, 1);
                        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date
                        
                        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                            numberOfWeekdays++;
                        } else {
                        	numberOfWeekends++;
                        }
                        System.out.println("Number of weekedays: " + numberOfWeekdays + "\nNumber of Weekends: " + numberOfWeekends);
                        break;
                        
                    case 5:
                        System.out.println("Input new number of adult");
                        numAdults = sc.nextInt();
                        sc.nextLine();
                        break;
                        
                    case 6:
                        System.out.println("Input new number of children");
                        numChild = sc.nextInt();
                        sc.nextLine();
                        break;
                        
                    case 7:
                        while(true) {
                            System.out.println("Select reserve status \n" + "1) CONFIRMED\n" + "2) WAITLIST\n"
                                    + "3) CHECKED_IN\n" + "4) EXPIRED");
                            statusSelection = sc.nextInt();
                            sc.nextLine();

                            if (statusSelection == 1) {
                                reserveStatus = RESERVE_STATUS.CONFIRMED;
                                break;
                            }
                            else if (statusSelection == 2) {
                                reserveStatus = RESERVE_STATUS.WAITLIST;
                                break;
                            }
                            else if (statusSelection == 3) {
                                reserveStatus = RESERVE_STATUS.CHECKED_IN;
                                break;
                            }
                            else if (statusSelection == 4) {
                                reserveStatus = RESERVE_STATUS.EXPIRED;
                                break;
                            }
                            else
                                System.out.println("Please select a correct input (1-4).");
                        }
                        break;
//                    case 8:
//                        System.out.println("Input number of weekdays ");
//                        numberOfWeekdays = sc.nextInt();
//                        sc.nextLine();
//                        break;
//
//                    case 9:
//                        System.out.println("Input number of weekends ");
//                        numberOfWeekends = sc.nextInt();
//                        sc.nextLine();
//                        break;

                    default:
                        break;
                }
            } while (choice != 8);

            reservation = new Reservation(guestName, roomNumber, checkInDate, checkOutDate, numAdults, numChild, numberOfWeekdays, numberOfWeekends, reserveStatus);
            if (reservationMgr.editReservation(reservationID, reservation)) {
                System.out.println("Succesfully updated reservationID " + reservationID + " information.");
            } else {
                System.out.println("System failed to update reservation " + reservationID + ". Please try again.");
            }

        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        } catch (NullPointerException npe) {
            logger.severe(npe.getMessage());
            System.out.println("No such reservation");
        }
    }
    
    
    public void removeReservation() {
        int reservationID;
        String confirmation;
        try {
            System.out.println("Please enter reservation ID to be deleted");
            reservationID = sc.nextInt();
            sc.nextLine();

            System.out.print("Are you sure you want to remove reservation id  " + Integer.toString(reservationID) + "? (Y/N): ");
            confirmation = sc.nextLine();
            if(!confirmation.toLowerCase().equals("y") && !confirmation.toLowerCase().equals("yes"))    {
                System.out.println("Removal of reservation id " + Integer.toString(reservationID) + " is canceled.");
                return;
            }

            if (reservationMgr.deleteReservation(reservationID)) {
                System.out.println("Deletion of resevation " + reservationID + " was successful");
            } else {
                System.out.println("Deletion of resevation " + reservationID + " was unsuccessful, please try again");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Wrong number input format");
        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        }
    }

    public void printReservation(int reservationID) {
        Reservation reservation = null;
        
        try {
            reservation = reservationMgr.getReservationByID(reservationID);
            System.out.println(
                "====================================================================" +
                "\nReservation ID: " + Integer.toString(reservation.getReservationID()) +
                "\nRoom Number: " + Integer.toString(reservation.getRoomNumber()) + 
                "\nNo. of Adults: " + Integer.toString(reservation.getNumAdults()) + 
                "\nNo. of Childrens: " + Integer.toString(reservation.getNumChild()) + 
                "\nCheck In Date: " + reservation.getCheckInDate() + 
                "\nCheck Out Date: " + reservation.getCheckOutDate() + 
                "\nNo. of Weekdays: " + Integer.toString(reservation.getNoOfWeekdays()) + 
                "\nNo. of Weekends: " + Integer.toString(reservation.getNoOfWeekends()) + 
                "\nReservation Status: " + reservation.getReserveStatus().toString() +
                "\n===================================================================="
            );
        } catch (NullPointerException npe) {
            logger.severe(npe.getMessage());
            System.out.println("No such reservation.");
        }
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
            System.out.println("Please Enter Room Number (1 - 48): ");
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
            System.out.println("Please Enter Bed Type Information (Single/Double/Master): ");
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
            System.out.println("Please Enter Unit Number (e.g. 02-01): ");
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
                        break;
                    case 11:
                        System.out.println("Please Enter Unit Number: ");
                        unitNumber = sc.nextLine();
                        break;
                    default:
                        break;
                }
            } while (choice != 12);

            room = new Room(roomNumber, roomType, maxNoAdults, maxNoChild, rateWeekdays, rateWeekends, bedType, wifiEnabled, facingView, smokingAllowed, roomStatus, unitNumber);
            if(roomMgr.editRoom(roomNumber, room))
                System.out.println("Successfully updated Room number " + roomNumber + ".");
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
        Meal meal;
        String mealName;
        String description;
        String preparedMethod;
        float mealPrice;
        
        try {
            System.out.println("===========Insert new meal==============");
            System.out.println("Please enter meal name: ");
            mealName = sc.nextLine();
            System.out.println("Please enter meal description: ");
            description = sc.nextLine();
            System.out.println("Please enter prepared method: ");
            preparedMethod = sc.nextLine();
            System.out.println("Please enter meal price: ");
            mealPrice = sc.nextFloat();
            sc.nextLine();
            
            meal = new Meal(mealName, description, preparedMethod, mealPrice);
            
            if (menuMgr.addNewMeal(meal)) {
                System.out.println("Meal " + mealName + " has been successfully registered! ");
            } else {
                System.out.println("System failed to register Meal" + mealName + ". Please try again");
            }
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        }
    }

    public void editMenuItem()    {
        Meal meal;
        int mealID;
        String mealName;
        String description;
        String preparedMethod;
        float mealPrice;
        int choice;
        try {
            System.out.println("Please enter the meal ID you want to edit: ");
            mealID = sc.nextInt();
            sc.nextLine();
            
            meal = menuMgr.getMealDetails(mealID);
            
            mealID = meal.getMealID();
            mealName = meal.getMealName();
            description = meal.getDesc();
            preparedMethod = meal.getPreparedMethod();
            mealPrice = meal.getMealPrice();
            
            do {
                System.out.println(
                    "Choose the option 1-5 to update: \n" +
                    "1) Meal Name: " + mealName + "\n" +
                    "2) Description: " + description + "\n" +
                    "3) Prepared Method: " + preparedMethod + "\n" +
                    "4) Meal Price: " + mealPrice + "\n" +
                    "5) Exit/Update" + "\n\n" +
                    "Choice: "
                );
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Meal Name: ");
                        mealName = sc.nextLine();
                        break;
                    case 2:
                        System.out.println("Description: ");
                        description = sc.nextLine();
                        break;
                    case 3:
                        System.out.println("Prepared Method: ");
                        preparedMethod = sc.nextLine();
                        break;
                    case 4: 
                        System.out.println("Meal Price");
                        mealPrice = sc.nextFloat();
                        sc.nextLine();
                        break;
                    default:
                        System.out.println("Please enter a valid choice (1-5).");
                        break;
                }
            } while (choice != 5);
            
            meal = new Meal(mealName, description, preparedMethod, mealPrice);
            if(menuMgr.editMeal(mealID, meal)) {
                System.out.println("Succesfully updated Meal ID " + Integer.toString(mealID) + " details.");
            } else {
                System.out.println("System failed to update Meal ID " + Integer.toString(mealID) + ". Please try again.");
            }
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        } catch(NullPointerException npe)   {
            logger.severe(npe.getMessage());
            System.out.println("No such meal");
        }
    }

    public void removeMenuItem()    {
        int mealID;
        String decision;

        try {  
            System.out.println("Please enter meal ID to be deleted");
            mealID = sc.nextInt();
            sc.nextLine();

            System.out.println("Are you sure you want to delete Meal ID: " + Integer.toString(mealID) + "? (Y/N): ");
            decision = sc.nextLine();

            if(!decision.toLowerCase().equals("yes") && !decision.toLowerCase().equals("y"))    {
                System.out.println("Deletion has been canceled.");
                return;
            }

            if (menuMgr.removeMeal(mealID)) {
                System.out.println("Deletion of meal number " + mealID +" was successful");
            } else {
                System.out.println("Deletion of meal number " + mealID +" was unsuccessful, please try again");
            } 
        } catch (InputMismatchException ime) {
                logger.severe(ime.getMessage());
                System.out.println(ERROR_MSG);
        } catch (NullPointerException npe)   {
            logger.severe(npe.getMessage());
            System.out.println("No meal data");
        }
    }

    public void printMealList() {
        Meal meal;
        List<Meal> mealList;
        String mealPrice;
        try {
            mealList = menuMgr.getMealOffered();
            if(mealList.size() == 0)    {
                System.out.println("The Menu is currently empty.");
                return;
            }
            System.out.println("========================== MENU ==========================");
            for(int i=0; i<mealList.size(); i++)    {
                meal = mealList.get(i);
                mealPrice = String.format("%.2f", meal.getMealPrice());
                System.out.println(
                    "Meal ID: " + meal.getMealID() + 
                    "\n Meal Name: " + meal.getMealName() + 
                    "\n Description: " + meal.getDesc() + 
                    "\n Prepared Method: " + meal.getPreparedMethod() + 
                    "\n Meal Price: $"+ mealPrice
                );
            }
            System.out.println("==========================================================");
        } catch (NullPointerException npe)   {
            logger.severe(npe.getMessage());
            System.out.println("The Menu is currently empty.");
        }
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
            sc.nextLine();

            // Print Menu by roomSvcMgr
            printMealList();
            
            while(true) {
                System.out.println("Choice of meal by Meal ID (0 to exit): ");
                // get individual meal by roomsvcMgr
                menuChoice = sc.nextInt();
                sc.nextLine();

                if(menuChoice == 0)
                    break;
                meal = menuMgr.getMealDetails(menuChoice);
                amountPayable += meal.getMealPrice();
            }
            
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
                System.out.println("System has failed to add new room service. Please try again.");
                
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
        Room room;
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
            sc.nextLine();

            switch(choice)  {
                case 1:
                    System.out.println("Please enter Room number: ");
                    roomNumber = sc.nextInt();
                    sc.nextLine();
                    room = roomMgr.getRoom(roomNumber);
                    System.out.println("\nUnit number " + room.getUnitNumber() + "(" + roomNumber + ") is " + room.getRoomStatus().toString() + ".\n");
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

    public void checkIn()    {
        int choice;
        Guest guest;
        Reservation reservation = null;
        Invoice invoice;
        Date checkInDate = null;
        Date checkOutDate = null;
        Date currentDate = new Date();
        String checkInDateInput;
        String checkOutDateInput;
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        int roomNumber = 0;
        int reservationID = -1;
        int totalWeekdays = 0;
        int totalWeekends = 0;
        boolean dateCheck = false;

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

                // get guest details
                guest = searchGuest();
                if(guest == null)   {
                    System.out.println("Guest does not exist. Please register first, then try again.");
                    return;
                }

                if(choice == 1) {
                    System.out.print("Please input the Reservation ID: ");
                    reservationID = sc.nextInt();
                    sc.nextLine();

                    reservation = reservationMgr.getReservationByID(reservationID);
                    if(reservation == null) {
                        System.out.println("Reservation ID " + Integer.toString(reservationID) + " does not exist.");
                        return;
                    }

                    if(reservation.getReserveStatus() == Reservation.RESERVE_STATUS.CHECKED_IN) {
                        System.out.println("Reservation ID " + Integer.toString(reservationID) + " has already checked in.");
                        return;
                    }

                    // Next in the waitlist to check in
                    if(reservation.getReserveStatus() == Reservation.RESERVE_STATUS.CONFIRMED)  {
                        roomNumber = reservation.getRoomNumber();
                        totalWeekdays = reservation.getNoOfWeekdays();
                        totalWeekends = reservation.getNoOfWeekends();
                        checkInDate = reservation.getCheckInDate();
                        checkOutDate = reservation.getCheckOutDate();
                    }
                }
                else if(choice == 2)    {

                    // Create blank invoice
                    System.out.print("Please input room number: ");
                    roomNumber = sc.nextInt();
                    sc.nextLine();
           
                    
                    do {
                        do {
                            System.out.println("Please enter Check In Date (dd/MM/yyyy)");
                            try {
                                checkInDateInput = sc.nextLine();
                                checkInDate = formatter.parse(checkInDateInput + " 14:00");
                                if (checkInDate.before(currentDate)) {
                                	dateCheck = false;
                                	System.out.println("Check in date has already passed current date. Try again");
                                } else {
                                    dateCheck = true;
                                }
                            } catch (ParseException pe) {
                                System.out.println("Incorrect date time format");
                                dateCheck = false;
                            }
                        } while (!dateCheck);

                        do {
                            System.out.println("Please enter Check Out Date (dd/MM/yyyy)");
                            try {
                                checkOutDateInput =sc.nextLine();
                                checkOutDate = formatter.parse(checkOutDateInput + " 12:00");
                                dateCheck = true;
                            } catch (ParseException pe) {
                                System.out.println("Incorrect date time format");
                                dateCheck = false;
                            }
                        } while (!dateCheck);

                        if (checkInDate.compareTo(checkOutDate) >= 0) {
                            System.out.println("Check in date can't be the same or later than the check out date, try again!");
                            dateCheck = false;
                        }
                    } while (!dateCheck);
                    
                	startCal.setTime(checkInDate);
                	endCal.setTime(checkOutDate);
                    do {
                        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                            totalWeekdays++;
                        } else {
                        	totalWeekends++;
                        }
                    	startCal.add(Calendar.DAY_OF_MONTH, 1);
                    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date
                    
                    if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                        totalWeekdays++;
                    } else {
                    	totalWeekends++;
                    }
                    System.out.println("Weekdays & Weekends: " + totalWeekdays + " " + totalWeekends);
                    
                    
                    
//                    System.out.print("Please input total weekdays of stay: ");
//                    totalWeekdays = sc.nextInt();
//                    sc.nextLine();
//                    System.out.print("Please input total weekends of stay: ");
//                    totalWeekends = sc.nextInt();
//                    sc.nextLine();
                }

                invoice = new Invoice(guest.getGuestID(), roomNumber, checkInDate, checkOutDate, totalWeekdays, totalWeekends);

                if(invoiceMgr.createBlankInvoice(invoice))  {
                    if(reservationMgr.reservationCheckInChanges(reservationID))  {
                        System.out.println("Check In for guest " + guest.getName() + " into Room number " 
                                + Integer.toString(roomNumber) + " is successful!");
                        roomMgr.setRoomToOccupied(roomNumber, Room.ROOM_STATUS.OCCUPIED);
                        return;
                    }
                }
                else    {
                    System.out.println("Check In for guest " + guest.getName() + " into Room number "
                            + Integer.toString(roomNumber) + " is unsuccessful. Please try again.");
                    return;
                }
                
            } while (choice != 3);
            
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        }
    }

    private void printBill(Invoice invoice) {
        try {
            int roomSvcID;
            RoomSvc roomSvc;
            List<Integer> list;
            System.out.println(
                "========== SushiHotel Bill ==========\n" + 
                "Room number:\t\t" + invoice.getRoomNumber() + "\n" +
                "Checked in on:\t\t" + formatter.format(invoice.getCheckInDate())+ "\n" + 
                "Checked out on:\t\t" + formatter.format(invoice.getCheckOutDate()) + "\n" +
                "Room charges:\t\t$" + invoice.getRoomCharges() + "\n" + 
                "Room Service charges:\t$" + invoice.getRoomSvcTotalCharges() + "\n" +
                "Late Fees:\t\t$" + invoice.getLateFees() + "\n" +
                "Discount:\t\t" + invoice.getDiscount() + "%\n" + 
                "Tax:\t\t\t" + invoice.getTax() + "%\n" +
                "Total Bill:\t\t$" + invoice.getTotalBill() + "\n" +
                "====================================="
            );
            list = invoice.getRoomSvc();

            System.out.println("======= Room Services Ordered =======");
            if(list.size() == 0)
                System.out.println("No Room service was ordered during the stay.\n");
            for(int i=0; i<list.size(); i++)    {
                roomSvcID = list.get(i);
                roomSvc = roomSvcMgr.getRoomSvc(roomSvcID);
                System.out.println(
                    "Room Service ID: " + Integer.toString(roomSvcID) + "\n" +
                    "Date Ordered: " + formatter.format(roomSvc.getDateTimeOrdered()) + "\n" +
                    "Service Bill: " + Float.toString(roomSvc.getAmountPayable()) + "\n" +
                    "Remarks: " + roomSvc.getRemarks() + "\n" +
                    "====================================="
                    );
            }
        } catch(NullPointerException npe)   {
            logger.severe(npe.getMessage());
            System.out.println("An error has occurred. Please contact the System administrator.");
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
                    // change room status back to vacant
                    roomMgr.setRoomToOccupied(roomNumber, Room.ROOM_STATUS.VACANT);
                    // remove reservation
                    reservationMgr.removeReservationAfterCheckOut(roomNumber);
                    // print bill
                    invoice = invoiceMgr.getInvoice(invoice.getInvoiceID());
                    printBill(invoice);
                }
                else
                    System.out.println("Check Out is unsuccessful for Room " + Integer.toString(roomNumber) + ". Please try again.");
            }
        } catch(InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        }
    }

    public void printRoomStatusStatisticReport()    {	

		Reservation reservation;
		Room room;
		int roomNumber = 0;
        int singleOccupied = 0, singleTotal = 0;
        int doubleOccupied = 0, doubleTotal = 0;
        int deluxeOccupied = 0, deluxeTotal = 0;
        int totalOccupied = 0, hotelTotal = 0;
        double orSingle;
        double orDouble;
        double orDeluxe;
        double orVip;
        double orTotal;
        int vipOccupied = 0, vipTotal = 0;
        String singleUnitNumber = "";
        String doubleUnitNumber = "";
        String deluxeUnitNumber = "";
        String vipUnitNumber = "";

		List reservationList;
		Date date = null;
		boolean dateCheck = false;
		
		System.out.println("Enter required occupancy (date dd/MM/yyyy)");
		do {
    		try {
    			String inputDate = sc.nextLine();
        		date = formatter.parse(inputDate + " 14:00");
        		dateCheck = true;
    		} catch (ParseException pe) {
    			System.out.println("Incorrect date time format");
    			dateCheck = false;
    		}
		} while (!dateCheck);

		try {
			reservationList = reservationMgr.getReservationList();
			if (reservationList.size() == 0) {
				System.out.println("Hotel is not occupied");
				return;
			}
			for (int i = 0; i<reservationList.size(); i++) {
				reservation = (Reservation)reservationList.get(i);
				roomNumber = reservation.getRoomNumber();
				room = roomMgr.getRoom(roomNumber);

				if (room.getRoomType() == Room.ROOM_TYPE.SINGLE) {
					singleTotal++;
					if (date.after(reservation.getCheckInDate()) && date.before(reservation.getCheckOutDate())) {
    						singleUnitNumber += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
    						singleOccupied++;
						}
					}
				if (room.getRoomType() == ROOM_TYPE.DOUBLE) {
					doubleTotal++;
					if (date.after(reservation.getCheckInDate()) && date.before(reservation.getCheckOutDate())) {
							doubleUnitNumber += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
							doubleOccupied++;
						}
					}
					
				if (room.getRoomType() == ROOM_TYPE.DELUXE) {
					deluxeTotal++;
					if (date.after(reservation.getCheckInDate()) && date.before(reservation.getCheckOutDate())) {
							deluxeUnitNumber += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
							deluxeOccupied++;
						}
					}
					
				if (room.getRoomType() == ROOM_TYPE.VIP) {
					vipTotal++; 
					if (date.after(reservation.getCheckInDate()) && date.before(reservation.getCheckOutDate())) {
							vipUnitNumber += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
							vipOccupied++;
						}
					}
				}
			orSingle = (double)singleOccupied/(double)singleTotal * 100.0;
			orDouble = (double)doubleOccupied/(double)doubleTotal * 100.0;
			orDeluxe = (double)deluxeOccupied/(double)deluxeTotal * 100.0;
			orVip = (double)vipOccupied/(double)vipTotal * 100.0;
			totalOccupied = singleOccupied + doubleOccupied + deluxeOccupied + vipOccupied;
			hotelTotal = singleTotal + doubleTotal + deluxeTotal + vipTotal;
			orTotal = (double)totalOccupied / (double)hotelTotal * 100;
            System.out.println(
                    "==============ROOM OCCUPANCY REPORT==============\n"  +
                    "On Date: " + date +"\n" +
                    "Single Room\n" +
                    "Total Single Rooms: " + singleTotal + "\n" +
                    "Occupied Single Rooms: " + singleOccupied +"\n" +
                    "Occupancy Rate: " + String.format("%.2f", orSingle) +"%\n" +
                    "Occupied Rooms: " + singleUnitNumber + "\n" +
                    "--------------------------------------------------\n" +
                    "Double Room\n" +
                    "Total Double Rooms: " + doubleTotal + "\n" +
                    "Occupied Double Rooms: " + doubleOccupied +"\n" +
                    "Occupancy Rate: " + String.format("%.2f", orDouble) +"%\n" +
                    "Occupied Rooms: " + doubleUnitNumber + "\n" +
                    "--------------------------------------------------\n" +
                    "Deluxe Room\n" +
                    "Total Deluxe Rooms: " + deluxeTotal + "\n" +
                    "Occupied Deluxe Rooms: " + deluxeOccupied +"\n" +
                    "Occupancy Rate: " + String.format("%.2f", orDeluxe) +"%\n" +
                    "Occupied Rooms: " + deluxeUnitNumber + "\n" +
                    "--------------------------------------------------\n" +
                    "VIP Room\n" +
                    "Total VIP Rooms: " + vipTotal + "\n" +
                    "Occupied VIP Rooms: " + vipOccupied +"\n" +
                    "Occupancy Rate: " + String.format("%.2f", orVip) +"%\n" +
                    "Occupied Rooms: " + vipUnitNumber + "\n" +
                    "--------------------------------------------------\n" +
                    "Hotel Occupany Rate: " +  String.format("%.2f", orTotal) +"%\n"
                );
		} catch (NullPointerException npe)   {
            logger.severe(npe.getMessage());
            npe.printStackTrace(System.out);
            System.out.println("The hotel is currently empty.");
        }		
    }

    public void printReservationList() {
    	Reservation reservation;
    	List<Reservation> reservationList;
    	try {
    		reservationList = reservationMgr.getReservationList();
    		if (reservationList.size()==0) {
    			System.out.println("There are currently no reservations");
    			return;
    		}
    		for (int i=0;i<reservationList.size();i++) {
    			reservation = reservationList.get(i);
    			System.out.println(
    	                "====================================================================" +
    	                "\nReservation ID: " + Integer.toString(reservation.getReservationID()) +
    	                "\nRoom Number: " + Integer.toString(reservation.getRoomNumber()) + 
    	                "\nNo. of Adults: " + Integer.toString(reservation.getNumAdults()) + 
    	                "\nNo. of Childrens: " + Integer.toString(reservation.getNumChild()) + 
    	                "\nCheck In Date: " + reservation.getCheckInDate() + 
    	                "\nCheck Out Date: " + reservation.getCheckOutDate() + 
    	                "\nNo. of Weekdays: " + Integer.toString(reservation.getNoOfWeekdays()) + 
    	                "\nNo. of Weekends: " + Integer.toString(reservation.getNoOfWeekends()) + 
    	                "\nReservation Status: " + reservation.getReserveStatus().toString() +
    	                "\n===================================================================="
    	            );
    		}
    	} catch (NullPointerException npe) {
    		logger.severe(npe.getMessage());
            System.out.println("The reservation is currently empty.");
    	}
    }
    
    public void checkRoomServiceStatus() {
    	int roomNumber;
    	List<RoomSvc> roomSvcList;
    	RoomSvc roomSvc;
    	int roomSvcID;
    	
    	System.out.println("Please input room number to check room service");
    	roomNumber = sc.nextInt();
    	sc.nextLine();
    	
    	roomSvcList = roomSvcMgr.getRoomSvcList(roomNumber);
    	
    	System.out.println("Room Number: " + roomNumber);
    	for(int i=0; i<roomSvcList.size(); i++)	{
    		roomSvc = roomSvcList.get(i);
    		System.out.print(
    				"===================================\n" + 
    				"Room Service ID: " + roomSvc.getRoomSvcID() + "\n" +
    				"Date Ordered: " + formatter.format(roomSvc.getDateTimeOrdered()) + "\n" +
    				"Amount Payable: " + roomSvc.getAmountPayable() + "\n" +
    				"Remarks: " + roomSvc.getRemarks() + "\n" +
    				"Status: " + roomSvc.getRoomSvcStatus().toString() + "\n" +
    				"==================================="
    		);
    	}
    	
    }
    
    public void updateExpiredStatus () {
		Date timeCheck = new Date();
		timeCheck.setHours(13);
		timeCheck.setMinutes(00);
		timeCheck.setSeconds(00);
		long initialDelay = new Date(timeCheck.getTime()-System.currentTimeMillis()).getTime();
		//System.out.println(initialDelay);
		if (initialDelay <= 0 ) {
			initialDelay = initialDelay + 86400000L;
		}
		ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
		execService.scheduleAtFixedRate(()->{
			reservationMgr.updateExpiredReservation();
		}, initialDelay, 86400000L, TimeUnit.MILLISECONDS); 
    }
    
    public void setDummyData()  {
         roomMgr.createRoom(new Room(6, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", true, "02-06"));
         roomMgr.createRoom(new Room(7, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", true, "02-07"));
         roomMgr.createRoom(new Room(8, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", true, "02-08"));
         roomMgr.createRoom(new Room(9, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", true, "03-01"));
         roomMgr.createRoom(new Room(10, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", true, "03-02"));
         roomMgr.createRoom(new Room(11, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", true, "03-03"));
         roomMgr.createRoom(new Room(12, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", true, "03-04"));
         roomMgr.createRoom(new Room(13, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "03-05"));
         roomMgr.createRoom(new Room(14, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "03-06"));
         roomMgr.createRoom(new Room(15, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", false, "Lake", false, "03-07"));
         roomMgr.createRoom(new Room(16, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", false, "Lake", false, "03-08"));
         roomMgr.createRoom(new Room(17, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "04-01"));
         roomMgr.createRoom(new Room(18, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "04-02"));
         roomMgr.createRoom(new Room(19, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "04-03"));
         roomMgr.createRoom(new Room(20, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "04-04"));
         roomMgr.createRoom(new Room(21, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "04-05"));
         roomMgr.createRoom(new Room(22, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", false, "04-06"));
         roomMgr.createRoom(new Room(23, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", false, "04-07"));
         roomMgr.createRoom(new Room(24, Room.ROOM_TYPE.VIP, 4, 1, 430f, 500f, "Master", true, "Sea", false, "04-08"));
         roomMgr.createRoom(new Room(25, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "05-01"));
         roomMgr.createRoom(new Room(26, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "05-02"));
         roomMgr.createRoom(new Room(27, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "05-03"));
         roomMgr.createRoom(new Room(28, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "05-04"));
         roomMgr.createRoom(new Room(29, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "05-05"));
         roomMgr.createRoom(new Room(30, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", false, "05-06"));
         roomMgr.createRoom(new Room(31, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", false, "05-07"));
         roomMgr.createRoom(new Room(32, Room.ROOM_TYPE.VIP, 4, 1, 430f, 500f, "Master", true, "Sea", false, "05-08"));
         roomMgr.createRoom(new Room(33, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "06-01"));
         roomMgr.createRoom(new Room(34, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "06-02"));
         roomMgr.createRoom(new Room(35, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "06-03"));
         roomMgr.createRoom(new Room(36, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "06-04"));
         roomMgr.createRoom(new Room(37, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "06-05"));
         roomMgr.createRoom(new Room(38, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "06-06"));
         roomMgr.createRoom(new Room(39, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", false, "06-07"));
         roomMgr.createRoom(new Room(40, Room.ROOM_TYPE.VIP, 4, 1, 430f, 500f, "Master", true, "Sea", false, "06-08"));
         roomMgr.createRoom(new Room(41, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "07-01"));
         roomMgr.createRoom(new Room(42, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "07-02"));
         roomMgr.createRoom(new Room(43, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "07-03"));
         roomMgr.createRoom(new Room(44, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "07-04"));
         roomMgr.createRoom(new Room(45, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "07-05"));
         roomMgr.createRoom(new Room(46, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "07-06"));
         roomMgr.createRoom(new Room(47, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", false, "07-07"));
         roomMgr.createRoom(new Room(48, Room.ROOM_TYPE.VIP, 4, 1, 430f, 500f, "Master", true, "Sea", false, "07-08"));
    }
    
    /**
     * 
     * END OF OPERATIONAL/BUSINESS LOGIC
     */
}
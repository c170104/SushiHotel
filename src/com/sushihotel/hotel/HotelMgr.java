package com.sushihotel.hotel;

import java.text.DecimalFormat;
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

public class HotelMgr {
    private GuestMgr guestMgr = new GuestMgr();
    private RoomMgr roomMgr = new RoomMgr();
    private InvoiceMgr invoiceMgr = new InvoiceMgr();
    private MenuMgr menuMgr = new MenuMgr();
    private ReservationMgr reservationMgr = new ReservationMgr();
    private RoomSvcMgr roomSvcMgr = new RoomSvcMgr();
    private Scanner sc = new Scanner(System.in);

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final DecimalFormat df = new DecimalFormat("0.00");
    private final String ERROR_MSG = "Error, please try again!";
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
    public void guestRegistration() {
        Guest guest;
        String identificationNo = null;
        String name;
        String creditCardNo;
        String billingAddress;
        String address;
        String country;
        String gender;
        String nationality;
        String contactNo;
        String passportNo = null;
        String choice;
        Boolean inputValidation = false;
        int i;

        try {
            // Guest Registration Begins
            System.out.println("============ Guest Registration ============");
            
            System.out.println("Please enter the exact Guest name: \n(Enter 'exit' to exit)");
            do {
                name = sc.nextLine();
            	if (name.equals("exit")) {
            		inputValidation = true;
                	return;
                }
                try {
                	if (guestMgr.searchGuest(name, Guest.GUEST_SEARCH_TYPE.GUEST_NAME) != null ) {
                	System.out.println("Guest has been registered before. Please try again");
                	inputValidation = false;
                	} else {
                		inputValidation = true;
                	}
                } catch (NullPointerException npe) {
                	inputValidation = true;
                }
            } while (!inputValidation);

            do {
                System.out.println("Please enter Gender (F/M): \n(Enter 'exit' to exit)");
                gender = sc.nextLine();
                inputValidation = true;
                if (!gender.equals("exit"))  {
                	if (!gender.equals("F") && !gender.equals("M")) {
                		System.out.println("Incorrect gender entry, try again!");
                		inputValidation = false;
	                } 
                }
            } while (!inputValidation);
            if (gender.equals("exit")) {
            	return;
            }
            
            System.out.println("Select type of ID \n1)Identification Number \n2)Passport Number\n(Enter 'exit' to exit)");
            do {
            	choice = sc.nextLine();
            	if (choice.equals("exit")) {
            		return;
                }
                if (!choice.equals("1") &&  !choice.equals("2")) {
                	System.out.println("Invalid input, please try again!");
                 	inputValidation = false;
                }
            }while (!inputValidation);
           
            switch (Integer.parseInt(choice)) {
            case 1:
            	// ID Validation
                System.out.println("Please enter Identification Number: \n(Enter 'exit' to exit)");
                do {
                    identificationNo = sc.nextLine();
                	if (identificationNo.equals("exit")) {
                		inputValidation = true;
                    	return;
                    }
                    try {
                    	if (guestMgr.searchGuest(identificationNo, Guest.GUEST_SEARCH_TYPE.IDENTIFICATION_NO) != null ) {
                    	System.out.println("Identification Number has been registered before. Please try again");
                    	inputValidation = false;
                    	} else {
                    		inputValidation = true;
                    	}
                    } catch (NullPointerException npe) {
                    	inputValidation = true;
                    }
                } while (!inputValidation);
            	break;
            case 2:
            	 //Passport Validation
                System.out.println("Please enter Passport Number: \n(Enter 'exit' to exit)");
                do {
                    passportNo = sc.nextLine();
                	if (passportNo.equals("exit")) {
                		inputValidation = true;
                    	return;
                    }
                    try {
                    	if (guestMgr.searchGuest(passportNo, Guest.GUEST_SEARCH_TYPE.PASSPORT_NO) != null ) {
                    	System.out.println("Passport Number has been registered before. Please try again");
                    	inputValidation = false;
                    	}  else {
                    		inputValidation = true;
                    	}
                    } catch (NullPointerException npe) {
                    	inputValidation = true;
                    }
                } while (!inputValidation);
            	break;
            default:
            	break;
            }

            System.out.println("Please enter Nationality: \n(Enter 'exit' to exit)");
            nationality = sc.nextLine();
            if (nationality.equals("exit")) {
            	return;
            }
            System.out.println("Please enter Address: \n(Enter 'exit' to exit)");
            address = sc.nextLine();
            if (address.equals("exit")) {
            	return;
            }
          //Contact number validation
            do {   
	            System.out.println("Please enter 8 digit Contact Number: \n(Enter 'exit' to exit)");
	            contactNo = sc.nextLine();
	            inputValidation = true;
	            if (!contactNo.equals("exit")) {
	                for (i = 0; i<contactNo.length(); i++) {
	                	if (contactNo.charAt(i) == ' ' || Character.isLetter(contactNo.charAt(i)) || contactNo.length()!=8) {
	                		System.out.println("Invalid entry, please try again");
	                		inputValidation = false;
	                		break;
	                	}
	                }
	            }
            } while (!inputValidation);
            if (contactNo.equals("exit")) {
            	return;
            }
            //Credit card validation
            do {
                System.out.println("Please enter 16 digit Credit Card Number: \n(Enter 'exit' to exit)");
                creditCardNo = sc.nextLine();
                inputValidation = true;
                if (!creditCardNo.equals("exit")) {
                    for (i = 0; i<creditCardNo.length(); i++) {
                    	if (creditCardNo.charAt(i) == ' ' || Character.isLetter(creditCardNo.charAt(i)) || creditCardNo.length()!=16) {
                    		System.out.println("Invalid entry, please try again");
                    		inputValidation = false;
                    		break;
                    	}
                    }
                }
            }   while (!inputValidation);

            if (creditCardNo.equals("exit")) {
            	return;
            }

            System.out.println("Please enter Billing Address: \n(Enter 'exit' to exit)");
            billingAddress = sc.nextLine();
            if (billingAddress.equals("exit")) {
            	return;
            }
            System.out.println("Please enter Country: \n(Enter 'exit' to exit)");
            country = sc.nextLine();
            if (country.equals("exit")) {
            	return;
            }

            // Guest object creation
            guest = new Guest(identificationNo, name, creditCardNo, billingAddress, address, country, gender,
                    nationality, contactNo, passportNo);
            System.out.println(identificationNo + "\n" +
            		name + "\n" +
            		creditCardNo + "\n" +
            		address + "\n" +
            		country + "\n" +
            		gender + "\n" +
            		nationality + "\n" +
            		contactNo + "\n" +
            		passportNo + "\n" 
            		);
            if (guestMgr.registerGuest(guest))
                System.out.println("Guest " + name + " has been successfully registered!");
            else
                System.out.println("System failed to register Guest " + name + ". Please try again.");
        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        }
    }

    public void updateGuestInformation() {
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
        String inputChecker;
        Boolean inputValidation = false;
        int i;

        try {
            System.out.println("Please enter the exact name of the Guest to be updated: \n(Enter 'exit' to exit)");
            name = sc.nextLine();
            if (name.equals("exit")) {
            	return;
            }

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
                System.out.print("Choose the option (1-10) to update: \n" + "1) Name\n" + "2) Gender\n"
                        + "3) Identification Number\n" + "4) Passport Number\n" + "5) Nationality\n" + "6) Address\n"
                        + "7) Contact Number\n" + "8) Credit Card Number\n" + "9) Billing Address\n" + "10) Country\n"
                        + "11) Exit\n\n" + "Choice: ");
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                case 1:
                    System.out.println("Name: \n(Enter 'exit' to exit)");
                    inputChecker = sc.nextLine();
                    if (inputChecker.equals("exit")) {
                    	break;
                    }
                    name = inputChecker;
                    break;
                case 2:
                	 do {
                         System.out.println("Please enter Gender (F/M/O): \n(Enter 'exit' to exit)");
                         inputChecker = sc.nextLine();
                         inputValidation = true;
                         if (!inputChecker.equals("exit"))  {
                         	if (!inputChecker.equals("F") && !inputChecker.equals("M") && !inputChecker.equals("O")) {
                         		System.out.println("Incorrect gender entry, try again!");
                         		inputValidation = false;
         	                } 
                         }
                     } while (!inputValidation);
                     if (inputChecker.equals("exit")) {
                     	break;
                     }
                     gender = inputChecker;
                    break;
                    
                case 3:
                    System.out.println("Identification Number: \n(Enter 'exit' to exit)");
                    do {
                    	inputChecker = sc.nextLine();
                    	if (inputChecker.equals("exit")) {
                    		inputValidation = true;
                        	break;
                        }
                        try {
                        	if (guestMgr.searchGuest(inputChecker, Guest.GUEST_SEARCH_TYPE.IDENTIFICATION_NO) != null ) {
                        	System.out.println("Identification Number has been regiestered before. Please try again");
                        	inputValidation = false;
                        	}
                        } catch (NullPointerException npe) {
                        	identificationNo = inputChecker;
                        	inputValidation = true;
                        }
                    } while (!inputValidation);
                    break;
                    
                case 4:
                	System.out.println("Passport Number: \n(Enter 'exit' to exit)");
                    do {
                        inputChecker = sc.nextLine();
                    	if (passportNo.equals("exit")) {
                    		inputValidation = true;
                        	return;
                        }
                        try {
                        	if (guestMgr.searchGuest(inputChecker, Guest.GUEST_SEARCH_TYPE.PASSPORT_NO) != null ) {
                        	System.out.println("Passport Number has been regiestered before. Please try again");
                        	inputValidation = false;
                        	}
                        } catch (NullPointerException npe) {
                        	passportNo = inputChecker;
                        	inputValidation = true;
                        }
                    } while (!inputValidation);
                    break;
                    
                case 5:
                    System.out.println("Nationality: \n(Enter 'exit' to exit)");
                    nationality = sc.nextLine();
                    break;
                case 6:
                    System.out.println("Address: \n(Enter 'exit' to exit)");
                    inputChecker = sc.nextLine();
                    if (inputChecker.equals("exit")) {
                    	break;
                    }
                    address = inputChecker;
                    break;
                case 7:
                	do {   
        	            System.out.println("Please enter 8 digit Contact Number: \n(Enter 'exit' to exit)");
        	            inputChecker = sc.nextLine();
        	            inputValidation = true;
        	            if (!inputChecker.equals("exit")) {
        	                for (i = 0; i<inputChecker.length(); i++) {
        	                	if (inputChecker.charAt(i) == ' ' || Character.isLetter(inputChecker.charAt(i)) || inputChecker.length()!=8) {
        	                		System.out.println("Invalid entry, please try again");
        	                		inputValidation = false;
        	                		break;
        	                	}
        	                }
        	            }
                    } while (!inputValidation);
                    if (inputChecker.equals("exit")) {
                    	break;
                    }
                    contactNo = inputChecker;
                    break;
                case 8:
                	do {   
        	            System.out.println("Please enter 16 digit Credit Card Number: \n(Enter 'exit' to exit)");
        	            inputChecker = sc.nextLine();
        	            inputValidation = true;
        	            if (!inputChecker.equals("exit")) {
        	                for (i = 0; i<inputChecker.length(); i++) {
        	                	if (inputChecker.charAt(i) == ' ' || Character.isLetter(inputChecker.charAt(i)) || inputChecker.length()!=16) {
        	                		System.out.println("Invalid entry, please try again");
        	                		inputValidation = false;
        	                		break;
        	                	}
        	                }
        	            }
                    } while (!inputValidation);
                    if (inputChecker.equals("exit")) {
                    	
                    	break;
                    }
                    creditCardNo = inputChecker;
                    break;
                    
                case 9:
                    System.out.println("Billing Address \n(Enter 'exit' to exit)");
                    inputChecker = sc.nextLine();
                    if (inputChecker.equals("exit")) {
                    	break;
                    }
                    billingAddress = inputChecker;
                    break;
                case 10:
                    System.out.println("Country: \n(Enter 'exit' to exit)");
                    inputChecker = sc.nextLine();
                    if (inputChecker.equals("exit")) {
                    	break;
                    }
                    country = inputChecker;
                    break;
                    
                default:
                    break;
                }
                
            } while (choice != 11);

            // Set new values
            guest = new Guest(identificationNo, name, creditCardNo, billingAddress, address, country, gender,
                    nationality, contactNo, passportNo);

            if (guestMgr.editGuest(guestID, guest))
                System.out.println("Successfully updated Guest " + name + " information.");
            else
                System.out.println("System failed to update Guest " + name + ". Please try again.");

        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        } catch (NullPointerException npe) {
            logger.severe(npe.getMessage());
            System.out.println("Sorry, there is no such guest.");
        }
    }

    public void searchGuests() {
        List<Guest> guestList;
        Guest guest;
        String guestName;

        try {
            System.out.println("Please enter the name to search: \n(Enter 'exit' to exit)");
            guestName = sc.nextLine();
            if (guestName.equals("exit")) {
            	return;
            }

            guestList = guestMgr.searchGuestsByName(guestName);
            
            System.out.println("============ Guest Search ============");
            for (int i = 0; i < guestList.size(); i++) {
                guest = guestList.get(i);
                System.out.println("Guest Name: " + guest.getName() + "\n" + "ID Number: "
                        + guest.getIdentificationNo() + "\n" + "Address: " + guest.getAddress() + "\n"
                        + "Country: " + guest.getCountry() + "\n" + "Gender: " + guest.getGender() + "\n"
                        + "Nationality: " + guest.getNationality() + "\n" + "Contact Number: "
                        + guest.getContactNumber() + "\n" + "Passport Number: " + guest.getPassportNumber()
                        + "\nCredit Card Number: " +guest.getCreditCardNumber()
                        + "\nBilling Address: " + guest.getBillingAddress());
            }
            System.out.println("=========== End of Search ===========");
        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        } catch (NullPointerException npe) {
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
                System.out.print("Get guest details by:\n" + "1)Guest Name\n" + "2)Guest Identification Number\n"
                        + "3)Guest Passport Number\n" + "Choice: ");
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
                    System.out.print("Please enter the exact Guest name (case insensitive): ");
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
     * START OF RESERVATION CRUD
     * 
     * METHODS:
     * newReservation()
     * editReservation()
     * removeReservation()
     * printReservation()
     */

    public void newReservation() {
        Room room;
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
            while (true) {
                System.out.println("Please enter the exact Guest name, type 'exit' to exit (case insensitive):");
                guestName = sc.nextLine();
                if (guestName.equals("exit")) {
                    return;
                }
                guest = guestMgr.searchGuest(guestName, Guest.GUEST_SEARCH_TYPE.GUEST_NAME);
                if (guest == null)
                    System.out.println("Guest does not exist.");
                else
                    break;
            }
            while(true) {
                System.out.println("Please enter Room Number (-1 to exit):");
                roomNumber = sc.nextInt();
                sc.nextLine();
                if(roomNumber == -1)
                    return;
                room = roomMgr.getRoom(roomNumber);
                if(room.getRoomStatus() == Room.ROOM_STATUS.UNDER_MAINTENANCE)  {
                    System.out.println("The room is under maintenance. Please select another room.");
                    return;
                }
                break;
            }

            do {
                do {
                    System.out.println("Please enter Check In Date (dd/MM/yyyy):");
                    try {
                        checkInDateInput = sc.nextLine();
                        checkInDate = formatter.parse(checkInDateInput + " 14:00");
                        //if (checkInDate.before(currentDate)) {
                        //    dateCheck = false;
                        //    System.out.println("Check in date has already passed current date. Try again.");
                        //} else {
                            dateCheck = true;
                        //}
                    } catch (ParseException pe) {
                        System.out.println("Incorrect date time format.");
                        dateCheck = false;
                    }
                } while (!dateCheck);

                do {
                    System.out.println("Please enter Check Out Date (dd/MM/yyyy):");
                    try {
                        checkOutDateInput = sc.nextLine();
                        checkOutDate = formatter.parse(checkOutDateInput + " 12:00");
                        dateCheck = true;
                    } catch (ParseException pe) {
                        System.out.println("Incorrect date time format.");
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
                if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                        && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                        && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                    numberOfWeekdays++;
                } else {
                    numberOfWeekends++;
                }
                startCal.add(Calendar.DAY_OF_MONTH, 1);
            } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

            System.out.println("# of Weekdays =  " + numberOfWeekdays + "\n# of Weekends = " + numberOfWeekends);

            System.out.println("Please enter Number of Adults: ");
            numAdults = sc.nextInt();
            sc.nextLine();
            System.out.println("Please enter Number of Children: ");
            numChild = sc.nextInt();
            sc.nextLine();

            reservation = new Reservation(guestName, roomNumber, checkInDate, checkOutDate, numAdults, numChild,
                    numberOfWeekdays, numberOfWeekends);

            reservationID = reservationMgr.beginReservation(reservation);

            if (reservationID != -1) {
                System.out.println("Reservation has been successfully made for guest " + guestName + ".");
                printReservation(reservationID);
            } else
                System.out.println("Reservation was unsuccessful. Please try again.");
        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        } catch (NullPointerException npe) {
            logger.severe(npe.getMessage());
            System.out.println("Sorry, there is no such reservation.");
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
            System.out.println("Please enter reservation ID to be edited: (Enter -1 to exit)");
            reservationID = sc.nextInt();
            if (reservationID == -1) {
                return;
            }
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
                System.out.print("Choose the option (1-8) to update: \n" + "1) Name: " + guestName + "\n"
                        + "2) Room number: " + Integer.toString(roomNumber) + "\n" + "3) Check in date: "
                        + formatter.format(checkInDate) + "\n" + "4) Check out date: " + formatter.format(checkOutDate)
                        + "\n" + "5) Number of adults: " + Integer.toString(numAdults) + "\n"
                        + "6) Number of children: " + Integer.toString(numChild) + "\n" + "7) Reserve status: "
                        + reserveStatus.toString() + "\n" +
                        "8) Exit/Update\n\n" + "Choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                case 1:
                    while (guest == null) {
                        System.out.print("Input a new name: ");
                        guestName = sc.nextLine();
                        guest = guestMgr.searchGuest(guestName, Guest.GUEST_SEARCH_TYPE.GUEST_NAME);
                        if (guest == null) {
                            System.out.println("Guest does no exist, please try again.");
                        } else
                            break;
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.print("Input new Room Number (1-48): ");
                        roomNumber = sc.nextInt();
                        sc.nextLine();
                        if (roomMgr.checkRoomAvailability(roomNumber))
                            break;
                        else
                            System.out.println("Room number " + Integer.toString(roomNumber) + " is not available.");
                    }
                    break;
                case 3:
                    dateCheck = false;
                    do {
                        System.out.println("Input new Check-In date (dd/MM/yyyy): ");
                        try {
                            checkInDateInput = sc.nextLine();
                            checkInDate = formatter.parse(checkInDateInput + " 14:00");
                            if (checkInDate.before(currentDate)) {
                                dateCheck = false;
                                System.out.println("Check in date has already passed current date. Try again.");
                            } else if (checkInDate.after(checkOutDate)) {
                                System.out.println("Check in is after check out date. Try again.");
                                dateCheck = false;
                            } else {
                                dateCheck = true;
                            }
                        } catch (ParseException pe) {
                            System.out.println("Incorrect date time format.");
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
                        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                                && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                                && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                            numberOfWeekdays++;
                        } else {
                            numberOfWeekends++;
                        }
                        startCal.add(Calendar.DAY_OF_MONTH, 1);
                    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date
                    System.out.println(
                            "Number of weekdays: " + numberOfWeekdays + "\nNumber of Weekends: " + numberOfWeekends);
                    break;

                case 4:
                    dateCheck = false;
                    do {
                        System.out.println("Input the new Check-Out date (dd/MM/yyyy):");
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
                            System.out.println("Incorrect date time format.");
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
                        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                                && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                                && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                            numberOfWeekdays++;
                        } else {
                            numberOfWeekends++;
                        }
                        startCal.add(Calendar.DAY_OF_MONTH, 1);
                    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date
                    System.out.println(
                            "Number of Weekdays: " + numberOfWeekdays + "\nNumber of Weekends: " + numberOfWeekends);
                    break;

                case 5:
                    System.out.println("Input new number of adult:");
                    numAdults = sc.nextInt();
                    sc.nextLine();
                    break;

                case 6:
                    System.out.println("Input new number of children:");
                    numChild = sc.nextInt();
                    sc.nextLine();
                    break;

                case 7:
                    while (true) {
                        System.out.println("Select reserve status \n" + "1) CONFIRMED\n" + "2) WAITLIST\n"
                                + "3) CHECKED_IN\n" + "4) EXPIRED");
                        statusSelection = sc.nextInt();
                        sc.nextLine();

                        if (statusSelection == 1) {
                            reserveStatus = RESERVE_STATUS.CONFIRMED;
                            break;
                        } else if (statusSelection == 2) {
                            reserveStatus = RESERVE_STATUS.WAITLIST;
                            break;
                        } else if (statusSelection == 3) {
                            reserveStatus = RESERVE_STATUS.CHECKED_IN;
                            break;
                        } else if (statusSelection == 4) {
                            reserveStatus = RESERVE_STATUS.EXPIRED;
                            break;
                        } else
                            System.out.println("Please select a correct input (1-4).");
                    }
                    break;

                default:
                    break;
                }
            } while (choice != 8);

            reservation = new Reservation(guestName, roomNumber, checkInDate, checkOutDate, numAdults, numChild,
                    numberOfWeekdays, numberOfWeekends, reserveStatus);
            if (reservationMgr.editReservation(reservationID, reservation)) {
                System.out.println("Successfully updated reservationID " + reservationID + " information.");
            } else {
                System.out.println("System failed to update reservation " + reservationID + ". Please try again.");
            }

        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        } catch (NullPointerException npe) {
            logger.severe(npe.getMessage());
            System.out.println("Sorry, there is no such reservation.");
        }
    }

    public void removeReservation() {
        int reservationID;
        String confirmation;
        try {
            System.out.println("Please enter reservation ID to be deleted: (Enter -1 to exit)");
            reservationID = sc.nextInt();
            sc.nextLine();
            if (reservationID == -1) {
                return;
            }

            System.out.print(
                    "Are you sure you want to remove reservation id  " + Integer.toString(reservationID) + "? (Y/N): ");
            confirmation = sc.nextLine();
            if (!confirmation.toLowerCase().equals("y") && !confirmation.toLowerCase().equals("yes")) {
                System.out.println("Removal of reservation id " + Integer.toString(reservationID) + " is canceled.");
                return;
            }

            if (reservationMgr.deleteReservation(reservationID)) {
                System.out.println("Deletion of reservation " + reservationID + " was successful.");
            } else {
                System.out.println("Deletion of reservation " + reservationID + " was unsuccessful, please try again.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Wrong number input format.");
        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        }
    }

    public void printReservation(int reservationID) {
        Reservation reservation = null;

        try {
            reservation = reservationMgr.getReservationByID(reservationID);
            System.out.println("===================================================================="
                    + "\nReservation ID: " + Integer.toString(reservation.getReservationID()) + "\nRoom Number: "
                    + Integer.toString(reservation.getRoomNumber()) + "\nNo. of Adults: "
                    + Integer.toString(reservation.getNumAdults()) + "\nNo. of Children: "
                    + Integer.toString(reservation.getNumChild()) + "\nCheck In Date: " + reservation.getCheckInDate()
                    + "\nCheck Out Date: " + reservation.getCheckOutDate() + "\nNo. of Weekdays: "
                    + Integer.toString(reservation.getNoOfWeekdays()) + "\nNo. of Weekends: "
                    + Integer.toString(reservation.getNoOfWeekends()) + "\nReservation Status: "
                    + reservation.getReserveStatus().toString()
                    + "\n====================================================================");
        } catch (NullPointerException npe) {
            logger.severe(npe.getMessage());
            System.out.println("Sorry, there is no such reservation.");
        }
    }

    /**
     * START OF ROOM CRUD
     * 
     * METHODS:
     * createRoom()
     * updateRoomDetails()
     */

    public void createRoom() {
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

            while (true) {
                System.out.println("Please Enter Room Type (Single/Double/Deluxe/VIP): ");
                roomTypeInput = sc.nextLine();
                if (roomTypeInput.toLowerCase().equals("single")) {
                    roomType = Room.ROOM_TYPE.SINGLE;
                    break;
                } else if (roomTypeInput.toLowerCase().equals("double")) {
                    roomType = Room.ROOM_TYPE.DOUBLE;
                    break;
                } else if (roomTypeInput.toLowerCase().equals("deluxe")) {
                    roomType = Room.ROOM_TYPE.DELUXE;
                    break;
                } else if (roomTypeInput.toLowerCase().equals("vip")) {
                    roomType = Room.ROOM_TYPE.VIP;
                    break;
                } else
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
            if (wifiEnabledInput.toLowerCase().equals("yes"))
                wifiEnabled = true;
            System.out.println("Please Enter Room View information: ");
            facingView = sc.nextLine();
            System.out.println("Smoking Allowed (Yes/No)(default: No): ");
            smokingAllowedInput = sc.nextLine();
            if (smokingAllowedInput.toLowerCase().equals("yes"))
                smokingAllowed = true;
            System.out.println("Please Enter Unit Number (e.g. 02-01): ");
            unitNumber = sc.nextLine();

            room = new Room(roomNumber, roomType, maxNoAdults, maxNoChild, rateWeekdays, rateWeekends, bedType,
                    wifiEnabled, facingView, smokingAllowed, unitNumber);

            if (roomMgr.createRoom(room))
                System.out.println("Room number " + roomNumber + " has been successfully created.");
            else
                System.out.println("System has failed to create Room number " + roomNumber + ". Please try again.");

        } catch (InputMismatchException ime) {
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
            System.out.println("Please enter the Room Number you want to view/update (1-48): ");
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
                System.out.println("Choose the options(1-12) to update:\n" + "1) Room Type: " + roomType.toString()
                        + "\n" + "2) Max Number of Adults: " + Integer.toString(maxNoAdults) + "\n"
                        + "3) Max Number of Children: " + Integer.toString(maxNoChild) + "\n" + "4) Weekdays Rate: "
                        + Float.toString(rateWeekdays) + "\n" + "5) Weekends Rate: " + Float.toString(rateWeekends)
                        + "\n" + "6) Bed Type: " + bedType + "\n" + "7) Wifi Enabled: " + (wifiEnabled ? "Yes" : "No")
                        + "\n" + "8) View Facing information: " + facingView + "\n" + "9) Smoking Allowed: "
                        + (smokingAllowed ? "Yes" : "No") + "\n" + "10) Room Status: " + roomStatus.toString() + "\n"
                        + "11) Unit Number: " + unitNumber + "\n" + "12) Update/Exit\n" + "Choice: ");
                choice = sc.nextInt();

                sc.nextLine(); // remove line carriage

                switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("Please Enter Room Type (Single/Double/Deluxe/VIP): ");
                        roomTypeInput = sc.nextLine();
                        if (roomTypeInput.toLowerCase().equals("single")) {
                            roomType = Room.ROOM_TYPE.SINGLE;
                            break;
                        } else if (roomTypeInput.toLowerCase().equals("double")) {
                            roomType = Room.ROOM_TYPE.DOUBLE;
                            break;
                        } else if (roomTypeInput.toLowerCase().equals("deluxe")) {
                            roomType = Room.ROOM_TYPE.DELUXE;
                            break;
                        } else if (roomTypeInput.toLowerCase().equals("vip")) {
                            roomType = Room.ROOM_TYPE.VIP;
                            break;
                        } else
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
                    if (wifiEnabledInput.toLowerCase().equals("yes"))
                        wifiEnabled = true;
                    else if (wifiEnabledInput.toLowerCase().equals("no"))
                        wifiEnabled = false;
                    break;
                case 8:
                    System.out.println("Please Enter Room View information: ");
                    facingView = sc.nextLine();
                    break;
                case 9:
                    System.out.println("Smoking Allowed (Yes/No)(default: No): ");
                    smokingAllowedInput = sc.nextLine();
                    if (smokingAllowedInput.toLowerCase().equals("yes"))
                        smokingAllowed = true;
                    else if (smokingAllowedInput.toLowerCase().equals("no"))
                        smokingAllowed = false;
                    break;
                case 10:
                    while (true) {
                        System.out.println("Please Enter Room Status(Vacant/Occupied/Reserved/Under Maintenance): ");
                        roomStatusInput = sc.nextLine();
                        if (roomStatusInput.toLowerCase().equals("vacant")) {
                            roomStatus = Room.ROOM_STATUS.VACANT;
                            break;
                        } else if (roomStatusInput.toLowerCase().equals("occupied")) {
                            roomStatus = Room.ROOM_STATUS.OCCUPIED;
                            break;
                        } else if (roomStatusInput.toLowerCase().equals("reserved")) {
                            roomStatus = Room.ROOM_STATUS.RESERVED;
                            break;
                        } else if (roomStatusInput.toLowerCase().equals("under maintenance")) {
                            roomStatus = Room.ROOM_STATUS.UNDER_MAINTENANCE;
                            break;
                        } else
                            System.out.println("Please input only (Vacant/Occupied/Reserved/Under Maintenance).");
                    }
                    break;
                case 11:
                    System.out.println("Please Enter Unit Number (e.g. 02-01): ");
                    unitNumber = sc.nextLine();
                    break;
                default:
                    break;
                }
            } while (choice != 12);

            room = new Room(roomNumber, roomType, maxNoAdults, maxNoChild, rateWeekdays, rateWeekends, bedType,
                    wifiEnabled, facingView, smokingAllowed, roomStatus, unitNumber);
            if (roomMgr.editRoom(roomNumber, room))
                System.out.println("Successfully updated Room number " + roomNumber + ".");
            else
                System.out.println("System failed to update Room number " + roomNumber + ". Please try again.");
        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        } catch (NullPointerException npe) {
            logger.severe(npe.getMessage());
            System.out.println("Sorry, there is no such room.");
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

    public void addMenuItem() {
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
        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        }
    }

    public void editMenuItem() {
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
                System.out.println("Choose the option 1-5 to update: \n" + "1) Meal Name: " + mealName + "\n"
                        + "2) Description: " + description + "\n" + "3) Prepared Method: " + preparedMethod + "\n"
                        + "4) Meal Price: " + df.format(mealPrice) + "\n" + "5) Exit/Update" + "\n\n" + "Choice: ");
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
            if (menuMgr.editMeal(mealID, meal)) {
                System.out.println("Successfully updated Meal ID " + Integer.toString(mealID) + " details.");
            } else {
                System.out
                        .println("System failed to update Meal ID " + Integer.toString(mealID) + ". Please try again.");
            }
        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        } catch (NullPointerException npe) {
            logger.severe(npe.getMessage());
            System.out.println("No such meal");
        }
    }

    public void removeMenuItem() {
        int mealID;
        String decision;

        try {
            System.out.println("Please enter meal ID to be deleted:");
            mealID = sc.nextInt();
            sc.nextLine();

            System.out.println("Are you sure you want to delete Meal ID: " + Integer.toString(mealID) + "? (Y/N): ");
            decision = sc.nextLine();

            if (!decision.toLowerCase().equals("yes") && !decision.toLowerCase().equals("y")) {
                System.out.println("Deletion has been canceled.");
                return;
            }

            if (menuMgr.removeMeal(mealID)) {
                System.out.println("Deletion of meal number " + mealID + " was successful!");
            } else {
                System.out.println("Deletion of meal number " + mealID + " was unsuccessful, please try again!");
            }
        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        } catch (NullPointerException npe) {
            logger.severe(npe.getMessage());
            System.out.println("No meal data.");
        }
    }

    public void printMealList() {
        Meal meal;
        List<Meal> mealList;

        try {
            mealList = menuMgr.getMealOffered();
            if (mealList.size() == 0) {
                System.out.println("The Menu is currently empty.");
                return;
            }
            System.out.println("========================== MENU ==========================");
            for (int i = 0; i < mealList.size(); i++) {
                meal = mealList.get(i);
                System.out.println("Meal ID: " + meal.getMealID() + "\n Meal Name: " + meal.getMealName()
                        + "\n Description: " + meal.getDesc() + "\n Prepared Method: " + meal.getPreparedMethod()
                        + "\n Meal Price: $" + df.format(meal.getMealPrice()));
            }
            System.out.println("==========================================================");
        } catch (NullPointerException npe) {
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

    public void callRoomService() {
        int roomNumber;
        int menuChoice;
        int roomSvcID;
        float amountPayable = 0.0f;
        RoomSvc roomSvc;
        String remarks;
        Meal meal;
        Room room;
        Date dateTimeOrdered = new Date();

        try {
            System.out.println("============== Room Service ==============");
            System.out.println("Please enter the Room number (1-48): ");
            roomNumber = sc.nextInt();
            sc.nextLine();
            if (!roomMgr.getRoom(roomNumber).getRoomStatus().equals(Room.ROOM_STATUS.OCCUPIED)) {
            	System.out.println("Room is not occupied.");
            	return;
            }
            // Print Menu by roomSvcMgr
            printMealList();

            while (true) {
                System.out.println("Choice of meal by Meal ID (Type 0 to exit): ");
                // get individual meal by roomsvcMgr
                menuChoice = sc.nextInt();
                sc.nextLine();
                if (menuChoice == 0) {
                	break;
                }
                meal = menuMgr.getMealDetails(menuChoice);
                amountPayable += meal.getMealPrice();
            }
            
            room = roomMgr.getRoom(roomNumber);

            if (amountPayable != 0) {
            	System.out.println("Please enter the room service remarks: ");
            	remarks = sc.nextLine();
            	roomSvc = new RoomSvc(roomNumber, amountPayable, remarks, dateTimeOrdered);
            	 if (roomSvcMgr.addNewRoomSvc(roomSvc)) {
                     roomSvcID = roomSvcMgr.getRoomSvcID(roomNumber);
                     if (invoiceMgr.addRoomSvc(roomNumber, roomSvcID)) {
                         roomSvcMgr.updateRoomSvcStatus(roomSvcID, RoomSvc.ROOM_SVC_STATUS.PREPARING);
                         System.out.println("Room Service has successfully been placed for Room number " + room.getUnitNumber() + "(" + roomNumber + ").");
                     } else
                         System.out.println("System has failed to place Room service for Room number " + room.getUnitNumber() + "(" + roomNumber
                                 + "). Please try again.");
                 } else
                     System.out.println("System has failed to add new room service. Please try again.");
            } else {
            	System.out.println("Exited room service call");
            }
        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        } catch (NullPointerException npe) {
            logger.severe(npe.getMessage());
            System.out.println("Sorry, there is no such Meal ID.");
        }
    }

    public void checkRoomAvailability() {
        int roomNumber;
        int choice;
        Room room;
        try {
            System.out.println("========================= Check Room Availability =========================\n"
                    + "Please select an option(1-4) to check Room Availability:\n" + "1) By Room Number\n"
                    + "2) By Room Availability\n" + "3) By Room Type\n" + "4) Exit\n" + "Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
            case 1:
                System.out.println("Please enter Room Number: ");
                roomNumber = sc.nextInt();
                sc.nextLine();
                room = roomMgr.getRoom(roomNumber);
                System.out.println("\nUnit number " + room.getUnitNumber() + "(" + roomNumber + ") is "
                        + room.getRoomStatus().toString() + ".\n");
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
        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        }
    }

    public void checkIn() {
        int choice;
        Guest guest;
        Room room;
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
                System.out.print("Made a prior Reservation?\n" + "1) Yes\n" + "2) No\n" + "3) Exit\n" + "Choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                // get guest details
                guest = searchGuest();
                if (guest == null) {
                    System.out.println("Guest does not exist. Please register first, then try again.");
                    return;
                }

                if (choice == 1) {
                    System.out.print("Please input the Reservation ID: (Enter -1 to exit)\n");
                    reservationID = sc.nextInt();
                    sc.nextLine();
                    if (reservationID == -1) {
                        return;
                    }

                    reservation = reservationMgr.getReservationByID(reservationID);
                    if (reservation == null) {
                        System.out.println("Reservation ID " + Integer.toString(reservationID) + " does not exist.");
                        return;
                    }

                    if (reservation.getReserveStatus() == Reservation.RESERVE_STATUS.CHECKED_IN) {
                        System.out.println(
                                "Reservation ID " + Integer.toString(reservationID) + " has already checked in.");
                        return;
                    }

                    // Next in the waitlist to check in
                    if (reservation.getReserveStatus() == Reservation.RESERVE_STATUS.CONFIRMED) {
                        roomNumber = reservation.getRoomNumber();
                        totalWeekdays = reservation.getNoOfWeekdays();
                        totalWeekends = reservation.getNoOfWeekends();
                        checkInDate = reservation.getCheckInDate();
                        checkOutDate = reservation.getCheckOutDate();
                    }
                } else if (choice == 2) {

                    // Create blank invoice
                    System.out.print("Please input room number (1-48): ");
                    roomNumber = sc.nextInt();
                    sc.nextLine();
                    
                    do {
                    	checkInDate = currentDate;
                    	System.out.println("Check in date: " + formatter.format(checkInDate));

                        do {
                            System.out.println("Please enter Check Out Date (dd/MM/yyyy):");
                            try {
                                checkOutDateInput = sc.nextLine();
                                checkOutDate = formatter.parse(checkOutDateInput + " 12:00");
                                dateCheck = true;
                            } catch (ParseException pe) {
                                System.out.println("Incorrect date time format.");
                                dateCheck = false;
                            }
                        } while (!dateCheck);

                        if (checkInDate.compareTo(checkOutDate) >= 0) {
                            System.out.println(
                                    "Check in date can't be the same or later than the check out date, try again!");
                            dateCheck = false;
                        }
                    } while (!dateCheck);

                    startCal.setTime(checkInDate);
                    endCal.setTime(checkOutDate);
                    do {
                        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                                && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                                && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY ) {
                            totalWeekdays++;
                        } else {
                            totalWeekends++;
                        }
                        startCal.add(Calendar.DAY_OF_MONTH, 1);
                    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

                    System.out.println("# of Weekdays = " + totalWeekdays + "\n# of Weekends = " + totalWeekends);
                }

                invoice = new Invoice(guest.getGuestID(), roomNumber, checkInDate, checkOutDate, totalWeekdays,
                        totalWeekends);
                
                room = roomMgr.getRoom(roomNumber);
                
                if (invoiceMgr.createBlankInvoice(invoice)) {
                    if (choice == 1 && !(reservationMgr.reservationCheckInChanges(reservationID))) { // error here, since using number 2 which is made no prior reservation
                    	// fail reservation
                    	System.out.println("Reservation for guest" + guest.getName() +" is unsuccessful. Please try again.");
                    	return;
                    }
                    System.out.println("Check In for guest " + guest.getName() + " into Room number " + room.getUnitNumber() + "("
                            + Integer.toString(roomNumber) + ") is successful!");
                    roomMgr.setRoomToOccupied(roomNumber, Room.ROOM_STATUS.OCCUPIED);
                    return;
                } else {
                    System.out.println("Check In for guest " + guest.getName() + " into Room number " + room.getUnitNumber() + "("
                            + Integer.toString(roomNumber) + ") is unsuccessful. Please try again.");
                    return;
                }

            } while (choice != 3);

        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        }
    }

    private void printBill(Invoice invoice) {
        try {
            int roomSvcID;
            RoomSvc roomSvc;
            List<Integer> list;
            System.out.println("========== SushiHotel Bill ==========\n" 
            + "Room number:\t\t" + invoice.getRoomNumber()
            + "\n" + "Checked in on:\t\t" + formatter.format(invoice.getCheckInDate()) 
            + "\n" + "Checked out on:\t\t" + formatter.format(invoice.getCheckOutDate()) 
            + "\n" + "Room charges:\t\t$" + df.format(invoice.getRoomCharges())
            + "\n" + "Room Service:\t\t$" + df.format(invoice.getRoomSvcTotalCharges())
            + "\n" + "Late Fees:\t\t\t$" + df.format(invoice.getLateFees())
            + "\n" + "Discount:\t\t   -" + df.format(invoice.getDiscount()*100)
            + "%\n" + "Tax:\t\t\t\t" + df.format(invoice.getTax()*100)
            + "%\n" + "Total Bill:\t\t\t$" + df.format(invoice.getTotalBill())
            + "\n" + "=====================================");
            list = invoice.getRoomSvc();

            System.out.println("======= Room Services Ordered =======");
            if (list.size() == 0)
                System.out.println("No Room service was ordered during the stay.\n");
            for (int i = 0; i < list.size(); i++) {
                roomSvcID = list.get(i);
                roomSvc = roomSvcMgr.getRoomSvc(roomSvcID);
                System.out.println("Room Service ID: " + Integer.toString(roomSvcID) + "\n" + "Date Ordered: "
                        + formatter.format(roomSvc.getDateTimeOrdered()) + "\n" + "Service Bill: "
                        + df.format(roomSvc.getAmountPayable()) + "\n" + "Remarks: " + roomSvc.getRemarks() + "\n"
                        + "=====================================");
            }
        } catch (NullPointerException npe) {
            logger.severe(npe.getMessage());
            System.out.println("An error has occurred. Please contact the System administrator.");
        }
    }

    public void checkOut() {
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
            System.out.println("Please enter Room number to check out (1-48): (Enter -1 to exit)");
            roomNumber = sc.nextInt();
            sc.nextLine();
            if (roomNumber == -1) {
                return;
            }

            invoice = invoiceMgr.getUnpaidInvoice(roomNumber);

            // calculate total room services incurred
            roomServicesID = invoice.getRoomSvc();
            for (int i = 0; i < roomServicesID.size(); i++) {
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
            System.out.print("Discount (in decimal): ");
            discount = sc.nextFloat();
            sc.nextLine();

            // add charges
            if (invoiceMgr.addCharges(roomNumber, discount, TAX_RATE, lateFees, roomSvcTotalPayable, weekDayRate,
                    weekEndRate)) {
                System.out.println("Payment method (cash/card)(default: cash): ");
                paymentMethodInput = sc.nextLine();
                if (paymentMethodInput.toLowerCase().equals("card"))
                    cashPayment = false;

                if (invoiceMgr.makePayment(roomNumber, cashPayment)) {
                    System.out.println("Check Out for Room " + room.getUnitNumber() + "(" + Integer.toString(roomNumber) + ") is successful.");
                    // change room status back to vacant
                    roomMgr.setRoomToOccupied(roomNumber, Room.ROOM_STATUS.VACANT);
                    // remove reservation
                    if(reservationMgr.getReservationByID(invoice.getInvoiceID()) != null)
                    	reservationMgr.removeReservationAfterCheckOut(roomNumber);
                    // print bill
                    invoice = invoiceMgr.getInvoice(invoice.getInvoiceID());
                    printBill(invoice);
                } else
                    System.out.println("Check Out is unsuccessful for Room " + room.getUnitNumber() + "(" + Integer.toString(roomNumber)
                            + "). Please try again.");
            }
        } catch (InputMismatchException ime) {
            logger.severe(ime.getMessage());
            System.out.println(ERROR_MSG);
        }
    }

    public void printRoomStatusStatisticReport() {

        Invoice invoice;
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

        List<Invoice> invoiceList;
        Date date = null;
        boolean dateCheck = false;

        System.out.println("Enter the required occupancy date (dd/MM/yyyy):");
        do {
            try {
                String inputDate = sc.nextLine();
                date = formatter.parse(inputDate + " 14:01");
                dateCheck = true;
            } catch (ParseException pe) {
                System.out.println("Incorrect date time format.");
                dateCheck = false;
            }
        } while (!dateCheck);

        try {
            invoiceList = invoiceMgr.getInvoice();
            if (invoiceList.size() == 0) {
                System.out.println("Hotel is not occupied.");
                return;
            }
            
            singleTotal = roomMgr.getTotalByRoomType(Room.ROOM_TYPE.SINGLE);
            doubleTotal = roomMgr.getTotalByRoomType(Room.ROOM_TYPE.DOUBLE);
            deluxeTotal = roomMgr.getTotalByRoomType(Room.ROOM_TYPE.DELUXE);
            vipTotal = roomMgr.getTotalByRoomType(Room.ROOM_TYPE.VIP);
            
            for (int i = 0; i < invoiceList.size(); i++) {
                invoice = (Invoice)invoiceList.get(i);
                roomNumber = invoice.getRoomNumber();
                room = roomMgr.getRoom(roomNumber);

                if (room.getRoomType() == Room.ROOM_TYPE.SINGLE) {
                    if (date.after(invoice.getCheckInDate()) && date.before(invoice.getCheckOutDate())) {
                        singleUnitNumber += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
                        singleOccupied++;
                    }
                }
                if (room.getRoomType() == ROOM_TYPE.DOUBLE) {
                    if (date.after(invoice.getCheckInDate()) && date.before(invoice.getCheckOutDate())) {
                        doubleUnitNumber += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
                        doubleOccupied++;
                    }
                }

                if (room.getRoomType() == ROOM_TYPE.DELUXE) {
                    if (date.after(invoice.getCheckInDate()) && date.before(invoice.getCheckOutDate())) {
                        deluxeUnitNumber += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
                        deluxeOccupied++;
                    }
                }

                if (room.getRoomType() == ROOM_TYPE.VIP) {
                    if (date.after(invoice.getCheckInDate()) && date.before(invoice.getCheckOutDate())) {
                        vipUnitNumber += room.getUnitNumber() + "(" + room.getRoomNumber() + ") , ";
                        vipOccupied++;
                    }
                }
            }
            orSingle = (double) singleOccupied / (double) singleTotal * 100.0;
            orDouble = (double) doubleOccupied / (double) doubleTotal * 100.0;
            orDeluxe = (double) deluxeOccupied / (double) deluxeTotal * 100.0;
            orVip = (double) vipOccupied / (double) vipTotal * 100.0;
            totalOccupied = singleOccupied + doubleOccupied + deluxeOccupied + vipOccupied;
            hotelTotal = singleTotal + doubleTotal + deluxeTotal + vipTotal;
            orTotal = (double) totalOccupied / (double) hotelTotal * 100;
            System.out.println("==============ROOM OCCUPANCY REPORT==============\n" + "On Date: " + date + "\n"
                + "Single Room\n" + "Total Single Rooms: " + singleTotal + "\n" + "Occupied Single Rooms: "
                + singleOccupied + "\n" + "Occupancy Rate: " + String.format("%.2f", orSingle) + "%\n"
                + "Occupied Rooms: " + singleUnitNumber + "\n"
                + "--------------------------------------------------\n" + "Double Room\n" + "Total Double Rooms: "
                + doubleTotal + "\n" + "Occupied Double Rooms: " + doubleOccupied + "\n" + "Occupancy Rate: "
                + String.format("%.2f", orDouble) + "%\n" + "Occupied Rooms: " + doubleUnitNumber + "\n"
                + "--------------------------------------------------\n" + "Deluxe Room\n" + "Total Deluxe Rooms: "
                + deluxeTotal + "\n" + "Occupied Deluxe Rooms: " + deluxeOccupied + "\n" + "Occupancy Rate: "
                + String.format("%.2f", orDeluxe) + "%\n" + "Occupied Rooms: " + deluxeUnitNumber + "\n"
                + "--------------------------------------------------\n" + "VIP Room\n" + "Total VIP Rooms: "
                + vipTotal + "\n" + "Occupied VIP Rooms: " + vipOccupied + "\n" + "Occupancy Rate: "
                + String.format("%.2f", orVip) + "%\n" + "Occupied Rooms: " + vipUnitNumber + "\n"
                + "--------------------------------------------------\n" + "Hotel Occupancy Rate: "
                + String.format("%.2f", orTotal) + "w%\n");
        } catch (NullPointerException npe) {
            System.out.println("Hotel is not occupied.");
        }
    }

    public void printReservationList() {
        Reservation reservation;
        List<Reservation> reservationList;
        try {
            reservationList = reservationMgr.getReservationList();
            if (reservationList.size() == 0) {
                System.out.println("There are currently no reservations");
                return;
            }
            for (int i = 0; i < reservationList.size(); i++) {
                reservation = reservationList.get(i);
                System.out.println("===================================================================="
                        + "\nReservation ID: " + Integer.toString(reservation.getReservationID()) + "\nRoom Number: "
                        + Integer.toString(reservation.getRoomNumber()) + "\nNo. of Adults: "
                        + Integer.toString(reservation.getNumAdults()) + "\nNo. of Childrens: "
                        + Integer.toString(reservation.getNumChild()) + "\nCheck In Date: "
                        + reservation.getCheckInDate() + "\nCheck Out Date: " + reservation.getCheckOutDate()
                        + "\nNo. of Weekdays: " + Integer.toString(reservation.getNoOfWeekdays())
                        + "\nNo. of Weekends: " + Integer.toString(reservation.getNoOfWeekends())
                        + "\nReservation Status: " + reservation.getReserveStatus().toString()
                        + "\n====================================================================");
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
        Room room;

        System.out.println("Please input room number to check room service:");
        roomNumber = sc.nextInt();
        sc.nextLine();

        room = roomMgr.getRoom(roomNumber);
        roomSvcList = roomSvcMgr.getRoomSvcList(roomNumber);

        System.out.println("Room Number: " + room.getUnitNumber() + "(" +  roomNumber + ").");
        for (int i = 0; i < roomSvcList.size(); i++) {
            roomSvc = roomSvcList.get(i);
            System.out.println("===================================\n" + "Room Service ID: " + roomSvc.getRoomSvcID()
                    + "\n" + "Date Ordered: " + formatter.format(roomSvc.getDateTimeOrdered()) + "\n"
                    + "Amount Payable: " + roomSvc.getAmountPayable() + "\n" + "Remarks: " + roomSvc.getRemarks() + "\n"
                    + "Status: " + roomSvc.getRoomSvcStatus().toString() + "\n"
                    + "===================================");
        }
    }

    public void updateExpiredStatus() {
        Date timeCheck = new Date();
        timeCheck.setHours(13);
        timeCheck.setMinutes(00);
        timeCheck.setSeconds(00);
        long initialDelay = new Date(timeCheck.getTime() - System.currentTimeMillis()).getTime();
        if (initialDelay <= 0) {
            initialDelay = initialDelay + 86400000L;
        }
        ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
        execService.scheduleAtFixedRate(() -> {
            reservationMgr.updateExpiredReservation();
        }, initialDelay, 86400000L, TimeUnit.MILLISECONDS);
    }
    
    public void updateRoomSvcDelivered() {
    	ScheduledExecutorService execService = Executors.newSingleThreadScheduledExecutor();
    	execService.scheduleAtFixedRate(()-> {
    		roomSvcMgr.updateRoomSvcStatusToDelivered();
    	}, 0, 180000L,TimeUnit.MILLISECONDS);
    }
    
    public void updateRoomToReserved() {
    	ScheduledExecutorService execService = Executors.newSingleThreadScheduledExecutor();
    	execService.scheduleAtFixedRate(() -> {
    		List<Reservation> reservationList;
    		Reservation reservation = null;
    		Date date = new Date();
    		try {
    			reservationList = reservationMgr.getReservationList();
    			for (int i = 0; i<reservationList.size(); i++) {
    				reservation = reservationList.get(i);
    				if (reservation.getReserveStatus() == Reservation.RESERVE_STATUS.CONFIRMED &&
    						reservation.getCheckInDate().getDate() == date.getDate() &&
    						reservation.getCheckInDate().getMonth() == date.getMonth() &&
    						reservation.getCheckInDate().getYear() == date.getYear()) {
    					roomMgr.updateRoomToReserve(reservation.getRoomNumber());
    					}
    				} 
    			}catch (NullPointerException npe) {
    				logger.info(npe.getMessage());
    		}
    	}, 0, 10000L,TimeUnit.MILLISECONDS);
    }
    
    public void printOccupiedRooms() {
    	List<Room> roomList;
    	Room room;
    	Guest guest;
    	int guestID;
    	Invoice invoice;
    	roomList = roomMgr.getOccupiedRoom();
    	try {
    	    if(roomList.size() == 0) {
                System.out.println("No room is currently occupied.");
                return;
            }

    		for (int i = 0; i<roomList.size(); i++) {
        		room = roomList.get(i);
        		invoice = invoiceMgr.getUnpaidInvoice(room.getRoomNumber());
        		guestID = invoice.getGuestID();
        		System.out.println("Room number: " + room.getUnitNumber() + "(" + room.getRoomNumber() +")"
        							+ "\nGuest name: " + guestMgr.getGuestName(guestID));
        	}

    	}catch (NullPointerException npe) {
            logger.severe(npe.getMessage());
            System.out.println("The invoice is currently empty.");
        }
    }

    public void setDummyData() {

        guestMgr.registerGuest(
            new Guest("S7608086T", "Zac Efron", "4539472608181189","8 Macademia Street", "8 Macademia Street", "America", "M", "American","96954268", "S7608086T"));
        guestMgr.registerGuest(
            new Guest("S2432773G", "Ashley Tisdale", "5471589173401465","6 Beverly Hills", "6 Beverly Hills", "Singapore", "F","Singaporean","86096615", "S2432773G"));
        guestMgr.registerGuest(
            new Guest("S3895996B", "Bruno Mars", "4716578256648199",
                                         "41 Mexico Road    ", "41 Mexico Road", "England", "M",
                                         "English", "95158887", "S3895996B"));
        guestMgr.registerGuest(
            new Guest("S9951850N", "Nicki Minaj", "5392350070654359",
                                         "Stamford East", "Stamford East    ", "Estonia",
                                         "F", "Estonian", "96894026", "S9951850N"));
        guestMgr.registerGuest(
            new Guest("S1641957T", "Oprah", "4532765208914447",
                                         "80 Fifth Avenue", "80 Fifth Avenue", "Singapore", "F",
                                         "Singaporean", "93627105", "S1641957T"));
        guestMgr.registerGuest(
            new Guest("S5564748Z", "Martin Garrix", "4539231608181189","27 Lonely Road", "27 Lonely Road", "Germany", "M", "German", "96954268", "S7308086T"));
        guestMgr.registerGuest(
            new Guest("S6999086L", "Walt Disney", "45394333608181189","100 Obs Street", "10 Obs Street", "America", "M", "American", "96952168", "S7608286T"));
        guestMgr.registerGuest(
            new Guest("S5467862L", "Martin Lee", "45322233608181189","7 Apple Street", "7 Apple Street", "Singapore", "M", "Singaporean", "96442168", "Z7602286K"));
        menuMgr.addNewMeal(
            new Meal("Caesar salad", "A green salad of romaine lettuce and croutons dressed with lemon juice, olive oil, egg, Worcestershire sauce, garlic, Parmesan cheese, and black pepper" , " ",  25f));
        menuMgr.addNewMeal(
            new Meal("Penang Special Char Kway Teow", "Fragrant, sweet and oily flat noodle with cockles, egg, and chilli paste.", " ", 17f));
        menuMgr.addNewMeal(
            new Meal("Strawberry Shortcake", "The pillowy sponge cake � alternated with layers of luscious whipped cream and juicy strawberries � makes a wonderful treat at any time of the day.", " ", 8f));
        menuMgr.addNewMeal(
            new Meal("Wild Mushroom Pizza", "It is served with caramelized onions, fontina and rosemary.", "0ven-baked", 18f));
        roomMgr.createRoom(
            new Room(1, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", true, "02-01"));
        roomMgr.createRoom(
            new Room(2, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", true, "02-02"));
        roomMgr.createRoom(
            new Room(3, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", true, "02-03"));
        roomMgr.createRoom(
            new Room(4, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", true, "02-04"));
        roomMgr.createRoom(
            new Room(5, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", true, "02-05"));
        roomMgr.createRoom(
            new Room(6, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", true, "02-06"));
        roomMgr.createRoom(
            new Room(7, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", true, "02-07"));
        roomMgr.createRoom(
            new Room(8, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", true, "02-08"));
        roomMgr.createRoom(
            new Room(9, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", true, "03-01"));
        roomMgr.createRoom(
            new Room(10, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", true, "03-02"));
        roomMgr.createRoom(
            new Room(11, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", true, "03-03"));
        roomMgr.createRoom(
            new Room(12, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", true, "03-04"));
        roomMgr.createRoom(
            new Room(13, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "03-05"));
        roomMgr.createRoom(
            new Room(14, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "03-06"));
        roomMgr.createRoom(
            new Room(15, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", false, "Lake", false, "03-07"));
        roomMgr.createRoom(
            new Room(16, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", false, "Lake", false, "03-08"));
        roomMgr.createRoom(
            new Room(17, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "04-01"));
        roomMgr.createRoom(
            new Room(18, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "04-02"));
        roomMgr.createRoom(
            new Room(19, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "04-03"));
        roomMgr.createRoom(
            new Room(20, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "04-04"));
        roomMgr.createRoom(
            new Room(21, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "04-05"));
        roomMgr.createRoom(
            new Room(22, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", false, "04-06"));
        roomMgr.createRoom(
            new Room(23, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", false, "04-07"));
        roomMgr.createRoom(
            new Room(24, Room.ROOM_TYPE.VIP, 4, 1, 430f, 500f, "Master", true, "Sea", false, "04-08"));
        roomMgr.createRoom(
            new Room(25, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "05-01"));
        roomMgr.createRoom(
            new Room(26, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "05-02"));
        roomMgr.createRoom(
            new Room(27, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "05-03"));
        roomMgr.createRoom(
            new Room(28, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "05-04"));
        roomMgr.createRoom(
            new Room(29, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "05-05"));
        roomMgr.createRoom(
            new Room(30, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", false, "05-06"));
        roomMgr.createRoom(
            new Room(31, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", false, "05-07"));
        roomMgr.createRoom(
            new Room(32, Room.ROOM_TYPE.VIP, 4, 1, 430f, 500f, "Master", true, "Sea", false, "05-08"));
        roomMgr.createRoom(
            new Room(33, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "06-01"));
        roomMgr.createRoom(
            new Room(34, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "06-02"));
        roomMgr.createRoom(
            new Room(35, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "06-03"));
        roomMgr.createRoom(
            new Room(36, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "06-04"));
        roomMgr.createRoom(
            new Room(37, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "06-05"));
        roomMgr.createRoom(
            new Room(38, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "06-06"));
        roomMgr.createRoom(
            new Room(39, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", false, "06-07"));
        roomMgr.createRoom(
            new Room(40, Room.ROOM_TYPE.VIP, 4, 1, 430f, 500f, "Master", true, "Sea", false, "06-08"));
        roomMgr.createRoom(
            new Room(41, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "07-01"));
        roomMgr.createRoom(
            new Room(42, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "07-02"));
        roomMgr.createRoom(
            new Room(43, Room.ROOM_TYPE.SINGLE, 1, 0, 200f, 265f, "Single", true, "Mountain", false, "07-03"));
        roomMgr.createRoom(
            new Room(44, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "07-04"));
        roomMgr.createRoom(
            new Room(45, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "07-05"));
        roomMgr.createRoom(
            new Room(46, Room.ROOM_TYPE.DOUBLE, 2, 1, 280f, 320f, "Double", true, "Mountain", false, "07-06"));
        roomMgr.createRoom(
            new Room(47, Room.ROOM_TYPE.DELUXE, 2, 2, 350f, 420f, "Master", true, "Lake", false, "07-07"));
        try {
        reservationMgr.beginReservation(new Reservation("Martin Lee", 8, formatter.parse("17/04/2018 14:00"), formatter.parse("19/04/2018 12:00"), 1, 0, 2, 0, RESERVE_STATUS.CONFIRMED));
        } catch (ParseException pe) {
            System.out.println("");
        }
    }

    /**
     * 
     * END OF OPERATIONAL/BUSINESS LOGIC
     */
}
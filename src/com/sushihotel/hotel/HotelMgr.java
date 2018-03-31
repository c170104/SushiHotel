package com.sushihotel.hotel;

import java.util.InputMismatchException;
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

    public void updateGuest()   {
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
        }
    }



    public boolean checkIn()    {
        

    }


    public void printBill() {
        
        
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
}
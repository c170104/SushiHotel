package com.sushihotel.ui;

import java.util.Scanner;

public class HotelApp {

    public static void main(String[] args){
        String staffName;
        Scanner sc = new Scanner(System.in);

        System.out.println("WELCOME TO HRPS\n"+
                        "Please enter your name: ");

        staffName = sc.next();

        System.out.println("Hi, " + staffName + ",");
        System.out.println("===== RESERVATION MATTERS =====\n" +
                        "1) Begin Reservation\n"+
                        "2) Update Reservation\n"+
                        "3) PrintReservation\n"+
                        "4) Remove Reservation\n");
        System.out.println("===== GUEST MATTERS =====\n" +
                "5) Search Guest Detail\n"+
                "6) Update Guest Detail\n"+
                "7) Order Room Service\n"+
                "8) Check Out Guest\n"); //calculate bill + print bill + update room status
        System.out.println("===== ROOM MATTERS =====\n" +
                "9) Print All Vacant Rooms for All Room Types\n"+
                "10) Print Room Numbers for All Room Status\n"+
                "11) Print Room Occupancy Rate (One Day)\n");

        System.out.print("\nPlease enter input: ");
        int option = sc.nextInt();

    }

}

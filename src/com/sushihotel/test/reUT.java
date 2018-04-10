package com.sushihotel.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.sushihotel.reservation.Reservation;
import com.sushihotel.reservation.ReservationMgr;

public class reUT {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		Reservation r1 = new Reservation("Lawrann", 1, formatter.parse("26/06/1995 14:00"),formatter.parse("30/06/1995 12:00"),4,4,2,2);
		Reservation r2 = new Reservation("Ben", 2, formatter.parse("25/06/1995 14:00"),formatter.parse("27/06/1995 12:00"),4,4,2,2);
		Reservation r3 = new Reservation("Lauren", 3, formatter.parse("19/07/1995 14:00"),formatter.parse("27/07/1995 12:00"),4,4,2,2);
		ReservationMgr mgr = new ReservationMgr();
		mgr.beginReservation(r1);
		mgr.beginReservation(r2);
		mgr.beginReservation(r3);
		List reservationList;
		Reservation reservation;
		
//		System.out.println("Please enter reservation ID to be edited ");
//		Scanner sc = new Scanner(System.in);
//		int reservationID = sc.nextInt();
//		System.out.println("Input new room number"); 
//		int checkRoomNumber = sc.nextInt();
//		boolean changeable = true;
//		reservationList = mgr.getRoomReservations(checkRoomNumber);
//		Reservation reservationTraverse;
//		Iterator iter;
//		iter = reservationList.iterator();
//		while(iter.hasNext()) {
//			reservationTraverse = (Reservation)iter.next();
//			reservation = mgr.searchReservationID(reservationID);
//			Date checkIn = reservationTraverse.getCheckInDate();
//			Date checkOut = reservationTraverse.getCheckOutDate();
//			if (reservation.getCheckInDate().before(checkIn) && reservation.getCheckOutDate().after(checkIn) && 
//					reservation.getCheckOutDate().before(checkOut)) {		// [ check in ] check out
//				changeable = false;
//				break;
//			};
//			
//			if (reservation.getCheckInDate().before(checkIn)  && 
//					reservation.getCheckOutDate().after(checkOut)) { 		// [ check in  check out ]
//				changeable = false;
//				break;
//			};
//			
//			if (reservation.getCheckInDate().before(checkOut) 
//					&& reservation.getCheckOutDate().after(checkOut) ) {	 //  check in [ check out ]
//				changeable = false;
//				break;
//			};
//		}
//		
//		if (!changeable) {
//			System.out.println("Room is reserved on this timing, unable to change room number");
//		} else {
//			System.out.println("Change can be made");
//		}
	}
}

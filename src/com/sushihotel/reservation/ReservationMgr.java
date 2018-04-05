package com.sushihotel.reservation;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

import com.sushihotel.reservation.Reservation;
import com.sushihotel.reservation.ReservationModel;

import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;
import com.sushihotel.menu.MenuModel;

public class ReservationMgr {
	private static final Logger logger = Logger.getLogger(ReservationMgr.class.getName());
	
	public boolean beginReservation(Reservation reservation) {
		try {
			if(ReservationModel.create(reservation)) {
				logger.info("[CREATE SUCCESS] Reservation ID: " + Integer.toString(reservation.getReservationID()) 
						+ " | Guest Name: " + reservation.getGuestName());
				return true;
			} else {
				logger.info("[CREATE FAIL] Reservation ID: " + Integer.toString(reservation.getReservationID()));
			}
		} catch (DuplicateData dd) {
			logger.log(Level.WARNING, dd.getMessage());
		}
		return false;
	}
	
	public boolean editReservation(int reservationID, Reservation reservation) {
		if (reservation == null) {
			return false;
		}
		try {
			if (ReservationModel.update(reservationID, reservation)) {
				logger.info("[UPDATE SUCCESS] Reservation ID: " + Integer.toString(reservation.getReservationID()) 
				+ " | Guest Name: " + reservation.getGuestName());
				return true;
			} else {
				logger.info("[UPDATE FAIL] Reservation ID: " + Integer.toString(reservation.getReservationID()));
			}
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		} catch (InvalidEntity ie) {
			logger.log(Level.WARNING, ie.getMessage());
		}
		return false;
	}
	
	public boolean deleteReservation(int reservationID) {
		Reservation reservation;
		
		try {
			reservation = ReservationModel.readRID(reservationID);
			if (reservation == null) {
				return false;
			}
			if (ReservationModel.delete(reservationID)) {
				logger.info("[DELETE SUCCESS] Reservation ID: " + Integer.toString(reservation.getReservationID()) 
				+ " | Guest Name: " + reservation.getGuestName());
				return true;
			} else {
				logger.info("[DELETE FAIL] Reservation ID: " + Integer.toString(reservation.getReservationID()));
			}
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		} catch (InvalidEntity ie) {
			logger.log(Level.WARNING, ie.getMessage());
		}
		return false;
	}
	
	public boolean printRsvDetails(int reservationID) {
		Reservation reservation;
		try {
			reservation = ReservationModel.readRID(reservationID);
			if (reservation == null) {
				return false;
			} else {
				System.out.println("Reservation ID: " + reservation.getReservationID()
						+ "\n No. of Adults: " + reservation.getNumAdults()
						+ "\n No. of Childrens: " + reservation.getNumChild()
						+ "\n Check In Date: " + reservation.getCheckInDate()
						+ "\n Check Out Date: " + reservation.getCheckOutDate()
						+ "\n No. of Weekdays: " + reservation.getNoOfWeekdays() 
						+ "\n No. of Weekends: " + reservation.getNoOfWeekends()
						+ "\n Reservation Status: " + reservation.getReserveStatus());
				return true;
			}
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		} catch (InvalidEntity ie) {
			logger.log(Level.WARNING, ie.getMessage());
		}
		return false;
	}
	public Reservation searchReservationID(int reservationID) { //returns reservation belonging to that reservationID
		Reservation reservation = null;
		try {
			reservation = ReservationModel.readRID(reservationID);
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		} catch (InvalidEntity ie) {
			logger.log(Level.WARNING, ie.getMessage());
		}
		return reservation;
		
	}
	
	public List<Reservation> getRoomReservations(int roomNumber) { // returns list of reservation made to the room except those EXPIRED
		List<Reservation> reservationList = null;
		try {
			reservationList = ReservationModel.readReservationList(roomNumber);
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		}
		 return reservationList;
	 }
	
	public List<Reservation> getReservationList() { // returns list of reservation made to the room except those EXPIRED
		List<Reservation> reservationList = null;
		try {
			reservationList = ReservationModel.readReservationList();
		} catch (EmptyDB edb) {
			logger.log(Level.WARNING, edb.getMessage());
		}
		 return reservationList;
	 }
	
	
	
//	public boolean checkRoomAvailability(int roomNumber, int reservationID) {
//		List reservationList;
//		Reservation reservation;
//		System.out.println("Please enter reservation ID to be edited ");
//		Scanner sc = new Scanner(System.in);
//		int reservationID = sc.nextInt();
//		System.out.println("Input new room number"); 
//		int checkRoomNumber = sc.nextInt();
//		boolean changeable = true;
//		reservationList = getRoomReservations(roomNumber);
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
//	}
	
}


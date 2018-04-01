package com.sushihotel.reservation;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

import com.sushihotel.reservation.Reservation;
import com.sushihotel.reservation.ReservationModel;

import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;

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
}

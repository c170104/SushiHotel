package com.sushihotel.reservation;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;

public class ReservationMgr {
	private static final Logger logger = Logger.getLogger(ReservationMgr.class.getName());
	
	
	public List<Reservation> getReservationList() {
			List<Reservation> reservationList = null;
			try {
				reservationList = ReservationModel.read();
			} catch (EmptyDB edb) {
				logger.log(Level.WARNING, edb.getMessage());
			}
			return reservationList;
	}
	
	public int beginReservation(Reservation reservation) {
		List<Reservation> list;
		Reservation dbReservation;
		int size;
		int reservationID;

		try {
			
			list = ReservationModel.read();
			
			reservation.setReserveStatus(Reservation.RESERVE_STATUS.CONFIRMED);

			size = list == null ? 0 : list.size();
			
			for(int i=0; i<size; i++)	{
				dbReservation = list.get(i);
				if(reservation.getRoomNumber() == dbReservation.getRoomNumber() && (
					dbReservation.getReserveStatus() == Reservation.RESERVE_STATUS.CONFIRMED || 
					dbReservation.getReserveStatus() == Reservation.RESERVE_STATUS.CHECKED_IN)
				)	{
					reservation.setReserveStatus(Reservation.RESERVE_STATUS.WAITLIST);
				}
			}
			
			reservationID = ReservationModel.create(reservation);
			if(reservationID != ReservationModel.RESERVATION_CREATION_ERROR) {
				logger.info("[CREATE SUCCESS] Reservation ID: " + Integer.toString(reservation.getReservationID()) 
						+ " | Guest Name: " + reservation.getGuestName());
				return reservationID;
			} else {
				logger.info("[CREATE FAIL] Reservation ID: " + Integer.toString(reservation.getReservationID()));
			}
		} catch(EmptyDB edb)	{
			logger.severe(edb.getMessage());
		}
		return ReservationModel.RESERVATION_CREATION_ERROR;
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
			reservation = ReservationModel.read(reservationID);
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

	public Reservation getReservationByID(int reservationID)	{
		Reservation reservation = null;
		
		try	{
			reservation = ReservationModel.read(reservationID);
		} catch(EmptyDB edb)	{
			logger.warning(edb.getMessage());
		} catch(InvalidEntity ie)	{
			logger.warning(ie.getMessage());
		}
		return reservation;
	}

	public List<Reservation> getReservationByRoomNumber(int roomNumber)	{
		List<Reservation> list = null;
		Reservation reservation;
		Iterator iter;

		try	{
			list = ReservationModel.read();
			iter = list.iterator();
			
			while(iter.hasNext())	{
				reservation = (Reservation)iter.next();
				if(reservation.getRoomNumber() != roomNumber)
					iter.remove();
			}
		} catch(EmptyDB edb)	{
			logger.severe(edb.getMessage());
		}
		return list;
	}

	public boolean reservationCheckInChanges(int reservationID)	{
		Reservation reservation;

		reservation = getReservationByID(reservationID);
		reservation.setReserveStatus(Reservation.RESERVE_STATUS.CHECKED_IN);
		
		return editReservation(reservationID, reservation);
	}

	public boolean removeReservationAfterCheckOut(int roomNumber)	{
		List<Reservation> reservationList;
		Reservation reservation = null;
		boolean trigger_flag = false;
	
		try	{
			reservationList = getReservationByRoomNumber(roomNumber);

			for(int i=0; i<reservationList.size(); i++)	{
				reservation = reservationList.get(i);
				if(reservation.getReserveStatus() == Reservation.RESERVE_STATUS.CHECKED_IN)	{
					trigger_flag = true;
					break;
				}
			}
			if(trigger_flag)
				return ReservationModel.delete(reservation.getReservationID());
		} catch(EmptyDB edb)	{
			logger.warning(edb.getMessage());
		} catch(InvalidEntity ie)	{
			logger.warning(ie.getMessage());
		}
		return false;
	}

	public Reservation getReservationByNameAndRoomNumber(String guestName, int roomNumber)	{
		Reservation reservation;
		List<Reservation> list;

		try	{
			list = ReservationModel.read();

			for(int i=0; i<list.size(); i++)	{
				reservation = list.get(i);
				if(reservation.getGuestName().toLowerCase().equals(guestName.toLowerCase()) && reservation.getRoomNumber() == roomNumber && reservation.getReserveStatus() != Reservation.RESERVE_STATUS.CHECKED_OUT)
					return reservation;
			}
		} catch(EmptyDB edb)	{
			logger.severe(edb.getMessage());
		}
		return null;
	}
}

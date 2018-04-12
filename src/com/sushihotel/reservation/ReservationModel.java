package com.sushihotel.reservation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.sushihotel.database.DataStoreFactory;
import com.sushihotel.database.IDataStore;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;


public class ReservationModel {
	private static IDataStore dataStore = DataStoreFactory.getDataStore();
	private static final String EMPTY_DB_MSG = "Reservation DB not found.";
	public static final int RESERVATION_CREATION_ERROR = -1;
	
	protected static int create(Reservation reservation) {
		List list;
		int size;
		boolean idSet = false;
		Reservation dbReservation;
		
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.RESERVATION);
		size = list == null ? 0 : list.size();

		if (list == null ) {
			list = new ArrayList();
		}

		for (int i =0; i<list.size(); i++) {
			dbReservation = (Reservation)list.get(i);
			if (dbReservation.getReservationID() != i+1 && idSet == false) {
				reservation.setReservationID(i+1);
				idSet = true;
			}
		}
		
        if (idSet == false) {
      	  reservation.setReservationID(size + 1);
        }
								
		list.add(reservation);
		list.sort(Comparator.comparing(Reservation::getReservationID));
		if(dataStore.write(list, IDataStore.DB_ENTITY_TYPE.RESERVATION))
			return reservation.getReservationID();
		else
			return RESERVATION_CREATION_ERROR;
	}
	
	protected static List<Reservation> read() throws EmptyDB {
		List<Reservation> list;
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.RESERVATION);
		if (list == null)
			new EmptyDB(EMPTY_DB_MSG);
		
		return list;
	}
	
	protected static Reservation read(int reservationID) throws EmptyDB, InvalidEntity {
		List list;
		Reservation reservation = null;
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.RESERVATION);
		if (list == null)
			new EmptyDB(EMPTY_DB_MSG);
		for (int i = 0; i<list.size(); i++) {
			reservation = (Reservation)list.get(i);
			if (reservation.getReservationID() == reservationID) {
				return reservation;
			}
		}
		throw new InvalidEntity(Integer.toString(reservation.getReservationID()) + " not found.", Reservation.RESERVATION_SEARCH_TYPE.RESERVATION_ID);
	}
	
	protected static boolean update(int reservationID, Reservation reservation) throws EmptyDB, InvalidEntity{
		List list;
		Iterator iter;
		Reservation dbReservation;
		boolean trigger_flag = false;
		
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.RESERVATION);
		if (list == null)
			new EmptyDB(EMPTY_DB_MSG);
		iter = list.iterator();
		while(iter.hasNext()) {
			dbReservation = (Reservation)iter.next();
			if(dbReservation.getReservationID() == reservationID) {
				dbReservation.setCheckInDate(reservation.getCheckInDate());
				dbReservation.setCheckOutDate(reservation.getCheckOutDate());
				dbReservation.setGuestDetails(reservation.getGuestName());
				dbReservation.setNumAdults(reservation.getNumAdults());
				dbReservation.setNumberofWeekdays(reservation.getNoOfWeekdays());
				dbReservation.setNumberOfWeekends(reservation.getNoOfWeekends());
				dbReservation.setNumChildren(reservation.getNumChild());
				dbReservation.setReserveStatus(reservation.getReserveStatus());
				dbReservation.setRoomDetails(reservation.getRoomNumber());
				trigger_flag = true;
				break;
			}
		}
		if (!trigger_flag)
			throw new InvalidEntity(reservationID + " not found. ", Reservation.RESERVATION_SEARCH_TYPE.RESERVATION_ID);
		
		reservation.setReservationID(reservationID);
		return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.RESERVATION);
	}
	
	protected static boolean delete(int reservationID) throws EmptyDB, InvalidEntity {
		List list;
		Iterator iter;
		Reservation dbReservation;
		boolean trigger_flag = false;
		
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.RESERVATION);
		if (list == null)
			new EmptyDB(EMPTY_DB_MSG);
		iter = list.iterator();
		while(iter.hasNext()) {
			dbReservation = (Reservation)iter.next();
			if(dbReservation.getReservationID() == reservationID) {
				iter.remove();
				trigger_flag = true;
				break;
			}
		}
		if (!trigger_flag)
			throw new InvalidEntity(reservationID + " not found. ", Reservation.RESERVATION_SEARCH_TYPE.RESERVATION_ID);
		return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.RESERVATION);
	}
}

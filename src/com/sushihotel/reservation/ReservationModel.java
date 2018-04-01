package com.sushihotel.reservation;

import com.sushihotel.database.DataStoreFactory;
import com.sushihotel.database.IDataStore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sushihotel.database.DataStoreFactory;
import com.sushihotel.database.IDataStore;
import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;


public class ReservationModel {
	private static IDataStore dataStore = DataStoreFactory.getDataStore();
	

	private static final String EmptyDBMsg = "Reservation DB not found.";

	protected static boolean create(Reservation reservation) throws DuplicateData{
		List list;
		int size;
		Reservation dbReservation;
		
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.RESERVATION);
		size = list == null ? 0 : list.size();
		
		if (list == null ) {
			list = new ArrayList();
		}
		for (int i=0; i<size; i++) {
			dbReservation = (Reservation)list.get(i);
			if(dbReservation.getReservationID() == reservation.getReservationID()) {
				throw new DuplicateData(""+reservation.getReservationID(), Reservation.RESERVATION_SEARCH_TYPE.RESERVATION_ID);
			}
		}
		reservation.setReservation(size+1);
		list.add(reservation);
		return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.RESERVATION);
	}
	
	protected static Reservation read(String guestName) throws EmptyDB, InvalidEntity {
		List list;
		Reservation reservation = null;
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.RESERVATION);
		if (list == null)
			new EmptyDB(EmptyDBMsg);
		for (int i = 0; i<list.size(); i++) {
			reservation = (Reservation)list.get(i);
			if (reservation.getGuestName().toLowerCase().equals(guestName.toLowerCase())) {
				return reservation;
			}
		}
		throw new InvalidEntity(""+ reservation.getGuestName(),  Reservation.RESERVATION_SEARCH_TYPE.GUEST_NAME);
	}
	
	protected static Reservation readRID(int reservationID) throws EmptyDB, InvalidEntity {
		List list;
		Reservation reservation = null;
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.RESERVATION);
		if (list == null)
			new EmptyDB(EmptyDBMsg);
		for (int i = 0; i<list.size(); i++) {
			reservation = (Reservation)list.get(i);
			if (reservation.getReservationID() == reservationID) {
				return reservation;
			}
		}
		throw new InvalidEntity(""+ reservation.getReservationID(), Reservation.RESERVATION_SEARCH_TYPE.RESERVATION_ID);
	}
	
	protected static boolean update(int reservationID, Reservation reservation) throws EmptyDB, InvalidEntity{
		List list;
		Iterator iter;
		Reservation dbReservation;
		boolean trigger_flag = false;
		
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.RESERVATION);
		if (list == null)
			new EmptyDB(EmptyDBMsg);
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
		list.add(reservation);
		return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.RESERVATION);
	}
	
	protected static boolean delete(int reservationID) throws EmptyDB, InvalidEntity {
		List list;
		Iterator iter;
		Reservation dbReservation;
		boolean trigger_flag = false;
		
		list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.RESERVATION);
		if (list == null)
			new EmptyDB(EmptyDBMsg);
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

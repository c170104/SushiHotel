package com.sushihotel.reservation;

import java.io.Serializable;

//import com.sushihotel.guest.Guest;
//import com.sushihotel.room.Room;

public class Reservation implements Serializable{
	private int reservationID;
	private String guestName;
	private int roomNumber;
	private String checkInDate;
	private String checkOutDate;
	private int numAdults;
	private int numChild;
	private Enum reserveStatus;
	private int numberOfWeekdays;
	private int numberOfWeekends;
	
	public enum RESERVE_STATUS{
		CONFIRMED,
		WAITLIST,
		CHECKED_IN,
		EXPIRED
	}
	public enum RESERVATION_SEARCH_TYPE {
		RESERVATION_ID,
		GUEST_NAME
		
	}
	
	
	public Reservation (
			String guestName,
			int roomNumber,
			String checkInDate,
			String checkOutDate,
			int numAdults,
			int numChildren,
			int numberOfWeekdays,
			int numberOfWeekends
			) {
		this.guestName = guestName;
		this.roomNumber = roomNumber;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.numAdults = numAdults;
		this.numChild = numChildren;
		this.reserveStatus = RESERVE_STATUS.CONFIRMED;
		this.numberOfWeekdays = numberOfWeekdays;
		this.numberOfWeekends = numberOfWeekends;
		
	}
	
	protected void setReservation(int reservationID) {
		this.reservationID = reservationID;
	}
	protected void setGuestDetails(String guestName) {
		this.guestName = guestName;
	}
	protected void setRoomDetails(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	protected void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	protected void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	protected void setNumAdults(int numAdults) {
		this.numAdults = numAdults;
	}
	protected void setNumChildren(int numChildren) {
		this.numChild = numChild;
	}
	protected void setNumberofWeekdays(int numberOfWeekdays) {
		this.numberOfWeekdays = numberOfWeekdays;
	}
	protected void setNumberOfWeekends(int numberOfWeekends) {
		this.numberOfWeekends = numberOfWeekends;
	}
	protected void setReserveStatus(Enum reserveStatus) {
		this.reserveStatus = reserveStatus;
	}
	
	public int getReservationID() {
		return this.reservationID;
	}
	public String getGuestName() {
		return this.guestName;
	}
	public int getRoomNumber() {
		return this.roomNumber;
	}
	public String getCheckInDate() {
		return this.checkInDate;
	}
	public String getCheckOutDate() {
		return this.checkOutDate;
	}
	public int getNumAdults() {
		return this.numAdults;
	}
	public int getNumChildren() {
		return this.numChild;
	}
	public Enum getReserveStatus() {
		return this.reserveStatus;
	}
	public int getNoOfWeekdays() {
		return this.numberOfWeekdays;
	}
	public int getNoOfWeekends() {
		return this.numberOfWeekends;
	}
}

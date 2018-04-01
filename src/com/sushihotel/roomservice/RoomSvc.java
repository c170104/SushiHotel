package com.sushihotel.roomservice;

import java.io.Serializable;

import com.sushihotel.menu.Meal;

public class RoomSvc implements Serializable{
	private int roomSvcID;
	private int roomNumber;
	//private Meal[] mealsOrdered;
	private float amountPayable;
	private String remarks;
	private String dateTimeOrdered;
	private Enum roomSvcStatus;
	
	public enum ROOM_SVC_STATUS{
		CONFIRMED,
		PREPARING,
		DELIVEREED
	}
	public enum ROOMSVC_SEARCH_TYPE {
		ROOM_SVC_NUMBER,
		ROOM_SVC_ID
	}
	
	public RoomSvc (
			//int roomSvcID,
			int roomNumber,
			//Meal[] mealsOrdered,
			float amountPayable,
			String remarks,
			String dateTimeOrdered
			) {
		//this.roomSvcID = roomSvcID;
		this.roomNumber = roomNumber;
		//this.mealsOrdered = mealsOrdered;
		this.amountPayable = amountPayable;
		this.remarks = remarks;
		this.dateTimeOrdered = dateTimeOrdered;
		this.roomSvcStatus = ROOM_SVC_STATUS.CONFIRMED;
		}
	
	protected void setRoomSvcID(int roomSvcID) {
		this.roomSvcID = roomSvcID;
	}
	protected void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
//	protected void setMealsOrdered(Meal[] mealsOrdered) {
//		this.mealsOrdered = mealsOrdered;
//	}
	protected void setAmountPayable(int amountPayable) {
		this.amountPayable = amountPayable;
	}
	protected void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	protected void setDateTime(String dateTimeOrdered) {
		this.dateTimeOrdered = dateTimeOrdered;
	}
	protected void setRoomSvcStatus(Enum roomSvcStatus) {
		this.roomSvcStatus = roomSvcStatus;
	}
	
	public int getRoomSvcID() {
		return this.roomSvcID;
	}
	public int getRoomNumber() {
		return this.roomNumber;
	}
//	public Meal[] getMealsOrdered() {
//		return this.mealsOrdered;
//	}
	public float getAmountPayable() {
		return this.amountPayable;
	}
	public String getRemarks() {
		return this.remarks;
	}
	public String getDateTimeOrdered() {
		return this.dateTimeOrdered;
	}
	public Enum getRoomSvcStatus() {
		return this.roomSvcStatus;
	}
	
	
}




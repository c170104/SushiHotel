package com.sushihotel.roomservice;

import java.io.Serializable;
import java.util.Date;

public class RoomSvc implements Serializable{
	private int roomSvcID;
	private int roomNumber;
	private float amountPayable;
	private String remarks;
	private Date dateTimeOrdered;
	private Enum roomSvcStatus;
	
	public enum ROOM_SVC_STATUS{
		CONFIRMED,
		PREPARING,
		DELIVERED
	}
	public enum ROOMSVC_SEARCH_TYPE {
		ROOM_NUMBER,
		ROOM_SVC_ID
	}
	
	public RoomSvc (
			int roomNumber,
			float amountPayable,
			String remarks,
			Date dateTimeOrdered
			) {
		this.roomNumber = roomNumber;
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
	protected void setAmountPayable(int amountPayable) {
		this.amountPayable = amountPayable;
	}
	protected void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	protected void setDateTime(Date dateTimeOrdered) {
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
	public float getAmountPayable() {
		return this.amountPayable;
	}
	public String getRemarks() {
		return this.remarks;
	}
	public Date getDateTimeOrdered() {
		return this.dateTimeOrdered;
	}
	public Enum getRoomSvcStatus() {
		return this.roomSvcStatus;
	}
	
}




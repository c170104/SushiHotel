package com.sushihotel.room;

import java.io.Serializable;

public class Room implements Serializable {
    private int roomNumber;
    private Enum roomType;
    private int maxNoAdults;
    private int maxNoChild;
    private float rateWeekdays;
    private float rateWeekends;
    private String bedType;
    private boolean wifiEnabled;
    private String facingView;
    private boolean smokingAllowed;
    private Enum roomStatus;
    private int floorNumber;

    public enum ROOM_TYPE   {
        SINGLE,
        DOUBLE,
        DELUXE,
        VIP
    }

    public enum ROOM_STATUS {
        VACANT,
        OCCUPIED,
        RESERVED,
        UNDER_MAINTENANCE
    }

    public Room(
        int roomNumber, 
        Enum roomType, 
        int maxNoAdults, 
        int maxNoChild, 
        float rateWeekdays,
        float rateWeekends, 
        String bedType, 
        boolean wifiEnabled, 
        String facingView, 
        boolean smokingAllowed,
        Enum roomStatus, 
        int floorNumber
    ) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.maxNoAdults = maxNoAdults;
        this.maxNoChild = maxNoChild;
        this.rateWeekdays = rateWeekdays;
        this.rateWeekends = rateWeekends;
        this.bedType = bedType;
        this.wifiEnabled = wifiEnabled;
        this.facingView = facingView;
        this.smokingAllowed = smokingAllowed;
        this.roomStatus = roomStatus;
        this.floorNumber = floorNumber;
    }

    protected void setRoomType(Enum roomType) {
        this.roomType = roomType;
    }

    protected void setMaxNoAdults(int maxNoAdults) {
        this.maxNoAdults = maxNoAdults;
    }

    protected void setMaxNoChild(int maxNoChild) {
        this.maxNoChild = maxNoChild;
    }

    protected void setRateWeekdays(float rateWeekdays) {
        this.rateWeekdays = rateWeekdays;
    }

    protected void setRateWeeknds(float rateWeekends) {
        this.rateWeekends = rateWeekends;
    }

    protected void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    protected void setBedType(String bedType) {
        this.bedType = bedType;
    }

    protected void setWifiEnabled(boolean wifiEnabled) {
        this.wifiEnabled = wifiEnabled;
    }

    protected void setFacingView(String facingView) {
        this.facingView = facingView;
    }

    protected void setSmokingAllowed(boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
    }

    protected void setRoomStatus(Enum roomStatus) {
        this.roomStatus = roomStatus;
    }

    protected void setFloorNumber(int floorNumber)  {
        this.floorNumber = floorNumber;
    }

    protected Enum getRoomType() {
        return this.roomType;
    }

    protected int getMaxNoAdults() {
        return this.maxNoAdults;
    }

    protected int getMaxNoChild9() {
        return this.maxNoChild;
    }

    protected float getRateWeekdays() {
        return this.rateWeekdays;
    }

    protected float getRateWeekend()    {
        return this.rateWeekends;
    }

    protected int getRoomNumber() {
        return this.roomNumber;
    }

    protected String getBedType() {
        return this.bedType;
    }

    protected boolean getWifiEnabled() {
        return this.wifiEnabled;
    }

    protected String getFacingView() {
        return this.facingView;
    }

    protected boolean getSmokingAllowed() {
        return this.smokingAllowed;
    }

    protected Enum getRoomStatus() {
        return this.roomStatus;
    }

    protected int getFloorNumber()  {
        return this.floorNumber;
    }
}
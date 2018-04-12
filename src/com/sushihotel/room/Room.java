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
    private String unitNumber;

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

    public enum ROOM_SEARCH_TYPE    {
        ROOM_NUMBER,
        ROOM_TYPE,
        WIFI_ENABLED,
        SMOKING_ENABLED,
        UNIT_NUMBER
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
        String unitNumber
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
        this.roomStatus = ROOM_STATUS.VACANT;
        this.unitNumber = unitNumber;
    }

    public Room(int roomNumber, Enum roomType, int maxNoAdults, int maxNoChild, float rateWeekdays, float rateWeekends,
            String bedType, boolean wifiEnabled, String facingView, boolean smokingAllowed, Enum roomStatus, String unitNumber) {
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
        this.unitNumber = unitNumber;
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

    protected void setUnitNumber(String unitNumber)  {
        this.unitNumber = unitNumber;
    }

    public Enum getRoomType() {
        return this.roomType;
    }

    public int getMaxNoAdults() {
        return this.maxNoAdults;
    }

    public int getMaxNoChild() {
        return this.maxNoChild;
    }

    public float getRateWeekdays() {
        return this.rateWeekdays;
    }

    public float getRateWeekend()    {
        return this.rateWeekends;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public String getBedType() {
        return this.bedType;
    }

    public boolean getWifiEnabled() {
        return this.wifiEnabled;
    }

    public String getFacingView() {
        return this.facingView;
    }

    public boolean getSmokingAllowed() {
        return this.smokingAllowed;
    }

    public Enum getRoomStatus() {
        return this.roomStatus;
    }

    public String getUnitNumber()  {
        return this.unitNumber;
    }
}
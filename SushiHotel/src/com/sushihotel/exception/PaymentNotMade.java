package com.sushihotel.exception;

public class PaymentNotMade extends Exception   {
    private int roomNumber;

    public PaymentNotMade(int roomNumber)   {
        this.roomNumber = roomNumber;
    }

    public void setRoomNumber(int roomNumber)   {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber()  {
        return this.roomNumber;
    }

    public String getMessage()  {
        return "Room number " + Integer.toString(getRoomNumber()) + " has not yet made payment.";
    }
}
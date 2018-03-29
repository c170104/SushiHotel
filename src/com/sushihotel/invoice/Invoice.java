package com.sushihotel.invoice;

import java.io.Serializable;
import java.util.List;

public class Invoice implements Serializable    {
    private int invoiceID; 
    private int guestID; 
    private String checkInDate; 
    private String checkOutDate; 
    private float tax; 
    private float discount; 
    private float roomCharges; 
    private List<Integer> roomSvc;
    private float totalBill; 
    private boolean cashPayment;
    private Enum invoiceStatus; 
    private float lateFee;
    
    public enum INVOICE_STATUS  {
        PAYMENT_MADE,
        PAYMENT_NOT_MADE
    }

    // blank invoice upon check in
    public Invoice(int guestID, String checkInDate)   {
        this.guestID = guestID;
        this.checkInDate = checkInDate;
    }  

    protected void setInvoiceID(int invoiceID)  {
        this.invoiceID = invoiceID;
    }
    protected void setGuestID(int guestID)  {
        this.guestID = guestID;
    }
    protected void setCheckInDate(String checkInDate)   {
        this.checkInDate = checkInDate;
    }
    protected void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    protected void setTax(float tax)    {
        this.tax = tax;
    }
    protected void setDiscount(float discount)  {
        this.discount = discount;
    }
    protected void setRoomCharges(float roomCharges)    {
        this.roomCharges = roomCharges;
    }
    protected void setRoomSvc(List<Integer> roomSvc)  {
        this.roomSvc = roomSvc;
    }
    protected void setTotalBill(float totalBill)    {
        this.totalBill = totalBill;
    }
    protected void setCashPayment(boolean cashPayment) {
        this.cashPayment = cashPayment;
    }
    protected void setInvoiceStatus(Enum invoiceStatus)  {
        this.invoiceStatus = invoiceStatus;
    }
    protected void setLateFee(float lateFee)    {
        this.lateFee = lateFee;
    }

    protected int getInvoiceID()    {
        return this.invoiceID;
    } 
    protected int getGuestID()  {
        return this.guestID;
    } 
    protected boolean getCashPayment()  {
        return this.cashPayment;
    } 
    protected String getCheckInDate()  {
        return this.checkInDate;
    } 
    protected String getCheckOutDate() {
        return this.checkOutDate;
    } 
    protected float getTax()  {
        return this.tax;
    } 
    protected float getLateFee()  {
        return this.lateFee;
    } 
    protected float getDiscount() {
        return this.discount;
    } 
    protected float getRoomCharges()  {
        return this.roomCharges;
    } 
    protected List<Integer> getRoomSvc()  {
        return this.roomSvc;
    }
    protected float getTotalBill()    {
        return this.totalBill;
    } 
    protected Enum getInvoiceStatus()    {
        return this.invoiceStatus;
    } 
}
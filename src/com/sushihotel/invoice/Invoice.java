package com.sushihotel.invoice;

import java.io.Serializable;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.List;

public class Invoice implements Serializable    {
    private int invoiceID; 
    private int guestID;
    private int roomNumber;
    private Date checkInDate; 
    private Date checkOutDate; 
    private int totalWeekdays;
    private int totalWeekends;
    private float tax; 
    private float discount; 
    private float roomCharges; 
    private List<Integer> roomSvc;
    private float totalBill; 
    private boolean cashPayment;
    private Enum invoiceStatus; 
    private float lateFees;
    
    public enum INVOICE_STATUS  {
        PAYMENT_MADE,
        PAYMENT_NOT_MADE
    }

    public enum INVOICE_SEARCH_TYPE {
        INVOICE_ID,
        GUEST_ID,
        ROOM_NUMBER
    }

    // blank invoice upon check in
    public Invoice(int guestID, int roomNumber, Date checkInDate, Date checkOutDate, int totalWeekdays, int totalWeekends)   {
        this.guestID = guestID;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalWeekdays = totalWeekdays;
        this.totalWeekends = totalWeekends;
        this.invoiceStatus = INVOICE_STATUS.PAYMENT_NOT_MADE;
    }  

    protected void setInvoiceID(int invoiceID)  {
        this.invoiceID = invoiceID;
    }
    protected void setGuestID(int guestID)  {
        this.guestID = guestID;
    }
    protected void setRoomNumber(int roomNumber)    {
        this.roomNumber = roomNumber;
    }
    protected void setCheckInDate(Date checkInDate)   {
        this.checkInDate = checkInDate;
    }
    protected void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    protected void setTotalWeekdays(int totalWeekdays)  {
        this.totalWeekdays = totalWeekdays;
    }
    protected void setTotalWeekends(int totalWeekends)  {
        this.totalWeekends = totalWeekends;
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
    protected void setLateFees(float lateFees)    {
        this.lateFees = lateFees;
    }

    public int getInvoiceID()    {
        return this.invoiceID;
    } 
    public int getGuestID()  {
        return this.guestID;
    }
    public int getRoomNumber()   {
        return this.roomNumber;
    }
    public boolean getCashPayment()  {
        return this.cashPayment;
    } 
    public Date getCheckInDate()  {
        return this.checkInDate;
    } 
    public Date getCheckOutDate() {
        return this.checkOutDate;
    } 
    public float getTax()  {
        return this.tax;
    } 
    public float getLateFees()  {
        return this.lateFees;
    } 
    public float getDiscount() {
        return this.discount;
    } 
    public float getRoomCharges()  {
        return this.roomCharges;
    } 
    public List<Integer> getRoomSvc()  {
        return this.roomSvc;
    }
    public float getTotalBill()    {
        return this.totalBill;
    } 
    public Enum getInvoiceStatus()    {
        return this.invoiceStatus;
    } 
}
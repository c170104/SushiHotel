package com.sushihotel.invoice;

import java.awt.List;
import java.util.logging.*;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;
import com.sushihotel.exception.PaymentNotMade;
import com.sushihotel.invoice.Invoice;
import com.sushihotel.invoice.InvoiceModel;
import com.sushihotel.roomservice.RoomSvc;

public class InvoiceMgr {
    private static final Logger logger = Logger.getLogger(InvoiceMgr.class.getName());

    public boolean createBlankInvoice(Invoice invoice)  {
        try {
            if(InvoiceModel.create(invoice))    {
                logger.info("[CREATE SUCCESS] Invoice ID: " + invoice.getInvoiceID());
                return true;
            }
            else    {
                logger.info("[CREATE FAIL] Invoice ID: " + invoice.getInvoiceID());
            }
        } catch(PaymentNotMade pnm) {
            logger.warning(pnm.getMessage());
        }
        return false;
    }

    public Invoice getInvoice(int guestID, int roomNumber) {
        Invoice invoice = null;
        try {
            invoice = InvoiceModel.read(guestID, roomNumber);
        } catch(EmptyDB edb)    {
            logger.warning(edb.getMessage());
        } catch(InvalidEntity ie)   {
            logger.warning(ie.getMessage());
        }
        return invoice;
    }

    public boolean editInvoice(int invoiceID, Invoice invoice)  {
        try {
            if(InvoiceModel.update(invoiceID, invoice)) {
                logger.info("[UPDATE SUCCESS] Invoice ID: " + Integer.toString(invoiceID));
                return true;
            }
            else
                logger.info("[UPDATE FAIL] Invoice ID: " + Integer.toString(invoiceID));
        } catch(EmptyDB edb)    {
            logger.warning(edb.getMessage());
        } catch(InvalidEntity ie)   {
            logger.warning(ie.getMessage());
        }
        return false;
    }

    public boolean deleteInvoice(int invoiceID) {
        try {
            if(InvoiceModel.delete(invoiceID))  {
                logger.info("[DELETE SUCCESS] Invoice ID: " + invoiceID);
                return true;
            }
            else
                logger.info("[DELETE FAIL] Invoice ID: " + invoiceID);
        } catch(EmptyDB edb)  {
            logger.warning(edb.getMessage());
        } catch (InvalidEntity ie)  {
            logger.warning(ie.getMessage());
        }
        return false;
    }

    public boolean addRoomSvc(int roomNumber, int roomSvcID)  {
        Invoice invoice;
        List<Integer> roomSvcList;

        try {
            invoice = InvoiceModel.readByOccupiedRoomNumber(roomNumber);

            roomSvcList = invoice.getRoomSvc();
            roomSvcList.add(roomSvc);
            invoice.setRoomSvc(roomSvcList);

            if(InvoiceModel.update(invoice.getInvoiceID(), invoice))    {
                logger.info("[ADD ROOM SVC SUCCESS] Invoice ID: " + invoice.getInvoiceID());
                return true;
            }
            else
                logger.info("[ADD ROOM SVC FAIL] Invoice ID: " + invoice.getInvoiceID());
        } catch(EmptyDB edb)    {
            logger.warning(edb.getMessage());
        } catch(InvalidEntity ie)   {
            logger.warning(ie.getMessage());
        }
        return false;
    }

    public boolean addCharges(int roomNumber, float discount, float tax, float lateFees, String checkOutDate, float weekDayRate, float weekEndRate)    {
        Invoice invoice;
        float roomCharges = 0.0f;
        float totalBill = 0.0f;
        float roomSvc = 0.0f;
        int totalWeekDay = 0;
        int totalWeekEnd = 0;
        String checkInDate;
        Calendar cIn = Calendar.getInstance();
        Calendar cOut = Calendar.getInstance();
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            invoice = InvoiceModel.readByOccupiedRoomNumber(roomNumber);

            checkInDate = invoice.getCheckInDate();
            cIn.setTime(myFormat.parse(checkInDate)); 
            cOut.setTime(myFormat.parse(checkOutDate));

            while(cOut.after(cIn))  {
                if(cIn.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cIn.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
                    totalWeekEnd++;
                totalWeekDay++;
                cIn.add(Calendar.DATE, 1); 
            }

            roomSvc = invoice.getRoomSvc();
            roomCharges = totalWeekDay * weekDayRate + totalWeekEnd * weekEndRate;
            totalBill = ((roomCharges + roomSvc + lateFees) * (1-discount)) * (1+tax);

            invoice.setRoomCharges(roomCharges);
            invoice.setDiscount(discount);
            invoice.setTax(tax);
            invoice.setLateFees(lateFees);
            invoice.setTotalBill(totalBill);
            invoice.setCheckOutDate(checkOutDate);

            if(InvoiceModel.update(invoice.getInvoiceID(), invoice))    {
                logger.info("[ADD CHARGES SUCCESS] Invoice ID: " + invoice.getInvoiceID());
                return true;
            }
            else
                logger.info("[ADD CHARGES FAIL] Invoice ID: " + invoice.getInvoiceID());
        } catch(EmptyDB edb)    {
            logger.warning(edb.getMessage());
        } catch(InvalidEntity ie)   {
            logger.warning(ie.getMessage());
        } catch(ParseException pe)  {
            logger.warning(pe.getMessage());
        }
        return false;
    }

    public boolean makePayment(int roomNumber, boolean cashPayment)    {
        Invoice invoice;
        try {
            invoice = InvoiceModel.readByOccupiedRoomNumber(roomNumber);

            invoice.setCashPayment(cashPayment);
            invoice.setInvoiceStatus(Invoice.INVOICE_STATUS.PAYMENT_MADE);

            if(InvoiceModel.update(invoice.getInvoiceID(), invoice)) {
                logger.info("[CHECKOUT SUCCESS] Invoice ID: " + invoice.getInvoiceID() + " |Guest ID: " + invoice.getGuestID());
                return true;
            }
            else
                logger.info("[CHECKOUT FAIL] Invoice ID: " + invoice.getInvoiceID());
        } catch(EmptyDB edb)    {
            logger.warning(edb.getMessage());
        } catch(InvalidEntity ie)   {
            logger.warning(ie.getMessage());
        }
        return false;
    }
}
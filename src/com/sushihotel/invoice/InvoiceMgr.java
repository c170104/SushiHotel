package com.sushihotel.invoice;

import java.awt.List;
import java.util.logging.*;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;
import com.sushihotel.invoice.Invoice;
import com.sushihotel.invoice.InvoiceModel;

import sun.invoke.empty.Empty;

public class InvoiceMgr {
    private static final Logger logger = Logger.getLogger(InvoiceMgr.class.getName());

    public boolean createBlankInvoice(Invoice invoice)  {
        if(InvoiceModel.create(invoice))    {
            logger.info("[CREATE SUCCESS] Invoice ID: " + invoice.getInvoiceID());
            return true;
        }
        else    {
            logger.info("[CREATE FAIL] Invoice ID: " + invoice.getInvoiceID());
            return false;
        }
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

    public boolean addRoomSvc(int roomNumber, float roomSvc)  {
        Invoice invoice;

        try {
            invoice = InvoiceModel.readByOccupiedRoomNumber(roomNumber);

            roomSvc += invoice.getRoomSvc();
            invoice.setRoomSvc(roomSvc);

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

    public boolean addCharges(int roomNumber, float discount, float tax, float lateFees)    {
        Invoice invoice;

        try {
            invoice = InvoiceModel.readByOccupiedRoomNumber(roomNumber);

            invoice.setDiscount(discount);
            invoice.setTax(tax);
            invoice.setLateFee(lateFee);

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
        }
        return false;
    }

    public boolean checkOutInvoice(int roomNumber, String checkOutDate)    {
        Invoice invoice;
        float totalBill = 0.0f;
        int totalWeekends = 0;
        int totalWeekDays = 0;
        String checkInDate;
        Date cIn;
        Date cOut;
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        
        try {
            invoice = InvoiceModel.readByOccupiedRoomNumber(roomNumber);

            invoice.setCheckOutDate(checkOutDate);

            cIn = myFormat.parse(checkInDate);
            cOut = myFormat.parse(checkOutDate);
            c1.setTime(cIn);
            c2.setTime(cOut);
            while(c2.after(c1)) {
                if(c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))
                    totalWeekends++;
                totalWeekDays++;
                c1.add(Calendar.DATE, 1);
            }
            // totalBill = totalWeekDays * 
        }
    }

    public boolean makePayment(int guestID, int roomNumber, boolean cashPayment)    {
        List<Invoice> invoices;
        Invoice invoice;
        try {
            invoices = InvoiceModel.readByGuestID(guestID);
            
            for(int i=0; invoices.size(); i++)  {
                invoice = invoices.get(i);
                if(invoice.getRoomNumber() == roomNumber && invoice.getInvoiceStatus() == Invoice.INVOICE_STATUS.PAYMENT_NOT_MADE)
                    break;
                invoice = null;
            }

            if(invoice == null)
                return false;

            invoice.setCashPayment(cashPayment);
            invoice.setInvoiceStatus(Invoice.INVOICE_STATUS.PAYMENT_MADE);

            if(InvoiceModel.update(invoice.getInvoiceID(), invoice)) {
                logger.info("[PAYMENT SUCCESS] Invoice ID: " + invoice.getInvoiceID() + " |Guest ID: " + invoice.getGuestID());
                return true;
            }
            else
                logger.info("[PAYMENT FAIL] Invoice ID: " + invoice.getInvoiceID());
        } catch(EmptyDB edb)    {
            logger.warning(edb.getMessage());
        } catch(InvalidEntity ie)   {
            logger.warning(ie.getMessage());
        }
        return false;
    }
}
package com.sushihotel.invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.Iterator;

import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;
import com.sushihotel.exception.PaymentNotMade;

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

    public List<Invoice> getInvoicesOfGuest(int guestID)   {
        List<Invoice> list = new ArrayList();
        Iterator iter;

        try {
            list = InvoiceModel.read();
            iter = list.iterator();
            while(iter.hasNext())   {
                if(((Invoice)iter.next()).getGuestID() != guestID)
                    iter.remove();
            }
        } catch(EmptyDB edb)    {
            logger.warning(edb.getMessage());
        }
        return list;
    }

    public Invoice getPaymentInvoice(int roomNumber) {
        Invoice invoice = null;
        try {
            invoice = InvoiceModel.read(roomNumber);
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
        boolean roomSvcIDExists = false;

        try {
            invoice = InvoiceModel.read(roomNumber);

            roomSvcList = invoice.getRoomSvc();
            for(int i=0; i<roomSvcList.size(); i++) {
                roomSvcIDExists = (roomSvcList.get(i) == roomSvcID);       
            }

            // If exists, dont have to add duplicate
            if(!roomSvcIDExists)
                roomSvcList.add(roomSvcID);

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

    public boolean addCharges(int roomNumber, float discount, float tax, float lateFees, float roomSvcTotalPayable, int totalWeekDay, int totalWeekEnd, float weekDayRate, float weekEndRate)    {
        Invoice invoice;
        float roomCharges = 0.0f;
        float totalBill = 0.0f;

        try {
            invoice = InvoiceModel.read(roomNumber);

            roomCharges = totalWeekDay * weekDayRate + totalWeekEnd * weekEndRate;
            totalBill = ((roomCharges + roomSvcTotalPayable + lateFees) * (1-discount)) * (1+tax);

            invoice.setRoomCharges(roomCharges);
            invoice.setDiscount(discount);
            invoice.setTax(tax);
            invoice.setLateFees(lateFees);
            invoice.setTotalBill(totalBill);

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

    public boolean makePayment(int roomNumber, boolean cashPayment)    {
        Invoice invoice;
        try {
            invoice = InvoiceModel.read(roomNumber);

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
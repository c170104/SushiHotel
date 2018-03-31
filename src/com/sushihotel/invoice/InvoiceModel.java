package com.sushihotel.invoice;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import com.sushihotel.database.DataStoreFactory;
import com.sushihotel.database.IDataStore;
import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;
import com.sushihotel.exception.PaymentNotMade;
import com.sushihotel.invoice.Invoice;

public class InvoiceModel   {
    private static IDataStore dataStore = DataStoreFactory.getDataStore();
    private static final String EmptyDBMsg = "Invoice DB not found.";

    protected static boolean create(Invoice invoice) throws PaymentNotMade {
        List list;
        Invoice dbInvoice;
        int size;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.INVOICE);

        size = list == null ? 0 : list.size();

        if(list == null)
            list = new ArrayList();

        for(int i=0; i<size; i++)   {
            dbInvoice = (Invoice)list.get(i);
            if(invoice.getRoomNumber() == dbInvoice.getRoomNumber() && dbInvoice.getInvoiceStatus() == Invoice.INVOICE_STATUS.PAYMENT_NOT_MADE)
                throw new PaymentNotMade(invoice.getRoomNumber());
        }

        invoice.setInvoiceID(size+1);

        list.add(invoice);
        
        return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.INVOICE);
    }

    protected static Invoice read(int guestID, int roomNumber) throws EmptyDB, InvalidEntity  {
        List list;
        Invoice invoice;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.INVOICE);

        if(list == null)
            throw new EmptyDB(EmptyDBMsg);

        for(int i=0; i<list.size(); i++)    {
            invoice = (Invoice)list.get(i);
            if(invoice.getGuestID() == guestID && invoice.getRoomNumber() == roomNumber && invoice.getInvoiceStatus() == Invoice.INVOICE_STATUS.PAYMENT_NOT_MADE) {
                return invoice;
            }
        }
        throw new InvalidEntity(roomNumber + " which is UNPAID is not found", Invoice.INVOICE_SEARCH_TYPE.ROOM_NUMBER);
    }

    protected static List<Invoice> read() throws EmptyDB  {
        List<Invoice> list;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.INVOICE);

        if(list == null)
            throw new EmptyDB(EmptyDBMsg);

        return newList;
    }

    protected static boolean update(int invoiceID, Invoice invoice) throws EmptyDB, InvalidEntity {
        List list;
        Iterator iter;
        Invoice dbInvoice;
        boolean trigger_flag = false;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.INVOICE);

        if(list == null)
            throw new EmptyDB(EmptyDBMsg);

        iter = list.iterator();
        while(iter.hasNext())   {
            dbInvoice = (Invoice)iter.next();
            if(dbInvoice.getInvoiceID() == invoiceID)   {
                iter.remove();
                trigger_flag = true;
                break;
            }
        }
        invoice.setInvoiceID(invoiceID);
        list.add(invoice);

        if(!trigger_flag)
            throw new InvalidEntity(invoiceID + " not found.", Invoice.INVOICE_SEARCH_TYPE.INVOICE_ID);

        return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.INVOICE);
    }

    protected static boolean delete(int invoiceID) throws EmptyDB, InvalidEntity {
        List list;
        Iterator iter;
        Invoice invoice;
        boolean trigger_flag = false;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.INVOICE);

        if(list == null)
            throw new EmptyDB(EmptyDBMsg);

        iter = list.iterator();
        while(iter.hasNext())   {
            invoice = (Invoice)iter.next();
            if(invoice.getInvoiceID() == invoiceID) {
                iter.remove();
                trigger_flag = true;
                break;
            }
        }

        if(!trigger_flag)
            throw new InvalidEntity(invoiceID + " not found.", Invoice.INVOICE_SEARCH_TYPE.INVOICE_ID);

        return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.INVOICE);
        
    }
}
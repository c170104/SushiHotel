package com.sushihotel.invoice;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import com.sushihotel.database.DataStoreFactory;
import com.sushihotel.database.IDataStore;
import com.sushihotel.exception.DuplicateData;
import com.sushihotel.exception.EmptyDB;
import com.sushihotel.exception.InvalidEntity;
import com.sushihotel.invoice.Invoice;

public class InvoiceModel   {
    private static IDataStore dataStore = DataStoreFactory.getDataStore();
    public enum INVOICE_SEARCH_TYPE {
        INVOICE_ID,
        GUEST_ID
    }
    private static final String EmptyDBMsg = "Invoice DB not found.";

    protected static boolean create(Invoice invoice)   {
        List list;
        int size;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.INVOICE);

        if(list == null)
            list = new ArrayList();

        size = list == null ? 0 : list.size();

        invoice.setInvoiceID(size+1);
        
        return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.INVOICE);
    }

    protected static List<Invoice> read(int guestID) throws EmptyDB  {
        List list;
        List<Invoice> newList = new ArrayList();
        Invoice invoice;

        list = (ArrayList)dataStore.read(IDataStore.DB_ENTITY_TYPE.INVOICE);

        if(list == null)
            throw new EmptyDB(EmptyDBMsg);

        for(int i=0; i<list.size(); i++)    {
            invoice = (Invoice)list.get(i);
            if(invoice.getGuestID() == guestID)
                newList.add(invoice);
        }

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
            throw new InvalidEntity(invoiceID + " not found.", INVOICE_SEARCH_TYPE.INVOICE_ID);

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
            throw new InvalidEntity(invoiceID + " not found.", INVOICE_SEARCH_TYPE.INVOICE_ID);

        return dataStore.write(list, IDataStore.DB_ENTITY_TYPE.INVOICE);
        
    }
}
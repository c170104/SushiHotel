package com.sushihotel.test;

import com.sushihotel.invoice.Invoice;
import com.sushihotel.invoice.InvoiceMgr;

public class invUT {
    public static void main(String[] args)  {
        InvoiceMgr invMgr = new InvoiceMgr();

        // Create blank invoice
        Invoice invoice = new Invoice(1, 1, 450.0f, "29/03/2018");
        invMgr.createInvoice(invoice);

        // add room Svc
        invMgr.addRoomSvc(1, 55.5f);

        // more room svc
        invMgr.addRoomSvc(1, 44.5f);

        // add charges before checkout
        invMgr.addCharges(1, 0.25f, 0.17f, 50.0f);
    }
}
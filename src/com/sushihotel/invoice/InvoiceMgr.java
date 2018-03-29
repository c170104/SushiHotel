package com.sushihotel.invoice;

import com.sushihotel.invoice.Invoice;
import com.sushihotel.invoice.InvoiceModel;

public class InvoiceMgr {
    public void createInvoice(Invoice invoice)  {
        try {
            if(InvoiceModel.create(invoice))
                System.out.println("");
        }
    }
}
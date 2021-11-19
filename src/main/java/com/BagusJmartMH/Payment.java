package com.BagusJmartMH;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Write a description of class Payment here.
 *
 * @author (bagus nurhuda)
 * @version (modul 3)
 */
public class Payment extends Invoice
{
    public Shipment shipment;
    public int productCount;
    ArrayList<Record> history = new ArrayList<>();

    static class Record{
        public Status status;
        public final Date date;
        public String message;

        public Record(Status status, String message){
            this.status = status;
            this.message = message;
            this.date = Calendar.getInstance().getTime();
        }
    }

    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId,productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    public double getTotalPay(Product product){
        return productCount * product.price;
    }
}
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
    public ArrayList<Record> history = new ArrayList<>();
    public int storeId;

    public static class Record{
        public Status status;
        public final Date date;
        public String message;

        /**
         * Constructor objek Record
         * @param status status dari Record dengan nilai dari objek Status
         * @param message pesan pada suatu Record
         */
        public Record(Status status, String message){
            this.status = status;
            this.message = message;
            this.date = Calendar.getInstance().getTime();
        }
    }

    /**
     * merupakan construktor untuk membuat object payment
     * @param buyerId
     * @param productId
     * @param productCount
     * @param shipment
     * @param storeId
     */
    public Payment(int buyerId, int productId, int productCount, Shipment shipment, int storeId) {
        super(buyerId, productId);
        this.shipment = shipment;
        this.productCount = productCount;
        this.storeId = storeId;
    }

    /**
     * method untuk mengembalikan nilai total harga dari product pada invoice
     * @param product
     * @return
     */
    public double getTotalPay(Product product){
        return productCount * product.price;
    }
}
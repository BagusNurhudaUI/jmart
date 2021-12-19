package com.BagusJmartMH;
import com.BagusJmartMH.dbjson.Serializable;

import java.util.Date;

/**
 * merupakan class yang digunakan untuk inisialisasi method dan enum pada invoice
 *
 * @author (bagus n)
 * @version (modul 3 PT)
 */

public abstract class Invoice extends Serializable
{
    public int buyerId;
    public int complaintID;
    public Date date;
    //public ArrayList<Record> history = new ArrayList<Record>();
    public int productId;
    public Rating rating;
    //public Status status;

    public enum Rating{
        NONE, BAD, NEUTRAL, GOOD;
    }
    public enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED,FAILED,DELIVERED;


    }



    protected Invoice(int buyerId, int productId){
        this.date = new Date();
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.complaintID = -1;
        //this.status = Status.WAITING_CONFIRMATION;
    }


    public abstract double getTotalPay(Product product);




}
